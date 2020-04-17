package cn.iocoder.mall.system.application.vo.datadict;

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
    private List<Value> values;

    @ApiModel("数据字典枚举值 VO")
    @Data
    @Accessors(chain = true)
    public static class Value {

        @ApiModelProperty(value = "小类数值", required = true, example = "1")
        private String value;

        @ApiModelProperty(value = "展示名", required = true, example = "男")
        private String displayName;

    }

}
