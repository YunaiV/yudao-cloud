package cn.iocoder.mall.promotion.api.rpc.coupon.dto.card;

import cn.iocoder.common.framework.vo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 优惠劵分页 DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CouponCardPageReqDTO extends PageParam {

    /**
     * 用户编号
     */
    private Integer userId;
    /**
     * 状态
     */
    private Integer status;

}
