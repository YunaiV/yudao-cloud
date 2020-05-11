package cn.iocoder.mall.product.rest.request.brand;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ApiModel("商品 - 品牌模块 - 商品品牌更新 Request")
@Data
@Accessors(chain = true)
public class ProductBrandUpdateRequest {

    @ApiModelProperty(name = "id", value = "规格编号", required = true, example = "1")
    @NotNull(message = "品牌编号不能为空")
    private Integer id;

    @ApiModelProperty(name = "name", value = "品牌名称", required = true, example = "安踏")
    @NotEmpty(message = "品牌名称不能为空")
    private String name;

    @ApiModelProperty(name = "description", value = "品牌描述", required = true, example = "安踏拖鞋")
    private String description;

    @ApiModelProperty(name = "picUrl", value = "品牌图片", required = true, example = "http://www.iocoder.cn")
    private String picUrl;

    @ApiModelProperty(name = "status", value = "状态 1开启 2禁用", required = true, example = "1")
    private Integer status;
}
