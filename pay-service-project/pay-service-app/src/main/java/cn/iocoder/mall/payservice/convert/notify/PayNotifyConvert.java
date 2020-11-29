package cn.iocoder.mall.payservice.convert.notify;

import cn.iocoder.mall.payservice.dal.mysql.dataobject.notify.PayNotifyTaskDO;
import cn.iocoder.mall.payservice.mq.producer.message.PayRefundSuccessMessage;
import cn.iocoder.mall.payservice.mq.producer.message.PayTransactionSuccessMessage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PayNotifyConvert {

    PayNotifyConvert INSTANCE = Mappers.getMapper(PayNotifyConvert.class);

    PayTransactionSuccessMessage convertTransaction(PayNotifyTaskDO payTransactionNotifyTaskDO);

    PayRefundSuccessMessage convertRefund(PayNotifyTaskDO payTransactionNotifyTaskDO);

}
