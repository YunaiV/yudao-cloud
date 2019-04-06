package cn.iocoder.mall.promotion.application.controller.users;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.promotion.api.CouponService;
import cn.iocoder.mall.promotion.api.bo.CouponCardBO;
import cn.iocoder.mall.promotion.api.bo.CouponTemplateBO;
import cn.iocoder.mall.promotion.application.convert.CouponCardConvert;
import cn.iocoder.mall.promotion.application.convert.CouponTemplateConvert;
import cn.iocoder.mall.promotion.application.vo.users.UsersCouponCardVO;
import cn.iocoder.mall.promotion.application.vo.users.UsersCouponTemplateVO;
import cn.iocoder.mall.user.sdk.context.UserSecurityContextHolder;
import com.alibaba.dubbo.config.annotation.Reference;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admins/coupon")
@Api("优惠劵（码）模块")
public class UsersCouponController {

    @Reference(validation = "true")
    private CouponService couponService;

    // ========== 优惠劵（码）模板 ==========

    @GetMapping("/template/get")
    @ApiOperation(value = "优惠劵（码）模板信息")
    @ApiImplicitParam(name = "id", value = "优惠劵（码）模板编号", required = true, example = "10")
    public CommonResult<UsersCouponTemplateVO> templateGet(@RequestParam("id") Integer id) {
        CouponTemplateBO template = couponService.getCouponTemplate(id).getData();
        return CommonResult.success(CouponTemplateConvert.INSTANCE.convert2(template));
    }

    // ========== 优惠劵 ==========

    @GetMapping("/card/add")
    @ApiOperation(value = "领取优惠劵")
    @ApiImplicitParam(name = "templateId", value = "优惠劵（码）模板编号", required = true, example = "10")
    public CommonResult<UsersCouponCardVO> cardAdd(@RequestParam("templateId") Integer templateId) {
        CommonResult<CouponCardBO> result = couponService.addCouponCard(UserSecurityContextHolder.getContext().getUserId(), templateId);
        return CouponCardConvert.INSTANCE.convert(result);
    }

    // ========== 优惠码 ==========


}
