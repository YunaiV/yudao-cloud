package cn.iocoder.mall.order.biz.convert;

import cn.iocoder.mall.order.api.bo.CalcOrderPriceBO;
import cn.iocoder.mall.order.api.bo.CartItemBO;
import cn.iocoder.mall.order.biz.dataobject.CartItemDO;
import cn.iocoder.mall.product.api.bo.ProductSkuDetailBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CartConvert {

    CartConvert INSTANCE = Mappers.getMapper(CartConvert.class);

    CalcOrderPriceBO.Item convert(ProductSkuDetailBO sku);

    List<CartItemBO> convert(List<CartItemDO> items);

}
