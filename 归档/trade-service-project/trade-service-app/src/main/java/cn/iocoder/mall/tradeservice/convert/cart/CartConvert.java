package cn.iocoder.mall.tradeservice.convert.cart;

import cn.iocoder.mall.tradeservice.dal.mysql.dataobject.cart.CartItemDO;
import cn.iocoder.mall.tradeservice.rpc.cart.dto.CartItemAddReqDTO;
import cn.iocoder.mall.tradeservice.rpc.cart.dto.CartItemListReqDTO;
import cn.iocoder.mall.tradeservice.rpc.cart.dto.CartItemRespDTO;
import cn.iocoder.mall.tradeservice.service.cart.bo.CartItemAddBO;
import cn.iocoder.mall.tradeservice.service.cart.bo.CartItemBO;
import cn.iocoder.mall.tradeservice.service.cart.bo.CartItemListQueryBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CartConvert {

    CartConvert INSTANCE = Mappers.getMapper(CartConvert.class);

    CartItemDO convert(CartItemAddBO bean);

    CartItemAddBO convert(CartItemAddReqDTO bean);

    List<CartItemBO> convertList(List<CartItemDO> list);

    CartItemListQueryBO convert(CartItemListReqDTO bean);

    List<CartItemRespDTO> convertList02(List<CartItemBO> list);

}
