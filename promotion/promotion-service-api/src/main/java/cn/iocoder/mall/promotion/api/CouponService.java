package cn.iocoder.mall.promotion.api;

import cn.iocoder.common.framework.validator.InEnum;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.promotion.api.bo.*;
import cn.iocoder.mall.promotion.api.constant.CouponTemplateStatusEnum;
import cn.iocoder.mall.promotion.api.dto.*;

import java.util.List;

public interface CouponService {

    // ========== 优惠劵（码）模板 ==========

    CommonResult<CouponTemplateBO> getCouponTemplate(Integer couponTemplateId);

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
    CommonResult<Boolean> updateCouponTemplateStatus(Integer adminId, Integer couponTemplateId,
                                                     @InEnum(value = CouponTemplateStatusEnum.class, message = "修改状态必须是 {value}") Integer status);

    // ========== 优惠劵 ==========

    CommonResult<CouponCardPageBO> getCouponCardPage(CouponCardPageDTO couponCardPageDTO);

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
     * @return 是否成功
     */
    CommonResult<Boolean> useCouponCard(Integer userId, Integer couponCardId);

    /**
     * 取消优惠劵的使用
     *
     * @param userId 用户编号
     * @param couponCardId 优惠劵编号
     * @return 是否成功
     */
    CommonResult<Boolean> cancelUseCouponCard(Integer userId, Integer couponCardId);

    /**
     * 获得指定优惠劵
     *
     * @param userId 用户编号
     * @param couponCardId 优惠劵编号
     * @return 优惠劵
     */
    CommonResult<CouponCardDetailBO> getCouponCardDetail(Integer userId, Integer couponCardId);

    /**
     * 获得用户所有优惠劵，并标明是否可用
     *
     * 注意，spus 是作为条件，判断优惠劵是否可用
     *
     * @param userId 用户编号
     * @param spus 匹配的商品/分类
     * @return 优惠劵列表
     */
    CommonResult<List<CouponCardAvailableBO>> getCouponCardList(Integer userId, List<CouponCardSpuDTO> spus);


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
