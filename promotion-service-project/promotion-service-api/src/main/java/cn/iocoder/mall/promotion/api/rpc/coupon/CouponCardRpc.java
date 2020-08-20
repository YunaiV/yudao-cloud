package cn.iocoder.mall.promotion.api.rpc.coupon;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.card.*;

import java.util.List;

/**
 * 优惠劵 Rpc 接口
 */
public interface CouponCardRpc {

    /**
     * 获得优惠劵分页
     *
     * @param pageReqDTO 优惠劵分页查询
     * @return 优惠劵分页结果
     */
    CommonResult<PageResult<CouponCardRespDTO>> pageCouponCard(CouponCardPageReqDTO pageReqDTO);

    /**
     * 给用户添加优惠劵
     *
     * @param createReqDTO 创建信息
     * @return 优惠劵编号
     */
    CommonResult<Integer> createCouponCard(CouponCardCreateReqDTO createReqDTO);

    /**
     * 用户使用优惠劵
     *
     * @param useReqDTO 使用信息
     * @return 成功
     */
    CommonResult<Boolean> useCouponCard(CouponCardUseReqDTO useReqDTO);

    /**
     * 用户取消使用优惠劵
     *
     * @param cancelUseReqDTO 取消使用信息
     * @return 成功
     */
    CommonResult<Boolean> cancelUseCouponCard(CouponCardCancelUseReqDTO cancelUseReqDTO);

    /**
     * 获得用户优惠劵的可用信息列表
     *
     * @param listReqDTO 查询信息
     * @return 优惠劵的可用信息列表
     */
    CommonResult<List<CouponCardAvailableRespDTO>> listAvailableCouponCards(CouponCardAvailableListReqDTO listReqDTO);

}
