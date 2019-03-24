package cn.iocoder.mall.order.application.convert;

import cn.iocoder.mall.order.api.dto.OrderCreateDTO;
import cn.iocoder.mall.order.api.dto.OrderLogisticsUpdateDTO;
import cn.iocoder.mall.order.dataobject.OrderLogisticsDO;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * 订单物流 convert
 *
 * @author Sin
 * @time 2019-03-23 14:39
 */
public interface OrderLogisticsConvert {

    OrderLogisticsConvert INSTANCE = Mappers.getMapper(OrderLogisticsConvert.class);

    @Mappings({})
    OrderLogisticsDO convert(OrderCreateDTO orderCreateDTO);

    @Mappings({})
    OrderLogisticsDO convert(OrderLogisticsUpdateDTO orderLogisticsDTO);
}
