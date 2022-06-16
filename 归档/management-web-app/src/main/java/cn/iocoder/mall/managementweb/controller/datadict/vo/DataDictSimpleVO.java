package cn.iocoder.mall.managementweb.controller.datadict.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@ApiModel("数据字典精简 VO")
@Data
public class DataDictSimpleVO {

    @ApiModelProperty(value = "大类枚举值", required = true, example = "gender")
    private String enumValue;
    @ApiModelProperty(value = "小类数值", required = true, example = "1")
    private String value;
    @ApiModelProperty(value = "展示名", required = true, example = "男")
    private String displayName;

}
