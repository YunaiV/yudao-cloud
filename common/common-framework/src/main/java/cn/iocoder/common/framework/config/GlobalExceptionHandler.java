package cn.iocoder.common.framework.config;

import cn.iocoder.common.framework.constant.SysErrorCodeEnum;
import cn.iocoder.common.framework.exception.ServiceException;
import cn.iocoder.common.framework.vo.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

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

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public CommonResult resultExceptionHandler(HttpServletRequest req, Exception e) {
        logger.error("[resultExceptionHandler]", e);
        // 返回
        return CommonResult.error(SysErrorCodeEnum.SYS_ERROR.getCode(), SysErrorCodeEnum.SYS_ERROR.getMessage());
    }

}
