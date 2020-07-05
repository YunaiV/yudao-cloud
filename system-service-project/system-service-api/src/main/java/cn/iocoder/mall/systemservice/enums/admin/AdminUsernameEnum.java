package cn.iocoder.mall.systemservice.enums.admin;

/**
 * 管理员的账号枚举，一般枚举特殊的账号
 *
 * 例如说，特殊管理员 admin 禁止编辑
 */
public enum  AdminUsernameEnum {

    ADMIN("admin"),
    DEMO("yudaoyuanma"),
    ;

    private final String username;

    AdminUsernameEnum(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
