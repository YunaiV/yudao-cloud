package cn.iocoder.mall.order.application.convert;

import cn.iocoder.mall.order.api.dto.OrderItemUpdateDTO;
import cn.iocoder.mall.order.api.dto.OrderLogisticsUpdateDTO;
import cn.iocoder.mall.order.api.dto.OrderQueryDTO;
import cn.iocoder.mall.order.application.vo.OrderItemUpdateVO;
import cn.iocoder.mall.order.application.vo.OrderLogisticsVO;
import cn.iocoder.mall.order.application.vo.OrderPageQueryVO;
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
    OrderQueryDTO convertPageBO(OrderPageQueryVO orderPageQueryVO);

    @Mappings({})
    OrderLogisticsUpdateDTO convertPageBO(OrderLogisticsVO orderLogisticsVO);

    @Mappings({})
    OrderItemUpdateDTO convertPageBO(OrderItemUpdateVO orderItemUpdateVO);
}
