package cn.iocoder.mall.order.biz.convert;

import cn.iocoder.mall.order.api.bo.OrderLogisticsBO;
import cn.iocoder.mall.order.api.dto.OrderCreateDTO;
import cn.iocoder.mall.order.api.dto.OrderLogisticsUpdateDTO;
import cn.iocoder.mall.order.biz.dataobject.OrderLogisticsDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 订单物流 convert
 *
 * @author Sin
 * @time 2019-03-23 14:39
 */
@Mapper
public interface OrderLogisticsConvert {

    OrderLogisticsConvert INSTANCE = Mappers.getMapper(OrderLogisticsConvert.class);

    @Mappings({})
    OrderLogisticsDO convert(OrderCreateDTO orderCreateDTO);

    @Mappings({})
    OrderLogisticsDO convert(OrderLogisticsUpdateDTO orderLogisticsDTO);

    @Mappings({})
    List<OrderLogisticsBO> convertOrderLogisticsBO(List<OrderLogisticsDO> orderLogisticsDOList);
}
