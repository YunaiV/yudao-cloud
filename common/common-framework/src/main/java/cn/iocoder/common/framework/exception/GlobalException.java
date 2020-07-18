package cn.iocoder.common.framework.exception;

import cn.iocoder.common.framework.exception.enums.GlobalErrorCodeEnum;
import cn.iocoder.common.framework.vo.CommonResult;

/**
 * 全局异常 Exception
 */
public class GlobalException extends RuntimeException {

    /**
     * 全局错误码
     *
     * @see GlobalErrorCodeEnum
     */
    private final Integer code;
    /**
     * 错误明细，内部调试错误
     *    * 和 {@link CommonResult#getDetailMessage()} 一致的设计
     */
    private String detailMessage;

    public GlobalException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    public GlobalException setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
        return this;
    }

}
