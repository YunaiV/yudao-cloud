package cn.iocoder.mall.shopweb.service.promotion;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.promotion.api.rpc.coupon.CouponTemplateFeign;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.template.CouponTemplateRespDTO;
import cn.iocoder.mall.shopweb.controller.promotion.vo.coupon.template.CouponTemplateRespVO;
import cn.iocoder.mall.shopweb.convert.promotion.CouponTemplateConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 优惠劵（码）模板 Manager
 */
@Service
public class CouponTemplateManager {
    @Autowired
    private CouponTemplateFeign couponTemplateFeign;
    public CouponTemplateRespVO getCouponTemplate(Integer id) {
        CommonResult<CouponTemplateRespDTO> getCouponTemplateResult = couponTemplateFeign.getCouponTemplate(id);
        getCouponTemplateResult.checkError();
        return CouponTemplateConvert.INSTANCE.convert(getCouponTemplateResult.getData());
    }
}
