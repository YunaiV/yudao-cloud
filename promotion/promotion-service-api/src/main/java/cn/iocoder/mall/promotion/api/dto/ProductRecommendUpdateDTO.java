package cn.iocoder.mall.promotion.api.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 商品推荐更新 DTO
 */
public class ProductRecommendUpdateDTO {

    @NotNull(message = "编号不能为空")
    private Integer id;
    @NotNull(message = "类型不能为空")
    private Integer type;
    @NotNull(message = "商品编号不能为空")
    private Integer productSpuId;
    @NotNull(message = "排序不能为空")
    private Integer sort;
    @Length(max = 255, message = "备注最大长度为 255 位")
    private String memo;

    public Integer getId() {
        return id;
    }

    public ProductRecommendUpdateDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public ProductRecommendUpdateDTO setType(Integer type) {
        this.type = type;
        return this;
    }

    public Integer getProductSpuId() {
        return productSpuId;
    }

    public ProductRecommendUpdateDTO setProductSpuId(Integer productSpuId) {
        this.productSpuId = productSpuId;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public ProductRecommendUpdateDTO setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public String getMemo() {
        return memo;
    }

    public ProductRecommendUpdateDTO setMemo(String memo) {
        this.memo = memo;
        return this;
    }

}