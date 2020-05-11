package cn.iocoder.mall.product.rest.response.brand;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

/**
 * VO
 */
@ApiModel(value = "商品品牌", description = "商品品牌")
@Data
@Accessors(chain = true)
public class AdminsProductBrandResponse {
    /**
     * 规格编号
     */
    @ApiModelProperty(value = "品牌编号", required = true, example = "1")
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
