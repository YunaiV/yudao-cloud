package cn.iocoder.mall.order.biz.convert;

import cn.iocoder.mall.order.api.bo.OrderReturnInfoBO;
import cn.iocoder.mall.order.api.bo.OrderReturnListBO;
import cn.iocoder.mall.order.api.dto.OrderReturnApplyDTO;
import cn.iocoder.mall.order.api.dto.OrderReturnCreateDTO;
import cn.iocoder.mall.order.biz.dataobject.OrderItemDO;
import cn.iocoder.mall.order.biz.dataobject.OrderReturnDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 订单 return
 *
 * @author Sin
 * @time 2019-03-30 15:46
 */
@Mapper
public interface OrderReturnConvert {

    OrderReturnConvert INSTANCE = Mappers.getMapper(OrderReturnConvert.class);

    @Mappings({})
    OrderReturnDO convert(OrderReturnCreateDTO orderReturnCreate);

    @Mappings({})
    OrderReturnDO convert(OrderReturnApplyDTO orderReturnApplyDTO);

    @Mappings({})
    OrderReturnInfoBO.ReturnInfo convert(OrderReturnDO orderReturnDO);

    @Mappings({})
    List<OrderReturnInfoBO.OrderItem> convert(List<OrderItemDO> orderItemDOList);

    @Mappings({})
    List<OrderReturnListBO.OrderReturn> convertListBO(List<OrderReturnDO> orderReturnDOList);
}
