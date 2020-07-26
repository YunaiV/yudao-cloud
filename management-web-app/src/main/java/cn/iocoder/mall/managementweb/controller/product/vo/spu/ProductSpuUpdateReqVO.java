package cn.iocoder.mall.managementweb.controller.product.vo.spu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ApiModel("商品 SPU更新 Request VO")
@Data
public class ProductSpuUpdateReqVO {

    @ApiModelProperty(value = "SPU 编号", required = true)
    @NotNull(message = "SPU 编号不能为空")
    private Integer id;
    @ApiModelProperty(value = "SPU 名字", required = true)
    @NotEmpty(message = "SPU 名字不能为空")
    private String name;
    @ApiModelProperty(value = "卖点", required = true)
    @NotEmpty(message = "卖点不能为空")
    private String sellPoint;
    @ApiModelProperty(value = "描述", required = true)
    @NotEmpty(message = "描述不能为空")
    private String description;
    @ApiModelProperty(value = "分类编号", required = true)
    @NotNull(message = "分类编号不能为空")
    private Integer cid;
    @ApiModelProperty(value = "商品主图地址", required = true)
    @NotEmpty(message = "商品主图地址不能为空")
    private String picUrls;
    @ApiModelProperty(value = "是否上架商品", required = true)
    @NotNull(message = "是否上架商品不能为空")
    private Integer visible;
    @ApiModelProperty(value = "排序字段", required = true)
    @NotNull(message = "排序字段不能为空")
    private Integer sort;
    @ApiModelProperty(value = "价格", required = true)
    @NotNull(message = "价格不能为空")
    private Integer price;
    @ApiModelProperty(value = "库存数量", required = true)
    @NotNull(message = "库存数量不能为空")
    private Integer quantity;

}
