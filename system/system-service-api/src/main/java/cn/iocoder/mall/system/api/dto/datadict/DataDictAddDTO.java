package cn.iocoder.mall.system.api.dto.datadict;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ApiModel("数据字典添加 DTO")
@Data
@Accessors(chain = true)
public class DataDictAddDTO implements Serializable {

    @ApiModelProperty(value = "大类枚举值", required = true, example = "gender")
    @NotEmpty(message = "大类枚举值不能为空")
    private String enumValue;

    @ApiModelProperty(value = "小类数值", required = true, example = "1")
    @NotEmpty(message = "小类数值不能为空")
    private String value;

    @ApiModelProperty(value = "展示名", required = true, example = "男")
    @NotEmpty(message = "展示名不能为空")
    private String displayName;

    @ApiModelProperty(required = true, value = "排序值", example = "123")
    @NotNull(message = "排序值不能为空")
    private Integer sort;

    @ApiModelProperty(value = "备注", example = "你猜我猜不猜")
    private String memo;

}
