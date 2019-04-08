package cn.iocoder.mall.order.application.convert;

import cn.iocoder.mall.order.api.dto.OrderCreateDTO;
import cn.iocoder.mall.order.api.dto.OrderItemUpdateDTO;
import cn.iocoder.mall.order.api.dto.OrderLogisticsUpdateDTO;
import cn.iocoder.mall.order.api.dto.OrderQueryDTO;
import cn.iocoder.mall.order.application.po.admin.OrderItemUpdatePO;
import cn.iocoder.mall.order.application.po.admin.OrderLogisticsPO;
import cn.iocoder.mall.order.application.po.admin.OrderPageQueryPO;
import cn.iocoder.mall.order.application.po.user.OrderCreatePO;
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
    OrderQueryDTO convert(OrderPageQueryPO orderPageQueryVO);

    @Mappings({})
    OrderLogisticsUpdateDTO convert(OrderLogisticsPO orderLogisticsVO);

    @Mappings({})
    OrderItemUpdateDTO convert(OrderItemUpdatePO orderItemUpdateVO);

    @Mappings({})
    OrderCreateDTO convert(OrderCreatePO orderCreatePO);
}
