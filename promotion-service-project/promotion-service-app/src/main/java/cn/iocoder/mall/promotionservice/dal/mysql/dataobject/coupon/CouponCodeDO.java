package cn.iocoder.mall.promotionservice.dal.mysql.dataobject.coupon;

import cn.iocoder.mall.mybatis.core.dataobject.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 优惠码
 */
@Data
@Accessors(chain = true)
public class CouponCodeDO extends BaseDO {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 模板编号 {@link CouponTemplateDO} 的 id
     */
    private Integer templateId;
    /**
     * 优惠码
     */
    private Integer code;
    /**
     * 领取时间
     */
    private Date takeTime;
    /**
     * 领取用户编号
     */
    private Integer userId;
    /**
     * 领取的优惠劵编号
     */
    private Integer couponId;

    // TODO 芋艿，后续要考虑状态的追踪

}
