package cn.iocoder.mall.shopweb.controller.product.vo.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@ApiModel("商品 SPU Response VO")
@Data
@Accessors(chain = true)
public class ProductSpuRespVO {

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
    @ApiModelProperty(value = "分类名字", required = true, example = "蔬菜")
    private String categoryName;
    @ApiModelProperty(value = "商品主图地址", required = true, example = "http://www.iocoder.cn/xxx.jpg", notes = "多个之间，使用逗号分隔")
    private List<String> picUrls;

    // ========== 其他信息 =========
    @ApiModelProperty(value = "是否上架商品", required = true, example = "true")
    private Boolean visible;
    @ApiModelProperty(value = "排序字段", required = true, example = "1024")
    private Integer sort;

    // ========== Sku 相关字段 =========
    @ApiModelProperty(value = "原始价格，单位：分", required = true, example = "233", notes = "该价格为商品的原始价格")
    private Integer originalPrice;
    @ApiModelProperty(value = "购买价格，单位：分", required = true, example = "233", notes = "该价格为商品经过优惠计算后的价格")
    private Integer buyPrice;
    @ApiModelProperty(value = "库存数量", required = true, example = "1024")
    private Integer quantity;

    // ========== 促销活动相关字段 ========= TODO 芋艿：等做到促销在处理
    // 目前只促销单体商品促销，目前仅限制折扣。
    /**
     * 促销活动编号
     */
    private Integer promotionActivityId;
    /**
     * 促销活动标题
     */
    private String promotionActivityTitle;
    /**
     * 促销活动类型
     */
    private Integer promotionActivityType;

}
