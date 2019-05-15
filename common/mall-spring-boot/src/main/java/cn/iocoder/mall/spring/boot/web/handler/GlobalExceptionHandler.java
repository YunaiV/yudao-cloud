package cn.iocoder.mall.spring.boot.web.handler;

import cn.iocoder.common.framework.constant.SysErrorCodeEnum;
import cn.iocoder.common.framework.exception.ServiceException;
import cn.iocoder.common.framework.util.ExceptionUtil;
import cn.iocoder.common.framework.util.HttpUtil;
import cn.iocoder.common.framework.util.MallUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.api.SystemLogService;
import cn.iocoder.mall.admin.api.dto.systemlog.AccessLogAddDTO;
import cn.iocoder.mall.admin.api.dto.systemlog.ExceptionLogAddDTO;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.Assert;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${spring.application.name}")
    private String applicationName;

    @Reference(validation = "true", version = "${dubbo.consumer.AdminAccessLogService.version:1.0.0}")
    private SystemLogService adminAccessLogService;

    // 逻辑异常
    @ResponseBody
    @ExceptionHandler(value = ServiceException.class)
    public CommonResult serviceExceptionHandler(HttpServletRequest req, ServiceException ex) {
        logger.debug("[serviceExceptionHandler]", ex);
        return CommonResult.error(ex.getCode(), ex.getMessage());
    }

    // Spring MVC 参数不正确
    @ResponseBody
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public CommonResult missingServletRequestParameterExceptionHandler(HttpServletRequest req, MissingServletRequestParameterException ex) {
        logger.warn("[missingServletRequestParameterExceptionHandler]", ex);
        return CommonResult.error(SysErrorCodeEnum.MISSING_REQUEST_PARAM_ERROR.getCode(), SysErrorCodeEnum.MISSING_REQUEST_PARAM_ERROR.getMessage() + ":" + ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public CommonResult constraintViolationExceptionHandler(HttpServletRequest req, ConstraintViolationException ex) {
        logger.info("[constraintViolationExceptionHandler]", ex);
        // TODO 芋艿，后续要想一个更好的方式。
        // 拼接详细报错
        StringBuilder detailMessage = new StringBuilder("\n\n详细错误如下：");
        ex.getConstraintViolations().forEach(constraintViolation -> detailMessage.append("\n").append(constraintViolation.getMessage()));
        return CommonResult.error(SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getCode(), SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getMessage()
            + detailMessage.toString());
    }

    // TODO 芋艿，应该还有其它的异常，需要进行翻译
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public CommonResult exceptionHandler(HttpServletRequest req, Exception e) {
        logger.error("[exceptionHandler]", e);
        // 插入异常日志
        ExceptionLogAddDTO exceptionLog = new ExceptionLogAddDTO();
        try {
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
        // 设置用户编号
        exceptionLog.setUserId(MallUtil.getUserId(request));
        if (exceptionLog.getUserId() == null) {
            exceptionLog.setUserId(AccessLogAddDTO.USER_ID_NULL);
        }
        exceptionLog.setUserType(MallUtil.getUserType(request));
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
        exceptionLog.setTraceId(MallUtil.getTraceId())
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
        adminAccessLogService.addExceptionLog(exceptionLog);
    }

}
