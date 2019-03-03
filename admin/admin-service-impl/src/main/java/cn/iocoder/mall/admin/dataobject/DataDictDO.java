package cn.iocoder.mall.admin.dataobject;

import cn.iocoder.common.framework.dataobject.BaseDO;

/**
 * 数据字典
 *
 * 使用 {@link #enumValue} 作为聚合。例如说：
 *
 * enumValue ：gender 性别
 *      value：1 男
 *      value：2 女
 */
public class DataDictDO extends BaseDO {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 大类枚举值
     */
    private String enumValue;
    /**
     * 小类数值
     */
    private String value;
    /**
     * 展示名
     */
    private String displayName;
    /**
     * 排序值
     */
    private Integer sort;
    /**
     * 备注
     */
    private String memo;

    public String getEnumValue() {
        return enumValue;
    }

    public DataDictDO setEnumValue(String enumValue) {
        this.enumValue = enumValue;
        return this;
    }

    public String getValue() {
        return value;
    }

    public DataDictDO setValue(String value) {
        this.value = value;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public DataDictDO setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public DataDictDO setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public String getMemo() {
        return memo;
    }

    public DataDictDO setMemo(String memo) {
        this.memo = memo;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public DataDictDO setId(Integer id) {
        this.id = id;
        return this;
    }
}