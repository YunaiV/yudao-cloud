package cn.iocoder.mall.managementweb.manager.promotion.coupon;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.promotion.coupon.vo.template.CouponTemplateCardCreateReqVO;
import cn.iocoder.mall.managementweb.controller.promotion.coupon.vo.template.CouponTemplateCardUpdateReqVO;
import cn.iocoder.mall.managementweb.controller.promotion.coupon.vo.template.CouponTemplatePageReqVO;
import cn.iocoder.mall.managementweb.controller.promotion.coupon.vo.template.CouponTemplateRespVO;
import cn.iocoder.mall.managementweb.convert.promotion.CouponTemplateConvert;
import cn.iocoder.mall.promotion.api.rpc.coupon.CouponTemplateFeign;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.template.CouponCardTemplateUpdateStatusReqDTO;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.template.CouponTemplateRespDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class CouponTemplateManager {


    @Autowired
    private CouponTemplateFeign couponTemplateFeign;
    // ========== 通用逻辑 =========

    public PageResult<CouponTemplateRespVO> pageCouponTemplate(CouponTemplatePageReqVO pageVO) {
        CommonResult<PageResult<CouponTemplateRespDTO>> pageCouponTemplateResult =
                couponTemplateFeign.pageCouponTemplate(CouponTemplateConvert.INSTANCE.convert(pageVO));
        pageCouponTemplateResult.checkError();
        return CouponTemplateConvert.INSTANCE.convertPage(pageCouponTemplateResult.getData());
    }

    public void updateCouponTemplateStatus(Integer id, Integer status) {
        CommonResult<Boolean> updateCouponTemplateStatusResult = couponTemplateFeign.updateCouponTemplateStatus(
                new CouponCardTemplateUpdateStatusReqDTO().setId(id).setStatus(status));
        updateCouponTemplateStatusResult.checkError();
    }

    // ========== 优惠劵模板 ==========

    public Integer createCouponCardTemplate(CouponTemplateCardCreateReqVO createVO) {
        CommonResult<Integer> createCouponCardTemplateResult = couponTemplateFeign.createCouponCardTemplate(
                CouponTemplateConvert.INSTANCE.convert(createVO));
        createCouponCardTemplateResult.checkError();
        return createCouponCardTemplateResult.getData();
    }

    public void updateCouponCardTemplate(CouponTemplateCardUpdateReqVO updateVO) {
        CommonResult<Boolean> updateCouponCardTemplateResult = couponTemplateFeign.updateCouponCardTemplate(
                CouponTemplateConvert.INSTANCE.convert(updateVO));
        updateCouponCardTemplateResult.checkError();
    }

}
