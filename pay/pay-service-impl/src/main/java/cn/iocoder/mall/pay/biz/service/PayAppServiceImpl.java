package cn.iocoder.mall.pay.biz.service;

import cn.iocoder.common.framework.constant.CommonStatusEnum;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.pay.api.constant.PayErrorCodeEnum;
import cn.iocoder.mall.pay.biz.dao.PayAppMapper;
import cn.iocoder.mall.pay.biz.dataobject.PayAppDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayAppServiceImpl {

    @Autowired
    private PayAppMapper payAppMapper;

    public CommonResult<PayAppDO> validPayApp(String appId) {
        PayAppDO payAppDO = payAppMapper.selectById(appId);
        // 校验是否存在
        if (payAppDO == null) {
            return ServiceExceptionUtil.error(PayErrorCodeEnum.PAY_APP_NOT_FOUND.getCode());
        }
        // 校验是否禁用
        if (CommonStatusEnum.DISABLE.getValue().equals(payAppDO.getStatus())) {
            return ServiceExceptionUtil.error(PayErrorCodeEnum.PAY_APP_IS_DISABLE.getCode());
        }
        return CommonResult.success(payAppDO);
    }

}