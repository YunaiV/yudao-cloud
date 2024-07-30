package cn.iocoder.yudao.framework.operatelog.core.service;

import cn.iocoder.yudao.module.system.api.logger.OperateLogApi;
import cn.iocoder.yudao.module.system.api.logger.dto.OperateLogCreateReqDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;

/**
 * API 操作日志 Framework Service 实现类
 *
 * 基于 {@link OperateLogApi} 服务，记录操作日志
 *
 * @author 陈晨成
 */
@RequiredArgsConstructor
@Slf4j
public class OperateLogFrameworkServiceImpl implements OperateLogFrameworkService {

    private final OperateLogApi operateLogApi;

    @Override
    @Async
    public void createOperateLog(OperateLogCreateReqDTO reqDTO) {
        try {
            operateLogApi.createOperateLog(reqDTO);
        } catch (Throwable ex) {
            // 由于 @Async 异步调用，这里打印下日志，更容易跟进
            log.error("[createOperateLog][url({}) log({}) 发生异常]", reqDTO.getRequestUrl(), reqDTO, ex);
        }
    }

}
