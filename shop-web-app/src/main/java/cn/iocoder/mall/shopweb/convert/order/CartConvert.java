package cn.iocoder.mall.shopweb.convert.order;

import cn.iocoder.mall.productservice.rpc.sku.dto.ProductSkuRespDTO;
import cn.iocoder.mall.promotion.api.rpc.price.dto.PriceProductCalcRespDTO;
import cn.iocoder.mall.shopweb.controller.order.vo.cart.CartDetailVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CartConvert {

    CartConvert INSTANCE = Mappers.getMapper(CartConvert.class);

    CartDetailVO.Fee convert(PriceProductCalcRespDTO.Fee bean);

    CartDetailVO.Sku convert(PriceProductCalcRespDTO.Item item, ProductSkuRespDTO sku);

}
