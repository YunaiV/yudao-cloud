package cn.iocoder.mall.promotion.application.convert;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.promotion.api.bo.CouponTemplateBO;
import cn.iocoder.mall.promotion.api.bo.CouponTemplatePageBO;
import cn.iocoder.mall.promotion.application.vo.admins.AdminsCouponTemplatePageVO;
import cn.iocoder.mall.promotion.application.vo.admins.AdminsCouponTemplateVO;
import cn.iocoder.mall.promotion.application.vo.users.UsersCouponTemplateVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CouponTemplateConvert {

    CouponTemplateConvert INSTANCE = Mappers.getMapper(CouponTemplateConvert.class);

    @Mappings({})
    AdminsCouponTemplateVO convert(CouponTemplateBO template);

    @Mappings({})
    CommonResult<AdminsCouponTemplateVO> convert2(CommonResult<CouponTemplateBO> result);

    @Mappings({})
    CommonResult<AdminsCouponTemplatePageVO> convert(CommonResult<CouponTemplatePageBO> result);

    @Mappings({})
    List<AdminsCouponTemplateVO> convertList(List<CouponTemplateBO> templates);

    @Mappings({})
    UsersCouponTemplateVO convert2(CouponTemplateBO template);

//
//    @Mappings({})
//    List<UsersCouponTemplateVO> convertList2(List<CouponTemplateBO> banners);

}
