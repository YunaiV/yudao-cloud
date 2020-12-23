package cn.iocoder.mall.promotionservice.rpc.coupon;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.promotion.api.rpc.coupon.CouponCardRpc;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.card.*;
import cn.iocoder.mall.promotionservice.manager.coupon.CouponCardManager;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@DubboService
public class CouponCardRpcImpl implements CouponCardRpc {

    @Autowired
    private CouponCardManager couponCardManager;

    @Override
    public CommonResult<PageResult<CouponCardRespDTO>> pageCouponCard(CouponCardPageReqDTO pageReqDTO) {
        return success(couponCardManager.pageCouponCard(pageReqDTO));
    }

    @Override
    public CommonResult<Integer> createCouponCard(CouponCardCreateReqDTO createReqDTO) {
        return success(couponCardManager.createCouponCard(createReqDTO));
    }

    @Override
    public CommonResult<Boolean> useCouponCard(CouponCardUseReqDTO useReqDTO) {
        return success(couponCardManager.useCouponCard(useReqDTO));
    }

    @Override
    public CommonResult<Boolean> cancelUseCouponCard(CouponCardCancelUseReqDTO cancelUseReqDTO) {
        return success(couponCardManager.cancelUseCouponCard(cancelUseReqDTO));
    }

    @Override
    public CommonResult<List<CouponCardAvailableRespDTO>> listAvailableCouponCards(CouponCardAvailableListReqDTO listReqDTO) {
        return success(couponCardManager.listAvailableCouponCards(listReqDTO));
    }

}
