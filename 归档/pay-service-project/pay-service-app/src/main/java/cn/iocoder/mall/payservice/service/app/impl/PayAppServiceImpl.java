package cn.iocoder.mall.payservice.service.app.impl;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.exception.util.ServiceExceptionUtil;
import cn.iocoder.mall.payservice.convert.app.PayAppConvert;
import cn.iocoder.mall.payservice.dal.mysql.dataobject.app.PayAppDO;
import cn.iocoder.mall.payservice.dal.mysql.mapper.app.PayAppMapper;
import cn.iocoder.mall.payservice.rpc.app.dto.PayAppRespDTO;
import cn.iocoder.mall.payservice.service.app.PayAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static cn.iocoder.mall.payservice.enums.PayErrorCodeConstants.*;

/**
 * 支付应用 Service 实现类
 */
@Service
public class PayAppServiceImpl implements PayAppService {

    @Autowired
    private PayAppMapper payAppMapper;

    @Override
    public PayAppRespDTO validPayApp(String payAppId) {
        PayAppDO payAppDO = payAppMapper.selectById(payAppId);
        // 校验是否存在
        if (payAppDO == null) {
            throw ServiceExceptionUtil.exception(PAY_APP_NOT_FOUND);
        }
        // 校验是否禁用
        if (CommonStatusEnum.DISABLE.getValue().equals(payAppDO.getStatus())) {
            throw ServiceExceptionUtil.exception(PAY_APP_IS_DISABLE);
        }
        return PayAppConvert.INSTANCE.convert(payAppDO);
    }

}
