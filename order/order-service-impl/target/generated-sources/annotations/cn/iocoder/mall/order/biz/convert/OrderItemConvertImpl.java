package cn.iocoder.mall.order.biz.convert;

import cn.iocoder.mall.order.api.bo.OrderItemBO;
import cn.iocoder.mall.order.api.dto.OrderCreateDTO.OrderItem;
import cn.iocoder.mall.order.api.dto.OrderItemUpdateDTO;
import cn.iocoder.mall.order.biz.dataobject.OrderItemDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-24T11:39:07+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class OrderItemConvertImpl implements OrderItemConvert {

    @Override
    public OrderItemDO convert(OrderItemUpdateDTO orderItemUpdateDTO) {
        if ( orderItemUpdateDTO == null ) {
            return null;
        }

        OrderItemDO orderItemDO = new OrderItemDO();

        orderItemDO.setId( orderItemUpdateDTO.getId() );
        orderItemDO.setSkuId( orderItemUpdateDTO.getSkuId() );
        orderItemDO.setQuantity( orderItemUpdateDTO.getQuantity() );

        return orderItemDO;
    }

    @Override
    public List<OrderItemBO> convertOrderItemBO(List<OrderItemDO> orderItemDOList) {
        if ( orderItemDOList == null ) {
            return null;
        }

        List<OrderItemBO> list = new ArrayList<OrderItemBO>( orderItemDOList.size() );
        for ( OrderItemDO orderItemDO : orderItemDOList ) {
            list.add( orderItemDOToOrderItemBO( orderItemDO ) );
        }

        return list;
    }

    @Override
    public List<OrderItemDO> convert(List<OrderItem> orderCreateItemDTOList) {
        if ( orderCreateItemDTOList == null ) {
            return null;
        }

        List<OrderItemDO> list = new ArrayList<OrderItemDO>( orderCreateItemDTOList.size() );
        for ( OrderItem orderItem : orderCreateItemDTOList ) {
            list.add( orderItemToOrderItemDO( orderItem ) );
        }

        return list;
    }

    @Override
    public List<OrderItemBO> convertOrderItemDO(List<OrderItemDO> orderItemDOList) {
        if ( orderItemDOList == null ) {
            return null;
        }

        List<OrderItemBO> list = new ArrayList<OrderItemBO>( orderItemDOList.size() );
        for ( OrderItemDO orderItemDO : orderItemDOList ) {
            list.add( orderItemDOToOrderItemBO( orderItemDO ) );
        }

        return list;
    }

    @Override
    public List<cn.iocoder.mall.order.api.bo.OrderInfoBO.OrderItem> convertOrderInfoWithOrderItem(List<OrderItemDO> orderItemDOList) {
        if ( orderItemDOList == null ) {
            return null;
        }

        List<cn.iocoder.mall.order.api.bo.OrderInfoBO.OrderItem> list = new ArrayList<cn.iocoder.mall.order.api.bo.OrderInfoBO.OrderItem>( orderItemDOList.size() );
        for ( OrderItemDO orderItemDO : orderItemDOList ) {
            list.add( orderItemDOToOrderItem( orderItemDO ) );
        }

        return list;
    }

    protected OrderItemBO orderItemDOToOrderItemBO(OrderItemDO orderItemDO) {
        if ( orderItemDO == null ) {
            return null;
        }

        OrderItemBO orderItemBO = new OrderItemBO();

        orderItemBO.setId( orderItemDO.getId() );
        orderItemBO.setOrderId( orderItemDO.getOrderId() );
        orderItemBO.setOrderNo( orderItemDO.getOrderNo() );
        orderItemBO.setSkuId( orderItemDO.getSkuId() );
        orderItemBO.setSkuName( orderItemDO.getSkuName() );
        orderItemBO.setSkuImage( orderItemDO.getSkuImage() );
        orderItemBO.setQuantity( orderItemDO.getQuantity() );
        orderItemBO.setOriginPrice( orderItemDO.getOriginPrice() );
        orderItemBO.setBuyPrice( orderItemDO.getBuyPrice() );
        orderItemBO.setPresentPrice( orderItemDO.getPresentPrice() );
        orderItemBO.setBuyTotal( orderItemDO.getBuyTotal() );
        orderItemBO.setDiscountTotal( orderItemDO.getDiscountTotal() );
        orderItemBO.setPresentTotal( orderItemDO.getPresentTotal() );
        orderItemBO.setPaymentTime( orderItemDO.getPaymentTime() );
        orderItemBO.setDeliveryTime( orderItemDO.getDeliveryTime() );
        orderItemBO.setReceiverTime( orderItemDO.getReceiverTime() );
        orderItemBO.setClosingTime( orderItemDO.getClosingTime() );
        orderItemBO.setHasReturnExchange( orderItemDO.getHasReturnExchange() );
        orderItemBO.setDeliveryType( orderItemDO.getDeliveryType() );
        orderItemBO.setStatus( orderItemDO.getStatus() );
        orderItemBO.setCreateTime( orderItemDO.getCreateTime() );
        orderItemBO.setUpdateTime( orderItemDO.getUpdateTime() );
        orderItemBO.setDeleted( orderItemDO.getDeleted() );

        return orderItemBO;
    }

    protected OrderItemDO orderItemToOrderItemDO(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }

        OrderItemDO orderItemDO = new OrderItemDO();

        orderItemDO.setSkuId( orderItem.getSkuId() );
        orderItemDO.setQuantity( orderItem.getQuantity() );

        return orderItemDO;
    }

    protected cn.iocoder.mall.order.api.bo.OrderInfoBO.OrderItem orderItemDOToOrderItem(OrderItemDO orderItemDO) {
        if ( orderItemDO == null ) {
            return null;
        }

        cn.iocoder.mall.order.api.bo.OrderInfoBO.OrderItem orderItem = new cn.iocoder.mall.order.api.bo.OrderInfoBO.OrderItem();

        orderItem.setSkuId( orderItemDO.getSkuId() );
        orderItem.setSkuName( orderItemDO.getSkuName() );
        orderItem.setSkuImage( orderItemDO.getSkuImage() );
        orderItem.setQuantity( orderItemDO.getQuantity() );
        orderItem.setOriginPrice( orderItemDO.getOriginPrice() );
        orderItem.setBuyPrice( orderItemDO.getBuyPrice() );
        orderItem.setPresentPrice( orderItemDO.getPresentPrice() );
        orderItem.setBuyTotal( orderItemDO.getBuyTotal() );
        orderItem.setDiscountTotal( orderItemDO.getDiscountTotal() );
        orderItem.setPresentTotal( orderItemDO.getPresentTotal() );

        return orderItem;
    }
}
