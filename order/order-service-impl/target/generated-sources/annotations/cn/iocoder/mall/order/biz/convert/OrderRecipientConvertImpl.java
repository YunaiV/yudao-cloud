package cn.iocoder.mall.order.biz.convert;

import cn.iocoder.mall.order.api.bo.OrderInfoBO.Recipient;
import cn.iocoder.mall.order.api.bo.OrderRecipientBO;
import cn.iocoder.mall.order.api.dto.OrderCreateDTO;
import cn.iocoder.mall.order.biz.dataobject.OrderRecipientDO;
import cn.iocoder.mall.user.api.bo.UserAddressBO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-24T11:39:07+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class OrderRecipientConvertImpl implements OrderRecipientConvert {

    @Override
    public OrderRecipientDO convert(OrderCreateDTO orderCreateDTO) {
        if ( orderCreateDTO == null ) {
            return null;
        }

        OrderRecipientDO orderRecipientDO = new OrderRecipientDO();

        return orderRecipientDO;
    }

    @Override
    public OrderRecipientDO convert(UserAddressBO userAddressBO) {
        if ( userAddressBO == null ) {
            return null;
        }

        OrderRecipientDO orderRecipientDO = new OrderRecipientDO();

        orderRecipientDO.setId( userAddressBO.getId() );
        orderRecipientDO.setAreaNo( userAddressBO.getAreaNo() );
        orderRecipientDO.setName( userAddressBO.getName() );
        orderRecipientDO.setMobile( userAddressBO.getMobile() );
        orderRecipientDO.setAddress( userAddressBO.getAddress() );

        return orderRecipientDO;
    }

    @Override
    public OrderRecipientBO convert(OrderRecipientDO orderRecipientDO) {
        if ( orderRecipientDO == null ) {
            return null;
        }

        OrderRecipientBO orderRecipientBO = new OrderRecipientBO();

        orderRecipientBO.setCreateTime( orderRecipientDO.getCreateTime() );
        orderRecipientBO.setUpdateTime( orderRecipientDO.getUpdateTime() );
        orderRecipientBO.setId( orderRecipientDO.getId() );
        orderRecipientBO.setOrderId( orderRecipientDO.getOrderId() );
        orderRecipientBO.setAreaNo( orderRecipientDO.getAreaNo() );
        orderRecipientBO.setName( orderRecipientDO.getName() );
        orderRecipientBO.setMobile( orderRecipientDO.getMobile() );
        orderRecipientBO.setType( orderRecipientDO.getType() );
        orderRecipientBO.setAddress( orderRecipientDO.getAddress() );

        return orderRecipientBO;
    }

    @Override
    public List<OrderRecipientBO> convert(List<OrderRecipientDO> orderRecipientDOList) {
        if ( orderRecipientDOList == null ) {
            return null;
        }

        List<OrderRecipientBO> list = new ArrayList<OrderRecipientBO>( orderRecipientDOList.size() );
        for ( OrderRecipientDO orderRecipientDO : orderRecipientDOList ) {
            list.add( convert( orderRecipientDO ) );
        }

        return list;
    }

    @Override
    public Recipient convertOrderInfoRecipient(OrderRecipientDO orderRecipientDO) {
        if ( orderRecipientDO == null ) {
            return null;
        }

        Recipient recipient = new Recipient();

        recipient.setId( orderRecipientDO.getId() );
        recipient.setOrderId( orderRecipientDO.getOrderId() );
        recipient.setAreaNo( orderRecipientDO.getAreaNo() );
        recipient.setName( orderRecipientDO.getName() );
        recipient.setMobile( orderRecipientDO.getMobile() );
        recipient.setType( orderRecipientDO.getType() );
        recipient.setAddress( orderRecipientDO.getAddress() );

        return recipient;
    }
}
