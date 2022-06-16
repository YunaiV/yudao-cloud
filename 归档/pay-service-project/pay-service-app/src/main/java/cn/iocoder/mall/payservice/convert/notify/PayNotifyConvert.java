package cn.iocoder.mall.payservice.convert.notify;

import cn.iocoder.mall.payservice.dal.mysql.dataobject.notify.PayNotifyTaskDO;
import cn.iocoder.mall.payservice.mq.producer.message.PayRefundSuccessMessage;
import cn.iocoder.mall.payservice.mq.producer.message.PayTransactionSuccessMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PayNotifyConvert {

    PayNotifyConvert INSTANCE = Mappers.getMapper(PayNotifyConvert.class);

    @Mappings({
            @Mapping(source = "transaction.transactionId", target = "transactionId"),
            @Mapping(source = "transaction.orderId", target = "orderId"),
    })
    PayTransactionSuccessMessage convertTransaction(PayNotifyTaskDO entity);

    @Mappings({
            @Mapping(source = "refund.transactionId", target = "transactionId"),
            @Mapping(source = "refund.orderId", target = "orderId"),
            @Mapping(source = "refund.refundId", target = "refundId"),
    })
    PayRefundSuccessMessage convertRefund(PayNotifyTaskDO entity);


}
