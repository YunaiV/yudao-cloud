package cn.iocoder.mall.promotionservice.rpc.coupon;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.promotion.api.rpc.coupon.CouponTemplateRpc;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.template.*;
import cn.iocoder.mall.promotionservice.manager.coupon.CouponTemplateManager;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@DubboService
public class CouponTemplateRpcImpl implements CouponTemplateRpc {

    @Autowired
    private CouponTemplateManager couponTemplateManager;

    // ========== 通用逻辑 =========

    @Override
    public CommonResult<CouponTemplateRespDTO> getCouponTemplate(Integer couponTemplateId) {
        return success(couponTemplateManager.getCouponTemplate(couponTemplateId));
    }

    @Override
    public CommonResult<PageResult<CouponTemplateRespDTO>> pageCouponTemplate(CouponTemplatePageReqDTO pageDTO) {
        return success(couponTemplateManager.pageCouponTemplate(pageDTO));
    }

    @Override
    public CommonResult<Boolean> updateCouponTemplateStatus(CouponCardTemplateUpdateStatusReqDTO updateStatusReqDTO) {
        couponTemplateManager.updateCouponTemplateStatus(updateStatusReqDTO);
        return success(true);
    }

    // ========== 优惠劵模板 ==========

    @Override
    public CommonResult<Integer> createCouponCardTemplate(CouponCardTemplateCreateReqDTO createDTO) {
        return success(couponTemplateManager.createCouponCardTemplate(createDTO));
    }

    @Override
    public CommonResult<Boolean> updateCouponCardTemplate(CouponCardTemplateUpdateReqDTO updateDTO) {
        couponTemplateManager.updateCouponCardTemplate(updateDTO);
        return success(true);
    }

}
