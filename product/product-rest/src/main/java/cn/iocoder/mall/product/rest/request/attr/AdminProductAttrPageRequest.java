package cn.iocoder.mall.product.rest.request.attr;

import cn.iocoder.common.framework.vo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@ApiModel("商品 - 规格模块 - 商品规格 Request")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AdminProductAttrPageRequest extends PageParam {

    @ApiModelProperty(value = "商品规格名字，模糊匹配", example = "材料")
    private String name;

}
