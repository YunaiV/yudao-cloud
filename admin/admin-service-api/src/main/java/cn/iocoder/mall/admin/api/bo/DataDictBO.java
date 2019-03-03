package cn.iocoder.mall.admin.api.bo;

import java.util.Date;

/**
 * 数据字典 BO
 */
public class DataDictBO {

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
    /**
     * 创建时间
     */
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public DataDictBO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getEnumValue() {
        return enumValue;
    }

    public DataDictBO setEnumValue(String enumValue) {
        this.enumValue = enumValue;
        return this;
    }

    public String getValue() {
        return value;
    }

    public DataDictBO setValue(String value) {
        this.value = value;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public DataDictBO setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public DataDictBO setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public String getMemo() {
        return memo;
    }

    public DataDictBO setMemo(String memo) {
        this.memo = memo;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public DataDictBO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

}