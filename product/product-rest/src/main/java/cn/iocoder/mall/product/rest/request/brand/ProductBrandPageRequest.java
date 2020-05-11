package cn.iocoder.mall.product.rest.request.brand;

import cn.iocoder.common.framework.vo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@ApiModel("商品 - 品牌模块 - 品牌分页 Request")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ProductBrandPageRequest extends PageParam {

    @ApiModelProperty(name = "name", value = "品牌名称", required = true, example = "安踏")
    private String name;

    @ApiModelProperty(name = "name", value = "品牌描述", required = true, example = "安踏拖鞋")
    private String description;

    @ApiModelProperty(name = "name", value = "状态 1开启 2禁用", required = true, example = "1")
    private String status;
}
