package cn.iocoder.mall.promotionservice.controller;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.card.*;
import cn.iocoder.mall.promotionservice.manager.coupon.CouponCardManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
 * Title:
 * Description:
 *
 * @author zhuyang
 * @version 1.0 2021/10/9
 */
@RestController
@RequestMapping("/coupon/card")
public class CouponCardController {

    @Autowired
    private CouponCardManager couponCardManager;

    @PostMapping("pageCouponCard")
    public CommonResult<PageResult<CouponCardRespDTO>> pageCouponCard(@RequestBody  CouponCardPageReqDTO pageReqDTO) {
        return success(couponCardManager.pageCouponCard(pageReqDTO));
    }

    @PostMapping("createCouponCard")
    public CommonResult<Integer> createCouponCard(@RequestBody CouponCardCreateReqDTO createReqDTO) {
        return success(couponCardManager.createCouponCard(createReqDTO));
    }

    @PostMapping("useCouponCard")
    public CommonResult<Boolean> useCouponCard(@RequestBody CouponCardUseReqDTO useReqDTO) {
        return success(couponCardManager.useCouponCard(useReqDTO));
    }

    @PostMapping("cancelUseCouponCard")
    public CommonResult<Boolean> cancelUseCouponCard(@RequestBody CouponCardCancelUseReqDTO cancelUseReqDTO) {
        return success(couponCardManager.cancelUseCouponCard(cancelUseReqDTO));
    }

    @PostMapping("listAvailableCouponCards")
    public CommonResult<List<CouponCardAvailableRespDTO>> listAvailableCouponCards(@RequestBody CouponCardAvailableListReqDTO listReqDTO) {
        return success(couponCardManager.listAvailableCouponCards(listReqDTO));
    }
}
