package cn.iocoder.mall.systemservice.enums.permission;

public enum RoleTypeEnum {

    /**
     * 内置角色
     */
    SYSTEM(1),
    /**
     * 自定义角色
     */
    CUSTOM(2);

    private final Integer type;

    RoleTypeEnum(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

}
