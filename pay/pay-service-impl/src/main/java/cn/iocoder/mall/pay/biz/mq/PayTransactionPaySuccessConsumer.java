package cn.iocoder.mall.pay.biz.mq;

import cn.iocoder.common.framework.util.DateUtil;
import cn.iocoder.common.framework.util.ExceptionUtil;
import cn.iocoder.mall.pay.api.constant.PayTransactionNotifyStatusEnum;
import cn.iocoder.mall.pay.biz.constant.MQConstant;
import cn.iocoder.mall.pay.biz.dao.PayTransactionMapper;
import cn.iocoder.mall.pay.biz.dao.PayTransactionNotifyLogMapper;
import cn.iocoder.mall.pay.biz.dao.PayTransactionNotifyTaskMapper;
import cn.iocoder.mall.pay.biz.dataobject.PayTransactionDO;
import cn.iocoder.mall.pay.biz.dataobject.PayTransactionNotifyLogDO;
import cn.iocoder.mall.pay.biz.dataobject.PayTransactionNotifyTaskDO;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.service.GenericService;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

@Service
@RocketMQMessageListener(
        topic = MQConstant.TOPIC_PAY_TRANSACTION_PAY_SUCCESS,
        consumerGroup = "pay-consumer-group-" + MQConstant.TOPIC_PAY_TRANSACTION_PAY_SUCCESS
)
public class PayTransactionPaySuccessConsumer implements RocketMQListener<PayTransactionPaySuccessMessage> {

    @Autowired
    private PayTransactionNotifyTaskMapper payTransactionNotifyTaskMapper;
    @Autowired
    private PayTransactionNotifyLogMapper payTransactionNotifyLogMapper;
    @Autowired
    private PayTransactionMapper payTransactionMapper;

    @Override
    @Transactional
    public void onMessage(PayTransactionPaySuccessMessage message) {
        // TODO 先简单写，后面重构

        ApplicationConfig application = new ApplicationConfig();
        application.setName("api-generic-consumer");

        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://127.0.0.1:2181");

        application.setRegistry(registry);

        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        // 弱类型接口名
        reference.setInterface("cn.iocoder.mall.pay.api.PayDemoService");
        // 声明为泛化接口
        reference.setGeneric(true);

        reference.setApplication(application);

        // 用com.alibaba.dubbo.rpc.service.GenericService可以替代所有接口引用
        GenericService genericService = reference.get(); // TODO 芋艿，要缓存，不然重复引用

        String response = null; // RPC / HTTP 调用的响应
        PayTransactionNotifyTaskDO updateTask = new PayTransactionNotifyTaskDO() // 更新 PayTransactionNotifyTaskDO 对象
                .setId(message.getId())
                .setLastExecuteTime(new Date())
                .setNotifyTimes(message.getNotifyTimes() + 1);
        try {
            response = (String) genericService.$invoke("updatePaySuccess", new String[]{String.class.getName()}, new Object[]{message.getOrderId()});
            if ("success".equals(response)) { // 情况一，请求成功且返回成功
                // 更新通知成功
                updateTask.setStatus(PayTransactionNotifyStatusEnum.SUCCESS.getValue());
                payTransactionNotifyTaskMapper.update(updateTask);
                // 需要更新支付交易单通知应用成功
                PayTransactionDO updateTransaction = new PayTransactionDO().setId(message.getTransactionId())
                        .setFinishTime(new Date());
                payTransactionMapper.update(updateTransaction, null);
            } else { // 情况二，请求成功且返回失败
                // 更新通知请求成功，但是结果失败
                handleFailure(updateTask, PayTransactionNotifyStatusEnum.REQUEST_SUCCESS.getValue());
                payTransactionNotifyTaskMapper.update(updateTask);
            }
        } catch (Throwable e) { // 请求失败
            // 更新通知请求失败
            response = ExceptionUtil.getRootCauseMessage(e);
            handleFailure(updateTask, PayTransactionNotifyStatusEnum.REQUEST_FAILURE.getValue());
            payTransactionNotifyTaskMapper.update(updateTask);
            // 抛出异常，回滚事务
            throw e;
        } finally {
            // 插入 PayTransactionNotifyLogDO 日志
            PayTransactionNotifyLogDO notifyLog = new PayTransactionNotifyLogDO().setNotifyId(message.getId())
                    .setRequest(message.getOrderId()).setResponse(response).setStatus(updateTask.getStatus());
            payTransactionNotifyLogMapper.insert(notifyLog);
        }
    }

    private void handleFailure(PayTransactionNotifyTaskDO updateTask, Integer defaultStatus) {
        if (updateTask.getNotifyTimes() >= PayTransactionNotifyTaskDO.NOTIFY_FREQUENCY.length) {
            updateTask.setStatus(PayTransactionNotifyStatusEnum.FAILURE.getValue());
        } else {
            updateTask.setNextNotifyTime(DateUtil.addDate(Calendar.SECOND, PayTransactionNotifyTaskDO.NOTIFY_FREQUENCY[updateTask.getNotifyTimes()]));
            updateTask.setStatus(defaultStatus);
        }
    }

}