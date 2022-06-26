package cn.iocoder.mall.promotion.api.rpc.coupon;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.card.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Title:
 * Description:
 *
 * @author zhuyang
 * @version 1.0 2021/10/9
 */
@FeignClient("promotion-service")
public interface CouponCardFeign {
    @PostMapping("/coupon/card/pageCouponCard")
    public CommonResult<PageResult<CouponCardRespDTO>> pageCouponCard(@RequestBody CouponCardPageReqDTO pageReqDTO) ;

    @PostMapping("/coupon/card/createCouponCard")
    public CommonResult<Integer> createCouponCard(@RequestBody CouponCardCreateReqDTO createReqDTO);

    @PostMapping("/coupon/card/useCouponCard")
    public CommonResult<Boolean> useCouponCard(@RequestBody CouponCardUseReqDTO useReqDTO) ;

    @PostMapping("/coupon/card/cancelUseCouponCard")
    public CommonResult<Boolean> cancelUseCouponCard(@RequestBody CouponCardCancelUseReqDTO cancelUseReqDTO) ;

    @PostMapping("/coupon/card/listAvailableCouponCards")
    public CommonResult<List<CouponCardAvailableRespDTO>> listAvailableCouponCards(@RequestBody CouponCardAvailableListReqDTO listReqDTO);
}
