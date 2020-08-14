package cn.iocoder.mall.promotionservice.service.coupon;

import cn.iocoder.mall.promotion.api.rpc.coupon.dto.card.CouponCardRespDTO;
import cn.iocoder.mall.promotionservice.convert.coupon.card.CouponCardConvert;
import cn.iocoder.mall.promotionservice.dal.mysql.dataobject.coupon.CouponCardDO;
import cn.iocoder.mall.promotionservice.dal.mysql.mapper.coupon.CouponCardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * 优惠劵 Service
 */
@Service
@Validated
public class CouponCardService {

    @Autowired
    private CouponCardMapper couponCardMapper;

    public CouponCardRespDTO getCouponCard(Integer userId, Integer couponCardId) {
        CouponCardDO card = couponCardMapper.selectById(couponCardId);
        if (card == null) {
            return null;
        }
        if (!Objects.equals(userId, card.getUserId())) {
            return null;
        }
        return CouponCardConvert.INSTANCE.convert(card);
    }

}
