package cn.iocoder.mall.payservice.enums;

import cn.iocoder.common.framework.exception.ErrorCode;

/**
 * 错误码枚举类
 *
 * 管理员系统，使用 1-004-000-000 段
 */
public interface PayErrorCodeConstants {

    // ========== APP 模块 ==========
    ErrorCode PAY_APP_NOT_FOUND = new ErrorCode(1004000000, "App 不存在");
    ErrorCode PAY_APP_IS_DISABLE = new ErrorCode(1004000001, "App 已经被禁用");

    // ========== TRANSACTION PAY 模块 ==========
    ErrorCode PAY_TRANSACTION_NOT_FOUND = new ErrorCode(100401000, "支付交易单不存在");
    ErrorCode PAY_TRANSACTION_STATUS_IS_NOT_WAITING = new ErrorCode(100401001, "支付交易单不处于待支付");
    ErrorCode PAY_TRANSACTION_STATUS_IS_NOT_SUCCESS = new ErrorCode(100401002, "支付交易单不处于已支付");
    ErrorCode PAY_TRANSACTION_ERROR_USER = new ErrorCode(100401003, "支付交易单用户不正确");

    ErrorCode PAY_TRANSACTION_EXTENSION_NOT_FOUND = new ErrorCode(100401050, "支付交易拓展单不存在");
    ErrorCode PAY_TRANSACTION_EXTENSION_STATUS_IS_NOT_WAITING = new ErrorCode(100401051, "支付交易拓展单不处于待支付");
    ErrorCode PAY_TRANSACTION_EXTENSION_STATUS_IS_NOT_SUCCESS = new ErrorCode(100401052, "支付交易单不处于已支付");

    // ========== TRANSACTION REFUND 模块 ==========
    ErrorCode PAY_REFUND_PRICE_EXCEED = new ErrorCode(100402000, "退款金额超过支付交易单可退金额");
    ErrorCode PAY_REFUND_NOT_FOUND = new ErrorCode(100402001, "退款单不存在");
    ErrorCode PAY_REFUND_STATUS_NOT_WAITING = new ErrorCode(100402002, "退款单不处于待处理");

}
