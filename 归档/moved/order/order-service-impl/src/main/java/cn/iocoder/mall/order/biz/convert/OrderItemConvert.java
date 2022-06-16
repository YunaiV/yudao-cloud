package cn.iocoder.mall.order.biz.convert;

import cn.iocoder.mall.order.api.bo.OrderInfoBO;
import cn.iocoder.mall.order.api.bo.OrderItemBO;
import cn.iocoder.mall.order.api.dto.OrderCreateDTO;
import cn.iocoder.mall.order.api.dto.OrderItemUpdateDTO;
import cn.iocoder.mall.order.biz.dataobject.OrderItemDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 订单 item convert
 *
 * @author Sin
 * @time 2019-03-23 14:34
 */
@Mapper
public interface OrderItemConvert {

    OrderItemConvert INSTANCE = Mappers.getMapper(OrderItemConvert.class);

    @Mappings({})
    OrderItemDO convert(OrderItemUpdateDTO orderItemUpdateDTO);

    @Mappings({})
    List<OrderItemBO> convertOrderItemBO(List<OrderItemDO> orderItemDOList);

    @Mappings({})
    List<OrderItemDO> convert(List<OrderCreateDTO.OrderItem> orderCreateItemDTOList);

    @Mappings({})
    List<OrderItemBO> convertOrderItemDO(List<OrderItemDO> orderItemDOList);

    @Mappings({})
    List<OrderInfoBO.OrderItem> convertOrderInfoWithOrderItem(List<OrderItemDO> orderItemDOList);
}
