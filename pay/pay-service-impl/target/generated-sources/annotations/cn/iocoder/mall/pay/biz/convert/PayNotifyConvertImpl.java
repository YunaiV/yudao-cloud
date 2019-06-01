package cn.iocoder.mall.pay.biz.convert;

import cn.iocoder.mall.pay.api.message.PayRefundSuccessMessage;
import cn.iocoder.mall.pay.api.message.PayTransactionSuccessMessage;
import cn.iocoder.mall.pay.biz.dataobject.PayNotifyTaskDO;
import cn.iocoder.mall.pay.biz.dataobject.PayNotifyTaskDO.Refund;
import cn.iocoder.mall.pay.biz.dataobject.PayNotifyTaskDO.Transaction;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-24T11:39:00+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class PayNotifyConvertImpl implements PayNotifyConvert {

    @Override
    public PayTransactionSuccessMessage convertTransaction(PayNotifyTaskDO payTransactionNotifyTaskDO) {
        if ( payTransactionNotifyTaskDO == null ) {
            return null;
        }

        PayTransactionSuccessMessage payTransactionSuccessMessage = new PayTransactionSuccessMessage();

        payTransactionSuccessMessage.setOrderId( payTransactionNotifyTaskDOTransactionOrderId( payTransactionNotifyTaskDO ) );
        payTransactionSuccessMessage.setTransactionId( payTransactionNotifyTaskDOTransactionTransactionId( payTransactionNotifyTaskDO ) );
        payTransactionSuccessMessage.setId( payTransactionNotifyTaskDO.getId() );
        payTransactionSuccessMessage.setAppId( payTransactionNotifyTaskDO.getAppId() );
        payTransactionSuccessMessage.setNotifyTimes( payTransactionNotifyTaskDO.getNotifyTimes() );
        payTransactionSuccessMessage.setNotifyUrl( payTransactionNotifyTaskDO.getNotifyUrl() );

        return payTransactionSuccessMessage;
    }

    @Override
    public PayRefundSuccessMessage convertRefund(PayNotifyTaskDO payTransactionNotifyTaskDO) {
        if ( payTransactionNotifyTaskDO == null ) {
            return null;
        }

        PayRefundSuccessMessage payRefundSuccessMessage = new PayRefundSuccessMessage();

        payRefundSuccessMessage.setOrderId( payTransactionNotifyTaskDORefundOrderId( payTransactionNotifyTaskDO ) );
        payRefundSuccessMessage.setTransactionId( payTransactionNotifyTaskDORefundTransactionId( payTransactionNotifyTaskDO ) );
        payRefundSuccessMessage.setRefundId( payTransactionNotifyTaskDORefundRefundId( payTransactionNotifyTaskDO ) );
        payRefundSuccessMessage.setId( payTransactionNotifyTaskDO.getId() );
        payRefundSuccessMessage.setAppId( payTransactionNotifyTaskDO.getAppId() );
        payRefundSuccessMessage.setNotifyTimes( payTransactionNotifyTaskDO.getNotifyTimes() );
        payRefundSuccessMessage.setNotifyUrl( payTransactionNotifyTaskDO.getNotifyUrl() );

        return payRefundSuccessMessage;
    }

    private String payTransactionNotifyTaskDOTransactionOrderId(PayNotifyTaskDO payNotifyTaskDO) {
        if ( payNotifyTaskDO == null ) {
            return null;
        }
        Transaction transaction = payNotifyTaskDO.getTransaction();
        if ( transaction == null ) {
            return null;
        }
        String orderId = transaction.getOrderId();
        if ( orderId == null ) {
            return null;
        }
        return orderId;
    }

    private Integer payTransactionNotifyTaskDOTransactionTransactionId(PayNotifyTaskDO payNotifyTaskDO) {
        if ( payNotifyTaskDO == null ) {
            return null;
        }
        Transaction transaction = payNotifyTaskDO.getTransaction();
        if ( transaction == null ) {
            return null;
        }
        Integer transactionId = transaction.getTransactionId();
        if ( transactionId == null ) {
            return null;
        }
        return transactionId;
    }

    private String payTransactionNotifyTaskDORefundOrderId(PayNotifyTaskDO payNotifyTaskDO) {
        if ( payNotifyTaskDO == null ) {
            return null;
        }
        Refund refund = payNotifyTaskDO.getRefund();
        if ( refund == null ) {
            return null;
        }
        String orderId = refund.getOrderId();
        if ( orderId == null ) {
            return null;
        }
        return orderId;
    }

    private Integer payTransactionNotifyTaskDORefundTransactionId(PayNotifyTaskDO payNotifyTaskDO) {
        if ( payNotifyTaskDO == null ) {
            return null;
        }
        Refund refund = payNotifyTaskDO.getRefund();
        if ( refund == null ) {
            return null;
        }
        Integer transactionId = refund.getTransactionId();
        if ( transactionId == null ) {
            return null;
        }
        return transactionId;
    }

    private Integer payTransactionNotifyTaskDORefundRefundId(PayNotifyTaskDO payNotifyTaskDO) {
        if ( payNotifyTaskDO == null ) {
            return null;
        }
        Refund refund = payNotifyTaskDO.getRefund();
        if ( refund == null ) {
            return null;
        }
        Integer refundId = refund.getRefundId();
        if ( refundId == null ) {
            return null;
        }
        return refundId;
    }
}
