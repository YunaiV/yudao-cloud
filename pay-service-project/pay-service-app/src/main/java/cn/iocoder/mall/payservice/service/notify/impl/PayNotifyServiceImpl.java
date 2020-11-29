package cn.iocoder.mall.payservice.service.notify.impl;

import cn.iocoder.common.framework.util.DateUtil;
import cn.iocoder.mall.payservice.convert.notify.PayNotifyConvert;
import cn.iocoder.mall.payservice.dal.mysql.dataobject.notify.PayNotifyTaskDO;
import cn.iocoder.mall.payservice.dal.mysql.dataobject.refund.PayRefundDO;
import cn.iocoder.mall.payservice.dal.mysql.dataobject.transaction.PayTransactionDO;
import cn.iocoder.mall.payservice.dal.mysql.dataobject.transaction.PayTransactionExtensionDO;
import cn.iocoder.mall.payservice.dal.mysql.mapper.notify.PayNotifyTaskMapper;
import cn.iocoder.mall.payservice.enums.notify.PayNotifyStatusEnum;
import cn.iocoder.mall.payservice.enums.notify.PayNotifyType;
import cn.iocoder.mall.payservice.mq.producer.PayMQProducer;
import cn.iocoder.mall.payservice.service.notify.PayNotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * 支付通知 Service 实现类
 */
@Service
public class PayNotifyServiceImpl implements PayNotifyService {

    @Autowired
    private PayNotifyTaskMapper payNotifyTaskMapper;

    @Autowired
    private PayMQProducer payMQProducer;

    @Override
    public void addPayRefundNotifyTask(PayRefundDO refund) {
        PayNotifyTaskDO payNotifyTaskDO = this.createBasePayNotifyTaskDO(refund.getAppId(), refund.getNotifyUrl())
                .setType(PayNotifyType.REFUND.getType());
        // 设置 Refund 属性
        payNotifyTaskDO.setRefund(new PayNotifyTaskDO.Refund().setRefundId(refund.getId())
                .setTransactionId(refund.getTransactionId()).setOrderId(refund.getOrderId()));
        // 保存到数据库
        payNotifyTaskMapper.insert(payNotifyTaskDO);

        // 发送 MQ 消息
        payMQProducer.sendPayRefundNotifyTaskMessage(PayNotifyConvert.INSTANCE.convertRefund(payNotifyTaskDO),
                refund.getId(), refund.getTransactionId(), refund.getOrderId());
    }

    @Override
    public void addPayTransactionNotifyTask(PayTransactionDO transaction, PayTransactionExtensionDO extension) {
        PayNotifyTaskDO payNotifyTaskDO = this.createBasePayNotifyTaskDO(transaction.getAppId(), transaction.getNotifyUrl())
                .setType(PayNotifyType.TRANSACTION.getType());
        // 设置 Transaction 属性
        payNotifyTaskDO.setTransaction(new PayNotifyTaskDO.Transaction().setOrderId(transaction.getOrderId())
                .setTransactionId(extension.getTransactionId()).setTransactionExtensionId(extension.getId()));
        // 保存到数据库
        payNotifyTaskMapper.insert(payNotifyTaskDO);

        // 发送 MQ 消息
        payMQProducer.sendPayTransactionNotifyTaskMessage(PayNotifyConvert.INSTANCE.convertTransaction(payNotifyTaskDO),
                transaction.getId(), transaction.getOrderId());
    }

    private PayNotifyTaskDO createBasePayNotifyTaskDO(String appId, String notifyUrl) {
        return new PayNotifyTaskDO()
                .setAppId(appId)
                .setStatus(PayNotifyStatusEnum.WAITING.getStatus())
                .setNotifyTimes(0).setMaxNotifyTimes(PayNotifyTaskDO.NOTIFY_FREQUENCY.length + 1)
                .setNextNotifyTime(DateUtil.addDate(Calendar.SECOND, PayNotifyTaskDO.NOTIFY_FREQUENCY[0]))
                .setNotifyUrl(notifyUrl);
    }

}
