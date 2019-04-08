package cn.iocoder.mall.promotion.biz.convert;

import cn.iocoder.mall.promotion.api.bo.CouponCardBO;
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

}
