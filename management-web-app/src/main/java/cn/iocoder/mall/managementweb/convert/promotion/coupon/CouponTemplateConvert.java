package cn.iocoder.mall.managementweb.convert.promotion.coupon;

import cn.iocoder.mall.managementweb.controller.promotion.coupon.vo.template.CouponTemplateCardUpdateReqVO;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.template.CouponCardTemplateUpdateReqDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CouponTemplateConvert {

    CouponTemplateConvert INSTANCE = Mappers.getMapper(CouponTemplateConvert.class);

    CouponCardTemplateUpdateReqDTO convert(CouponTemplateCardUpdateReqVO bean);

}
