package cn.iocoder.mall.promotionservice.manager.coupon;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.card.*;
import cn.iocoder.mall.promotionservice.service.coupon.CouponCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class CouponCardManager {

    @Autowired
    private CouponCardService couponCardService;

    public PageResult<CouponCardRespDTO> pageCouponCard(CouponCardPageReqDTO pageReqDTO) {
        return couponCardService.pageCouponCard(pageReqDTO);
    }

    public Integer createCouponCard(CouponCardCreateReqDTO createReqDTO) {
        return couponCardService.createCouponCard(createReqDTO.getUserId(), createReqDTO.getCouponTemplateId());
    }

    public Boolean useCouponCard(CouponCardUseReqDTO useReqDTO) {
        couponCardService.useCouponCard(useReqDTO.getUserId(), useReqDTO.getCouponCardId());
        return true;
    }

    public Boolean cancelUseCouponCard(CouponCardCancelUseReqDTO cancelUseReqDTO) {
        couponCardService.cancelUseCouponCard(cancelUseReqDTO.getUserId(), cancelUseReqDTO.getCouponCardId());
        return true;
    }

    public List<CouponCardAvailableRespDTO> listAvailableCouponCards(CouponCardAvailableListReqDTO listReqDTO) {
        return couponCardService.listAvailableCouponCards(listReqDTO);
    }

}
