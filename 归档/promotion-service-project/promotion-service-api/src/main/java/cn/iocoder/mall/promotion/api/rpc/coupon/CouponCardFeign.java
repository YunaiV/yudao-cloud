package cn.iocoder.mall.promotion.api.rpc.coupon;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.card.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("promotion-service")
public interface CouponCardFeign {

    @PostMapping("/coupon/card/pageCouponCard")
    public CommonResult<PageResult<CouponCardRespDTO>> pageCouponCard(@RequestBody CouponCardPageReqDTO pageReqDTO) ;

    @PostMapping("/coupon/card/createCouponCard")
    public CommonResult<Integer> createCouponCard(@RequestBody CouponCardCreateReqDTO createReqDTO);

    @PostMapping("/coupon/card/cancelUseCouponCard")
    public CommonResult<Boolean> cancelUseCouponCard(@RequestBody CouponCardCancelUseReqDTO cancelUseReqDTO) ;

}
