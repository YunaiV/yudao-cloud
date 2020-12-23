package cn.iocoder.mall.searchservice.service.product.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 搜索商品 BO
 */
@Data
@Accessors(chain = true)
public class SearchProductBO {

    private Integer id;

    // ========== 基本信息 =========
    /**
     * SPU 名字
     */
    private String name;
    /**
     * 卖点
     */
    private String sellPoint;
    /**
     * 描述
     */
    private String description;
    /**
     * 分类编号
     */
    private Integer cid;
    /**
     * 分类名
     */
    private String categoryName;
    /**
     * 商品主图地数组
     */
    private List<String> picUrls;

    // ========== 其他信息 =========
    /**
     * 是否上架商品（是否可见）。
     *
     * true 为已上架
     * false 为已下架
     */
    private Boolean visible;
    /**
     * 排序字段
     */
    private Integer sort;

    // ========== Sku 相关字段 =========
    /**
     * 原价格，单位：分
     */
    private Integer originalPrice;
    /**
     * 购买价格，单位：分。
     */
    private Integer buyPrice;
    /**
     * 库存数量
     */
    private Integer quantity;

    // ========== 促销活动相关字段 =========
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
