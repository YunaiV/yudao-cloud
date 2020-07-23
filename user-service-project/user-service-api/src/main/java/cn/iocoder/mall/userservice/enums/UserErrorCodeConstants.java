package cn.iocoder.mall.userservice.enums;

import cn.iocoder.common.framework.exception.ErrorCode;

/**
 * 错误码枚举类
 *
 * user-service 服务，使用 1-002-000-000 段
 */
public interface UserErrorCodeConstants {

    // ========== 用户手机验证码模块 ==========
    ErrorCode USER_SMS_CODE_NOT_FOUND = new ErrorCode(1001001200, "验证码不存在");
    ErrorCode USER_SMS_CODE_EXPIRED = new ErrorCode(1001001201, "验证码已过期");
    ErrorCode USER_SMS_CODE_USED = new ErrorCode(1001001202, "验证码已使用");
    ErrorCode USER_SMS_CODE_NOT_CORRECT = new ErrorCode(1001001203, "验证码不正确");
    ErrorCode USER_SMS_CODE_EXCEED_SEND_MAXIMUM_QUANTITY_PER_DAY = new ErrorCode(1001001204, "超过每日短信发送数量");
    ErrorCode USER_SMS_CODE_SEND_TOO_FAST = new ErrorCode(1001001205, "短信发送过于频率");

    // ========== 用户地址 ==========
    ErrorCode USER_ADDRESS_NOT_FOUND = new ErrorCode(1001004000, "用户地址不存在!");
    ErrorCode USER_GET_ADDRESS_NOT_EXISTS = new ErrorCode(1001004001, "获取的地址不存在!");

    // ========== 用户信息模块 1004004100 ==========
    ErrorCode USER_NOT_EXISTS = new ErrorCode(1004004100, "用户不存在");
    ErrorCode USER_STATUS_EQUALS = new ErrorCode(1004004101, "用户已经是该状态");
    ErrorCode USER_MOBILE_EXISTS = new ErrorCode(1004004102, "手机号已经存在");

}
