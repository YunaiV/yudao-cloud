package cn.iocoder.yudao.module.infra.framework;

import cn.iocoder.yudao.framework.operatelog.core.dto.OperateLogCreateReqDTO;
import cn.iocoder.yudao.framework.operatelog.core.service.OperateLogFrameworkService;
import cn.iocoder.yudao.framework.tenant.core.service.TenantFrameworkService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.Future;

@Configuration
public class TmpConfiguration {

    @Bean
    public OperateLogFrameworkService operateLogFrameworkService() {
        return new OperateLogFrameworkService() {
            @Override
            public Future<Boolean> createOperateLogAsync(OperateLogCreateReqDTO reqVO) {
                return null;
            }
        };
    }

    @Bean
    public TenantFrameworkService tenantFrameworkService() {
        return new TenantFrameworkService() {
            @Override
            public List<Long> getTenantIds() {
                return null;
            }

            @Override
            public void validTenant(Long id) {

            }
        };
    }

}
