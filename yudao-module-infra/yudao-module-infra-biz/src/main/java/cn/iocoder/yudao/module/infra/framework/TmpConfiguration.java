package cn.iocoder.yudao.module.infra.framework;

import cn.iocoder.yudao.framework.apilog.core.service.ApiAccessLogFrameworkService;
import cn.iocoder.yudao.framework.apilog.core.service.ApiErrorLogFrameworkService;
import cn.iocoder.yudao.framework.apilog.core.service.dto.ApiAccessLogCreateReqDTO;
import cn.iocoder.yudao.framework.apilog.core.service.dto.ApiErrorLogCreateReqDTO;
import cn.iocoder.yudao.framework.operatelog.core.dto.OperateLogCreateReqDTO;
import cn.iocoder.yudao.framework.operatelog.core.service.OperateLogFrameworkService;
import cn.iocoder.yudao.module.infra.api.file.FileApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

}
