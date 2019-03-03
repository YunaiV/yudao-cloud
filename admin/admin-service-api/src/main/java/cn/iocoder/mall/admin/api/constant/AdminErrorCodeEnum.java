package cn.iocoder.mall.admin.api.constant;

/**
 * 错误码枚举类
 *
 * 用户中心，使用 1-002-000-000 段
 */
public enum AdminErrorCodeEnum {

    // ========== OAUTH2 模块 ==========
    OAUTH2_UNKNOWN(1002001000, "未知错误"), // 预留
//    OAUTH2_INVALID_GRANT_BAD_CREDENTIALS(1001001001, "密码不正确"), // 暂时没用到
//    OAUTH2_INVALID_GRANT_USERNAME_NOT_FOUND(1001001002, "账号不存在"), // 暂时没用到
//    OAUTH2_INVALID_GRANT(1001001010, ""), // 预留
    OAUTH_INVALID_TOKEN_NOT_FOUND(1002001011, "访问令牌不存在"),
    OAUTH_INVALID_TOKEN_EXPIRED(1002001012, "访问令牌已过期"),
    OAUTH_INVALID_TOKEN_INVALID(1002001013, "访问令牌已失效"),
    OAUTH_INVALID_PERMISSION(1002001014, "没有该操作权限"), // TODO 芋艿，临时放在 OAUTH2 模块，理论来说，OAUTH2 只做认证，不做鉴权。
    OAUTH_NOT_LOGIN(1002001015, "账号未登陆"),

    OAUTH_INVALID_TOKEN(1002001020, ""), // 预留

    // ========== 管理员模块 1002002000 ==========
    ADMIN_USERNAME_NOT_REGISTERED(1002002000, "账号不存在"),
    ADMIN_PASSWORD_ERROR(1002002001, "密码不正确"),
    ADMIN_IS_DISABLE(1002002002, "账号被禁用"),
    ADMIN_USERNAME_EXISTS(1002002002, "账号已经存在"),
    ADMIN_STATUS_EQUALS(1002002003, "账号已经是该状态"),
    ADMIN_DELETE_ONLY_DISABLE(1002002004, "只有关闭的账号才可以删除"),

    // ========== 资源模块 1002003000 ==========
    RESOURCE_NAME_DUPLICATE(1002003000, "已经存在该名字的资源"),
    RESOURCE_PARENT_NOT_EXISTS(1002003001, "父资源不存在"),
    RESOURCE_PARENT_ERROR(1002003002, "不能设置自己为父资源"),
    RESOURCE_NOT_EXISTS(1002003003, "资源不存在"),
    RESOURCE_EXISTS_CHILDREN(1002003004, "存在子资源，无法删除"),

    // ========== 角色模块 1002004000 ==========
    ROLE_NOT_EXISTS(1002004000, "角色不存在"),
    ROLE_ASSIGN_RESOURCE_NOT_EXISTS(1002004001, "分配角色资源时，有资源不存在"),

    // ========== 数据字典模块 1002005000 ==========
    DATA_DICT_EXISTS(1002005000, "该数据字典已经存在"),
    DATA_DICT_NOT_EXISTS(1002005001, "该数据字典不存在"),

    ;

    private final int code;
    private final String message;

    AdminErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}