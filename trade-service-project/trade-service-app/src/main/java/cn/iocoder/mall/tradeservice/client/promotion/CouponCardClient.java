package cn.iocoder.mall.tradeservice.client.promotion;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.promotion.api.rpc.coupon.CouponCardRpc;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.card.CouponCardUseReqDTO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class CouponCardClient {

    @DubboReference(version = "${dubbo.consumer.CouponCardRpc.version}")
    private CouponCardRpc couponCardRpc;

    public void useCouponCard(Integer userId, Integer couponCardId) {
        CommonResult<Boolean> useCouponCardResult = couponCardRpc.useCouponCard(new CouponCardUseReqDTO()
            .setUserId(userId).setCouponCardId(couponCardId));
        useCouponCardResult.checkError();
    }

}
