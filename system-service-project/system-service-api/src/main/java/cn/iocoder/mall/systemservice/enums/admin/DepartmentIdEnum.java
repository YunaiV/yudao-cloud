package cn.iocoder.mall.systemservice.enums.admin;

/**
 * 部门的编号枚举
 */
public enum DepartmentIdEnum {

    /**
     * 根节点
     */
    ROOT(0);

    private final Integer id;

    DepartmentIdEnum(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

}
