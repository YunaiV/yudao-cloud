package cn.iocoder.yudao.framework.apilog.core.service;

import cn.iocoder.yudao.module.infra.api.logger.ApiErrorLogApi;
import cn.iocoder.yudao.module.infra.api.logger.dto.ApiErrorLogCreateReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;

/**
 * API 错误日志 Framework Service 实现类
 *
 * 基于 {@link ApiErrorLogApi} 服务，记录错误日志
 *
 * @author 芋道源码
 */
@RequiredArgsConstructor
public class ApiErrorLogFrameworkServiceImpl implements ApiErrorLogFrameworkService {

    private final ApiErrorLogApi apiErrorLogApi;

    @Override
    @Async
    public void createApiErrorLog(ApiErrorLogCreateReqDTO reqDTO) {
        apiErrorLogApi.createApiErrorLog(reqDTO);
    }

}
