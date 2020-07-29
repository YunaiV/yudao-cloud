package cn.iocoder.mall.promotionservice.service.coupon.bo;

import cn.iocoder.mall.promotion.api.rpc.coupon.CouponRpc;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 优惠劵商品 DTO
 *
 * 主要用于 {@link CouponRpc#getCouponCardList(Integer, List)}
 */
@Data
@Accessors(chain = true)
public class CouponCardSpuBO implements Serializable {

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
