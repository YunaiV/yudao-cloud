package cn.iocoder.mall.promotion.api.bo;

import java.util.Date;

/**
 * 商品推荐 BO
 */
public class ProductRecommendBO {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 类型
     *
     * {@link cn.iocoder.mall.promotion.api.constant.ProductRecommendType}
     */
    private Integer type;
    /**
     * 商品 Spu 编号
     */
    private Integer productSpuId;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 状态
     *
     * {@link cn.iocoder.common.framework.constant.CommonStatusEnum}
     */
    private Integer status;
    /**
     * 备注
     */
    private String memo;
    /**
     * 创建时间
     */
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public ProductRecommendBO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public ProductRecommendBO setType(Integer type) {
        this.type = type;
        return this;
    }

    public Integer getProductSpuId() {
        return productSpuId;
    }

    public ProductRecommendBO setProductSpuId(Integer productSpuId) {
        this.productSpuId = productSpuId;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public ProductRecommendBO setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public ProductRecommendBO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMemo() {
        return memo;
    }

    public ProductRecommendBO setMemo(String memo) {
        this.memo = memo;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public ProductRecommendBO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

}