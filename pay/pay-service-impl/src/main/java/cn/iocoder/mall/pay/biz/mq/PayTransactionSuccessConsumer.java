package cn.iocoder.mall.pay.biz.mq;

import cn.iocoder.common.framework.util.DateUtil;
import cn.iocoder.common.framework.util.ExceptionUtil;
import cn.iocoder.mall.pay.api.constant.PayTransactionNotifyStatusEnum;
import cn.iocoder.mall.pay.api.message.PayTransactionSuccessMessage;
import cn.iocoder.mall.pay.biz.dao.PayTransactionMapper;
import cn.iocoder.mall.pay.biz.dao.PayTransactionNotifyLogMapper;
import cn.iocoder.mall.pay.biz.dao.PayTransactionNotifyTaskMapper;
import cn.iocoder.mall.pay.biz.dataobject.PayNotifyTaskDO;
import cn.iocoder.mall.pay.biz.dataobject.PayTransactionDO;
import cn.iocoder.mall.pay.biz.dataobject.PayNotifyLogDO;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.Data;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Calendar;
import java.util.Date;

@Service
@RocketMQMessageListener(
        topic = PayTransactionSuccessMessage.TOPIC,
        consumerGroup = "pay-consumer-group-" + PayTransactionSuccessMessage.TOPIC
)
public class PayTransactionSuccessConsumer implements RocketMQListener<PayTransactionSuccessMessage> {

    @Data
    private class ReferenceMeta {

        private final ReferenceConfig config; // TODO 芋艿，后续需要做销毁
        private final GenericService service;
        private final String methodName;

        private ReferenceMeta(ReferenceConfig config, GenericService service, String methodName) {
            this.config = config;
            this.service = service;
            this.methodName = methodName;
        }

    }

    @Value("${dubbo.registry.address}")
    private String dubboRegistryAddress;
    @Value("${dubbo.application.name}")
    private String dubboApplicationName;

    @Autowired
    private PayTransactionNotifyTaskMapper payTransactionNotifyTaskMapper;
    @Autowired
    private PayTransactionNotifyLogMapper payTransactionNotifyLogMapper;
    @Autowired
    private PayTransactionMapper payTransactionMapper;

    private LoadingCache<String, ReferenceMeta> referenceMetaCache = CacheBuilder.newBuilder()
            .build(new CacheLoader<String, ReferenceMeta>() {
                @Override
                public ReferenceMeta load(String notifyUrl) {
                    return createGenericService(notifyUrl);
                }
            });

    private ReferenceMeta createGenericService(String notifyUrl) {
        String[] notifyUrlParts = notifyUrl.split("#");
        // 创建 ApplicationConfig 对象
        ApplicationConfig application = new ApplicationConfig();
        application.setName(dubboApplicationName);
        // 创建 RegistryConfig 对象
        RegistryConfig registry = new RegistryConfig();
//        registry.setAddress("zookeeper://127.0.0.1:2181");
        registry.setAddress(dubboRegistryAddress);
        application.setRegistry(registry);
        // 创建 ReferenceConfig 对象
        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        reference.setInterface(notifyUrlParts[0]); // 弱类型接口名
        reference.setGeneric(true); // 声明为泛化接口
        reference.setApplication(application);
        // 获得 GenericService 对象
        GenericService genericService = reference.get();
        // 构建最终的 ReferenceMeta 对象
        return new ReferenceMeta(reference, genericService, notifyUrlParts[1]);
    }

    @Override
    @Transactional
    public void onMessage(PayTransactionSuccessMessage message) {
        // 获得 ReferenceMeta 对象
        ReferenceMeta referenceMeta = referenceMetaCache.getUnchecked(message.getNotifyUrl());
        Assert.notNull(referenceMeta, String.format("notifyUrl(%s) 不存在对应的 ReferenceMeta 对象", message.getNotifyUrl()));
        GenericService genericService  = referenceMeta.getService();
        String methodName = referenceMeta.getMethodName();
        // 查询支付交易
        PayTransactionDO transaction = payTransactionMapper.selectById(message.getTransactionId());
        Assert.notNull(transaction, String.format("回调消息(%s) 订单交易不能为空", message.toString()));
        // 发起调用
        String response = null; // RPC / HTTP 调用的响应
        PayNotifyTaskDO updateTask = new PayNotifyTaskDO() // 更新 PayTransactionNotifyTaskDO 对象
                .setId(message.getId())
                .setLastExecuteTime(new Date())
                .setNotifyTimes(message.getNotifyTimes() + 1);
        try {
            response = (String) genericService.$invoke(methodName, new String[]{String.class.getName(), Integer.class.getName()},
                    new Object[]{message.getOrderId(), transaction.getPrice()});
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
            throw e; // TODO 芋艿，此处不能抛出异常。因为，会导致 MQ + 定时任务多重试。此处的目标是，事务回滚 + 吃掉事务。另外，最后的 finally 的日志，要插入成功。
        } finally {
            // 插入 PayTransactionNotifyLogDO 日志
            PayNotifyLogDO notifyLog = new PayNotifyLogDO().setNotifyId(message.getId())
                    .setRequest(message.getOrderId()).setResponse(response).setStatus(updateTask.getStatus());
            payTransactionNotifyLogMapper.insert(notifyLog);
        }
    }

    private void handleFailure(PayNotifyTaskDO updateTask, Integer defaultStatus) {
        if (updateTask.getNotifyTimes() >= PayNotifyTaskDO.NOTIFY_FREQUENCY.length) {
            updateTask.setStatus(PayTransactionNotifyStatusEnum.FAILURE.getValue());
        } else {
            updateTask.setNextNotifyTime(DateUtil.addDate(Calendar.SECOND, PayNotifyTaskDO.NOTIFY_FREQUENCY[updateTask.getNotifyTimes()]));
            updateTask.setStatus(defaultStatus);
        }
    }

}
