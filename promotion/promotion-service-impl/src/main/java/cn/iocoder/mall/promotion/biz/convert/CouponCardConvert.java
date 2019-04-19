package cn.iocoder.mall.promotion.biz.convert;

import cn.iocoder.mall.promotion.api.bo.CouponCardAvailableBO;
import cn.iocoder.mall.promotion.api.bo.CouponCardBO;
import cn.iocoder.mall.promotion.api.bo.CouponCardDetailBO;
import cn.iocoder.mall.promotion.biz.dataobject.CouponCardDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CouponCardConvert {

    CouponCardConvert INSTANCE = Mappers.getMapper(CouponCardConvert.class);

//    @Mappings({})
//    CouponCardBO convertToBO(CouponCardDO banner);
//
    @Mappings({})
    List<CouponCardBO> convertToBO(List<CouponCardDO> cardList);

    @Mappings({})
    CouponCardBO convert(CouponCardDO card);

    @Mappings({})
    CouponCardDetailBO convert2(CouponCardDO card);

    @Mappings({})
    CouponCardAvailableBO convert2(CouponCardDO card,  boolean x); // TODO 芋艿，临时用来解决 mapstruct 无法正确匹配方法的问题

}
