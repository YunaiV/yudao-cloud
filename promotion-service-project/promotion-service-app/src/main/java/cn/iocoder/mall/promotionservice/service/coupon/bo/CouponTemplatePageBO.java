package cn.iocoder.mall.promotionservice.service.coupon.bo;

import cn.iocoder.mall.promotion.api.rpc.coupon.dto.CouponTemplateReqDTO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 优惠劵（码）模板分页 BO
 */
@Data
@Accessors(chain = true)
public class CouponTemplatePageBO implements Serializable {

    /**
     * 优惠劵（码）数组
     */
    private List<CouponTemplateReqDTO> list;
    /**
     * 总量
     */
    private Integer total;

}
