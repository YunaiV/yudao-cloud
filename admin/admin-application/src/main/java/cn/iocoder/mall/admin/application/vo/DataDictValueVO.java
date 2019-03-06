package cn.iocoder.mall.admin.application.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("数据字典枚举值 VO")
public class DataDictValueVO {

    @ApiModelProperty(value = "小类数值", required = true, example = "1")
    private String value;
    @ApiModelProperty(value = "展示名", required = true, example = "男")
    private String displayName;

    public String getValue() {
        return value;
    }

    public DataDictValueVO setValue(String value) {
        this.value = value;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public DataDictValueVO setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

}