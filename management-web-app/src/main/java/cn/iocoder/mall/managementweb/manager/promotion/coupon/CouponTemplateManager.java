package cn.iocoder.mall.managementweb.manager.promotion.coupon;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.managementweb.controller.promotion.coupon.vo.template.CouponTemplateCardCreateReqVO;
import cn.iocoder.mall.managementweb.controller.promotion.coupon.vo.template.CouponTemplateCardUpdateReqVO;
import cn.iocoder.mall.managementweb.convert.promotion.coupon.CouponTemplateConvert;
import cn.iocoder.mall.promotion.api.rpc.coupon.CouponTemplateRpc;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.template.CouponCardTemplateUpdateStatusReqDTO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class CouponTemplateManager {

    @DubboReference(version = "${dubbo.consumer.CouponTemplateRpc.version}")
    private CouponTemplateRpc couponTemplateRpc;

    public Integer createCouponTemplateCard(CouponTemplateCardCreateReqVO createVO) {
        return null;
    }

    public void updateCouponTemplateCard(CouponTemplateCardUpdateReqVO updateVO) {
        CommonResult<Boolean> updateCouponCardTemplateResult = couponTemplateRpc.updateCouponCardTemplate(
                CouponTemplateConvert.INSTANCE.convert(updateVO));
        updateCouponCardTemplateResult.checkError();
    }

    public void updateCouponTemplateStatus(Integer id, Integer status) {
        CommonResult<Boolean> updateCouponTemplateStatusResult = couponTemplateRpc.updateCouponTemplateStatus(
                new CouponCardTemplateUpdateStatusReqDTO().setId(id).setStatus(status));
        updateCouponTemplateStatusResult.checkError();
    }

}
