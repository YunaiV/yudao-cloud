package cn.iocoder.mall.payservice.service.app;

import cn.iocoder.mall.payservice.rpc.app.dto.PayAppRespDTO;

/**
 * 支付应用 Service 接口
 */
public interface PayAppService {

    /**
     * 交易支付应用的合法性
     *
     * 如果不合法，抛出 {@link cn.iocoder.common.framework.exception.ServiceException} 业务异常
     *
     * @param payAppId 应用编号
     * @return 应用信息
     */
    PayAppRespDTO validPayApp(String payAppId);

}
