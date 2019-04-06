package cn.iocoder.mall.order.biz.convert;

import cn.iocoder.mall.order.api.bo.OrderRecipientBO;
import cn.iocoder.mall.order.api.dto.OrderCreateDTO;
import cn.iocoder.mall.order.biz.dataobject.OrderRecipientDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-04-05T22:26:02+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.1 (Oracle Corporation)"
)
public class OrderRecipientConvertImpl implements OrderRecipientConvert {

    @Override
    public OrderRecipientDO convert(OrderCreateDTO orderCreateDTO) {
        if ( orderCreateDTO == null ) {
            return null;
        }

        OrderRecipientDO orderRecipientDO = new OrderRecipientDO();

        orderRecipientDO.setAreaNo( orderCreateDTO.getAreaNo() );
        orderRecipientDO.setName( orderCreateDTO.getName() );
        orderRecipientDO.setMobile( orderCreateDTO.getMobile() );
        orderRecipientDO.setAddress( orderCreateDTO.getAddress() );

        return orderRecipientDO;
    }

    @Override
    public List<OrderRecipientBO> convert(List<OrderRecipientDO> orderRecipientDOList) {
        if ( orderRecipientDOList == null ) {
            return null;
        }

        List<OrderRecipientBO> list = new ArrayList<OrderRecipientBO>( orderRecipientDOList.size() );
        for ( OrderRecipientDO orderRecipientDO : orderRecipientDOList ) {
            list.add( orderRecipientDOToOrderRecipientBO( orderRecipientDO ) );
        }

        return list;
    }

    protected OrderRecipientBO orderRecipientDOToOrderRecipientBO(OrderRecipientDO orderRecipientDO) {
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
        orderRecipientBO.setAddress( orderRecipientDO.getAddress() );

        return orderRecipientBO;
    }
}
