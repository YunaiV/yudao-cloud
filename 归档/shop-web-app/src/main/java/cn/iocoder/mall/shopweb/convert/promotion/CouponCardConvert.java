package cn.iocoder.mall.shopweb.convert.promotion;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.card.CouponCardPageReqDTO;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.card.CouponCardRespDTO;
import cn.iocoder.mall.shopweb.controller.promotion.vo.coupon.card.CouponCardPageReqVO;
import cn.iocoder.mall.shopweb.controller.promotion.vo.coupon.card.CouponCardRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 优惠劵 Convert
 */
@Mapper
public interface CouponCardConvert {

    CouponCardConvert INSTANCE = Mappers.getMapper(CouponCardConvert.class);

    PageResult<CouponCardRespVO> convertPage(PageResult<CouponCardRespDTO> page);

    CouponCardPageReqDTO convert(CouponCardPageReqVO bean);

}
