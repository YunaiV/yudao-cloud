package cn.iocoder.mall.tradeservice.client.promotion;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.promotion.api.rpc.coupon.CouponCardFeign;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.card.CouponCardUseReqDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CouponCardClient {

    @Autowired
    private CouponCardFeign couponCardFeign;
    public void useCouponCard(Integer userId, Integer couponCardId) {
        CommonResult<Boolean> useCouponCardResult = couponCardFeign.useCouponCard(new CouponCardUseReqDTO()
            .setUserId(userId).setCouponCardId(couponCardId));
        useCouponCardResult.checkError();
    }

}
