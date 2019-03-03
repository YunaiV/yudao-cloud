package cn.iocoder.mall.admin.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 数据字典添加 DTO
 */
public class DataDictAddDTO {

    /**
     * 大类枚举值
     */
    @NotEmpty(message = "大类枚举值不能为空")
    private String enumValue;
    /**
     * 小类数值
     */
    @NotEmpty(message = "小类数值不能为空")
    private String value;
    /**
     * 展示名
     */
    @NotEmpty(message = "展示名不能为空")
    private String displayName;
    /**
     * 排序值
     */
    @NotNull(message = "排序值不能为空")
    private Integer sort;
    /**
     * 备注
     */
    private String memo;

    public String getEnumValue() {
        return enumValue;
    }

    public DataDictAddDTO setEnumValue(String enumValue) {
        this.enumValue = enumValue;
        return this;
    }

    public String getValue() {
        return value;
    }

    public DataDictAddDTO setValue(String value) {
        this.value = value;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public DataDictAddDTO setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public DataDictAddDTO setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public String getMemo() {
        return memo;
    }

    public DataDictAddDTO setMemo(String memo) {
        this.memo = memo;
        return this;
    }

}