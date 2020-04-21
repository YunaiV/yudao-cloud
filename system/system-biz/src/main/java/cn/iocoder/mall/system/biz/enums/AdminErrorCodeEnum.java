package cn.iocoder.mall.system.biz.enums;

/**
 * 错误码枚举类
 *
 * 管理员系统，使用 1-002-000-000 段
 */
@Deprecated
public enum AdminErrorCodeEnum {

    // ========== OAUTH2 模块 ==========
    OAUTH2_UNKNOWN(1002001000, "未知错误"), // 预留
//    OAUTH2_INVALID_GRANT_BAD_CREDENTIALS(1001001001, "密码不正确"), // 暂时没用到
//    OAUTH2_INVALID_GRANT_USERNAME_NOT_FOUND(1001001002, "账号不存在"), // 暂时没用到
//    OAUTH2_INVALID_GRANT(1001001010, ""), // 预留
    OAUTH2_INVALID_TOKEN_NOT_FOUND(1002001011, "访问令牌不存在"),
    OAUTH2_INVALID_TOKEN_EXPIRED(1002001012, "访问令牌已过期"),
    OAUTH2_INVALID_TOKEN_INVALID(1002001013, "访问令牌已失效"),
    OAUTH2_NOT_LOGIN(1002001015, "账号未登陆"),
    OAUTH2_INVALID_TOKEN_ERROR_USER_TYPE(1002001016, "访问令牌用户类型不正确"),
    OAUTH_INVALID_REFRESH_TOKEN_NOT_FOUND(1002001017, "刷新令牌不存在"),
    OAUTH_INVALID_REFRESH_TOKEN_EXPIRED(1002001018, "访问令牌已过期"),
    OAUTH_INVALID_REFRESH_TOKEN_INVALID(1002001019, "刷新令牌已失效"),

    // ========== 管理员模块 1002002000 ==========
    ADMIN_USERNAME_NOT_REGISTERED(1002002000, "账号不存在"),
    ADMIN_PASSWORD_ERROR(1002002001, "密码不正确"),
    ADMIN_IS_DISABLE(1002002002, "账号被禁用"),
    ADMIN_USERNAME_EXISTS(1002002002, "账号已经存在"),
    ADMIN_STATUS_EQUALS(1002002003, "账号已经是该状态"),
    ADMIN_DELETE_ONLY_DISABLE(1002002004, "只有关闭的账号才可以删除"),
    ADMIN_ADMIN_STATUS_CAN_NOT_UPDATE(1002002005, "管理员的账号状态不允许变更"),
    ADMIN_ASSIGN_ROLE_NOT_EXISTS(1002002006, "分配员工角色时，有角色不存在"),
    ADMIN_INVALID_PERMISSION(1002002007, "没有该操作权限"),
    ADMIN_ADMIN_CAN_NOT_UPDATE(1002002008, "管理员的账号不允许变更"),
    ADMIN_DEMO_CAN_NOT_WRITE(1002002009, "演示账号，暂不允许写操作。欢迎加入我们的交流群：http://t.cn/EKEr5WE"),

    // ========== 资源模块 1002003000 ==========
    RESOURCE_NAME_DUPLICATE(1002003000, "已经存在该名字的资源"),
    RESOURCE_PARENT_NOT_EXISTS(1002003001, "父资源不存在"),
    RESOURCE_PARENT_ERROR(1002003002, "不能设置自己为父资源"),
    RESOURCE_NOT_EXISTS(1002003003, "资源不存在"),
    RESOURCE_EXISTS_CHILDREN(1002003004, "存在子资源，无法删除"),
    RESOURCE_PARENT_NOT_MENU(1002003005, "父资源的类型必须是菜单"),

    // ========== 角色模块 1002004000 ==========
    ROLE_NOT_EXISTS(1002004000, "角色不存在"),
    ROLE_ASSIGN_RESOURCE_NOT_EXISTS(1002004001, "分配角色资源时，有资源不存在"),

    // ========== 数据字典模块 1002005000 ==========
    DATA_DICT_EXISTS(1002005000, "该数据字典已经存在"),
    DATA_DICT_NOT_EXISTS(1002005001, "该数据字典不存在"),

    // ========== 短信模板 1002006000 ==========
    SMS_PLATFORM_FAIL(1002006000, "短信平台调用失败【具体错误会动态替换】"),
    SMS_SIGN_NOT_EXISTENT(1002006001, "短信签名不存在"),
    SMS_SIGN_IS_EXISTENT(1002006002, "短信签名已存在"),
    SMS_TEMPLATE_NOT_EXISTENT(1002006020, "短信签名不存在"),
    SMS_TEMPLATE_IS_EXISTENT(1002006021, "短信签名不存在"),
    SMS_NOT_SEND_CLIENT(1002006030, "短信没有发送的client"),

    // ========== 部门模块 1002007000 ==========
    DEPT_SAME_LEVEL_NAME_EXITS(1002007001,"当前级别部门名字已存在"),
    DEPT_PARENT_NOT_EXITS(1002007002,"父级部门不存在"),
    DEPT_NOT_EXITS(1002007003, "当前部门不存在"),
    DEPT_EXITS_CHILDREN(1002007004, "当前部门存在子部门"),
    DEPT_PARENT_NOT_LEGAL(1002007005, "父级部门不合法"),
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
