package cn.iocoder.mall.product.api.bo;

import java.util.List;

public class ProductSpuBO {

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

    // ========== Sku 相关字段 =========
    /**
     * 价格
     *
     * 目前的计算方式是，以 Sku 最小价格为准
     */
    private Integer price;
    /**
     * 库存数量
     *
     * 目前的计算方式是，以 Sku 库存累加为准
     */
    private Integer quantity;

    public Integer getId() {
        return id;
    }

    public ProductSpuBO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductSpuBO setName(String name) {
        this.name = name;
        return this;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public ProductSpuBO setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductSpuBO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getCid() {
        return cid;
    }

    public ProductSpuBO setCid(Integer cid) {
        this.cid = cid;
        return this;
    }

    public List<String> getPicUrls() {
        return picUrls;
    }

    public ProductSpuBO setPicUrls(List<String> picUrls) {
        this.picUrls = picUrls;
        return this;
    }

    public Boolean getVisible() {
        return visible;
    }

    public ProductSpuBO setVisible(Boolean visible) {
        this.visible = visible;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public ProductSpuBO setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public ProductSpuBO setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public ProductSpuBO setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }
}