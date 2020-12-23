package cn.iocoder.mall.order.biz.convert;

import cn.iocoder.mall.order.api.bo.OrderLastLogisticsInfoBO;
import cn.iocoder.mall.order.api.bo.OrderLogisticsInfoBO;
import cn.iocoder.mall.order.api.bo.OrderLogisticsInfoWithOrderBO;
import cn.iocoder.mall.order.api.dto.OrderDeliveryDTO;
import cn.iocoder.mall.order.api.dto.OrderLogisticsUpdateDTO;
import cn.iocoder.mall.order.biz.dataobject.OrderLogisticsDO;
import cn.iocoder.mall.order.biz.dataobject.OrderLogisticsDetailDO;
import cn.iocoder.mall.order.biz.dataobject.OrderRecipientDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
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
    OrderLogisticsDO convert(OrderDeliveryDTO orderDelivery);

    @Mappings({})
    OrderLogisticsDO convert(OrderLogisticsUpdateDTO orderLogisticsDTO);

    @Mappings({})
    OrderLogisticsDO convert(OrderRecipientDO orderRecipientDO);

    @Mappings({})
    List<OrderLogisticsInfoWithOrderBO.Logistics> convertLogistics(List<OrderLogisticsDO> orderLogisticsDOList);

    @Mappings({})
    List<OrderLogisticsInfoWithOrderBO.LogisticsDetail> convertLogisticsDetail(List<OrderLogisticsDetailDO> orderLogisticsDOList);

    @Mappings({})
    OrderLogisticsInfoBO convert(OrderLogisticsDO orderLogisticsDO);

    @Mappings({})
    List<OrderLogisticsInfoBO.LogisticsDetail> convert(List<OrderLogisticsDetailDO> orderLogisticsDetailDOList);

    @Mappings({})
    @Named(value = "orderLastLogisticsInfoBO")
    OrderLastLogisticsInfoBO convertOrderLastLogisticsInfoBO(OrderLogisticsDO orderLogisticsDO);

    @Mappings({})
    OrderLastLogisticsInfoBO.LogisticsDetail convertLastLogisticsDetail(OrderLogisticsDetailDO orderLogisticsDetailDO);
}
