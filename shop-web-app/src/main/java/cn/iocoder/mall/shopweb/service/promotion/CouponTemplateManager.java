package cn.iocoder.mall.shopweb.service.promotion;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.promotion.api.rpc.coupon.CouponTemplateRpc;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.template.CouponTemplateRespDTO;
import cn.iocoder.mall.shopweb.controller.promotion.vo.coupon.template.CouponTemplateRespVO;
import cn.iocoder.mall.shopweb.convert.promotion.CouponTemplateConvert;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

/**
 * 优惠劵（码）模板 Manager
 */
@Service
public class CouponTemplateManager {

    @DubboReference(version = "${dubbo.consumer.CouponTemplateRpc.version}")
    private CouponTemplateRpc couponTemplateRpc;

    public CouponTemplateRespVO getCouponTemplate(Integer id) {
        CommonResult<CouponTemplateRespDTO> getCouponTemplateResult = couponTemplateRpc.getCouponTemplate(id);
        getCouponTemplateResult.checkError();
        return CouponTemplateConvert.INSTANCE.convert(getCouponTemplateResult.getData());
    }
}
