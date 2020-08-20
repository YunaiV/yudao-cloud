package cn.iocoder.mall.promotion.api.rpc.coupon.dto.card;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 可用优惠劵 BO
 *
 * 注意，如果优惠劵不可用，标记 available = false ，并写明 unavailableReason 原因
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CouponCardAvailableRespDTO extends CouponCardRespDTO {

    /**
     * 是否可用
     */
    private Boolean available;
    /**
     * 不可用原因
     */
    private String unavailableReason;

}
