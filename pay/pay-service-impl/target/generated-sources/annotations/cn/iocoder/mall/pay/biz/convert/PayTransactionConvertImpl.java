package cn.iocoder.mall.pay.biz.convert;

import cn.iocoder.mall.pay.api.bo.transaction.PayTransactionBO;
import cn.iocoder.mall.pay.api.dto.transaction.PayTransactionCreateDTO;
import cn.iocoder.mall.pay.api.dto.transaction.PayTransactionSubmitDTO;
import cn.iocoder.mall.pay.biz.dataobject.PayTransactionDO;
import cn.iocoder.mall.pay.biz.dataobject.PayTransactionExtensionDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-24T11:39:00+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class PayTransactionConvertImpl implements PayTransactionConvert {

    @Override
    public PayTransactionDO convert(PayTransactionCreateDTO payTransactionCreateDTO) {
        if ( payTransactionCreateDTO == null ) {
            return null;
        }

        PayTransactionDO payTransactionDO = new PayTransactionDO();

        payTransactionDO.setAppId( payTransactionCreateDTO.getAppId() );
        payTransactionDO.setOrderId( payTransactionCreateDTO.getOrderId() );
        payTransactionDO.setCreateIp( payTransactionCreateDTO.getCreateIp() );
        payTransactionDO.setOrderSubject( payTransactionCreateDTO.getOrderSubject() );
        payTransactionDO.setOrderDescription( payTransactionCreateDTO.getOrderDescription() );
        payTransactionDO.setOrderMemo( payTransactionCreateDTO.getOrderMemo() );
        payTransactionDO.setPrice( payTransactionCreateDTO.getPrice() );
        payTransactionDO.setExpireTime( payTransactionCreateDTO.getExpireTime() );

        return payTransactionDO;
    }

    @Override
    public PayTransactionBO convert(PayTransactionDO payTransactionDO) {
        if ( payTransactionDO == null ) {
            return null;
        }

        PayTransactionBO payTransactionBO = new PayTransactionBO();

        payTransactionBO.setId( payTransactionDO.getId() );
        payTransactionBO.setAppId( payTransactionDO.getAppId() );
        payTransactionBO.setCreateIp( payTransactionDO.getCreateIp() );
        payTransactionBO.setOrderId( payTransactionDO.getOrderId() );
        payTransactionBO.setOrderSubject( payTransactionDO.getOrderSubject() );
        payTransactionBO.setOrderDescription( payTransactionDO.getOrderDescription() );
        payTransactionBO.setOrderMemo( payTransactionDO.getOrderMemo() );
        payTransactionBO.setPrice( payTransactionDO.getPrice() );
        payTransactionBO.setStatus( payTransactionDO.getStatus() );
        payTransactionBO.setExpireTime( payTransactionDO.getExpireTime() );
        payTransactionBO.setFinishTime( payTransactionDO.getFinishTime() );
        payTransactionBO.setExtensionId( payTransactionDO.getExtensionId() );
        payTransactionBO.setPayChannel( payTransactionDO.getPayChannel() );
        payTransactionBO.setPaymentTime( payTransactionDO.getPaymentTime() );
        payTransactionBO.setNotifyTime( payTransactionDO.getNotifyTime() );
        payTransactionBO.setTradeNo( payTransactionDO.getTradeNo() );
        payTransactionBO.setCreateTime( payTransactionDO.getCreateTime() );
        payTransactionBO.setRefundTotal( payTransactionDO.getRefundTotal() );

        return payTransactionBO;
    }

    @Override
    public List<PayTransactionBO> convertList(List<PayTransactionDO> list) {
        if ( list == null ) {
            return null;
        }

        List<PayTransactionBO> list1 = new ArrayList<PayTransactionBO>( list.size() );
        for ( PayTransactionDO payTransactionDO : list ) {
            list1.add( convert( payTransactionDO ) );
        }

        return list1;
    }

    @Override
    public PayTransactionExtensionDO convert(PayTransactionSubmitDTO payTransactionSubmitDTO) {
        if ( payTransactionSubmitDTO == null ) {
            return null;
        }

        PayTransactionExtensionDO payTransactionExtensionDO = new PayTransactionExtensionDO();

        payTransactionExtensionDO.setPayChannel( payTransactionSubmitDTO.getPayChannel() );
        payTransactionExtensionDO.setCreateIp( payTransactionSubmitDTO.getCreateIp() );

        return payTransactionExtensionDO;
    }
}
