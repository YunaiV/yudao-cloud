package cn.iocoder.mall.managementweb.manager.promotion.coupon;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.promotion.coupon.vo.template.CouponTemplateCardCreateReqVO;
import cn.iocoder.mall.managementweb.controller.promotion.coupon.vo.template.CouponTemplateCardUpdateReqVO;
import cn.iocoder.mall.managementweb.controller.promotion.coupon.vo.template.CouponTemplatePageReqVO;
import cn.iocoder.mall.managementweb.controller.promotion.coupon.vo.template.CouponTemplateRespVO;
import cn.iocoder.mall.managementweb.convert.promotion.CouponTemplateConvert;
import cn.iocoder.mall.promotion.api.rpc.coupon.CouponTemplateRpc;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.template.CouponCardTemplateUpdateStatusReqDTO;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.template.CouponTemplateRespDTO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class CouponTemplateManager {

    @DubboReference(version = "${dubbo.consumer.CouponTemplateRpc.version}")
    private CouponTemplateRpc couponTemplateRpc;

    // ========== 通用逻辑 =========

    public PageResult<CouponTemplateRespVO> pageCouponTemplate(CouponTemplatePageReqVO pageVO) {
        CommonResult<PageResult<CouponTemplateRespDTO>> pageCouponTemplateResult =
                couponTemplateRpc.pageCouponTemplate(CouponTemplateConvert.INSTANCE.convert(pageVO));
        pageCouponTemplateResult.checkError();
        return CouponTemplateConvert.INSTANCE.convertPage(pageCouponTemplateResult.getData());
    }

    public void updateCouponTemplateStatus(Integer id, Integer status) {
        CommonResult<Boolean> updateCouponTemplateStatusResult = couponTemplateRpc.updateCouponTemplateStatus(
                new CouponCardTemplateUpdateStatusReqDTO().setId(id).setStatus(status));
        updateCouponTemplateStatusResult.checkError();
    }

    // ========== 优惠劵模板 ==========

    public Integer createCouponCardTemplate(CouponTemplateCardCreateReqVO createVO) {
        CommonResult<Integer> createCouponCardTemplateResult = couponTemplateRpc.createCouponCardTemplate(
                CouponTemplateConvert.INSTANCE.convert(createVO));
        createCouponCardTemplateResult.checkError();
        return createCouponCardTemplateResult.getData();
    }

    public void updateCouponCardTemplate(CouponTemplateCardUpdateReqVO updateVO) {
        CommonResult<Boolean> updateCouponCardTemplateResult = couponTemplateRpc.updateCouponCardTemplate(
                CouponTemplateConvert.INSTANCE.convert(updateVO));
        updateCouponCardTemplateResult.checkError();
    }

}
