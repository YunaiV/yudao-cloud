package cn.iocoder.mall.promotionservice.service.coupon;

import cn.iocoder.mall.promotion.api.rpc.coupon.dto.template.CouponTemplateRespDTO;
import cn.iocoder.mall.promotionservice.convert.coupon.card.CouponTemplateConvert;
import cn.iocoder.mall.promotionservice.dal.mysql.mapper.coupon.CouponTemplateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * 优惠劵模板 Service
 */
@Service
@Validated
public class CouponTemplateService {

    @Autowired
    private CouponTemplateMapper couponTemplateMapper;

    public CouponTemplateRespDTO getCouponTemplate(Integer couponCardId) {
        return CouponTemplateConvert.INSTANCE.convert(couponTemplateMapper.selectById(couponCardId));
    }

}
