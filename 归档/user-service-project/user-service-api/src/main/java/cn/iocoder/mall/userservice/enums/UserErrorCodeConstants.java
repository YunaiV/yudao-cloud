package cn.iocoder.mall.userservice.enums;

import cn.iocoder.common.framework.exception.ErrorCode;

/**
 * 错误码枚举类
 *
 * user-service 服务，使用 1-002-000-000 段
 */
public interface UserErrorCodeConstants {

    // ========== 用户地址 ==========
    ErrorCode USER_ADDRESS_NOT_FOUND = new ErrorCode(1001004000, "用户地址不存在!");
    ErrorCode USER_GET_ADDRESS_NOT_EXISTS = new ErrorCode(1001004001, "获取的地址不存在!");

}
