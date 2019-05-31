package cn.iocoder.mall.order.application.convert;

import cn.iocoder.mall.order.api.dto.OrderDeliveryDTO;
import cn.iocoder.mall.order.application.po.admin.OrderDeliverPO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-24T11:47:18+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class OrderDeliveryConvertImpl implements OrderDeliveryConvert {

    @Override
    public OrderDeliveryDTO convert(OrderDeliverPO orderDeliverPO) {
        if ( orderDeliverPO == null ) {
            return null;
        }

        OrderDeliveryDTO orderDeliveryDTO = new OrderDeliveryDTO();

        orderDeliveryDTO.setOrderId( orderDeliverPO.getOrderId() );
        orderDeliveryDTO.setLogistics( orderDeliverPO.getLogistics() );
        orderDeliveryDTO.setLogisticsNo( orderDeliverPO.getLogisticsNo() );
        List<Integer> list = orderDeliverPO.getOrderItemIds();
        if ( list != null ) {
            orderDeliveryDTO.setOrderItemIds( new ArrayList<Integer>( list ) );
        }

        return orderDeliveryDTO;
    }
}
