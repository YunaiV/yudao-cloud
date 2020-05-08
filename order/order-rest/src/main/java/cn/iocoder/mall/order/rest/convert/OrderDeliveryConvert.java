package cn.iocoder.mall.order.rest.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Sin
 * @time 2019-04-05 17:00
 */
@Mapper
public interface OrderDeliveryConvert {

    OrderDeliveryConvert INSTANCE = Mappers.getMapper(OrderDeliveryConvert.class);

//    @Mappings({})
//    OrderDeliveryDTO convert(OrderDeliverPO orderDeliverPO);
}
