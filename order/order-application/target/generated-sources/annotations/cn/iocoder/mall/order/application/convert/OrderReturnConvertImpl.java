package cn.iocoder.mall.order.application.convert;

import cn.iocoder.mall.order.api.dto.OrderReturnApplyDTO;
import cn.iocoder.mall.order.api.dto.OrderReturnQueryDTO;
import cn.iocoder.mall.order.application.po.admin.OrderReturnQueryPO;
import cn.iocoder.mall.order.application.po.user.OrderReturnApplyPO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-24T11:47:18+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class OrderReturnConvertImpl implements OrderReturnConvert {

    @Override
    public OrderReturnApplyDTO convert(OrderReturnApplyPO orderReturnApplyPO) {
        if ( orderReturnApplyPO == null ) {
            return null;
        }

        OrderReturnApplyDTO orderReturnApplyDTO = new OrderReturnApplyDTO();

        orderReturnApplyDTO.setOrderId( orderReturnApplyPO.getOrderId() );
        orderReturnApplyDTO.setReason( orderReturnApplyPO.getReason() );
        orderReturnApplyDTO.setDescribe( orderReturnApplyPO.getDescribe() );
        orderReturnApplyDTO.setReturnType( orderReturnApplyPO.getReturnType() );

        return orderReturnApplyDTO;
    }

    @Override
    public OrderReturnQueryDTO convert(OrderReturnQueryPO orderReturnQueryPO) {
        if ( orderReturnQueryPO == null ) {
            return null;
        }

        OrderReturnQueryDTO orderReturnQueryDTO = new OrderReturnQueryDTO();

        orderReturnQueryDTO.setOrderId( orderReturnQueryPO.getOrderId() );
        orderReturnQueryDTO.setOrderNo( orderReturnQueryPO.getOrderNo() );
        orderReturnQueryDTO.setServiceNumber( orderReturnQueryPO.getServiceNumber() );
        orderReturnQueryDTO.setStartCreateTime( orderReturnQueryPO.getStartCreateTime() );
        orderReturnQueryDTO.setEndCreateTime( orderReturnQueryPO.getEndCreateTime() );
        orderReturnQueryDTO.setStatus( orderReturnQueryPO.getStatus() );
        orderReturnQueryDTO.setIndex( orderReturnQueryPO.getIndex() );
        orderReturnQueryDTO.setPageSize( orderReturnQueryPO.getPageSize() );

        return orderReturnQueryDTO;
    }
}
