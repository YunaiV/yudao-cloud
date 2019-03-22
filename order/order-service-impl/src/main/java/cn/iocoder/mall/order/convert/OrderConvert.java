package cn.iocoder.mall.order.convert;

import cn.iocoder.mall.order.api.dto.OrderCreateDTO;
import cn.iocoder.mall.order.api.dto.OrderCreateItemDTO;
import cn.iocoder.mall.order.api.dto.OrderReceiverInformationDTO;
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
     * 转换 OrderDO - OrderCreateDTO
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
     * 转换 OrderDO - orderReceiverInformationDTO
     *
     * @param orderReceiverInformationDTO
     * @return
     */
    @Mappings({})
    OrderDO convert(OrderReceiverInformationDTO orderReceiverInformationDTO);
}
