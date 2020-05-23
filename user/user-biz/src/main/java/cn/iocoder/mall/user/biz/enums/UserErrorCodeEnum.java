package cn.iocoder.mall.user.biz.enums;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;

/**
 * 错误码枚举类
 *
 * 用户系统，使用 1-001-000-000 段
 */
public enum UserErrorCodeEnum implements ServiceExceptionUtil.Enumerable {

    // ========== 用户地址 ==========
    USER_ADDRESS_NOT_EXISTENT(1001004000, "用户地址不存在!"),
    USER_ADDRESS_IS_DELETED(1001004001, "用户地址已被删除!"),
    USER_GET_ADDRESS_NOT_EXISTS(1001004002, "获取的地址不存在!"),

    // ========== 用户 ==========
    // TODO DONE FROM 芋艿 to linhj：是不是提示不对呀
    USER_NOT_EXISTS(1001004003, "用户不存在!"),
    ;

    private final int code;
    private final String message;

    UserErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public int getGroup() {
        return 0;
    }

}
