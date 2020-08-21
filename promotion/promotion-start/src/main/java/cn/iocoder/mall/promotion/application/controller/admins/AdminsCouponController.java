package cn.iocoder.mall.promotion.application.controller.admins;

import cn.iocoder.common.framework.util.DateUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.promotion.api.CouponService;
import cn.iocoder.mall.promotion.api.bo.CouponTemplateBO;
import cn.iocoder.mall.promotion.api.bo.CouponTemplatePageBO;
import cn.iocoder.mall.promotion.api.dto.CouponCardTemplateAddDTO;
import cn.iocoder.mall.promotion.api.dto.CouponCardTemplateUpdateDTO;
import cn.iocoder.mall.promotion.api.dto.CouponTemplatePageDTO;
import cn.iocoder.mall.promotion.application.convert.CouponTemplateConvert;
import cn.iocoder.mall.promotion.application.vo.admins.AdminsCouponTemplatePageVO;
import cn.iocoder.mall.promotion.application.vo.admins.AdminsCouponTemplateVO;
import cn.iocoder.mall.security.core.context.AdminSecurityContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@RestController
@RequestMapping("admins/coupon")
@Api("优惠劵（码）模块")
public class AdminsCouponController {

    @Reference(validation = "true", version = "${dubbo.provider.CouponService.version}")
    private CouponService couponService;

    // ========== 优惠劵（码）模板 ==========

    @GetMapping("/template/page")
    @ApiOperation(value = "优惠劵（码）模板分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型", example = "参考 CouponTemplateTypeEnum 枚举"),
            @ApiImplicitParam(name = "title", value = "标题，模糊匹配", example = "活动 A"),
            @ApiImplicitParam(name = "status", value = "状态", example = "参考 CouponTemplateStatusEnum 枚举"),
            @ApiImplicitParam(name = "preferentialType", value = "优惠类型", example = "参考 CouponTemplatePreferentialTypeEnum 枚举"),
            @ApiImplicitParam(name = "pageNo", value = "页码，从 1 开始", example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, example = "10"),
    })
    public CommonResult<AdminsCouponTemplatePageVO> templatePage(@RequestParam(value = "type", required = false) Integer type,
                                                                 @RequestParam(value = "title", required = false) String title,
                                                                 @RequestParam(value = "status", required = false) Integer status,
                                                                 @RequestParam(value = "preferentialType", required = false) Integer preferentialType,
                                                                 @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
                                                                 @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        CouponTemplatePageBO result = couponService.getCouponTemplatePage(new CouponTemplatePageDTO()
                .setType(type).setTitle(title).setStatus(status).setPreferentialType(preferentialType)
                .setPageNo(pageNo).setPageSize(pageSize));
        return success(CouponTemplateConvert.ADMINS.convertPage(result));
    }

}
