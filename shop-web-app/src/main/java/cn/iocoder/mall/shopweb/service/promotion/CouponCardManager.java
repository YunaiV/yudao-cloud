package cn.iocoder.mall.shopweb.service.promotion;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.promotion.api.rpc.coupon.CouponCardRpc;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.card.CouponCardCreateReqDTO;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.card.CouponCardRespDTO;
import cn.iocoder.mall.shopweb.controller.promotion.vo.coupon.card.CouponCardPageReqVO;
import cn.iocoder.mall.shopweb.controller.promotion.vo.coupon.card.CouponCardRespVO;
import cn.iocoder.mall.shopweb.convert.promotion.CouponCardConvert;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

/**
 * 优惠劵 Manager
 */
@Service
public class CouponCardManager {

    @DubboReference(version = "${dubbo.consumer.CouponCardRpc.version}")
    private CouponCardRpc couponCardRpc;

    /**
     * 获得优惠劵分页
     *
     * @param userId 用户编号
     * @param pageVO 优惠劵分页查询
     * @return 优惠劵分页结果
     */
    public PageResult<CouponCardRespVO> pageCouponCard(Integer userId, CouponCardPageReqVO pageVO) {
        CommonResult<PageResult<CouponCardRespDTO>> pageCouponCardResult = couponCardRpc.pageCouponCard(
                CouponCardConvert.INSTANCE.convert(pageVO).setUserId(userId));
        pageCouponCardResult.checkError();
        return CouponCardConvert.INSTANCE.convertPage(pageCouponCardResult.getData());
    }

    /**
     * 用户领取优惠劵
     *
     * @param userId 用户编号
     * @param couponTemplateId 优惠劵模板编号
     * @return 优惠劵编号
     */
    public Integer createCouponCard(Integer userId, Integer couponTemplateId) {
        CommonResult<Integer> createCouponCardResult = couponCardRpc.createCouponCard(
                new CouponCardCreateReqDTO().setUserId(userId).setCouponTemplateId(couponTemplateId));
        createCouponCardResult.checkError();
        return createCouponCardResult.getData();
    }

}
