package cn.iocoder.mall.managementweb.controller.product.vo.attr;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.validator.InEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ApiModel("商品规格键创建 Request VO")
@Data
public class ProductAttrKeyCreateReqVO {

    @ApiModelProperty(value = "规格键名称", required = true, example = "尺寸")
    @NotEmpty(message = "规格键名称不能为空")
    private String name;
    @ApiModelProperty(value = "状态", required = true, example = "1", notes = "见 CommonStatusEnum 枚举")
    @NotNull(message = "状态不能为空")
    @InEnum(value = CommonStatusEnum.class, message = "修改状态必须是 {value}")
    private Integer status;

}
