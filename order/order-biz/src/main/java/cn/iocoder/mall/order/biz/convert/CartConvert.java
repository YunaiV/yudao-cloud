package cn.iocoder.mall.order.biz.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CartConvert {

    CartConvert INSTANCE = Mappers.getMapper(CartConvert.class);

//    CalcOrderPriceBO.Item convert(ProductSkuDetailBO sku);
//
//    List<CartItemBO> convert(List<CartItemDO> items);

}
