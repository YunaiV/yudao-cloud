package cn.iocoder.mall.promotion.application.convert;

import cn.iocoder.mall.promotion.api.bo.CouponCardBO;
import cn.iocoder.mall.promotion.api.bo.CouponCardPageBO;
import cn.iocoder.mall.promotion.application.vo.users.UsersCouponCardPageVO;
import cn.iocoder.mall.promotion.application.vo.users.UsersCouponCardVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CouponCardConvert {

    CouponCardConvert INSTANCE = Mappers.getMapper(CouponCardConvert.class);

    @Mappings({})
    UsersCouponCardVO convert(CouponCardBO result);

    @Mappings({})
    UsersCouponCardPageVO convert2(CouponCardPageBO result);

//
//    @Mappings({})
//    List<UsersCouponTemplateVO> convertList2(List<CouponTemplateBO> banners);



}
