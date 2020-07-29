package cn.iocoder.mall.promotionservice.service.coupon.bo;

import cn.iocoder.mall.promotion.api.rpc.coupon.dto.CouponCardReqDTO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 优惠劵分页 BO
 */
@Data
@Accessors(chain = true)
public class CouponCardPageBO implements Serializable {

    /**
     * 优惠劵数组
     */
    private List<CouponCardReqDTO> list;
    /**
     * 总量
     */
    private Integer total;

}
