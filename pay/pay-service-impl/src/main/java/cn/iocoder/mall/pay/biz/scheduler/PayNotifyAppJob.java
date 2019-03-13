package cn.iocoder.mall.pay.biz.scheduler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.springframework.stereotype.Component;

/**
 * TODO
 */
@Component
@JobHandler(value = "payNotifyAppJob")
public class PayNotifyAppJob extends IJobHandler {

    @Override
    public ReturnT<String> execute(String param) throws Exception {
        System.out.println("1");
        return null;
    }

    // TODO 需要考虑下是基于 MQ 还是 Job
    // TODO 通知频率
    // TODO rpc 泛化回调

}