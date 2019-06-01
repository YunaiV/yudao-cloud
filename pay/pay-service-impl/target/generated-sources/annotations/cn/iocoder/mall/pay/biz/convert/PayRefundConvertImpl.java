package cn.iocoder.mall.pay.biz.convert;

import cn.iocoder.mall.pay.api.bo.refund.PayRefundBO;
import cn.iocoder.mall.pay.api.dto.refund.PayRefundSubmitDTO;
import cn.iocoder.mall.pay.biz.dataobject.PayRefundDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-24T11:39:00+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class PayRefundConvertImpl implements PayRefundConvert {

    @Override
    public PayRefundDO convert(PayRefundSubmitDTO payRefundSubmitDTO) {
        if ( payRefundSubmitDTO == null ) {
            return null;
        }

        PayRefundDO payRefundDO = new PayRefundDO();

        payRefundDO.setAppId( payRefundSubmitDTO.getAppId() );
        payRefundDO.setOrderId( payRefundSubmitDTO.getOrderId() );
        payRefundDO.setCreateIp( payRefundSubmitDTO.getCreateIp() );
        payRefundDO.setOrderDescription( payRefundSubmitDTO.getOrderDescription() );
        payRefundDO.setPrice( payRefundSubmitDTO.getPrice() );

        return payRefundDO;
    }

    @Override
    public PayRefundBO convert(PayRefundDO refund) {
        if ( refund == null ) {
            return null;
        }

        PayRefundBO payRefundBO = new PayRefundBO();

        payRefundBO.setId( refund.getId() );
        payRefundBO.setTransactionId( refund.getTransactionId() );
        payRefundBO.setRefundCode( refund.getRefundCode() );
        payRefundBO.setAppId( refund.getAppId() );
        payRefundBO.setOrderId( refund.getOrderId() );
        payRefundBO.setCreateIp( refund.getCreateIp() );
        payRefundBO.setOrderDescription( refund.getOrderDescription() );
        payRefundBO.setPrice( refund.getPrice() );
        payRefundBO.setStatus( refund.getStatus() );
        payRefundBO.setFinishTime( refund.getFinishTime() );
        payRefundBO.setNotifyUrl( refund.getNotifyUrl() );
        payRefundBO.setExtensionData( refund.getExtensionData() );
        payRefundBO.setRefundChannel( refund.getRefundChannel() );
        payRefundBO.setRefundTime( refund.getRefundTime() );
        payRefundBO.setNotifyTime( refund.getNotifyTime() );
        payRefundBO.setTradeNo( refund.getTradeNo() );
        payRefundBO.setCreateTime( refund.getCreateTime() );

        return payRefundBO;
    }

    @Override
    public List<PayRefundBO> convertList(List<PayRefundDO> refunds) {
        if ( refunds == null ) {
            return null;
        }

        List<PayRefundBO> list = new ArrayList<PayRefundBO>( refunds.size() );
        for ( PayRefundDO payRefundDO : refunds ) {
            list.add( convert( payRefundDO ) );
        }

        return list;
    }
}
