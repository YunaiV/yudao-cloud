package cn.iocoder.mall.product.api.bo;

import java.util.List;

/**
 * 商品 Spu 明细 BO（包括 Sku 明细）
 */
public class ProductSpuDetailBO {

    /**
     * SPU 编号
     */
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
     * 商品主图地址
     *
     * 数组，以逗号分隔
     *
     * 建议尺寸：800*800像素，你可以拖拽图片调整顺序，最多上传15张
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

    // ========== SKU =========

    /**
     * SKU 数组
     */
    private List<ProductSkuDetailBO> skus;

    public Integer getId() {
        return id;
    }

    public ProductSpuDetailBO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductSpuDetailBO setName(String name) {
        this.name = name;
        return this;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public ProductSpuDetailBO setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductSpuDetailBO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getCid() {
        return cid;
    }

    public ProductSpuDetailBO setCid(Integer cid) {
        this.cid = cid;
        return this;
    }

    public List<String> getPicUrls() {
        return picUrls;
    }

    public ProductSpuDetailBO setPicUrls(List<String> picUrls) {
        this.picUrls = picUrls;
        return this;
    }

    public Boolean getVisible() {
        return visible;
    }

    public ProductSpuDetailBO setVisible(Boolean visible) {
        this.visible = visible;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public ProductSpuDetailBO setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public List<ProductSkuDetailBO> getSkus() {
        return skus;
    }

    public ProductSpuDetailBO setSkus(List<ProductSkuDetailBO> skus) {
        this.skus = skus;
        return this;
    }

}