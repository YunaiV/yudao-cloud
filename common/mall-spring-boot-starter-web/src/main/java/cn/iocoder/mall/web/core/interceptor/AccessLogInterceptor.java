package cn.iocoder.mall.web.core.interceptor;

import cn.iocoder.common.framework.util.HttpUtil;
import cn.iocoder.common.framework.util.MallUtils;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.systemservice.rpc.systemlog.SystemAccessLogRpc;
import cn.iocoder.mall.systemservice.rpc.systemlog.dto.SystemAccessLogCreateDTO;
import cn.iocoder.mall.web.core.util.CommonWebUtil;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 访问日志拦截器
 */
public class AccessLogInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Reference(version = "${dubbo.consumer.SystemAccessLogRpc.version}")
    private SystemAccessLogRpc systemAccessLogRpc;

    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 记录当前时间
        CommonWebUtil.setAccessStartTime(request, new Date());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        SystemAccessLogCreateDTO accessLog = new SystemAccessLogCreateDTO();
        try {
            // 初始化 accessLog
            initAccessLog(accessLog, request);
            // 执行插入 accessLog
            addAccessLog(accessLog);
            // TODO 提升：暂时不考虑 ELK 的方案。而是基于 MySQL 存储。如果访问日志比较多，需要定期归档。
        } catch (Throwable th) {
            logger.error("[afterCompletion][插入访问日志({}) 发生异常({})", JSON.toJSONString(accessLog), ExceptionUtils.getRootCauseMessage(th));
        }
    }

    private void initAccessLog(SystemAccessLogCreateDTO accessLog, HttpServletRequest request) {
        // 设置账号编号
        accessLog.setUserId(CommonWebUtil.getUserId(request));
        accessLog.setUserType(CommonWebUtil.getUserType(request));
        // 设置访问结果
        CommonResult result = CommonWebUtil.getCommonResult(request);
        if (result != null) {
            accessLog.setErrorCode(result.getCode()).setErrorMessage(result.getMessage());
        } else {
            // 在访问非 onemall 系统提供的 API 时，会存在没有 CommonResult 的情况。例如说，Swagger 提供的接口
            accessLog.setErrorCode(0).setErrorMessage("");
        }
        // 设置其它字段
        accessLog.setTraceId(MallUtils.getTraceId())
                .setApplicationName(applicationName)
                .setUri(request.getRequestURI()) // TODO 提升：如果想要优化，可以使用 Swagger 的 @ApiOperation 注解。
                .setQueryString(HttpUtil.buildQueryString(request))
                .setMethod(request.getMethod())
                .setUserAgent(HttpUtil.getUserAgent(request))
                .setIp(HttpUtil.getIp(request))
                .setStartTime(CommonWebUtil.getAccessStartTime(request))
                .setResponseTime((int) (System.currentTimeMillis() - accessLog.getStartTime().getTime())); // 默认响应时间设为 0
    }

    // TODO 优化点：后续可以增加事件
    @Async // 异步入库
    public void addAccessLog(SystemAccessLogCreateDTO accessLog) {
        try {
            systemAccessLogRpc.createSystemAccessLog(accessLog);
        } catch (Throwable th) {
            logger.error("[addAccessLog][插入访问日志({}) 发生异常({})", JSON.toJSONString(accessLog), ExceptionUtils.getRootCauseMessage(th));
        }
    }

}
