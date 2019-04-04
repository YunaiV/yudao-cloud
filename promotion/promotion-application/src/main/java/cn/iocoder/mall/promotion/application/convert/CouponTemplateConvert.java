package cn.iocoder.mall.promotion.application.convert;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.promotion.api.bo.CouponTemplateBO;
import cn.iocoder.mall.promotion.application.vo.admins.AdminsCouponTemplateVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CouponTemplateConvert {

    CouponTemplateConvert INSTANCE = Mappers.getMapper(CouponTemplateConvert.class);

    @Mappings({})
    AdminsCouponTemplateVO convert(CouponTemplateBO bannerBO);

    @Mappings({})
    CommonResult<AdminsCouponTemplateVO> convert2(CommonResult<CouponTemplateBO> result);

//    @Mappings({})
//    CommonResult<AdminsCouponTemplatePageVO> convert(CommonResult<CouponTemplatePageBO> result);
//
//    @Mappings({})
//    List<UsersCouponTemplateVO> convertList(List<CouponTemplateBO> banners);

}
