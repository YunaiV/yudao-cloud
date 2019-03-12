package cn.iocoder.mall.pay.service;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.pay.api.PayTransactionService;
import cn.iocoder.mall.pay.api.bo.PayTransactionBO;
import cn.iocoder.mall.pay.api.bo.PayTransactionSubmitBO;
import cn.iocoder.mall.pay.api.constant.PayErrorCodeEnum;
import cn.iocoder.mall.pay.api.constant.PayTransactionStatusEnum;
import cn.iocoder.mall.pay.api.dto.PayTransactionCreateDTO;
import cn.iocoder.mall.pay.api.dto.PayTransactionSubmitDTO;
import cn.iocoder.mall.pay.client.PaySDKFactory;
import cn.iocoder.mall.pay.convert.PayTransactionConvert;
import cn.iocoder.mall.pay.dao.PayTransactionExtensionMapper;
import cn.iocoder.mall.pay.dao.PayTransactionMapper;
import cn.iocoder.mall.pay.dataobject.PayAppDO;
import cn.iocoder.mall.pay.dataobject.PayTransactionDO;
import cn.iocoder.mall.pay.dataobject.PayTransactionExtensionDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@com.alibaba.dubbo.config.annotation.Service(validation = "true")
public class PayServiceImpl implements PayTransactionService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PayTransactionMapper payTransactionMapper;
    @Autowired
    private PayTransactionExtensionMapper payTransactionExtensionMapper;
    @Autowired
    private PayAppServiceImpl payAppService;

    @Override
    @SuppressWarnings("Duplicates")
    public CommonResult<PayTransactionBO> createTransaction(PayTransactionCreateDTO payTransactionCreateDTO) {
        // 校验 App
        CommonResult<PayAppDO> appResult = payAppService.validPayApp(payTransactionCreateDTO.getAppId());
        if (appResult.isError()) {
            return CommonResult.error(appResult);
        }
        // 插入 PayTransactionDO
        PayTransactionDO payTransaction = payTransactionMapper.selectByAppIdAndOrderId(
                payTransactionCreateDTO.getAppId(), payTransactionCreateDTO.getOrderId());
        if (payTransaction != null) {
            logger.warn("[createTransaction][appId({}) orderId({}) exists]", payTransactionCreateDTO.getAppId(),
                    payTransactionCreateDTO.getOrderId()); // 理论来说，不会出现这个情况
            // TODO 芋艿 可能要考虑，更新订单。例如说，业务线订单可以修改价格
        } else {
            payTransaction = PayTransactionConvert.INSTANCE.convert(payTransactionCreateDTO);
            payTransaction.setStatus(PayTransactionStatusEnum.WAITTING.getValue())
                    .setNotifyUrl(appResult.getData().getNotifyUrl());
            payTransaction.setCreateTime(new Date());
            payTransactionMapper.insert(payTransaction);
        }
        // 返回成功
        return CommonResult.success(PayTransactionConvert.INSTANCE.convert(payTransaction));
    }

    @Override
    @SuppressWarnings("Duplicates")
    public CommonResult<PayTransactionSubmitBO> submitTransaction(PayTransactionSubmitDTO payTransactionSubmitDTO) {
        // TODO 校验支付渠道是否有效
        // 校验 App 是否有效
        CommonResult<PayAppDO> appResult = payAppService.validPayApp(payTransactionSubmitDTO.getAppId());
        if (appResult.isError()) {
            return CommonResult.error(appResult);
        }
        // 获得 PayTransactionDO ，并校验其是否存在
        PayTransactionDO payTransaction = payTransactionMapper.selectByAppIdAndOrderId(
                payTransactionSubmitDTO.getAppId(), payTransactionSubmitDTO.getOrderId());
        if (payTransaction == null) { // 是否存在
            return ServiceExceptionUtil.error(PayErrorCodeEnum.PAY_TRANSACTION_NOT_FOUND.getCode());
        }
        if (PayTransactionStatusEnum.WAITTING.getValue().equals(payTransaction.getStatus())) { // 校验状态
            return ServiceExceptionUtil.error(PayErrorCodeEnum.PAY_TRANSACTION_STATUS_IS_NOT_WAITING.getCode());
        }
        // 插入 PayTransactionExtensionDO
        PayTransactionExtensionDO payTransactionExtensionDO = PayTransactionConvert.INSTANCE.convert(payTransactionSubmitDTO)
                .setTransactionId(payTransaction.getId())
                .setTransactionCode("TODO")
                .setStatus(PayTransactionStatusEnum.WAITTING.getValue());
        payTransactionExtensionMapper.insert(payTransactionExtensionDO);
        // 调用三方接口
        CommonResult<String> invokeResult = PaySDKFactory.getSDK(payTransactionSubmitDTO.getPayChannel()).submitTransaction(payTransaction, payTransactionExtensionDO, null); // TODO 暂时传入 extra = null
        if (invokeResult.isError()) {
            return CommonResult.error(invokeResult);
        }
        // TODO 轮询三方接口，是否已经支付的任务
        // 返回成功
        PayTransactionSubmitBO payTransactionSubmitBO = new PayTransactionSubmitBO()
                .setId(payTransactionExtensionDO.getId()).setInvokeResponse(invokeResult.getData());
        return CommonResult.success(payTransactionSubmitBO);
    }

    @Override
    public CommonResult cancelTransaction() {
        return null;
    }

}