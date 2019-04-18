package cn.iocoder.mall.promotion.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 优惠劵商品 DTO
 *
 * 主要用于 {@link cn.iocoder.mall.promotion.api.CouponService#getCouponCardList(Integer, List)}
 */
@Data
@Accessors(chain = true)
public class CouponCardSpuDTO implements Serializable {

    /**
     * 商品 SPU 编号
     */
    private Integer spuId;
    /**
     * 商品 SKU 编号
     */
    private Integer skuId;
    /**
     * 分类编号
     */
    private Integer categoryId;
    /**
     * 价格
     */
    private Integer price;
    /**
     * 数量
     */
    private Integer quantity;

}
