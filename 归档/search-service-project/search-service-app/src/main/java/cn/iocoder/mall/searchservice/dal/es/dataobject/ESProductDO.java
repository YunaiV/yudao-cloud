package cn.iocoder.mall.searchservice.dal.es.dataobject;

import cn.iocoder.mall.searchservice.dal.es.FieldAnalyzer;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

/**
 * 商品 ES DO
 */
@Document(indexName = "product", type = "product", shards = 1, replicas = 0)
@Data
@Accessors(chain = true)
public class ESProductDO {

    @Id
    private Integer id;

    // ========== 基本信息 =========
    /**
     * SPU 名字
     */
    @Field(analyzer = FieldAnalyzer.IK_MAX_WORD, type = FieldType.Text)
    private String name;
    /**
     * 卖点
     */
    @Field(analyzer = FieldAnalyzer.IK_MAX_WORD, type = FieldType.Text)
    private String sellPoint;
    /**
     * 描述
     */
    @Field(analyzer = FieldAnalyzer.IK_MAX_WORD, type = FieldType.Text)
    private String description;
    /**
     * 分类编号
     */
    private Integer cid;
    /**
     * 分类名
     */
    @Field(analyzer = FieldAnalyzer.IK_MAX_WORD, type = FieldType.Text)
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
