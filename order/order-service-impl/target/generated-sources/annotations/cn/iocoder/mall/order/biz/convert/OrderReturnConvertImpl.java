package cn.iocoder.mall.order.biz.convert;

import cn.iocoder.mall.order.api.bo.OrderReturnInfoBO.OrderItem;
import cn.iocoder.mall.order.api.bo.OrderReturnInfoBO.ReturnInfo;
import cn.iocoder.mall.order.api.bo.OrderReturnListBO.OrderReturn;
import cn.iocoder.mall.order.api.dto.OrderReturnApplyDTO;
import cn.iocoder.mall.order.api.dto.OrderReturnCreateDTO;
import cn.iocoder.mall.order.biz.dataobject.OrderItemDO;
import cn.iocoder.mall.order.biz.dataobject.OrderReturnDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-24T11:39:07+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class OrderReturnConvertImpl implements OrderReturnConvert {

    @Override
    public OrderReturnDO convert(OrderReturnCreateDTO orderReturnCreate) {
        if ( orderReturnCreate == null ) {
            return null;
        }

        OrderReturnDO orderReturnDO = new OrderReturnDO();

        orderReturnDO.setOrderId( orderReturnCreate.getOrderId() );

        return orderReturnDO;
    }

    @Override
    public OrderReturnDO convert(OrderReturnApplyDTO orderReturnApplyDTO) {
        if ( orderReturnApplyDTO == null ) {
            return null;
        }

        OrderReturnDO orderReturnDO = new OrderReturnDO();

        orderReturnDO.setOrderId( orderReturnApplyDTO.getOrderId() );
        orderReturnDO.setReason( orderReturnApplyDTO.getReason() );
        orderReturnDO.setDescribe( orderReturnApplyDTO.getDescribe() );

        return orderReturnDO;
    }

    @Override
    public ReturnInfo convert(OrderReturnDO orderReturnDO) {
        if ( orderReturnDO == null ) {
            return null;
        }

        ReturnInfo returnInfo = new ReturnInfo();

        returnInfo.setId( orderReturnDO.getId() );
        returnInfo.setServiceNumber( orderReturnDO.getServiceNumber() );
        returnInfo.setOrderId( orderReturnDO.getOrderId() );
        returnInfo.setOrderNo( orderReturnDO.getOrderNo() );
        returnInfo.setOrderLogisticsId( orderReturnDO.getOrderLogisticsId() );
        returnInfo.setRefundPrice( orderReturnDO.getRefundPrice() );
        returnInfo.setReason( orderReturnDO.getReason() );
        returnInfo.setDescribe( orderReturnDO.getDescribe() );
        returnInfo.setApprovalTime( orderReturnDO.getApprovalTime() );
        returnInfo.setLogisticsTime( orderReturnDO.getLogisticsTime() );
        returnInfo.setReceiverTime( orderReturnDO.getReceiverTime() );
        returnInfo.setClosingTime( orderReturnDO.getClosingTime() );
        returnInfo.setServiceType( orderReturnDO.getServiceType() );
        returnInfo.setStatus( orderReturnDO.getStatus() );

        return returnInfo;
    }

    @Override
    public List<OrderItem> convert(List<OrderItemDO> orderItemDOList) {
        if ( orderItemDOList == null ) {
            return null;
        }

        List<OrderItem> list = new ArrayList<OrderItem>( orderItemDOList.size() );
        for ( OrderItemDO orderItemDO : orderItemDOList ) {
            list.add( orderItemDOToOrderItem( orderItemDO ) );
        }

        return list;
    }

    @Override
    public List<OrderReturn> convertListBO(List<OrderReturnDO> orderReturnDOList) {
        if ( orderReturnDOList == null ) {
            return null;
        }

        List<OrderReturn> list = new ArrayList<OrderReturn>( orderReturnDOList.size() );
        for ( OrderReturnDO orderReturnDO : orderReturnDOList ) {
            list.add( orderReturnDOToOrderReturn( orderReturnDO ) );
        }

        return list;
    }

    protected OrderItem orderItemDOToOrderItem(OrderItemDO orderItemDO) {
        if ( orderItemDO == null ) {
            return null;
        }

        OrderItem orderItem = new OrderItem();

        orderItem.setSkuId( orderItemDO.getSkuId() );
        orderItem.setSkuName( orderItemDO.getSkuName() );
        orderItem.setSkuImage( orderItemDO.getSkuImage() );
        orderItem.setQuantity( orderItemDO.getQuantity() );
        orderItem.setPresentTotal( orderItemDO.getPresentTotal() );

        return orderItem;
    }

    protected OrderReturn orderReturnDOToOrderReturn(OrderReturnDO orderReturnDO) {
        if ( orderReturnDO == null ) {
            return null;
        }

        OrderReturn orderReturn = new OrderReturn();

        orderReturn.setId( orderReturnDO.getId() );
        orderReturn.setServiceNumber( orderReturnDO.getServiceNumber() );
        orderReturn.setOrderId( orderReturnDO.getOrderId() );
        orderReturn.setOrderNo( orderReturnDO.getOrderNo() );
        orderReturn.setOrderLogisticsId( orderReturnDO.getOrderLogisticsId() );
        orderReturn.setRefundPrice( orderReturnDO.getRefundPrice() );
        orderReturn.setReason( orderReturnDO.getReason() );
        orderReturn.setDescribe( orderReturnDO.getDescribe() );
        orderReturn.setApprovalTime( orderReturnDO.getApprovalTime() );
        orderReturn.setLogisticsTime( orderReturnDO.getLogisticsTime() );
        orderReturn.setReceiverTime( orderReturnDO.getReceiverTime() );
        orderReturn.setClosingTime( orderReturnDO.getClosingTime() );
        orderReturn.setServiceType( orderReturnDO.getServiceType() );
        orderReturn.setStatus( orderReturnDO.getStatus() );
        orderReturn.setCreateTime( orderReturnDO.getCreateTime() );
        orderReturn.setUpdateTime( orderReturnDO.getUpdateTime() );

        return orderReturn;
    }
}
