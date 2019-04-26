package cn.iocoder.mall.pay.biz.service;

import cn.iocoder.common.framework.util.DateUtil;
import cn.iocoder.mall.pay.api.constant.PayNotifyType;
import cn.iocoder.mall.pay.api.constant.PayTransactionNotifyStatusEnum;
import cn.iocoder.mall.pay.api.message.PayTransactionSuccessMessage;
import cn.iocoder.mall.pay.biz.convert.PayTransactionConvert;
import cn.iocoder.mall.pay.biz.dao.PayTransactionNotifyTaskMapper;
import cn.iocoder.mall.pay.biz.dataobject.PayNotifyTaskDO;
import cn.iocoder.mall.pay.biz.dataobject.PayRefundDO;
import cn.iocoder.mall.pay.biz.dataobject.PayTransactionDO;
import cn.iocoder.mall.pay.biz.dataobject.PayTransactionExtensionDO;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;

@Service
public class PayNotifyServiceImpl {

    @Autowired
    private PayTransactionNotifyTaskMapper payTransactionNotifyTaskMapper;

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public void addRefundNotifyTask(PayRefundDO refund) {
        PayNotifyTaskDO payTransactionNotifyTask = this.createBasePayNotifyTaskDO(refund.getAppId(), refund.getNotifyUrl())
                .setType(PayNotifyType.REFUND.getValue());
        // 设置 Refund 属性
        payTransactionNotifyTask.setRefund(new PayNotifyTaskDO.Refund().setRefundId(refund.getId())
                .setTransactionId(refund.getTransactionId()).setOrderId(refund.getOrderId()));
        // 保存到数据库
        payTransactionNotifyTaskMapper.insert(payTransactionNotifyTask);
        // 发送 MQ 消息
        rocketMQTemplate.convertAndSend(PayTransactionSuccessMessage.TOPIC,
                PayTransactionConvert.INSTANCE.convert(payTransactionNotifyTask));
    }

    public void addTransactionNotifyTask(PayTransactionDO transaction, PayTransactionExtensionDO extension) {
        PayNotifyTaskDO payTransactionNotifyTask = this.createBasePayNotifyTaskDO(transaction.getAppId(), transaction.getNotifyUrl())
                .setType(PayNotifyType.TRANSACTION.getValue());
        // 设置 Transaction 属性
        payTransactionNotifyTask.setTransaction(new PayNotifyTaskDO.Transaction().setOrderId(transaction.getOrderId())
                .setTransactionId(extension.getTransactionId()).setTransactionExtensionId(extension.getId()));
        payTransactionNotifyTaskMapper.insert(payTransactionNotifyTask);
        // 3.2 发送 MQ
        rocketMQTemplate.convertAndSend(PayTransactionSuccessMessage.TOPIC,
                PayTransactionConvert.INSTANCE.convert(payTransactionNotifyTask));
    }

    private PayNotifyTaskDO createBasePayNotifyTaskDO(String appId, String notifyUrl) {
        return new PayNotifyTaskDO()
                .setAppId(appId)
                .setStatus(PayTransactionNotifyStatusEnum.WAITING.getValue())
                .setNotifyTimes(0).setMaxNotifyTimes(PayNotifyTaskDO.NOTIFY_FREQUENCY.length + 1)
                .setNextNotifyTime(DateUtil.addDate(Calendar.SECOND, PayNotifyTaskDO.NOTIFY_FREQUENCY[0]))
                .setNotifyUrl(notifyUrl);
    }

}
