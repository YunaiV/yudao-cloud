package cn.iocoder.mall.shopweb.convert.promotion;

import cn.iocoder.mall.promotion.api.rpc.coupon.dto.template.CouponTemplateRespDTO;
import cn.iocoder.mall.shopweb.controller.promotion.vo.coupon.template.CouponTemplateRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CouponTemplateConvert {

    CouponTemplateConvert INSTANCE = Mappers.getMapper(CouponTemplateConvert.class);

    CouponTemplateRespVO convert(CouponTemplateRespDTO bean);

}
