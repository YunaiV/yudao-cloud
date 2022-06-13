package cn.iocoder.yudao.module.infra.framework;

import cn.iocoder.yudao.framework.tenant.core.service.TenantFrameworkService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TmpConfiguration {

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
