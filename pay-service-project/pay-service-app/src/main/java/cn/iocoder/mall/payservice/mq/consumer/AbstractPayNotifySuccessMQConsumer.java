package cn.iocoder.mall.payservice.mq.consumer;

import cn.iocoder.common.framework.util.DateUtil;
import cn.iocoder.common.framework.util.ExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.payservice.common.dubbo.DubboReferencePool;
import cn.iocoder.mall.payservice.dal.mysql.dataobject.notify.PayNotifyLogDO;
import cn.iocoder.mall.payservice.dal.mysql.dataobject.notify.PayNotifyTaskDO;
import cn.iocoder.mall.payservice.dal.mysql.mapper.notify.PayNotifyLogMapper;
import cn.iocoder.mall.payservice.dal.mysql.mapper.notify.PayNotifyTaskMapper;
import cn.iocoder.mall.payservice.enums.notify.PayNotifyStatusEnum;
import cn.iocoder.mall.payservice.mq.producer.message.AbstractPayNotifySuccessMessage;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public abstract class AbstractPayNotifySuccessMQConsumer<T extends AbstractPayNotifySuccessMessage> {
//    implements RocketMQListener<T> TODO 芋艿，理论来说，可以实现 RocketMQListener 接口，然后 execute 作为 onMessage 的具体实现。但是新版本貌似不行，后续在排查下；

    @Autowired
    private DubboReferencePool dubboReferencePool;

    @Autowired
    private PayNotifyTaskMapper payNotifyTaskMapper;
    @Autowired
    private PayNotifyLogMapper payTransactionNotifyLogMapper;

    @Transactional
    public void execute(T message) {
        // 发起调用
        CommonResult<Boolean> invokeResult = null; // RPC / HTTP 调用的响应
        Throwable invokeException = null; //
        PayNotifyTaskDO updateTask = new PayNotifyTaskDO() // 更新 PayTransactionNotifyTaskDO 对象
                .setId(message.getId())
                .setActive(false) // 标记本地通知已经完成
                .setLastExecuteTime(new Date())
                .setNotifyTimes(message.getNotifyTimes() + 1);
        try {
            // 获得 ReferenceMeta 对象
            DubboReferencePool.ReferenceMeta referenceMeta = dubboReferencePool.getReferenceMeta(message.getNotifyUrl());
            // TODO 芋艿，这里要优化下，不要在事务里，进行 RPC 调用
            invokeResult = invoke(message, referenceMeta);
            if (invokeResult.isSuccess()) { // 情况一，请求成功且返回成功
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
            invokeException = e;
            // 更新通知请求失败
            handleFailure(updateTask, PayNotifyStatusEnum.REQUEST_FAILURE.getStatus());
            payNotifyTaskMapper.updateById(updateTask);
            // 抛出异常，回滚事务
            // TODO 芋艿，此处不能抛出异常。因为，会导致 MQ + 定时任务多重试。此处的目标是，事务回滚 + 吃掉事务。另外，最后的 finally 的日志，要插入成功。
//            throw e;
        } finally {
            // 插入 PayTransactionNotifyLogDO 日志
            PayNotifyLogDO notifyLog = new PayNotifyLogDO().setNotifyId(message.getId())
                    .setStatus(updateTask.getStatus())
                    .setRequest(JSON.toJSONString(message))
                    .setResponse(invokeResult != null ? JSON.toJSONString(invokeResult) : ExceptionUtil.getRootCauseMessage(invokeException));
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

    protected abstract CommonResult<Boolean> invoke(T message, DubboReferencePool.ReferenceMeta referenceMeta);

    protected abstract void afterInvokeSuccess(T message);

    /**
     * 将 Dubbo 泛化调用的结果，解析成 CommonResult
     *
     * 目前，约定 Dubbo 返回的结果为 CommonResult<Boolean>
     *
     * @param dubboResult Dubbo 调用结果
     * @return CommonResult 结果
     */
    protected static CommonResult<Boolean> parseDubboGenericResult(Object dubboResult) {
        // TODO 芋艿，目前暂时这么实现，未来找下更合适的
        Map<String, Object> dubboResultMap = (Map<String, Object>) dubboResult;
        CommonResult<Boolean> commonResult = new CommonResult<>();
        commonResult.setCode((Integer) dubboResultMap.get("code"));
        commonResult.setMessage((String) dubboResultMap.get("message"));
        commonResult.setData((Boolean) dubboResultMap.get("data"));
        return commonResult;
    }

}
