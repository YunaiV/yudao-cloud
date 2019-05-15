package cn.iocoder.mall.admin.application.vo.datadict;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@ApiModel("数据字典枚举 VO")
@Data
@Accessors(chain = true)
public class DataDictEnumVO {

    @ApiModelProperty(value = "大类枚举值", required = true, example = "gender")
    private String enumValue;
    @ApiModelProperty(value = "小类数值数组", required = true)
    private List<DataDictValueVO> values;

}
