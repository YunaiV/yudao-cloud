package cn.iocoder.yudao.module.system.framework;

import cn.iocoder.yudao.framework.apilog.core.service.ApiAccessLog;
import cn.iocoder.yudao.framework.apilog.core.service.ApiAccessLogFrameworkService;
import cn.iocoder.yudao.framework.apilog.core.service.ApiErrorLog;
import cn.iocoder.yudao.framework.apilog.core.service.ApiErrorLogFrameworkService;
import cn.iocoder.yudao.module.infra.api.file.FileApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TmpConfiguration {

    @Bean
    public FileApi fileApi() {
        return new FileApi() {
            @Override
            public String createFile(String path, byte[] content) throws Exception {
                return null;
            }
        };
    }

    @Bean
    public ApiAccessLogFrameworkService apiAccessLogFrameworkService() {
        return new ApiAccessLogFrameworkService() {
            @Override
            public void createApiAccessLog(ApiAccessLog apiAccessLog) {

            }
        };
    }

    @Bean
    public ApiErrorLogFrameworkService apiErrorLogFrameworkService() {
        return new ApiErrorLogFrameworkService() {
            @Override
            public void createApiErrorLog(ApiErrorLog apiErrorLog) {

            }
        };
    }

}
