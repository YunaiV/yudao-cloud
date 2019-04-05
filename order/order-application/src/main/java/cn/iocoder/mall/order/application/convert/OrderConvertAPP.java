package cn.iocoder.mall.order.application.convert;

import cn.iocoder.mall.order.api.dto.OrderItemUpdateDTO;
import cn.iocoder.mall.order.api.dto.OrderLogisticsUpdateDTO;
import cn.iocoder.mall.order.api.dto.OrderQueryDTO;
import cn.iocoder.mall.order.application.po.OrderItemUpdatePO;
import cn.iocoder.mall.order.application.po.OrderPageQueryPO;
import cn.iocoder.mall.order.application.po.OrderLogisticsPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
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

    @Mappings({})
    OrderQueryDTO convertPageBO(OrderPageQueryPO orderPageQueryVO);

    @Mappings({})
    OrderLogisticsUpdateDTO convertPageBO(OrderLogisticsPO orderLogisticsVO);

    @Mappings({})
    OrderItemUpdateDTO convertPageBO(OrderItemUpdatePO orderItemUpdateVO);
}
