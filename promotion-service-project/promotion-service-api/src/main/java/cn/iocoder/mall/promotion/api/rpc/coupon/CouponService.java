package cn.iocoder.mall.promotion.api.rpc.coupon;

import cn.iocoder.common.framework.validator.InEnum;
import cn.iocoder.mall.promotion.api.enums.CouponTemplateStatusEnum;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.*;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface CouponService {

    // ========== 优惠劵（码）模板 ==========

    CouponTemplateBO getCouponTemplate(Integer couponTemplateId);

    CouponTemplatePageBO getCouponTemplatePage(CouponTemplatePageReqDTO couponTemplatePageDTO);

    /**
     * 创建优惠码模板
     *
     * @param couponCodeTemplateAddDTO 优惠码模板添加 DTO
     * @return 优惠码模板
     */
    CouponTemplateBO addCouponCodeTemplate(CouponCodeTemplateAddReqDTO couponCodeTemplateAddDTO);

    /**
     * 创建优惠劵模板
     *
     * @param couponCardTemplateAddDTO 优惠码模板添加 DTO
     * @return 优惠劵模板
     */
    CouponTemplateBO addCouponCardTemplate(CouponCardTemplateAddReqDTO couponCardTemplateAddDTO);

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

    // ========== 优惠劵 ==========

    CouponCardPageBO getCouponCardPage(CouponCardPageReqDTO couponCardPageDTO);

    /**
     * 基于优惠劵模板，领取优惠劵
     *
     * @param userId           用户编号
     * @param couponTemplateId 优惠劵模板
     * @return 优惠劵
     */
    CouponCardBO addCouponCard(Integer userId, Integer couponTemplateId);

    /**
     * 使用优惠劵下单
     *
     * @param userId       用户编号
     * @param couponCardId 优惠劵编号
     * @return 是否成功
     */
    Boolean useCouponCard(Integer userId,
                        @NotNull(message = "优惠劵编号不能为空") Integer couponCardId);

    /**
     * 取消优惠劵的使用
     *
     * @param userId       用户编号
     * @param couponCardId 优惠劵编号
     * @return 是否成功
     */
    Boolean cancelUseCouponCard(Integer userId, Integer couponCardId);

    /**
     * 获得指定优惠劵
     *
     * @param userId       用户编号
     * @param couponCardId 优惠劵编号
     * @return 优惠劵
     */
    CouponCardDetailBO getCouponCardDetail(Integer userId, Integer couponCardId);

    /**
     * 获得用户所有优惠劵，并标明是否可用
     * <p>
     * 注意，spus 是作为条件，判断优惠劵是否可用
     *
     * @param userId 用户编号
     * @param spus   匹配的商品/分类
     * @return 优惠劵列表
     */
    List<CouponCardAvailableRespDTO> getCouponCardList(Integer userId, List<CouponCardSpuRespDTO> spus);

    // ========== 优惠码 ==========

    /**
     * 使用优惠码，兑换优惠劵
     *
     * @param userId 用户编号
     * @param code   优惠码
     * @return 优惠劵
     */
    CouponCardBO useCouponCode(Integer userId, String code);

}
