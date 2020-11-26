package cn.iocoder.mall.shopweb.controller.promotion;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.security.user.core.context.UserSecurityContextHolder;
import cn.iocoder.mall.shopweb.controller.promotion.vo.coupon.card.CouponCardPageReqVO;
import cn.iocoder.mall.shopweb.controller.promotion.vo.coupon.card.CouponCardRespVO;
import cn.iocoder.mall.shopweb.service.promotion.CouponCardManager;
import cn.iocoder.security.annotations.RequiresAuthenticate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@RestController
@RequestMapping("/promotion/coupon-card")
@Api(tags = "优惠劵 API")
@Validated
public class CouponCardController {

    @Autowired
    private CouponCardManager couponCardManager;

    @GetMapping("/page")
    @ApiOperation("获得优惠劵分页")
    @RequiresAuthenticate
    public CommonResult<PageResult<CouponCardRespVO>> pageCouponCard(CouponCardPageReqVO pageVO) {
        return success(couponCardManager.pageCouponCard(UserSecurityContextHolder.getUserId(),pageVO));
    }

    @PostMapping("/create")
    @ApiOperation("用户领取优惠劵")
    @ApiImplicitParam(name = "couponTemplateId", value = "优惠劵模板编号", required = true, example = "1024")
    @RequiresAuthenticate
    public CommonResult<Integer> createCouponCard(@RequestParam("couponTemplateId") Integer couponTemplateId) {
        return success(couponCardManager.createCouponCard(UserSecurityContextHolder.getUserId(), couponTemplateId));
    }

}
