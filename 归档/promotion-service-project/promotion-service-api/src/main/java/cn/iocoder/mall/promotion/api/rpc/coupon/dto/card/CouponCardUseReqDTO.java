package cn.iocoder.mall.promotion.api.rpc.coupon.dto.card;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 优惠劵使用 Request DTO
 */
@Data
@Accessors(chain = true)
public class CouponCardUseReqDTO implements Serializable {

    /**
     * 用户编号
     */
    @NotNull(message = "用户编号不能为空")
    private Integer userId;
    /**
     * 优惠劵编号
     */
    @NotNull(message = "优惠劵编号不能为空")
    private Integer couponCardId;

}
