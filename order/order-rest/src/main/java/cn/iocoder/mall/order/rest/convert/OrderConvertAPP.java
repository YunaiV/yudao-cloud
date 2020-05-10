package cn.iocoder.mall.order.rest.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * application 订单 convert
 *
 * TODO 这种方式 文件名不能一样哈!
 *
 * @author Sin
 * @time 2019-03-24 10:45
 */
@Mapper
public interface OrderConvertAPP {

    OrderConvertAPP INSTANCE = Mappers.getMapper(OrderConvertAPP.class);

//    @Mappings({})
//    OrderQueryDTO convert(OrderPageQueryPO orderPageQueryVO);
//
//    @Mappings({})
//    OrderLogisticsUpdateDTO convert(OrderLogisticsPO orderLogisticsVO);
//
//    @Mappings({})
//    OrderItemUpdateDTO convert(OrderItemUpdatePO orderItemUpdateVO);
//
//    @Mappings({})
//    OrderCreateDTO convert(OrderCreatePO orderCreatePO);
//
//    @Mappings({})
//    List<OrderCreateDTO.OrderItem> convert(List<CartItemBO> cartItems);
//
//    default OrderCreateDTO createOrderCreateDTO(Integer userId, Integer userAddressId,
//            String remark, String ip,
//            List<CartItemBO> cartItems, Integer couponCardId) {
//        return new OrderCreateDTO()
//                .setUserId(userId)
//                .setUserAddressId(userAddressId)
//                .setRemark(remark)
//                .setIp(ip)
//                .setOrderItems(this.convert(cartItems))
//                .setCouponCardId(couponCardId);
//    }

}
