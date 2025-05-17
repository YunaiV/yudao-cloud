package cn.iocoder.yudao.module.pay.job.order;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.tenant.core.job.TenantJob;
import cn.iocoder.yudao.module.pay.service.order.PayOrderService;
import com.xxl.job.core.handler.annotation.XxlJob;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 支付订单的过期 Job
 *
 * 支付超过过期时间时，支付渠道是不会通知进行过期，所以需要定时进行过期关闭。
 *
 * @author 芋道源码
 */
@Component
@Slf4j
public class PayOrderExpireJob {

    @Resource
    private PayOrderService orderService;

    @XxlJob("payOrderExpireJob")
    @TenantJob // 多租户
    public String execute(String param) {
        int count = orderService.expireOrder();
        log.info("[execute][支付过期 ({}) 个]", count);
        return StrUtil.format("支付过期 ({}) 个",count);
    }

}
