package cn.iocoder.mall.orderservice.convert.cart;

import cn.iocoder.mall.orderservice.dal.mysql.dataobject.cart.CartItemDO;
import cn.iocoder.mall.orderservice.rpc.cart.dto.CartItemAddReqDTO;
import cn.iocoder.mall.orderservice.service.cart.bo.CartItemAddBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CartConvert {

    CartConvert INSTANCE = Mappers.getMapper(CartConvert.class);

    CartItemDO convert(CartItemAddBO bean);

    CartItemAddBO convert(CartItemAddReqDTO bean);

}
