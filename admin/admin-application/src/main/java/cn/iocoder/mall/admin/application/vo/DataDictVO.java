package cn.iocoder.mall.admin.application.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("数据字典 VO")
public class DataDictVO {

    @ApiModelProperty(value = "编号", required = true, example = "1")
    private Integer id;
    @ApiModelProperty(value = "大类枚举值", required = true, example = "gender")
    private String enumValue;
    @ApiModelProperty(value = "小类数值", required = true, example = "1")
    private String value;
    @ApiModelProperty(value = "展示名", required = true, example = "男")
    private String displayName;
    @ApiModelProperty(value = "排序值", required = true, example = "10")
    private Integer sort;
    @ApiModelProperty(value = "备注", example = "你猜")
    private Integer memo;

    public Integer getId() {
        return id;
    }

    public DataDictVO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getEnumValue() {
        return enumValue;
    }

    public DataDictVO setEnumValue(String enumValue) {
        this.enumValue = enumValue;
        return this;
    }

    public String getValue() {
        return value;
    }

    public DataDictVO setValue(String value) {
        this.value = value;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public DataDictVO setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public DataDictVO setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public Integer getMemo() {
        return memo;
    }

    public DataDictVO setMemo(Integer memo) {
        this.memo = memo;
        return this;
    }

}