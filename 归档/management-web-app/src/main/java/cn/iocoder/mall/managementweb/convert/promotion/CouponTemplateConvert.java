package cn.iocoder.mall.managementweb.convert.promotion;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.promotion.coupon.vo.template.CouponTemplateCardCreateReqVO;
import cn.iocoder.mall.managementweb.controller.promotion.coupon.vo.template.CouponTemplateCardUpdateReqVO;
import cn.iocoder.mall.managementweb.controller.promotion.coupon.vo.template.CouponTemplatePageReqVO;
import cn.iocoder.mall.managementweb.controller.promotion.coupon.vo.template.CouponTemplateRespVO;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.template.CouponCardTemplateCreateReqDTO;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.template.CouponCardTemplateUpdateReqDTO;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.template.CouponTemplatePageReqDTO;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.template.CouponTemplateRespDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CouponTemplateConvert {

    CouponTemplateConvert INSTANCE = Mappers.getMapper(CouponTemplateConvert.class);

    CouponCardTemplateUpdateReqDTO convert(CouponTemplateCardUpdateReqVO bean);

    CouponTemplatePageReqDTO convert(CouponTemplatePageReqVO bean);

    PageResult<CouponTemplateRespVO> convertPage(PageResult<CouponTemplateRespDTO> page);

    CouponCardTemplateCreateReqDTO convert(CouponTemplateCardCreateReqVO bean);

}
