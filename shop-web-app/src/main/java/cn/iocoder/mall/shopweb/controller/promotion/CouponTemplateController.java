package cn.iocoder.mall.shopweb.controller.promotion;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.shopweb.controller.promotion.vo.coupon.template.CouponTemplateRespVO;
import cn.iocoder.mall.shopweb.service.promotion.CouponTemplateManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@RestController
@RequestMapping("/promotion/coupon-template")
@Api(tags = "优惠劵（码）模板 API")
@Validated
public class CouponTemplateController {

    @Autowired
    private CouponTemplateManager couponTemplateManager;

    @GetMapping("/get")
    @ApiOperation(value = "优惠劵（码）模板信息")
    @ApiImplicitParam(name = "id", value = "优惠劵（码）模板编号", required = true, example = "10")
    public CommonResult<CouponTemplateRespVO> getCouponTemplate(@RequestParam("id") Integer id) {
        return success(couponTemplateManager.getCouponTemplate(id));
    }

}
