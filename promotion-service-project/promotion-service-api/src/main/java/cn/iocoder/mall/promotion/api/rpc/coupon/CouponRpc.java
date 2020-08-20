package cn.iocoder.mall.promotion.api.rpc.coupon;

import cn.iocoder.common.framework.validator.InEnum;
import cn.iocoder.mall.promotion.api.enums.coupon.template.CouponTemplateStatusEnum;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.*;

public interface CouponRpc {

    // ========== 优惠劵（码）模板 ==========

    CouponTemplateReqDTO getCouponTemplate(Integer couponTemplateId);

    CouponTemplatePageRespDTO getCouponTemplatePage(CouponTemplatePageReqDTO couponTemplatePageDTO);

    /**
     * 创建优惠码模板
     *
     * @param couponCodeTemplateAddDTO 优惠码模板添加 DTO
     * @return 优惠码模板
     */
    CouponTemplateReqDTO addCouponCodeTemplate(CouponCodeTemplateAddReqDTO couponCodeTemplateAddDTO);

    /**
     * 创建优惠劵模板
     *
     * @param couponCardTemplateAddDTO 优惠码模板添加 DTO
     * @return 优惠劵模板
     */
    CouponTemplateReqDTO addCouponCardTemplate(CouponCardTemplateAddReqDTO couponCardTemplateAddDTO);

    /**
     * 更新优惠码模板
     *
     * @param couponCodeTemplateUpdateDTO 优惠码模板修改 DTO
     * @return 是否成功
     */
    Boolean updateCouponCodeTemplate(CouponCodeTemplateUpdateReqDTO couponCodeTemplateUpdateDTO);

    /**
     * 更新优惠劵模板
     *
     * @param couponCardTemplateUpdateDTO 优惠劵模板修改 DTO
     * @return 是否成功
     */
    Boolean updateCouponCardTemplate(CouponCardTemplateUpdateReqDTO couponCardTemplateUpdateDTO);

    /**
     * 更新优惠劵（码）模板的状态
     *
     * @param adminId          操作管理员编号
     * @param couponTemplateId 模板编号
     * @param status           状态
     * @return 是否成功
     */
    Boolean updateCouponTemplateStatus(Integer adminId, Integer couponTemplateId,
                                       @InEnum(value = CouponTemplateStatusEnum.class, message = "修改状态必须是 {value}") Integer status);

}
