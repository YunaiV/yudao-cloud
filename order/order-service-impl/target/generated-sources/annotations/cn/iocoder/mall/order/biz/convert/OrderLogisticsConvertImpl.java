package cn.iocoder.mall.order.biz.convert;

import cn.iocoder.mall.order.api.bo.OrderLastLogisticsInfoBO;
import cn.iocoder.mall.order.api.bo.OrderLogisticsInfoBO;
import cn.iocoder.mall.order.api.bo.OrderLogisticsInfoWithOrderBO.Logistics;
import cn.iocoder.mall.order.api.bo.OrderLogisticsInfoWithOrderBO.LogisticsDetail;
import cn.iocoder.mall.order.api.dto.OrderDeliveryDTO;
import cn.iocoder.mall.order.api.dto.OrderLogisticsUpdateDTO;
import cn.iocoder.mall.order.biz.dataobject.OrderLogisticsDO;
import cn.iocoder.mall.order.biz.dataobject.OrderLogisticsDetailDO;
import cn.iocoder.mall.order.biz.dataobject.OrderRecipientDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-24T11:39:07+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class OrderLogisticsConvertImpl implements OrderLogisticsConvert {

    @Override
    public OrderLogisticsDO convert(OrderDeliveryDTO orderDelivery) {
        if ( orderDelivery == null ) {
            return null;
        }

        OrderLogisticsDO orderLogisticsDO = new OrderLogisticsDO();

        orderLogisticsDO.setLogistics( orderDelivery.getLogistics() );
        orderLogisticsDO.setLogisticsNo( orderDelivery.getLogisticsNo() );

        return orderLogisticsDO;
    }

    @Override
    public OrderLogisticsDO convert(OrderLogisticsUpdateDTO orderLogisticsDTO) {
        if ( orderLogisticsDTO == null ) {
            return null;
        }

        OrderLogisticsDO orderLogisticsDO = new OrderLogisticsDO();

        orderLogisticsDO.setId( orderLogisticsDTO.getId() );
        orderLogisticsDO.setAreaNo( orderLogisticsDTO.getAreaNo() );
        orderLogisticsDO.setName( orderLogisticsDTO.getName() );
        orderLogisticsDO.setMobile( orderLogisticsDTO.getMobile() );
        orderLogisticsDO.setAddress( orderLogisticsDTO.getAddress() );
        orderLogisticsDO.setLogisticsNo( orderLogisticsDTO.getLogisticsNo() );

        return orderLogisticsDO;
    }

    @Override
    public OrderLogisticsDO convert(OrderRecipientDO orderRecipientDO) {
        if ( orderRecipientDO == null ) {
            return null;
        }

        OrderLogisticsDO orderLogisticsDO = new OrderLogisticsDO();

        orderLogisticsDO.setCreateTime( orderRecipientDO.getCreateTime() );
        orderLogisticsDO.setUpdateTime( orderRecipientDO.getUpdateTime() );
        orderLogisticsDO.setId( orderRecipientDO.getId() );
        orderLogisticsDO.setAreaNo( orderRecipientDO.getAreaNo() );
        orderLogisticsDO.setName( orderRecipientDO.getName() );
        orderLogisticsDO.setMobile( orderRecipientDO.getMobile() );
        orderLogisticsDO.setAddress( orderRecipientDO.getAddress() );

        return orderLogisticsDO;
    }

    @Override
    public List<Logistics> convertLogistics(List<OrderLogisticsDO> orderLogisticsDOList) {
        if ( orderLogisticsDOList == null ) {
            return null;
        }

        List<Logistics> list = new ArrayList<Logistics>( orderLogisticsDOList.size() );
        for ( OrderLogisticsDO orderLogisticsDO : orderLogisticsDOList ) {
            list.add( orderLogisticsDOToLogistics( orderLogisticsDO ) );
        }

        return list;
    }

    @Override
    public List<LogisticsDetail> convertLogisticsDetail(List<OrderLogisticsDetailDO> orderLogisticsDOList) {
        if ( orderLogisticsDOList == null ) {
            return null;
        }

        List<LogisticsDetail> list = new ArrayList<LogisticsDetail>( orderLogisticsDOList.size() );
        for ( OrderLogisticsDetailDO orderLogisticsDetailDO : orderLogisticsDOList ) {
            list.add( orderLogisticsDetailDOToLogisticsDetail( orderLogisticsDetailDO ) );
        }

        return list;
    }

    @Override
    public OrderLogisticsInfoBO convert(OrderLogisticsDO orderLogisticsDO) {
        if ( orderLogisticsDO == null ) {
            return null;
        }

        OrderLogisticsInfoBO orderLogisticsInfoBO = new OrderLogisticsInfoBO();

        orderLogisticsInfoBO.setId( orderLogisticsDO.getId() );
        orderLogisticsInfoBO.setAreaNo( orderLogisticsDO.getAreaNo() );
        orderLogisticsInfoBO.setName( orderLogisticsDO.getName() );
        orderLogisticsInfoBO.setMobile( orderLogisticsDO.getMobile() );
        orderLogisticsInfoBO.setAddress( orderLogisticsDO.getAddress() );
        orderLogisticsInfoBO.setLogistics( orderLogisticsDO.getLogistics() );
        orderLogisticsInfoBO.setLogisticsNo( orderLogisticsDO.getLogisticsNo() );

        return orderLogisticsInfoBO;
    }

    @Override
    public List<cn.iocoder.mall.order.api.bo.OrderLogisticsInfoBO.LogisticsDetail> convert(List<OrderLogisticsDetailDO> orderLogisticsDetailDOList) {
        if ( orderLogisticsDetailDOList == null ) {
            return null;
        }

        List<cn.iocoder.mall.order.api.bo.OrderLogisticsInfoBO.LogisticsDetail> list = new ArrayList<cn.iocoder.mall.order.api.bo.OrderLogisticsInfoBO.LogisticsDetail>( orderLogisticsDetailDOList.size() );
        for ( OrderLogisticsDetailDO orderLogisticsDetailDO : orderLogisticsDetailDOList ) {
            list.add( orderLogisticsDetailDOToLogisticsDetail1( orderLogisticsDetailDO ) );
        }

        return list;
    }

    @Override
    public OrderLastLogisticsInfoBO convertOrderLastLogisticsInfoBO(OrderLogisticsDO orderLogisticsDO) {
        if ( orderLogisticsDO == null ) {
            return null;
        }

        OrderLastLogisticsInfoBO orderLastLogisticsInfoBO = new OrderLastLogisticsInfoBO();

        orderLastLogisticsInfoBO.setId( orderLogisticsDO.getId() );
        orderLastLogisticsInfoBO.setAreaNo( orderLogisticsDO.getAreaNo() );
        orderLastLogisticsInfoBO.setName( orderLogisticsDO.getName() );
        orderLastLogisticsInfoBO.setMobile( orderLogisticsDO.getMobile() );
        orderLastLogisticsInfoBO.setAddress( orderLogisticsDO.getAddress() );
        orderLastLogisticsInfoBO.setLogistics( orderLogisticsDO.getLogistics() );
        orderLastLogisticsInfoBO.setLogisticsNo( orderLogisticsDO.getLogisticsNo() );

        return orderLastLogisticsInfoBO;
    }

    @Override
    public cn.iocoder.mall.order.api.bo.OrderLastLogisticsInfoBO.LogisticsDetail convertLastLogisticsDetail(OrderLogisticsDetailDO orderLogisticsDetailDO) {
        if ( orderLogisticsDetailDO == null ) {
            return null;
        }

        cn.iocoder.mall.order.api.bo.OrderLastLogisticsInfoBO.LogisticsDetail logisticsDetail = new cn.iocoder.mall.order.api.bo.OrderLastLogisticsInfoBO.LogisticsDetail();

        logisticsDetail.setId( orderLogisticsDetailDO.getId() );
        logisticsDetail.setOrderLogisticsId( orderLogisticsDetailDO.getOrderLogisticsId() );
        logisticsDetail.setLogisticsTime( orderLogisticsDetailDO.getLogisticsTime() );
        logisticsDetail.setLogisticsInformation( orderLogisticsDetailDO.getLogisticsInformation() );

        return logisticsDetail;
    }

    protected Logistics orderLogisticsDOToLogistics(OrderLogisticsDO orderLogisticsDO) {
        if ( orderLogisticsDO == null ) {
            return null;
        }

        Logistics logistics = new Logistics();

        logistics.setId( orderLogisticsDO.getId() );
        logistics.setAreaNo( orderLogisticsDO.getAreaNo() );
        logistics.setName( orderLogisticsDO.getName() );
        logistics.setMobile( orderLogisticsDO.getMobile() );
        logistics.setAddress( orderLogisticsDO.getAddress() );
        logistics.setLogistics( orderLogisticsDO.getLogistics() );
        logistics.setLogisticsNo( orderLogisticsDO.getLogisticsNo() );

        return logistics;
    }

    protected LogisticsDetail orderLogisticsDetailDOToLogisticsDetail(OrderLogisticsDetailDO orderLogisticsDetailDO) {
        if ( orderLogisticsDetailDO == null ) {
            return null;
        }

        LogisticsDetail logisticsDetail = new LogisticsDetail();

        logisticsDetail.setId( orderLogisticsDetailDO.getId() );
        logisticsDetail.setOrderLogisticsId( orderLogisticsDetailDO.getOrderLogisticsId() );
        logisticsDetail.setLogisticsTime( orderLogisticsDetailDO.getLogisticsTime() );
        logisticsDetail.setLogisticsInformation( orderLogisticsDetailDO.getLogisticsInformation() );

        return logisticsDetail;
    }

    protected cn.iocoder.mall.order.api.bo.OrderLogisticsInfoBO.LogisticsDetail orderLogisticsDetailDOToLogisticsDetail1(OrderLogisticsDetailDO orderLogisticsDetailDO) {
        if ( orderLogisticsDetailDO == null ) {
            return null;
        }

        cn.iocoder.mall.order.api.bo.OrderLogisticsInfoBO.LogisticsDetail logisticsDetail = new cn.iocoder.mall.order.api.bo.OrderLogisticsInfoBO.LogisticsDetail();

        logisticsDetail.setId( orderLogisticsDetailDO.getId() );
        logisticsDetail.setOrderLogisticsId( orderLogisticsDetailDO.getOrderLogisticsId() );
        logisticsDetail.setLogisticsTime( orderLogisticsDetailDO.getLogisticsTime() );
        logisticsDetail.setLogisticsInformation( orderLogisticsDetailDO.getLogisticsInformation() );

        return logisticsDetail;
    }
}
