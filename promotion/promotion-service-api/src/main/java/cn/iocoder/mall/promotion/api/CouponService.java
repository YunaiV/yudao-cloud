package cn.iocoder.mall.promotion.api;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.promotion.api.bo.CouponCardBO;
import cn.iocoder.mall.promotion.api.bo.CouponTemplateBO;
import cn.iocoder.mall.promotion.api.bo.CouponTemplatePageBO;
import cn.iocoder.mall.promotion.api.dto.*;

public interface CouponService {

    // ========== 优惠劵（码）模板 ==========

    CommonResult<CouponTemplatePageBO> getCouponTemplatePage(CouponTemplatePageDTO couponTemplatePageDTO);

    /**
     * 创建优惠码模板
     *
     * @param couponCodeTemplateAddDTO 优惠码模板添加 DTO
     * @return 优惠码模板
     */
    CommonResult<CouponTemplateBO> addCouponCodeTemplate(CouponCodeTemplateAddDTO couponCodeTemplateAddDTO);

    /**
     * 创建优惠劵模板
     *
     * @param couponCardTemplateAddDTO 优惠码模板添加 DTO
     * @return 优惠劵模板
     */
    CommonResult<CouponTemplateBO> addCouponCardTemplate(CouponCardTemplateAddDTO couponCardTemplateAddDTO);

    /**
     * 更新优惠码模板
     *
     * @param couponCodeTemplateUpdateDTO 优惠码模板修改 DTO
     * @return 是否成功
     */
    CommonResult<Boolean> updateCouponCodeTemplate(CouponCodeTemplateUpdateDTO couponCodeTemplateUpdateDTO);

    /**
     * 更新优惠劵模板
     *
     * @param couponCardTemplateUpdateDTO 优惠劵模板修改 DTO
     * @return 是否成功
     */
    CommonResult<Boolean> updateCouponCardTemplate(CouponCardTemplateUpdateDTO couponCardTemplateUpdateDTO);

    /**
     * 更新优惠劵（码）模板的状态
     *
     * @param adminId 操作管理员编号
     * @param couponTemplateId 模板编号
     * @param status 状态
     * @return 是否成功
     */
    CommonResult<Boolean> updateCouponTemplateStatus(Integer adminId, Integer couponTemplateId, Integer status);

    // ========== 优惠劵 ==========

    /**
     * 基于优惠劵模板，领取优惠劵
     *
     * @param userId 用户编号
     * @param couponTemplateId 优惠劵模板
     * @return 优惠劵
     */
    CommonResult<CouponCardBO> addCouponCard(Integer userId, Integer couponTemplateId);

    /**
     * 使用优惠劵下单
     *
     * @param userId 用户编号
     * @param couponCardId 优惠劵编号
     * @param usedOrderId 下单的编号
     * @param usedPrice 下单的价格
     * @return 是否成功
     */
    CommonResult<Boolean> useCouponCard(Integer userId, Integer couponCardId, Integer usedOrderId, Integer usedPrice);

    /**
     * 取消优惠劵的使用
     *
     * @param userId 用户编号
     * @param couponCardId 优惠劵编号
     * @return 是否成功
     */
    CommonResult<Boolean> cancelUseCouponCard(Integer userId, Integer couponCardId);

    // ========== 优惠码 ==========

    /**
     * 使用优惠码，兑换优惠劵
     *
     * @param userId 用户编号
     * @param code 优惠码
     * @return 优惠劵
     */
    CommonResult<CouponCardBO> useCouponCode(Integer userId, String code);

}
