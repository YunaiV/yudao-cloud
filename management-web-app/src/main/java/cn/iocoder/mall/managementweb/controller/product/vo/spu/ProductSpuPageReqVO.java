package cn.iocoder.mall.managementweb.controller.product.vo.spu;

import cn.iocoder.common.framework.vo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@ApiModel("商品 SPU分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductSpuPageReqVO extends PageParam {

    @ApiModelProperty(value = "SPU 名字", required = true)
    private String name;
    @ApiModelProperty(value = "卖点", required = true)
    private String sellPoint;
    @ApiModelProperty(value = "描述", required = true)
    private String description;
    @ApiModelProperty(value = "分类编号", required = true)
    private Integer cid;
    @ApiModelProperty(value = "商品主图地址", required = true)
    private String picUrls;
    @ApiModelProperty(value = "是否上架商品", required = true)
    private Integer visible;
    @ApiModelProperty(value = "排序字段", required = true)
    private Integer sort;
    @ApiModelProperty(value = "价格", required = true)
    private Integer price;
    @ApiModelProperty(value = "库存数量", required = true)
    private Integer quantity;

}
