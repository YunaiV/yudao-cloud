package cn.iocoder.mall.promotionservice.manager.coupon;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.template.*;
import cn.iocoder.mall.promotionservice.service.coupon.CouponTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 优惠劵（码）模板 Manager
 */
@Service
public class CouponTemplateManager {

    @Autowired
    private CouponTemplateService couponTemplateService;

    // ========== 通用逻辑 =========

    public CouponTemplateRespDTO getCouponTemplate(Integer couponTemplateId) {
        return couponTemplateService.getCouponTemplate(couponTemplateId);
    }

    public PageResult<CouponTemplateRespDTO> pageCouponTemplate(CouponTemplatePageReqDTO pageDTO) {
        return couponTemplateService.pageCouponTemplate(pageDTO);
    }

    public void updateCouponTemplateStatus(CouponCardTemplateUpdateStatusReqDTO updateStatusReqDTO) {
        couponTemplateService.updateCouponTemplateStatus(updateStatusReqDTO.getId(), updateStatusReqDTO.getStatus());
    }

    // ========== 优惠劵模板 ==========

    public Integer createCouponCardTemplate(CouponCardTemplateCreateReqDTO createDTO) {
        return couponTemplateService.createCouponCardTemplate(createDTO);
    }


    public void updateCouponCardTemplate(CouponCardTemplateUpdateReqDTO updateDTO) {
        couponTemplateService.updateCouponCardTemplate(updateDTO);
    }

}
