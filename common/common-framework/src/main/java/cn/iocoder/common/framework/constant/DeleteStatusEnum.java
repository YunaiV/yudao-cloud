package cn.iocoder.common.framework.constant;

import cn.iocoder.common.framework.dataobject.DeletableDO;

/**
 * {@link DeletableDO#getDeleted()} delete 状态
 *
 * @author Sin
 * @time 2019-03-22 21:15
 */
public enum DeleteStatusEnum {

    DELETE_NO(0, "正常(未删除)"),
    DELETE_YES(1, "删除");

    /**
     * 状态值
     */
    private Integer value;
    /**
     * 状态名
     */
    private String name;

    DeleteStatusEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
