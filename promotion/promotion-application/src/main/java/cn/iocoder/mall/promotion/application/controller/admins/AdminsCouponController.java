package cn.iocoder.mall.promotion.application.controller.admins;

import cn.iocoder.common.framework.util.DateUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.system.sdk.context.AdminSecurityContextHolder;
import cn.iocoder.mall.promotion.api.CouponService;
import cn.iocoder.mall.promotion.api.bo.CouponTemplateBO;
import cn.iocoder.mall.promotion.api.bo.CouponTemplatePageBO;
import cn.iocoder.mall.promotion.api.dto.CouponCardTemplateAddDTO;
import cn.iocoder.mall.promotion.api.dto.CouponCardTemplateUpdateDTO;
import cn.iocoder.mall.promotion.api.dto.CouponTemplatePageDTO;
import cn.iocoder.mall.promotion.application.convert.CouponTemplateConvert;
import cn.iocoder.mall.promotion.application.vo.admins.AdminsCouponTemplatePageVO;
import cn.iocoder.mall.promotion.application.vo.admins.AdminsCouponTemplateVO;
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

    @PostMapping("/template/add_card")
    @ApiOperation(value = "创建优惠劵模板")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "标题", required = true, example = "优惠劵牛逼"),
            @ApiImplicitParam(name = "description", value = "使用说明", example = "我只是描述"),
            @ApiImplicitParam(name = "quota", value = "每人限领个数", required = true),
            @ApiImplicitParam(name = "total", value = "发行总量"),
            @ApiImplicitParam(name = "priceAvailable", value = "是否设置满多少金额可用，单位：分", example = "0-不限制；大于0-多少金额可用"),
            @ApiImplicitParam(name = "rangeType", value = "可用范围的类型", required = true, example = "参见 CouponTemplateRangeTypeEnum 枚举"),
            @ApiImplicitParam(name = "rangeValues", value = "指定商品 / 分类列表，使用逗号分隔商品编号"),
            @ApiImplicitParam(name = "dateType", value = "生效日期类型", example = "参见 CouponTemplateDateTypeEnum 枚举"),
            @ApiImplicitParam(name = "validStartTime", value = "固定日期-生效开始时间", example = "当 dateType 为固定日期时，非空"),
            @ApiImplicitParam(name = "validEndTime", value = "固定日期-生效结束时间", example = "当 dateType 为固定日期时，非空"),
            @ApiImplicitParam(name = "fixedBeginTerm", value = "领取日期-开始天数", example = "当 dateType 为领取日期时，非空"),
            @ApiImplicitParam(name = "fixedEndTerm", value = "领取日期-结束天数", example = "当 dateType 为领取日期时，非空"),
            @ApiImplicitParam(name = "preferentialType", value = "优惠类型", example = "参见 CouponTemplatePreferentialTypeEnum 枚举"),
            @ApiImplicitParam(name = "priceOff", value = "优惠金额，单位：分", example = "当 preferentialType 为现金券时，非空"),
            @ApiImplicitParam(name = "percentOff", value = "折扣百分比", example = "当 preferentialType 为折扣卷时，非空"),
            @ApiImplicitParam(name = "discountPriceLimit", value = "折扣上限", example = "当 preferentialType 为折扣卷时，非空"),
    })
    public CommonResult<AdminsCouponTemplateVO> templateCardAdd(@RequestParam(value = "title") String title,
                                                                @RequestParam(value = "description", required = false) String description,
                                                                @RequestParam(value = "quota") Integer quota,
                                                                @RequestParam(value = "total", required = false) Integer total,
                                                                @RequestParam(value = "priceAvailable") Integer priceAvailable,
                                                                @RequestParam(value = "rangeType") Integer rangeType,
                                                                @RequestParam(value = "rangeValues", required = false) String rangeValues,
                                                                @RequestParam(value = "dateType") Integer dateType,
                                                                @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                    @RequestParam(value = "validStartTime", required = false) Date validStartTime,
                                                                @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                    @RequestParam(value = "validEndTime", required = false) Date validEndTime,
                                                                @RequestParam(value = "fixedBeginTerm", required = false) Integer fixedBeginTerm,
                                                                @RequestParam(value = "fixedEndTerm", required = false) Integer fixedEndTerm,
                                                                @RequestParam(value = "preferentialType") Integer preferentialType,
                                                                @RequestParam(value = "priceOff", required = false) Integer priceOff,
                                                                @RequestParam(value = "percentOff", required = false) Integer percentOff,
                                                                @RequestParam(value = "discountPriceLimit", required = false) Integer discountPriceLimit) {
        // 创建 CouponCardTemplateAddDTO 对象
        validStartTime = DateUtil.getDayBegin(validStartTime); // 开始时间，以当前 00:00:00 为准
        validEndTime = DateUtil.getDayBegin(validEndTime); // 结束时间，以当前 25:59:59 为准
        CouponCardTemplateAddDTO couponCardTemplateAddDTO = new CouponCardTemplateAddDTO()
                .setTitle(title).setDescription(description)
                .setQuota(quota).setTotal(total)
                .setPriceAvailable(priceAvailable).setRangeType(rangeType).setRangeValues(rangeValues)
                .setDateType(dateType).setValidStartTime(validStartTime).setValidEndTime(validEndTime)
                .setFixedBeginTerm(fixedBeginTerm).setFixedEndTerm(fixedEndTerm)
                .setPreferentialType(preferentialType).setPriceOff(priceOff).setPercentOff(percentOff).setDiscountPriceLimit(discountPriceLimit);
        // 提交请求
        CouponTemplateBO result = couponService.addCouponCardTemplate(couponCardTemplateAddDTO);
        // 返回结果
        return success(CouponTemplateConvert.ADMINS.convert(result));
    }

    @PostMapping("/template/update_card")
    @ApiOperation(value = "更新优惠劵模板")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1"),
            @ApiImplicitParam(name = "title", value = "标题", required = true, example = "优惠劵牛逼"),
            @ApiImplicitParam(name = "description", value = "使用说明", example = "我只是描述"),
            @ApiImplicitParam(name = "quota", value = "每人限领个数", required = true),
            @ApiImplicitParam(name = "total", value = "发行总量"),
            @ApiImplicitParam(name = "rangeType", value = "可用范围的类型", required = true, example = "参见 CouponTemplateRangeTypeEnum 枚举"),
            @ApiImplicitParam(name = "rangeValues", value = "指定商品 / 分类列表，使用逗号分隔商品编号"),
    })
    public CommonResult<Boolean> templateCardUpdate(@RequestParam(value = "id") Integer id,
                                                    @RequestParam(value = "title") String title,
                                                    @RequestParam(value = "description", required = false) String description,
                                                    @RequestParam(value = "quota") Integer quota,
                                                    @RequestParam(value = "total", required = false) Integer total,
                                                    @RequestParam(value = "rangeType") Integer rangeType,
                                                    @RequestParam(value = "rangeValues", required = false) String rangeValues) {
        // 创建 CouponCardTemplateAddDTO 对象
        CouponCardTemplateUpdateDTO couponCardTemplateUpdateDTO = new CouponCardTemplateUpdateDTO()
                .setId(id)
                .setTitle(title).setDescription(description)
                .setQuota(quota).setTotal(total)
                .setRangeType(rangeType).setRangeValues(rangeValues);
        return success(couponService.updateCouponCardTemplate(couponCardTemplateUpdateDTO));
    }

    @PostMapping("/template/update_status")
    @ApiOperation(value = "更新优惠劵（码）模板状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Banner 编号", required = true, example = "1"),
            @ApiImplicitParam(name = "status", value = "状态。1 - 开启；2 - 禁用", required = true, example = "1"),
    })
    public CommonResult<Boolean> templateUpdateStatus(@RequestParam("id") Integer id,
                                                      @RequestParam("status") Integer status) {
        return success(couponService.updateCouponTemplateStatus(AdminSecurityContextHolder.getContext().getAdminId(), id, status));
    }

    // ========== 优惠劵 ==========

    // ========== 优惠码 ==========

}
