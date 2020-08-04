package cn.iocoder.mall.shopweb.controller.product.vo.product;

import cn.iocoder.mall.shopweb.controller.product.vo.attr.ProductAttrKeyValueRespVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@ApiModel(value = "商品 SPU 详细 Response VO", description = "包括 SKU 信息 VO")
@Data
@Accessors(chain = true)
public class ProductSpuDetailRespVO {

    @ApiModelProperty(value = "SPU 编号", required = true, example = "1")
    private Integer id;

    // ========== 基本信息 =========
    @ApiModelProperty(value = "SPU 名字", required = true, example = "芋艿")
    private String name;
    @ApiModelProperty(value = "卖点", required = true, example = "好吃好玩")
    private String sellPoint;
    @ApiModelProperty(value = "描述", required = true, example = "我是哈哈哈")
    private String description;
    @ApiModelProperty(value = "分类编号", required = true, example = "1")
    private Integer cid;
    @ApiModelProperty(value = "商品主图地址", required = true, example = "http://www.iocoder.cn/xxx.jpg", notes = "多个之间，使用逗号分隔")
    private List<String> picUrls;

    // ========== SKU =========

    /**
     * SKU 数组
     */
    private List<Sku> skus;

    @ApiModel("商品 SKU 详细 Response VO")
    @Data
    @Accessors(chain = true)
    public static class Sku implements Serializable {

        @ApiModelProperty(value = "sku 编号", required = true, example = "1")
        private Integer id;
        @ApiModelProperty(value = "SPU 编号", required = true, example = "1")
        private Integer spuId;
        @ApiModelProperty(value = "图片地址", required = true, example = "http://www.iocoder.cn")
        private String picURL;
        /**
         * 规格值数组
         */
        private List<ProductAttrKeyValueRespVO> attrs;
        @ApiModelProperty(value = "价格，单位：分", required = true, example = "100")
        private Integer price;
        @ApiModelProperty(value = "库存数量", required = true, example = "100")
        private Integer quantity;

    }

}
