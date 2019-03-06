package cn.iocoder.mall.admin.application.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("数据字典枚举 VO")
public class DataDictEnumVO {

    @ApiModelProperty(value = "大类枚举值", required = true, example = "gender")
    private String enumValue;
    @ApiModelProperty(value = "小类数值数组", required = true)
    private List<DataDictValueVO> values;

    public String getEnumValue() {
        return enumValue;
    }

    public DataDictEnumVO setEnumValue(String enumValue) {
        this.enumValue = enumValue;
        return this;
    }

    public List<DataDictValueVO> getValues() {
        return values;
    }

    public DataDictEnumVO setValues(List<DataDictValueVO> values) {
        this.values = values;
        return this;
    }

}