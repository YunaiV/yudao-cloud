package cn.iocoder.mall.promotion.api.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 商品推荐添加 DTO
 */
public class ProductRecommendAddDTO {

    @NotNull(message = "推荐类型不能为空")
    private Integer type;
    @NotNull(message = "商品编号不能为空")
    private Integer productSpuId;
    @NotNull(message = "排序不能为空")
    private Integer sort;
    @Length(max = 255, message = "备注最大长度为 255 位")
    private String memo;

    public Integer getType() {
        return type;
    }

    public ProductRecommendAddDTO setType(Integer type) {
        this.type = type;
        return this;
    }

    public Integer getProductSpuId() {
        return productSpuId;
    }

    public ProductRecommendAddDTO setProductSpuId(Integer productSpuId) {
        this.productSpuId = productSpuId;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public ProductRecommendAddDTO setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public String getMemo() {
        return memo;
    }

    public ProductRecommendAddDTO setMemo(String memo) {
        this.memo = memo;
        return this;
    }

}