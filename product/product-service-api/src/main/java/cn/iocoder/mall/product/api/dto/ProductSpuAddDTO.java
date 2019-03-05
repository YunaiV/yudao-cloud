package cn.iocoder.mall.product.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 商品 SPU + SKU 添加 DTO
 */
public class ProductSpuAddDTO {

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
    @NotNull(message = "分类不能为空")
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
    private List<ProductSkuAddDTO> skus;

    public String getName() {
        return name;
    }

    public ProductSpuAddDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public ProductSpuAddDTO setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductSpuAddDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getCid() {
        return cid;
    }

    public ProductSpuAddDTO setCid(Integer cid) {
        this.cid = cid;
        return this;
    }

    public List<String> getPicUrls() {
        return picUrls;
    }

    public ProductSpuAddDTO setPicUrls(List<String> picUrls) {
        this.picUrls = picUrls;
        return this;
    }

    public Boolean getVisible() {
        return visible;
    }

    public ProductSpuAddDTO setVisible(Boolean visible) {
        this.visible = visible;
        return this;
    }

    public List<ProductSkuAddDTO> getSkus() {
        return skus;
    }

    public ProductSpuAddDTO setSkus(List<ProductSkuAddDTO> skus) {
        this.skus = skus;
        return this;
    }

}