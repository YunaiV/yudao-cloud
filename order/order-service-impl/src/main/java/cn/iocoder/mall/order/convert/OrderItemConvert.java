package cn.iocoder.mall.order.convert;

import cn.iocoder.mall.order.api.dto.OrderCreateItemDTO;
import cn.iocoder.mall.order.api.bo.OrderItemBO;
import cn.iocoder.mall.order.api.dto.OrderItemUpdateDTO;
import cn.iocoder.mall.order.dataobject.OrderItemDO;
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
    List<OrderItemDO> convert(List<OrderCreateItemDTO> orderCreateItemDTOList);

    @Mappings({})
    List<OrderItemBO> convertOrderItemDO(List<OrderItemDO> orderItemDOList);
}
