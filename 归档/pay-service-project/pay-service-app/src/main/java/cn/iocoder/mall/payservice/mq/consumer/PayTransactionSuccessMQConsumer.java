package cn.iocoder.mall.payservice.mq.consumer;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.payservice.common.dubbo.DubboReferencePool;
import cn.iocoder.mall.payservice.dal.mysql.dataobject.transaction.PayTransactionDO;
import cn.iocoder.mall.payservice.dal.mysql.mapper.transaction.PayTransactionMapper;
import cn.iocoder.mall.payservice.mq.producer.message.PayTransactionSuccessMessage;
import org.apache.dubbo.rpc.service.GenericService;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;

@Service
@RocketMQMessageListener(
        topic = PayTransactionSuccessMessage.TOPIC,
        consumerGroup = "pay-consumer-group-" + PayTransactionSuccessMessage.TOPIC
)
public class PayTransactionSuccessMQConsumer extends AbstractPayNotifySuccessMQConsumer<PayTransactionSuccessMessage>
        implements RocketMQListener<PayTransactionSuccessMessage> {

    @Autowired
    private PayTransactionMapper payTransactionMapper;

    @Override
    public void onMessage(PayTransactionSuccessMessage message) {
        super.execute(message);
    }

    @Override
    protected CommonResult<Boolean> invoke(PayTransactionSuccessMessage message, DubboReferencePool.ReferenceMeta referenceMeta) {
        // 查询支付交易
        PayTransactionDO transaction = payTransactionMapper.selectById(message.getTransactionId());
        Assert.notNull(transaction, String.format("回调消息(%s) 订单交易不能为空", message.toString()));
        // 执行调用
        GenericService genericService = referenceMeta.getService();
        String methodName = referenceMeta.getMethodName();
        Object dubboResult = genericService.$invoke(methodName,
                new String[]{String.class.getName(), Integer.class.getName()},
                new Object[]{message.getOrderId(), transaction.getPrice()});
        return parseDubboGenericResult(dubboResult);
    }

    @Override
    protected void afterInvokeSuccess(PayTransactionSuccessMessage message) {
        PayTransactionDO updateTransaction = new PayTransactionDO().setId(message.getTransactionId()).setFinishTime(new Date());
        payTransactionMapper.updateById(updateTransaction);
    }

}
