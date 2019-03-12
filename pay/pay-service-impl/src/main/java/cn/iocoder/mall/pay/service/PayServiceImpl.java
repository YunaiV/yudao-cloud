package cn.iocoder.mall.pay.service;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.pay.api.PayTransactionService;
import cn.iocoder.mall.pay.api.bo.PayTransactionBO;
import cn.iocoder.mall.pay.api.dto.PayTransactionCreateDTO;
import cn.iocoder.mall.pay.dao.PayTransactionMapper;
import cn.iocoder.mall.pay.dataobject.PayAppDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@com.alibaba.dubbo.config.annotation.Service(validation = "true")
public class PayServiceImpl implements PayTransactionService {

    @Autowired
    private PayTransactionMapper payTransactionMapper;
    @Autowired
    private PayAppServiceImpl payAppService;

    @Override
    public CommonResult<PayTransactionBO> createTransaction(PayTransactionCreateDTO payTransactionCreateDTO) {
        // 校验 App
        CommonResult<PayAppDO> appResult = payAppService.validPayApp(payTransactionCreateDTO.getAppId());
        if (appResult.isError()) {
            return CommonResult.error(appResult);
        }
        // 插入 PayTransactionDO
        return null;
    }

    @Override
    public CommonResult submitTransaction() {
        return null;
    }

    @Override
    public CommonResult cancelTransaction() {
        return null;
    }



}