package cn.iocoder.yudao.framework.operatelog.core.service;

import cn.iocoder.yudao.module.system.api.logger.dto.OperateLogCreateReqDTO;

/**
 * API 操作日志 Framework Service 接口
 *
 * @author 陈晨成
 */
public interface OperateLogFrameworkService {

    /**
     * 创建 操作日志
     *
     * @param reqDTO API 操作日志
     */
    void createOperateLog(OperateLogCreateReqDTO reqDTO);

}
