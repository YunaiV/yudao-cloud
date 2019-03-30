package cn.iocoder.mall.order.biz.convert;

import cn.iocoder.mall.order.api.dto.OrderCreateDTO;
import cn.iocoder.mall.order.api.dto.OrderReturnCreateDTO;
import cn.iocoder.mall.order.biz.dataobject.OrderReturnDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

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
}
