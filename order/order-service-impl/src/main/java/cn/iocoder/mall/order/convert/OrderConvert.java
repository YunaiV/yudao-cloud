package cn.iocoder.mall.order.convert;

import cn.iocoder.mall.order.api.dto.OrderCreateDTO;
import cn.iocoder.mall.order.api.dto.OrderCreateItemDTO;
import cn.iocoder.mall.order.dataobject.OrderDO;
import cn.iocoder.mall.order.dataobject.OrderItemDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 订单 convert
 *
 * @author Sin
 * @time 2019-03-17 10:14
 */
@Mapper
public interface OrderConvert {

    OrderConvert INSTANCE = Mappers.getMapper(OrderConvert.class);

    /**
     * 转换 OrderDO
     *
     * @param orderCreateDTO
     * @return
     */
    @Mappings({})
    OrderDO convert(OrderCreateDTO orderCreateDTO);

    /**
     * 转换 OrderItemDO
     *
     * @param orderCreateItemDTOList
     * @return
     */
    @Mappings({})
    List<OrderItemDO> convert(List<OrderCreateItemDTO> orderCreateItemDTOList);
}
