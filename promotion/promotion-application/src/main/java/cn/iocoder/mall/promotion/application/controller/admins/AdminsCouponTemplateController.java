package cn.iocoder.mall.promotion.application.controller.admins;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.promotion.api.CouponService;
import cn.iocoder.mall.promotion.api.bo.CouponTemplateBO;
import cn.iocoder.mall.promotion.api.dto.CouponCardTemplateAddDTO;
import cn.iocoder.mall.promotion.application.convert.CouponTemplateConvert;
import cn.iocoder.mall.promotion.application.vo.admins.AdminsCouponTemplateVO;
import com.alibaba.dubbo.config.annotation.Reference;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("admins/product_recommend")
@Api("优惠劵（码）模块")
public class AdminsCouponTemplateController {

    @Reference(validation = "true")
    private CouponService couponService;

    // ========== 优惠劵（码）模板 ==========

    @PostMapping("/template/add_card")
    @ApiOperation(value = "创建优惠劵模板")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "标题", required = true, example = "优惠劵牛逼"),
            @ApiImplicitParam(name = "description", value = "使用说明", example = "我只是描述"),
            @ApiImplicitParam(name = "quota", value = "每人限领个数", example = "null - 则表示不限制"),
            @ApiImplicitParam(name = "stock", value = "剩余可用库存", example = "null - 则表示无限库存"),
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
    public CommonResult<AdminsCouponTemplateVO> add(@RequestParam(value = "title") String title,
                                                    @RequestParam(value = "description", required = false) String description,
                                                    @RequestParam(value = "quota", required = false) Integer quota,
                                                    @RequestParam(value = "stock", required = false) Integer stock,
                                                    @RequestParam(value = "priceAvailable") Integer priceAvailable,
                                                    @RequestParam(value = "rangeType") Integer rangeType,
                                                    @RequestParam(value = "rangeType", required = false) String rangeValues,
                                                    @RequestParam(value = "dateType") Integer dateType,
                                                    @RequestParam(value = "validStartTime", required = false) Date validStartTime,
                                                    @RequestParam(value = "validEndTime", required = false) Date validEndTime,
                                                    @RequestParam(value = "fixedBeginTerm", required = false) Integer fixedBeginTerm,
                                                    @RequestParam(value = "fixedEndTerm", required = false) Integer fixedEndTerm,
                                                    @RequestParam(value = "preferentialType") Integer preferentialType,
                                                    @RequestParam(value = "priceOff", required = false) Integer priceOff,
                                                    @RequestParam(value = "percentOff", required = false) Integer percentOff,
                                                    @RequestParam(value = "discountPriceLimit", required = false) Integer discountPriceLimit) {
        // 创建 CouponCardTemplateAddDTO 对象
        CouponCardTemplateAddDTO couponCardTemplateAddDTO = new CouponCardTemplateAddDTO()
                .setTitle(title).setDescription(description)
                .setQuota(quota).setStock(stock)
                .setPriceAvailable(priceAvailable).setRangeType(rangeType).setRangeValues(rangeValues)
                .setDateType(dateType).setValidStartTime(validStartTime).setValidEndTime(validEndTime)
                .setFixedBeginTerm(fixedBeginTerm).setFixedEndTerm(fixedEndTerm)
                .setPreferentialType(preferentialType).setPriceOff(priceOff).setPercentOff(percentOff).setDiscountPriceLimit(discountPriceLimit);
        // 提交请求
        CommonResult<CouponTemplateBO> result = couponService.addCouponCardTemplate(couponCardTemplateAddDTO);
        // 返回结果
        return CouponTemplateConvert.INSTANCE.convert2(result);
    }

    // ========== 优惠劵 ==========

    // ========== 优惠码 ==========

}
