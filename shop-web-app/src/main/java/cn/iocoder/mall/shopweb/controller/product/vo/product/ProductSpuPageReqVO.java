package cn.iocoder.mall.shopweb.controller.product.vo.product;

import cn.iocoder.common.framework.vo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@ApiModel("商品 SPU 分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ProductSpuPageReqVO extends PageParam {

    @ApiModelProperty(value = "分类编号", example = "1")
    private Integer cid;
    @ApiModelProperty(value = "关键字", example = "芋艿")
    private String keyword;
    @ApiModelProperty(value = "排序字段", example = "buyPrice", notes = "参见 SearchProductPageQuerySortFieldEnum 枚举")
    private String sortField;
    @ApiModelProperty(value = "排序顺序", example = "1")
    private String sortOrder;

}
