package cn.iocoder.mall.admin.application.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel("数据字典枚举值 VO")
@Data
@Accessors(chain = true)
public class DataDictValueVO {

    @ApiModelProperty(value = "小类数值", required = true, example = "1")
    private String value;
    @ApiModelProperty(value = "展示名", required = true, example = "男")
    private String displayName;

}
