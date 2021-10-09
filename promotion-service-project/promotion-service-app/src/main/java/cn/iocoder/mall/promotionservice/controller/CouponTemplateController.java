package cn.iocoder.mall.promotionservice.controller;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.template.*;
import cn.iocoder.mall.promotionservice.manager.coupon.CouponTemplateManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
 * Title:
 * Description:
 *
 * @author zhuyang
 * @version 1.0 2021/10/9
 */
@RestController
@RequestMapping("/coupon/template")
public class CouponTemplateController {

    @Autowired
    private CouponTemplateManager couponTemplateManager;

    // ========== 通用逻辑 =========

    @GetMapping("getCouponTemplate")
    public CommonResult<CouponTemplateRespDTO> getCouponTemplate(@RequestParam("couponTemplateId") Integer couponTemplateId) {
        return success(couponTemplateManager.getCouponTemplate(couponTemplateId));
    }

    @PostMapping("pageCouponTemplate")
    public CommonResult<PageResult<CouponTemplateRespDTO>> pageCouponTemplate(@RequestBody  CouponTemplatePageReqDTO pageDTO) {
        return success(couponTemplateManager.pageCouponTemplate(pageDTO));
    }

    @PostMapping("updateCouponTemplateStatus")
    public CommonResult<Boolean> updateCouponTemplateStatus(@RequestBody CouponCardTemplateUpdateStatusReqDTO updateStatusReqDTO) {
        couponTemplateManager.updateCouponTemplateStatus(updateStatusReqDTO);
        return success(true);
    }

    // ========== 优惠劵模板 ==========

    @PostMapping("createCouponCardTemplate")
    public CommonResult<Integer> createCouponCardTemplate(@RequestBody CouponCardTemplateCreateReqDTO createDTO) {
        return success(couponTemplateManager.createCouponCardTemplate(createDTO));
    }

    @PostMapping("updateCouponCardTemplate")
    public CommonResult<Boolean> updateCouponCardTemplate(@RequestBody CouponCardTemplateUpdateReqDTO updateDTO) {
        couponTemplateManager.updateCouponCardTemplate(updateDTO);
        return success(true);
    }
}
