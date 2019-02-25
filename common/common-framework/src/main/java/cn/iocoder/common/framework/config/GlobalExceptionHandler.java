package cn.iocoder.common.framework.config;

import cn.iocoder.common.framework.constant.SysErrorCodeEnum;
import cn.iocoder.common.framework.exception.ServiceException;
import cn.iocoder.common.framework.util.ExceptionUtil;
import cn.iocoder.common.framework.vo.RestResult;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.UndeclaredThrowableException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = ServiceException.class)
    public RestResult serviceExceptionHandler(HttpServletRequest req, ServiceException ex) {
        return RestResult.error(ex.getCode(), ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = UndeclaredThrowableException.class)
    public RestResult undeclaredThrowableExceptionHandler(HttpServletRequest req, UndeclaredThrowableException e) {
        // 尝试获得 ServiceException 异常。如果是，则使用 serviceExceptionHandler 方法处理。
        ServiceException serviceException = ExceptionUtil.getServiceException(e);
        if (serviceException != null) {
            return serviceExceptionHandler(req, serviceException);
        }
        // 获得不到，使用 异常日志 方法处理。
        return resultExceptionHandler(req, e);
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public RestResult resultExceptionHandler(HttpServletRequest req, Exception e) {
        // TODO 异常日志
        e.printStackTrace();
        // TODO 翻译不同的异常
        if (e instanceof MissingServletRequestParameterException) {
            return RestResult.error(SysErrorCodeEnum.MISSING_REQUEST_PARAM_ERROR.getCode(), SysErrorCodeEnum.MISSING_REQUEST_PARAM_ERROR.getMessage());
        }
        // 返回
        return RestResult.error(SysErrorCodeEnum.SYS_ERROR.getCode(), SysErrorCodeEnum.SYS_ERROR.getMessage());
    }

}