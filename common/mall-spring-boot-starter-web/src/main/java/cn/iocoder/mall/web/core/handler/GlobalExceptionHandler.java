package cn.iocoder.mall.web.core.handler;

import cn.iocoder.common.framework.enums.SysErrorCodeEnum;
import cn.iocoder.common.framework.exception.ServiceException;
import cn.iocoder.common.framework.util.ExceptionUtil;
import cn.iocoder.common.framework.util.HttpUtil;
import cn.iocoder.common.framework.util.MallUtils;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.systemservice.rpc.systemlog.SystemLogRPC;
import cn.iocoder.mall.systemservice.rpc.systemlog.dto.ExceptionLogAddDTO;
import cn.iocoder.mall.web.core.util.CommonWebUtil;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.Assert;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.Date;

/**
 * 全局异常处理器，将 Exception 翻译成 CommonResult + 对应的异常编号
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    // TODO 芋艿，应该还有其它的异常，需要进行翻译

//    /**
//     * 异常总数 Metrics
//     */
//    private static final Counter EXCEPTION_COUNTER = Metrics.counter("mall.exception.total");

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${spring.application.name}")
    private String applicationName;

    // TODO 目前存在一个问题，如果未引入 system-rpc-api 依赖，GlobalExceptionHandler 会报类不存在。未来封装出 Repository 解决该问题
    @Reference(validation = "true", version = "${dubbo.consumer.SystemLogRPC.version}")
    private SystemLogRPC systemLogRPC;

    // 逻辑异常
    @ExceptionHandler(value = ServiceException.class)
    public CommonResult serviceExceptionHandler(ServiceException ex) {
        logger.debug("[serviceExceptionHandler]", ex);
        return CommonResult.error(ex.getCode(), ex.getMessage());
    }

    // Spring MVC 参数不正确
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public CommonResult missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException ex) {
        logger.warn("[missingServletRequestParameterExceptionHandler]", ex);
        return CommonResult.error(SysErrorCodeEnum.MISSING_REQUEST_PARAM_ERROR.getCode(), SysErrorCodeEnum.MISSING_REQUEST_PARAM_ERROR.getMessage() + ":" + ex.getMessage());
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public CommonResult constraintViolationExceptionHandler(ConstraintViolationException ex) {
        logger.info("[constraintViolationExceptionHandler]", ex);
        // TODO 芋艿，后续要想一个更好的方式。
        // 拼接详细报错
        StringBuilder detailMessage = new StringBuilder("\n\n详细错误如下：");
        ex.getConstraintViolations().forEach(constraintViolation -> detailMessage.append("\n").append(constraintViolation.getMessage()));
        return CommonResult.error(SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getCode(),
                SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getMessage() + detailMessage.toString());
    }

    @ExceptionHandler(value = Exception.class)
    public CommonResult exceptionHandler(HttpServletRequest req, Exception e) {
        logger.error("[exceptionHandler]", e);
        // 插入异常日志
        ExceptionLogAddDTO exceptionLog = new ExceptionLogAddDTO();
        try {
            // 增加异常计数 metrics TODO 暂时去掉
//            EXCEPTION_COUNTER.increment();
            // 初始化 exceptionLog
            initExceptionLog(exceptionLog, req, e);
            // 执行插入 exceptionLog
            addExceptionLog(exceptionLog);
        } catch (Throwable th) {
            logger.error("[exceptionHandler][插入访问日志({}) 发生异常({})", JSON.toJSONString(exceptionLog), ExceptionUtils.getRootCauseMessage(th));
        }
        // 返回 ERROR CommonResult
        return CommonResult.error(SysErrorCodeEnum.SYS_ERROR.getCode(), SysErrorCodeEnum.SYS_ERROR.getMessage());
    }

    private void initExceptionLog(ExceptionLogAddDTO exceptionLog, HttpServletRequest request,  Exception e) {
        // 设置账号编号
        exceptionLog.setUserId(CommonWebUtil.getUserId(request));
        exceptionLog.setUserType(CommonWebUtil.getUserType(request));
        // 设置异常字段
        exceptionLog.setExceptionName(e.getClass().getName());
        exceptionLog.setExceptionMessage(ExceptionUtil.getMessage(e));
        exceptionLog.setExceptionRootCauseMessage(ExceptionUtil.getRootCauseMessage(e));
        exceptionLog.setExceptionStackTrace(ExceptionUtil.getStackTrace(e));
        StackTraceElement[] stackTraceElements = e.getStackTrace();
        Assert.notEmpty(stackTraceElements, "异常 stackTraceElements 不能为空");
        StackTraceElement stackTraceElement = stackTraceElements[0];
        exceptionLog.setExceptionClassName(stackTraceElement.getClassName());
        exceptionLog.setExceptionFileName(stackTraceElement.getFileName());
        exceptionLog.setExceptionMethodName(stackTraceElement.getMethodName());
        exceptionLog.setExceptionLineNumber(stackTraceElement.getLineNumber());
        // 设置其它字段
        exceptionLog.setTraceId(MallUtils.getTraceId())
                .setApplicationName(applicationName)
                .setUri(request.getRequestURI()) // TODO 提升：如果想要优化，可以使用 Swagger 的 @ApiOperation 注解。
                .setQueryString(HttpUtil.buildQueryString(request))
                .setMethod(request.getMethod())
                .setUserAgent(HttpUtil.getUserAgent(request))
                .setIp(HttpUtil.getIp(request))
                .setExceptionTime(new Date());
    }

    @Async
    public void addExceptionLog(ExceptionLogAddDTO exceptionLog) {
        try {
            systemLogRPC.addExceptionLog(exceptionLog);
        } catch (Throwable th) {
            logger.error("[addAccessLog][插入异常日志({}) 发生异常({})", JSON.toJSONString(exceptionLog), ExceptionUtils.getRootCauseMessage(th));
        }
    }

}
