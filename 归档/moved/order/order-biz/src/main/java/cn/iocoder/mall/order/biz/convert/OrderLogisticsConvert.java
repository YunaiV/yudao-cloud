package cn.iocoder.mall.order.biz.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 订单物流 convert
 *
 * @author Sin
 * @time 2019-03-23 14:39
 */
@Mapper
public interface OrderLogisticsConvert {

    OrderLogisticsConvert INSTANCE = Mappers.getMapper(OrderLogisticsConvert.class);

//    @Mappings({})
//    OrderLogisticsDO convert(OrderDeliveryDTO orderDelivery);
//
//    @Mappings({})
//    OrderLogisticsDO convert(OrderLogisticsUpdateDTO orderLogisticsDTO);
//
//    @Mappings({})
//    OrderLogisticsDO convert(OrderRecipientDO orderRecipientDO);
//
//    @Mappings({})
//    List<OrderLogisticsInfoWithOrderBO.Logistics> convertLogistics(
//        List<OrderLogisticsDO> orderLogisticsDOList);
//
//    @Mappings({})
//    List<OrderLogisticsInfoWithOrderBO.LogisticsDetail> convertLogisticsDetail(
//        List<OrderLogisticsDetailDO> orderLogisticsDOList);
//
//    @Mappings({})
//    OrderLogisticsInfoBO convert(OrderLogisticsDO orderLogisticsDO);
//
//    @Mappings({})
//    List<OrderLogisticsInfoBO.LogisticsDetail> convert(
//        List<OrderLogisticsDetailDO> orderLogisticsDetailDOList);
//
//    @Mappings({})
//    @Named(value = "orderLastLogisticsInfoBO")
//    OrderLastLogisticsInfoBO convertOrderLastLogisticsInfoBO(OrderLogisticsDO orderLogisticsDO);
//
//    @Mappings({})
//    OrderLastLogisticsInfoBO.LogisticsDetail convertLastLogisticsDetail(
//        OrderLogisticsDetailDO orderLogisticsDetailDO);
}
