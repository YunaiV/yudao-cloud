package cn.iocoder.mall.shopweb.controller.product.vo.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@ApiModel("商品 SPU 搜索条件 Response VO")
@Data
@Accessors(chain = true)
public class ProductSpuSearchConditionRespVO {

    @ApiModel("商品分类信息")
    @Data
    @Accessors(chain = true)
    public static class Category {

        @ApiModelProperty(value = "分类编号", required = true, example = "1")
        private Integer id;
        @ApiModelProperty(value = "分类名称", required = true, example = "手机")
        private String name;

    }

    /**
     * 商品分类数组
     */
    private List<Category> categories;

}
