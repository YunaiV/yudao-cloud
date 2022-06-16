package cn.iocoder.mall.promotion.api.rpc.coupon;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.template.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Title:
 * Description:
 *
 * @author zhuyang
 * @version 1.0 2021/10/9
 */
@FeignClient("promotion-service")
public interface CouponTemplateFeign {
    // ========== 通用逻辑 =========

    @GetMapping("/coupon/template/getCouponTemplate")
    public CommonResult<CouponTemplateRespDTO> getCouponTemplate(@RequestParam("couponTemplateId") Integer couponTemplateId);

    @PostMapping("/coupon/template/pageCouponTemplate")
    public CommonResult<PageResult<CouponTemplateRespDTO>> pageCouponTemplate(@RequestBody CouponTemplatePageReqDTO pageDTO);

    @PostMapping("/coupon/template/updateCouponTemplateStatus")
    public CommonResult<Boolean> updateCouponTemplateStatus(@RequestBody CouponCardTemplateUpdateStatusReqDTO updateStatusReqDTO) ;

    // ========== 优惠劵模板 ==========

    @PostMapping("/coupon/template/createCouponCardTemplate")
    public CommonResult<Integer> createCouponCardTemplate(@RequestBody CouponCardTemplateCreateReqDTO createDTO) ;

    @PostMapping("/coupon/template/updateCouponCardTemplate")
    public CommonResult<Boolean> updateCouponCardTemplate(@RequestBody CouponCardTemplateUpdateReqDTO updateDTO) ;
}
