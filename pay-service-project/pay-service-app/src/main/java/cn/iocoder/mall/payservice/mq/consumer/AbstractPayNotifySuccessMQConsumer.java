package cn.iocoder.mall.payservice.mq.consumer;

import cn.iocoder.common.framework.util.DateUtil;
import cn.iocoder.common.framework.util.ExceptionUtil;
import cn.iocoder.mall.payservice.common.dubbo.DubboReferencePool;
import cn.iocoder.mall.payservice.dal.mysql.dataobject.notify.PayNotifyLogDO;
import cn.iocoder.mall.payservice.dal.mysql.dataobject.notify.PayNotifyTaskDO;
import cn.iocoder.mall.payservice.dal.mysql.mapper.notify.PayNotifyLogMapper;
import cn.iocoder.mall.payservice.dal.mysql.mapper.notify.PayNotifyTaskMapper;
import cn.iocoder.mall.payservice.enums.notify.PayNotifyStatusEnum;
import cn.iocoder.mall.payservice.mq.producer.message.AbstractPayNotifySuccessMessage;
import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

public abstract class AbstractPayNotifySuccessMQConsumer<T extends AbstractPayNotifySuccessMessage> implements RocketMQListener<T> {

    @Autowired
    private DubboReferencePool dubboReferencePool;

    @Autowired
    private PayNotifyTaskMapper payNotifyTaskMapper;
    @Autowired
    private PayNotifyLogMapper payTransactionNotifyLogMapper;

    @Override
    @Transactional
    public void onMessage(T message) {
        // 获得 ReferenceMeta 对象
        DubboReferencePool.ReferenceMeta referenceMeta = dubboReferencePool.getReferenceMeta(message.getNotifyUrl());
        // 发起调用
        String response = null; // RPC / HTTP 调用的响应
        PayNotifyTaskDO updateTask = new PayNotifyTaskDO() // 更新 PayTransactionNotifyTaskDO 对象
                .setId(message.getId())
                .setLastExecuteTime(new Date())
                .setNotifyTimes(message.getNotifyTimes() + 1);
        try {
            // TODO 芋艿，这里要优化下，不要在事务里，进行 RPC 调用
            response = invoke(message, referenceMeta);
            if ("success".equals(response)) { // 情况一，请求成功且返回成功
                // 更新通知成功
                updateTask.setStatus(PayNotifyStatusEnum.SUCCESS.getStatus());
                payNotifyTaskMapper.updateById(updateTask);
                // 需要更新支付交易单通知应用成功
                afterInvokeSuccess(message);
            } else { // 情况二，请求成功且返回失败
                // 更新通知请求成功，但是结果失败
                handleFailure(updateTask, PayNotifyStatusEnum.REQUEST_SUCCESS.getStatus());
                payNotifyTaskMapper.updateById(updateTask);
            }
        } catch (Throwable e) { // 请求失败
            // 更新通知请求失败
            response = ExceptionUtil.getRootCauseMessage(e);
            handleFailure(updateTask, PayNotifyStatusEnum.REQUEST_FAILURE.getStatus());
            payNotifyTaskMapper.updateById(updateTask);
            // 抛出异常，回滚事务
            // TODO 芋艿，此处不能抛出异常。因为，会导致 MQ + 定时任务多重试。此处的目标是，事务回滚 + 吃掉事务。另外，最后的 finally 的日志，要插入成功。
//            throw e;
        } finally {
            // 插入 PayTransactionNotifyLogDO 日志
            PayNotifyLogDO notifyLog = new PayNotifyLogDO().setNotifyId(message.getId())
                    .setRequest(JSON.toJSONString(message)).setResponse(response).setStatus(updateTask.getStatus());
            payTransactionNotifyLogMapper.insert(notifyLog);
        }
    }

    private void handleFailure(PayNotifyTaskDO updateTask, Integer defaultStatus) {
        if (updateTask.getNotifyTimes() >= PayNotifyTaskDO.NOTIFY_FREQUENCY.length) {
            updateTask.setStatus(PayNotifyStatusEnum.FAILURE.getStatus());
        } else {
            updateTask.setNextNotifyTime(DateUtil.addDate(Calendar.SECOND, PayNotifyTaskDO.NOTIFY_FREQUENCY[updateTask.getNotifyTimes()]));
            updateTask.setStatus(defaultStatus);
        }
    }

    protected abstract String invoke(T message, DubboReferencePool.ReferenceMeta referenceMeta);

    protected abstract void afterInvokeSuccess(T message);

}
