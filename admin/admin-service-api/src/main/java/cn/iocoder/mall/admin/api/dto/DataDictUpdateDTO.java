package cn.iocoder.mall.admin.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 数据字典更新 DTO
 */
public class DataDictUpdateDTO {

    /**
     * 编号
     */
    @NotNull(message = "编号不能为空")
    private Integer id;
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

    public Integer getId() {
        return id;
    }

    public DataDictUpdateDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getValue() {
        return value;
    }

    public DataDictUpdateDTO setValue(String value) {
        this.value = value;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public DataDictUpdateDTO setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public DataDictUpdateDTO setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public String getMemo() {
        return memo;
    }

    public DataDictUpdateDTO setMemo(String memo) {
        this.memo = memo;
        return this;
    }

}