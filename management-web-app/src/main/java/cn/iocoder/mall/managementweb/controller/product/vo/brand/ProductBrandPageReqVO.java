package cn.iocoder.mall.managementweb.controller.product.vo.brand;

import cn.iocoder.common.framework.vo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@ApiModel("商品品牌分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductBrandPageReqVO extends PageParam {

    @ApiModelProperty(value = "品牌名称", required = true, notes = "模糊匹配", example = "这个商品品牌很吊")
    private String name;
    @ApiModelProperty(value = "状态", required = true, example = "1", notes = "见 CommonStatusEnum 枚举")
    private Integer status;

}
