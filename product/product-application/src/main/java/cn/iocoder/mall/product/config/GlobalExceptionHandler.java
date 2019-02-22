package cn.iocoder.mall.product.config;

import cn.iocoder.mall.product.constants.ErrorCodeEnum;
import cn.iocoder.mall.product.exception.ServiceException;
import cn.iocoder.mall.product.vo.RestResult;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = ServiceException.class)
    public RestResult serviceExceptionHandler(HttpServletRequest req, Exception e) {
        ServiceException ex = (ServiceException) e;
        return RestResult.error(ex.getCode(), ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public RestResult resultExceptionHandler(HttpServletRequest req, Exception e) {
        // TODO 异常日志
        e.printStackTrace();
        // TODO 翻译不同的异常
        if (e instanceof MissingServletRequestParameterException) {
            return RestResult.error(ErrorCodeEnum.MISSING_REQUEST_PARAM_ERROR.getCode(), ErrorCodeEnum.MISSING_REQUEST_PARAM_ERROR.getMessage());
        }
        // 返回
        return RestResult.error(ErrorCodeEnum.SYS_ERROR.getCode(), ErrorCodeEnum.SYS_ERROR.getMessage());
    }

}