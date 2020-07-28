package cn.iocoder.mall.managementweb.controller.product.vo.attr;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.validator.InEnum;
import cn.iocoder.common.framework.vo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@ApiModel("商品规格键分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductAttrKeyPageReqVO extends PageParam {

    @ApiModelProperty(value = "规格键名称", required = true, example = "尺寸", notes = "模糊匹配")
    private String name;
    @ApiModelProperty(value = "状态", example = "1", notes = "见 CommonStatusEnum 枚举")
    @InEnum(value = CommonStatusEnum.class, message = "修改状态必须是 {value}")
    private Integer status;

}
