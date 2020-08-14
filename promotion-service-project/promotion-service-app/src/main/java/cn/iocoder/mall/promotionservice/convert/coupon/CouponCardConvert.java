package cn.iocoder.mall.promotionservice.convert.coupon;

import cn.iocoder.mall.promotion.api.rpc.coupon.dto.CouponCardDetailRespDTO;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.CouponCardReqDTO;
import cn.iocoder.mall.promotionservice.dal.mysql.dataobject.coupon.CouponCardDO;
import cn.iocoder.mall.promotionservice.service.coupon.bo.CouponCardAvailableBO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CouponCardConvert {

    CouponCardConvert INSTANCE = Mappers.getMapper(CouponCardConvert.class);

//    @Mappings({})
//    CouponCardBO convertToBO(CouponCardDO banner);



    List<CouponCardReqDTO> convertToDTO(List<CouponCardDO> cardList);

    CouponCardReqDTO convertToSingleDTO(CouponCardDO card);

    @Mappings({})
    CouponCardDetailRespDTO convert2(CouponCardDO card);

    CouponCardAvailableBO convertAvailBO(CouponCardDO card,boolean x);

    //@Mappings({})
    //CouponCardAvailableBO convert2(CouponCardDO card, boolean x); // TODO 芋艿，临时用来解决 mapstruct 无法正确匹配方法的问题

}
