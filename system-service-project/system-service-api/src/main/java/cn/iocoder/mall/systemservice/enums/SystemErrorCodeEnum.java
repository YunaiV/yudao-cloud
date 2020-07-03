package cn.iocoder.mall.systemservice.enums;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;

/**
 * 错误码枚举类
 *
 * system 系统，使用 1-002-000-000 段
 */
public enum SystemErrorCodeEnum implements ServiceExceptionUtil.Enumerable<SystemErrorCodeEnum> {

    // ========== OAUTH2 模块 ==========
    OAUTH2_UNKNOWN(1001001000, "未知错误"), // 预留
    // 预留 1001001001 ~ 1001001099 错误码，方便前端
    OAUTH2_ACCESS_TOKEN_NOT_FOUND(1001001001, "访问令牌不存在"),
    OAUTH2_ACCESS_TOKEN_TOKEN_EXPIRED(1001001002, "访问令牌已过期"),
    OAUTH2_ACCESS_TOKEN_INVALID(1001001003, "访问令牌已失效"),
    OAUTH2_NOT_AUTHENTICATE(1001001004, "账号未登陆"),
    OAUTH2_REFRESH_TOKEN_NOT_FOUND(1001001005, "刷新令牌不存在"),
    OAUTH_REFRESH_TOKEN_EXPIRED(1001001006, "访问令牌已过期"),
    OAUTH_REFRESH_TOKEN_INVALID(1001001007, "刷新令牌已失效"),
    // 其它 1001001100 开始
    OAUTH2_ACCOUNT_NOT_FOUND(1001001100, "账号不存在"),
    OAUTH2_ACCOUNT_PASSWORD_ERROR(1001001101, "密码不正确"),

    // ========== 管理员模块 1002002000 ==========
    ADMIN_NOT_FOUND(1002002000, "管理员不存在"),
// 废弃    ADMIN_USERNAME_NOT_REGISTERED(1002002000, "账号不存在"),
// 废弃    ADMIN_PASSWORD_ERROR(1002002001, "密码不正确"),
//    ADMIN_IS_DISABLE(1002002002, "账号被禁用"),
//    ADMIN_USERNAME_EXISTS(1002002002, "账号已经存在"),
//    ADMIN_STATUS_EQUALS(1002002003, "账号已经是该状态"),
//    ADMIN_DELETE_ONLY_DISABLE(1002002004, "只有关闭的账号才可以删除"),
//    ADMIN_ADMIN_STATUS_CAN_NOT_UPDATE(1002002005, "管理员的账号状态不允许变更"),
//    ADMIN_ASSIGN_ROLE_NOT_EXISTS(1002002006, "分配员工角色时，有角色不存在"),
//    ADMIN_ADMIN_CAN_NOT_UPDATE(1002002008, "管理员的账号不允许变更"),

    // ========== 资源模块 1002003000 ==========
    RESOURCE_NAME_DUPLICATE(1002003000, "已经存在该名字的资源"),
    RESOURCE_PARENT_NOT_EXISTS(1002003001, "父资源不存在"),
    RESOURCE_PARENT_ERROR(1002003002, "不能设置自己为父资源"),
    RESOURCE_NOT_EXISTS(1002003003, "资源不存在"),
    RESOURCE_EXISTS_CHILDREN(1002003004, "存在子资源，无法删除"),
    RESOURCE_PARENT_NOT_MENU(1002003005, "父资源的类型必须是菜单"),

    // ========== 角色模块 1002004000 ==========
    ROLE_NOT_EXISTS(1002004000, "角色不存在"),
    ROLE_NAME_DUPLICATE(1002004001, "已经存在名为【{}}】的角色"),
    ROLE_CODE_DUPLICATE(1002004002, "已经存在编码为【{}}】的角色"),
    ROLE_CAN_NOT_UPDATE_SYSTEM_TYPE_ROLE(1002004004, "不能修改类型为系统内置的角色"),
    ROLE_CAN_NOT_DELETE_SYSTEM_TYPE_ROLE(1002004005, "不能删除类型为系统内置的角色"),

    // ========== 数据字典模块 1002005000 ==========
//    DATA_DICT_EXISTS(1002005000, "该数据字典已经存在"),
//    DATA_DICT_NOT_EXISTS(1002005001, "该数据字典不存在"),

    // ========== 短信模板 1002006000 ==========
    SMS_PLATFORM_FAIL(1002006000, "短信平台调用失败【具体错误会动态替换】"),
    SMS_SIGN_NOT_EXISTENT(1002006001, "短信签名不存在"),
    SMS_SIGN_IS_EXISTENT(1002006002, "短信签名已存在"),
    SMS_TEMPLATE_NOT_EXISTENT(1002006020, "短信签名不存在"),
    SMS_TEMPLATE_IS_EXISTENT(1002006021, "短信签名不存在"),
    SMS_NOT_SEND_CLIENT(1002006030, "短信没有发送的client"),

    // ========== 部门模块 1002007000 ==========
//    DEPT_SAME_LEVEL_NAME_EXITS(1002007001,"当前级别部门名字已存在"),
//    DEPT_PARENT_NOT_EXITS(1002007002,"父级部门不存在"),
//    DEPT_NOT_EXITS(1002007003, "当前部门不存在"),
//    DEPT_EXITS_CHILDREN(1002007004, "当前部门存在子部门"),
//    DEPT_PARENT_NOT_LEGAL(1002007005, "父级部门不合法"),

    // ========== 授权模块 1002008000 ==========
    AUTHORIZATION_PERMISSION_DENY(1002008001, "没有该操作权限"),
    AUTHORIZATION_DEMO_PERMISSION_DENY(1002008002, "演示账号，暂不允许写操作。欢迎加入我们的交流群：http://t.cn/EKEr5WE"),
    AUTHORIZATION_ROLE_ASSIGN_RESOURCE_NOT_EXISTS(1002004001, "分配角色资源时，有资源不存在"),

    // ========== 错误码模块 1002009000 ==========
    ERROR_CODE_NOT_EXISTS(1002009000, "错误码不存在"),
    ERROR_CODE_DUPLICATE(1002009001, "已经存在编码为【{}}】的错误码"),
    ERROR_CAN_NOT_UPDATE_SYSTEM_TYPE_ERROR(1002004003, "不能修改类型为系统内置的错误码"),
    ;


    private final int code;
    private final String message;

    SystemErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    // TODO: 2020-05-22 封装成start的时候，直接在start中定义一个统一的枚举，从中取值；
    @Override
    public int getGroup() {
        return 0;
    }


}
