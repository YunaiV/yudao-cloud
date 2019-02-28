package cn.iocoder.common.framework.config;

import cn.iocoder.common.framework.constant.SysErrorCodeEnum;
import cn.iocoder.common.framework.exception.ServiceException;
import cn.iocoder.common.framework.util.ExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.lang.reflect.UndeclaredThrowableException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = ServiceException.class)
    public CommonResult serviceExceptionHandler(HttpServletRequest req, ServiceException ex) {
        return CommonResult.error(ex.getCode(), ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public CommonResult constraintViolationExceptionHandler(HttpServletRequest req, ConstraintViolationException ex) {
        // TODO 芋艿，后续要想一个更好的方式。
        // 拼接详细报错
        StringBuilder detailMessage = new StringBuilder("\n\n详细错误如下：");
        ex.getConstraintViolations().forEach(constraintViolation -> detailMessage.append("\n").append(constraintViolation.getMessage()));
        return CommonResult.error(SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getCode(), SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getMessage()
            + detailMessage.toString());
    }

    @ResponseBody
    @ExceptionHandler(value = UndeclaredThrowableException.class)
    public CommonResult undeclaredThrowableExceptionHandler(HttpServletRequest req, UndeclaredThrowableException e) {
        // 尝试获得 ServiceException 异常。如果是，则使用 serviceExceptionHandler 方法处理。
        ServiceException serviceException = ExceptionUtil.getServiceException(e);
        if (serviceException != null) {
            return serviceExceptionHandler(req, serviceException);
        }
        // 尝试获得 ConstraintViolationException 异常。如果是，
        ConstraintViolationException constraintViolationException = ExceptionUtil.getConstraintViolationException(e);
        if (constraintViolationException != null) {
            return constraintViolationExceptionHandler(req, constraintViolationException);
        }
        // 获得不到，使用 异常日志 方法处理。
        return resultExceptionHandler(req, e);
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public CommonResult resultExceptionHandler(HttpServletRequest req, Exception e) {
        // TODO 异常日志
        e.printStackTrace();
        // TODO 翻译不同的异常
        if (e instanceof MissingServletRequestParameterException) {
            return CommonResult.error(SysErrorCodeEnum.MISSING_REQUEST_PARAM_ERROR.getCode(), SysErrorCodeEnum.MISSING_REQUEST_PARAM_ERROR.getMessage());
        }
        // 返回
        return CommonResult.error(SysErrorCodeEnum.SYS_ERROR.getCode(), SysErrorCodeEnum.SYS_ERROR.getMessage());
    }

}