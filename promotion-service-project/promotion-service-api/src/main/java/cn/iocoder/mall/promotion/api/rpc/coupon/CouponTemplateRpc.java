package cn.iocoder.mall.promotion.api.rpc.coupon;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.template.*;

/**
 * 优惠劵模板 Rpc 接口
 */
public interface CouponTemplateRpc {

    // ========== 通用逻辑 =========

    /**
     * 获得优惠劵（码）模板
     *
     * @param couponTemplateId 优惠劵模板编号
     * @return 优惠劵模板
     */
    CommonResult<CouponTemplateRespDTO> getCouponTemplate(Integer couponTemplateId);

    /**
     * 获得优惠劵（码）模板分页
     *
     * @param pageDTO 优惠劵模板分页查询
     * @return 优惠劵模板分页结果
     */
    CommonResult<PageResult<CouponTemplateRespDTO>> pageCouponTemplate(CouponTemplatePageReqDTO pageDTO);

    /**
     * 更新优惠劵（码）模板的状态
     *
     * @param updateStatusReqDTO 更新状态 DTO
     */
    CommonResult<Boolean> updateCouponTemplateStatus(CouponCardTemplateUpdateStatusReqDTO updateStatusReqDTO);

    // ========== 优惠劵模板 ==========

    /**
     * 创建优惠劵模板
     *
     * @param createDTO 创建优惠劵模板 DTO
     * @return 优惠劵模板编号
     */
    CommonResult<Integer> createCouponCardTemplate(CouponCardTemplateCreateReqDTO createDTO);

    /**
     * 更新优惠劵模板
     *
     * @param updateDTO 更新优惠劵模板 DTO
     */
    CommonResult<Boolean> updateCouponCardTemplate(CouponCardTemplateUpdateReqDTO updateDTO);

    // ========== 优惠码模板 ==========


}
