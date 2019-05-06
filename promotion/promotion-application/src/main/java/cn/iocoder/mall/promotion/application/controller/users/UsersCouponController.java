package cn.iocoder.mall.promotion.application.controller.users;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.promotion.api.CouponService;
import cn.iocoder.mall.promotion.api.bo.CouponCardBO;
import cn.iocoder.mall.promotion.api.bo.CouponCardPageBO;
import cn.iocoder.mall.promotion.api.bo.CouponTemplateBO;
import cn.iocoder.mall.promotion.api.dto.CouponCardPageDTO;
import cn.iocoder.mall.promotion.application.convert.CouponCardConvert;
import cn.iocoder.mall.promotion.application.convert.CouponTemplateConvert;
import cn.iocoder.mall.promotion.application.vo.users.UsersCouponCardPageVO;
import cn.iocoder.mall.promotion.application.vo.users.UsersCouponCardVO;
import cn.iocoder.mall.promotion.application.vo.users.UsersCouponTemplateVO;
import cn.iocoder.mall.user.sdk.annotation.PermitAll;
import cn.iocoder.mall.user.sdk.context.UserSecurityContextHolder;
import org.apache.dubbo.config.annotation.Reference;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users/coupon")
@Api("优惠劵（码）模块")
public class UsersCouponController {

    @Reference(validation = "true")
    @Autowired
    private CouponService couponService;

    // ========== 优惠劵（码）模板 ==========

    @GetMapping("/template/get")
    @ApiOperation(value = "优惠劵（码）模板信息")
    @ApiImplicitParam(name = "id", value = "优惠劵（码）模板编号", required = true, example = "10")
    @PermitAll
    public CommonResult<UsersCouponTemplateVO> templateGet(@RequestParam("id") Integer id) {
        CouponTemplateBO template = couponService.getCouponTemplate(id).getData();
        return CommonResult.success(CouponTemplateConvert.INSTANCE.convert2(template));
    }

    // ========== 优惠劵 ==========

    @GetMapping("/card/page")
    @ApiOperation(value = "优惠劵分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status", value = "状态", example = "参考 CouponCardStatusEnum 枚举"),
            @ApiImplicitParam(name = "pageNo", value = "页码，从 1 开始", example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, example = "10"),
    })
    public CommonResult<UsersCouponCardPageVO> cardPage(@RequestParam(value = "status", required = false) Integer status,
                                                        @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
                                                        @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        CommonResult<CouponCardPageBO> result = couponService.getCouponCardPage(new CouponCardPageDTO()
                .setStatus(status).setUserId(UserSecurityContextHolder.getContext().getUserId())
                .setPageNo(pageNo).setPageSize(pageSize));
        return CouponCardConvert.INSTANCE.convert2(result);
    }

    @PostMapping("/card/add")
    @ApiOperation(value = "领取优惠劵")
    @ApiImplicitParam(name = "templateId", value = "优惠劵（码）模板编号", required = true, example = "10")
    public CommonResult<UsersCouponCardVO> cardAdd(@RequestParam("templateId") Integer templateId) {
        CommonResult<CouponCardBO> result = couponService.addCouponCard(UserSecurityContextHolder.getContext().getUserId(), templateId);
        return CouponCardConvert.INSTANCE.convert(result);
    }

    // ========== 优惠码 ==========


}
