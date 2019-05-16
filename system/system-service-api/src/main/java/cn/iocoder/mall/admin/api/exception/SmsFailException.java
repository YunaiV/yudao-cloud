package cn.iocoder.mall.admin.api.exception;

import cn.iocoder.common.framework.exception.ServiceException;

/**
 * @author Sin
 * @time 2019/5/16 11:17 AM
 */
public class SmsFailException extends ServiceException {

    public SmsFailException(Integer code, String message) {
        super(code, message);
    }
}
