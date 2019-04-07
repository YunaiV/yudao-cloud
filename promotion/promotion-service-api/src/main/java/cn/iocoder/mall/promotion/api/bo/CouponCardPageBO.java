package cn.iocoder.mall.promotion.api.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 优惠劵分页 BO
 */
@Data
@Accessors(chain = true)
public class CouponCardPageBO {

    /**
     * 优惠劵数组
     */
    private List<CouponCardBO> list;
    /**
     * 总量
     */
    private Integer total;

}
