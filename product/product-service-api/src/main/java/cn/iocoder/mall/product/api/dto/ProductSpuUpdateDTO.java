package cn.iocoder.mall.product.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 商品 SPU + SKU 更新 DTO
 */
public class ProductSpuUpdateDTO {

    /**
     * Spu 编号
     */
    @NotNull(message = "SPU 编号不能为空")
    private Integer id;

    // ========== 基本信息 =========
    /**
     * SPU 名字
     */
    @NotEmpty(message = "SPU 名字不能为空")
    private String name;
    /**
     * 卖点
     */
    @NotEmpty(message = "卖点不能为空")
    private String sellPoint;
    /**
     * 描述
     */
    @NotEmpty(message = "描述不能为空")
    private String description;
    /**
     * 分类编号
     */
    @NotEmpty(message = "分类不能为空")
    private Integer cid;
    /**
     * 商品主图地址
     */
    @NotNull(message = "商品主图不能为空")
    private List<String> picUrls;

    // ========== 其他信息 =========
    /**
     * 是否上架商品（是否可见）。
     *
     * true 为已上架
     * false 为已下架
     */
    @NotNull(message = "是否上架不能为空")
    private Boolean visible;

    // ========== SKU =========

    /**
     * SKU 数组
     */
    @NotNull(message = "SKU 不能为空")
    private List<ProductSkuUpdateDTO> skus;

    public String getName() {
        return name;
    }

    public ProductSpuUpdateDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public ProductSpuUpdateDTO setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductSpuUpdateDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getCid() {
        return cid;
    }

    public ProductSpuUpdateDTO setCid(Integer cid) {
        this.cid = cid;
        return this;
    }

    public List<String> getPicUrls() {
        return picUrls;
    }

    public ProductSpuUpdateDTO setPicUrls(List<String> picUrls) {
        this.picUrls = picUrls;
        return this;
    }

    public Boolean getVisible() {
        return visible;
    }

    public ProductSpuUpdateDTO setVisible(Boolean visible) {
        this.visible = visible;
        return this;
    }

    public List<ProductSkuUpdateDTO> getSkus() {
        return skus;
    }

    public ProductSpuUpdateDTO setSkus(List<ProductSkuUpdateDTO> skus) {
        this.skus = skus;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public ProductSpuUpdateDTO setId(Integer id) {
        this.id = id;
        return this;
    }
}