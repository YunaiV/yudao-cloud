package cn.iocoder.mall.payservice.service.app.impl;

import cn.iocoder.mall.payservice.service.app.PayAppService;

/**
 * 支付应用 Service 实现类
 */
public class PayAppServiceImpl implements PayAppService {



//    public PayAppDO validPayApp(String appId) {
//        PayAppDO payAppDO = payAppMapper.selectById(appId);
//        // 校验是否存在
//        if (payAppDO == null) {
//            throw ServiceExceptionUtil.exception(PayErrorCodeEnum.PAY_APP_NOT_FOUND.getCode());
//        }
//        // 校验是否禁用
//        if (CommonStatusEnum.DISABLE.getValue().equals(payAppDO.getStatus())) {
//            throw ServiceExceptionUtil.exception(PayErrorCodeEnum.PAY_APP_IS_DISABLE.getCode());
//        }
//        return payAppDO;
//    }

}
