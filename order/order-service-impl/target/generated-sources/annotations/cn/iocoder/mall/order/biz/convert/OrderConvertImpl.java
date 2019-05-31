package cn.iocoder.mall.order.biz.convert;

import cn.iocoder.mall.order.api.bo.OrderBO;
import cn.iocoder.mall.order.api.bo.OrderInfoBO;
import cn.iocoder.mall.order.biz.dataobject.OrderDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-24T11:39:07+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class OrderConvertImpl implements OrderConvert {

    @Override
    public List<OrderBO> convertPageBO(List<OrderDO> orderDOList) {
        if ( orderDOList == null ) {
            return null;
        }

        List<OrderBO> list = new ArrayList<OrderBO>( orderDOList.size() );
        for ( OrderDO orderDO : orderDOList ) {
            list.add( orderDOToOrderBO( orderDO ) );
        }

        return list;
    }

    @Override
    public OrderInfoBO convert(OrderDO orderDO) {
        if ( orderDO == null ) {
            return null;
        }

        OrderInfoBO orderInfoBO = new OrderInfoBO();

        orderInfoBO.setId( orderDO.getId() );
        orderInfoBO.setOrderNo( orderDO.getOrderNo() );
        orderInfoBO.setBuyPrice( orderDO.getBuyPrice() );
        orderInfoBO.setDiscountPrice( orderDO.getDiscountPrice() );
        orderInfoBO.setLogisticsPrice( orderDO.getLogisticsPrice() );
        orderInfoBO.setPresentPrice( orderDO.getPresentPrice() );
        orderInfoBO.setPayAmount( orderDO.getPayAmount() );
        orderInfoBO.setPaymentTime( orderDO.getPaymentTime() );
        orderInfoBO.setDeliveryTime( orderDO.getDeliveryTime() );
        orderInfoBO.setReceiverTime( orderDO.getReceiverTime() );
        orderInfoBO.setClosingTime( orderDO.getClosingTime() );
        orderInfoBO.setHasReturnExchange( orderDO.getHasReturnExchange() );
        orderInfoBO.setStatus( orderDO.getStatus() );
        orderInfoBO.setRemark( orderDO.getRemark() );

        return orderInfoBO;
    }

    protected OrderBO orderDOToOrderBO(OrderDO orderDO) {
        if ( orderDO == null ) {
            return null;
        }

        OrderBO orderBO = new OrderBO();

        orderBO.setId( orderDO.getId() );
        orderBO.setUserId( orderDO.getUserId() );
        orderBO.setOrderNo( orderDO.getOrderNo() );
        orderBO.setBuyPrice( orderDO.getBuyPrice() );
        orderBO.setDiscountPrice( orderDO.getDiscountPrice() );
        orderBO.setLogisticsPrice( orderDO.getLogisticsPrice() );
        orderBO.setPresentPrice( orderDO.getPresentPrice() );
        orderBO.setPayAmount( orderDO.getPayAmount() );
        orderBO.setPaymentTime( orderDO.getPaymentTime() );
        orderBO.setDeliveryTime( orderDO.getDeliveryTime() );
        orderBO.setReceiverTime( orderDO.getReceiverTime() );
        orderBO.setClosingTime( orderDO.getClosingTime() );
        orderBO.setHasReturnExchange( orderDO.getHasReturnExchange() );
        orderBO.setStatus( orderDO.getStatus() );
        orderBO.setRemark( orderDO.getRemark() );

        return orderBO;
    }
}
