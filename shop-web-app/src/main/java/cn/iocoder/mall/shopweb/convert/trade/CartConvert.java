package cn.iocoder.mall.shopweb.convert.trade;

import cn.iocoder.mall.productservice.rpc.sku.dto.ProductSkuRespDTO;
import cn.iocoder.mall.promotion.api.rpc.activity.dto.PromotionActivityRespDTO;
import cn.iocoder.mall.promotion.api.rpc.price.dto.PriceProductCalcRespDTO;
import cn.iocoder.mall.shopweb.controller.trade.vo.cart.CartDetailVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CartConvert {

    CartConvert INSTANCE = Mappers.getMapper(CartConvert.class);

    CartDetailVO.Fee convert(PriceProductCalcRespDTO.Fee bean);

    @Mapping(source = "sku.id", target = "id")
    CartDetailVO.Sku convert(PriceProductCalcRespDTO.Item item, ProductSkuRespDTO sku, PromotionActivityRespDTO activity);

}
