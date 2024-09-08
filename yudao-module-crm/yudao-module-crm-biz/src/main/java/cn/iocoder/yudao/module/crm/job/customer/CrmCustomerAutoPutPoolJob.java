package cn.iocoder.yudao.module.crm.job.customer;

import cn.iocoder.yudao.framework.tenant.core.job.TenantJob;
import cn.iocoder.yudao.module.crm.service.customer.CrmCustomerService;
import com.xxl.job.core.handler.annotation.XxlJob;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 客户自动掉入公海 Job
 *
 * @author 芋道源码
 */
@Component
public class CrmCustomerAutoPutPoolJob {

    @Resource
    private CrmCustomerService customerService;

    @XxlJob("customerAutoPutPoolJob")
    @TenantJob
    public String execute() {
        int count = customerService.autoPutCustomerPool();
        return String.format("掉入公海客户 %s 个", count);
    }

}