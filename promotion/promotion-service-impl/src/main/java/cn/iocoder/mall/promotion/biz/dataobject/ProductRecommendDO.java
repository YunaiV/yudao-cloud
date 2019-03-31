package cn.iocoder.mall.promotion.biz.dataobject;

import cn.iocoder.common.framework.dataobject.DeletableDO;

/**
 * 商品推荐 DO
 */
public class ProductRecommendDO extends DeletableDO {

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
    // TODO 芋艿，商品 spu 名
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

    public Integer getId() {
        return id;
    }

    public ProductRecommendDO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public ProductRecommendDO setType(Integer type) {
        this.type = type;
        return this;
    }

    public Integer getProductSpuId() {
        return productSpuId;
    }

    public ProductRecommendDO setProductSpuId(Integer productSpuId) {
        this.productSpuId = productSpuId;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public ProductRecommendDO setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public ProductRecommendDO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMemo() {
        return memo;
    }

    public ProductRecommendDO setMemo(String memo) {
        this.memo = memo;
        return this;
    }

}