package cn.iocoder.mall.managementweb.controller.product.vo.attr;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.validator.InEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ApiModel("商品规格值更新 Request VO")
@Data
public class ProductAttrValueUpdateReqVO {

    @ApiModelProperty(value = "规格值编号", required = true, example = "1")
    @NotNull(message = "规格值编号不能为空")
    private Integer id;
    @ApiModelProperty(value = "规格值名字", required = true, example = "XXL")
    @NotEmpty(message = "规格值名字不能为空")
    private String name;
    @ApiModelProperty(value = "状态", required = true, example = "1", notes = "见 CommonStatusEnum 枚举")
    @NotNull(message = "状态不能为空")
    @InEnum(value = CommonStatusEnum.class, message = "修改状态必须是 {value}")
    private Integer status;

}

