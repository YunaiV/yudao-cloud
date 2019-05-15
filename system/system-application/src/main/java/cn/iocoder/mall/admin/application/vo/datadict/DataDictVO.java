package cn.iocoder.mall.admin.application.vo.datadict;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel("数据字典 VO")
@Data
@Accessors(chain = true)
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
    private String memo;

}
