package cn.iocoder.mall.system.biz.log.operation.aspect;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.json.JSONUtil;
import cn.iocoder.common.framework.util.HttpUtil;
import cn.iocoder.common.framework.util.MallUtils;
import cn.iocoder.mall.system.biz.log.operation.annotation.OperationLogging;
import cn.iocoder.mall.system.biz.log.operation.enums.LogStatus;
import cn.iocoder.mall.system.biz.log.operation.event.OperationLogEvent;
import cn.iocoder.mall.system.biz.log.operation.model.dto.OperationLogDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Hccake
 * @version 1.0
 * @date 2019/10/15 18:16
 */
@Slf4j
@Aspect
@Order(0)
@RequiredArgsConstructor
public class OperationLogAspect {
    private final ApplicationEventPublisher publisher;

    @Around("@annotation(operationLogging)")
    public Object around(ProceedingJoinPoint joinPoint, OperationLogging operationLogging) throws Throwable {
        Signature signature = joinPoint.getSignature();
        String strClassName = joinPoint.getTarget().getClass().getName();
        String strMethodName = signature.getName();
        log.debug("[类名]:{},[方法]:{}", strClassName, strMethodName);

        // 获取日志
        OperationLogDTO operationLogDTO = prodOperationLog();
        operationLogDTO.setMsg(operationLogging.value());
        // 记录参数
        MethodSignature methodSignature = (MethodSignature) signature;
        operationLogDTO.setParams(getParams(joinPoint, methodSignature));
        // 开始时间
        long startTime = System.currentTimeMillis();
        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            operationLogDTO.setStatus(LogStatus.FAIL.getValue());
            throw throwable;
        }
        // 结束时间
        operationLogDTO.setResponseTime((int) (System.currentTimeMillis() - startTime));
        // 发布事件
        publisher.publishEvent(new OperationLogEvent(operationLogDTO));

        return result;
    }


    /**
     * 获取方法参数
     * @param joinPoint joinPoint
     * @param methodSignature 方法签名
     * @return 方法参数的Json字符串形式
     */
    private String getParams(ProceedingJoinPoint joinPoint, MethodSignature methodSignature) {
        String[] parameterNames = methodSignature.getParameterNames();
        Object[] args = joinPoint.getArgs();
        if(ArrayUtil.isEmpty(parameterNames)){
            return null;
        }
        Map<String, Object> paramsMap = new HashMap<>();
        for (int i = 0; i < parameterNames.length; i++) {
            paramsMap.put(parameterNames[i], args[i]);
        }
        return JSONUtil.toJsonStr(paramsMap);
    }


    /**
     * 根据请求生成操作日志
     * @return 操作日志DTO
     */
    private OperationLogDTO prodOperationLog() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        return new OperationLogDTO()
                .setTraceId(MallUtils.getTraceId())
                .setUri(URLUtil.getPath(request.getRequestURI()))
                .setUserAgent(HttpUtil.getUserAgent(request))
                .setIp(HttpUtil.getIp(request))
                .setMethod(request.getMethod())
                // TODO 获取管理员用户名 或者 用户ID
                // .setOperator(Objects.requireNonNull(LogUtils.getUsername()))
                .setStatus(LogStatus.SUCCESS.getValue());
    }








}
