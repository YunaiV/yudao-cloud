package cn.iocoder.mall.order.convert;

import cn.iocoder.mall.order.api.dto.OrderCreateDTO;
import cn.iocoder.mall.order.api.dto.OrderCreateItemDTO;
import cn.iocoder.mall.order.api.dto.OrderItemUpdateDTO;
import cn.iocoder.mall.order.api.dto.OrderLogisticsDTO;
import cn.iocoder.mall.order.dataobject.OrderDO;
import cn.iocoder.mall.order.dataobject.OrderItemDO;
import cn.iocoder.mall.order.dataobject.OrderLogisticsDO;
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
     * 转换 OrderLogisticsDO - OrderCreateDTO
     *
     * @param orderCreateDTO
     * @return
     */
    @Mappings({})
    OrderLogisticsDO convert(OrderCreateDTO orderCreateDTO);

    /**
     * 转换 OrderItemDO - orderCreateItemDTOList
     *
     * @param orderCreateItemDTOList
     * @return
     */
    @Mappings({})
    List<OrderItemDO> convert(List<OrderCreateItemDTO> orderCreateItemDTOList);

    /**
     * 转换 OrderLogisticsDO - orderReceiverInformationDTO
     *
     * @param orderLogisticsDTO
     * @return
     */
    @Mappings({})
    OrderLogisticsDO convert(OrderLogisticsDTO orderLogisticsDTO);

    /**
     * 转换 OrderItemDO - orderReceiverInformationDTO
     *
     * @param orderItemUpdateDTO
     * @return
     */
    @Mappings({})
    OrderItemDO convert(OrderItemUpdateDTO orderItemUpdateDTO);
}
