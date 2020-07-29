package cn.iocoder.mall.promotionservice.service.coupon.bo;

import cn.iocoder.mall.promotion.api.rpc.coupon.dto.CouponCardReqDTO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 可用优惠劵 BO
 *
 * 注意，如果优惠劵不可用，标记 available = false ，并写明 unavailableReason 原因
 */
@Data
@Accessors(chain = true)
public class CouponCardAvailableBO extends CouponCardReqDTO {

    /**
     * 是否可用
     */
    private Boolean available;
    /**
     * 不可用原因
     */
    private String unavailableReason;

}
