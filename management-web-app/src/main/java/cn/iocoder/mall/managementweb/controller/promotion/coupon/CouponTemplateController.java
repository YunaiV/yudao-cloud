package cn.iocoder.mall.managementweb.controller.promotion.coupon;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.promotion.coupon.vo.template.CouponTemplateCardCreateReqVO;
import cn.iocoder.mall.managementweb.controller.promotion.coupon.vo.template.CouponTemplateCardUpdateReqVO;
import cn.iocoder.mall.managementweb.controller.promotion.coupon.vo.template.CouponTemplatePageReqVO;
import cn.iocoder.mall.managementweb.controller.promotion.coupon.vo.template.CouponTemplateRespVO;
import cn.iocoder.mall.managementweb.manager.promotion.coupon.CouponTemplateManager;
import cn.iocoder.security.annotations.RequiresPermissions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@RestController
@RequestMapping("/promotion/coupon-template")
@Api(tags = "优惠劵（码）模板 API")
@Validated
public class CouponTemplateController {

    @Autowired
    private CouponTemplateManager couponTemplateManager;

    // ========== 通用逻辑 =========

    @GetMapping("/page")
    @ApiOperation("获得优惠劵模板分页")
    @RequiresPermissions("promotion:coupon-template:page")
    public CommonResult<PageResult<CouponTemplateRespVO>> pageCouponTemplate(CouponTemplatePageReqVO pageVO) {
        return success(couponTemplateManager.pageCouponTemplate(pageVO));
    }

    @PostMapping("/update-status")
    @ApiOperation("更新优惠劵（码）模板的状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "优惠劵（码）模板编号", required = true, example = "1"),
            @ApiImplicitParam(name = "status", value = "状态。1 - 开启；2 - 禁用", required = true, example = "1"),
    })
    @RequiresPermissions("promotion:coupon-template:update-status")
    public CommonResult<Boolean> updateCouponTemplateStatus(@RequestParam("id") Integer id,
                                                            @RequestParam("status") Integer status) {
        couponTemplateManager.updateCouponTemplateStatus(id, status);
        return success(true);
    }

    // ========== 优惠劵模板 ==========

    @PostMapping("/create-card")
    @ApiOperation("创建优惠劵模板")
    @RequiresPermissions("promotion:coupon-template:create-card")
    public CommonResult<Integer> createCouponCardTemplate(@Valid CouponTemplateCardCreateReqVO createVO) {
        return success(couponTemplateManager.createCouponCardTemplate(createVO));
    }

    @PostMapping("/update-card")
    @ApiOperation("更新优惠劵模板")
    @RequiresPermissions("promotion:coupon-template:update-card")
    public CommonResult<Boolean> updateCouponCardTemplate(@Valid CouponTemplateCardUpdateReqVO updateVO) {
        couponTemplateManager.updateCouponCardTemplate(updateVO);
        return success(true);
    }

}
