package cn.iocoder.mall.order.biz.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 订单 item convert
 *
 * @author Sin
 * @time 2019-03-23 14:34
 */
@Mapper
public interface OrderItemConvert {

    OrderItemConvert INSTANCE = Mappers.getMapper(OrderItemConvert.class);

//    @Mappings({})
//    OrderItemDO convert(OrderItemUpdateDTO orderItemUpdateDTO);
//
//    @Mappings({})
//    List<OrderItemBO> convertOrderItemBO(List<OrderItemDO> orderItemDOList);
//
//    @Mappings({})
//    List<OrderItemDO> convert(List<OrderCreateDTO.OrderItem> orderCreateItemDTOList);
//
//    @Mappings({})
//    List<OrderItemBO> convertOrderItemDO(List<OrderItemDO> orderItemDOList);
//
//    @Mappings({})
//    List<OrderInfoBO.OrderItem> convertOrderInfoWithOrderItem(List<OrderItemDO> orderItemDOList);
}
