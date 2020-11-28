package cn.iocoder.mall.payservice.service.transaction.impl;

import cn.iocoder.common.framework.exception.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.util.DateUtil;
import cn.iocoder.common.framework.util.MathUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.payservice.client.thirdpay.AbstractThirdPayClient;
import cn.iocoder.mall.payservice.client.thirdpay.ThirdPayClientFactory;
import cn.iocoder.mall.payservice.convert.transaction.PayTransactionConvert;
import cn.iocoder.mall.payservice.dal.mysql.dataobject.transaction.PayTransactionDO;
import cn.iocoder.mall.payservice.dal.mysql.dataobject.transaction.PayTransactionExtensionDO;
import cn.iocoder.mall.payservice.dal.mysql.mapper.transaction.PayTransactionExtensionMapper;
import cn.iocoder.mall.payservice.dal.mysql.mapper.transaction.PayTransactionMapper;
import cn.iocoder.mall.payservice.enums.transaction.PayTransactionStatusEnum;
import cn.iocoder.mall.payservice.rpc.app.dto.PayAppRespDTO;
import cn.iocoder.mall.payservice.rpc.transaction.dto.*;
import cn.iocoder.mall.payservice.service.app.PayAppService;
import cn.iocoder.mall.payservice.service.transaction.PayTransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

import static cn.iocoder.mall.payservice.enums.PayErrorCodeConstants.PAY_TRANSACTION_NOT_FOUND;
import static cn.iocoder.mall.payservice.enums.PayErrorCodeConstants.PAY_TRANSACTION_STATUS_IS_NOT_WAITING;

/**
* 支付交易单 Service 实现类
*/
@Service
@Validated
@Slf4j
public class PayTransactionServiceImpl implements PayTransactionService {

    @Autowired
    private PayTransactionMapper payTransactionMapper;
    @Autowired
    private PayTransactionExtensionMapper payTransactionExtensionMapper;

    @Autowired
    private PayAppService payAppService;

    @Override
    public Integer createPayTransaction(PayTransactionCreateReqDTO createReqDTO) {
        // 校验 App
        PayAppRespDTO payAppRespDTO = payAppService.validPayApp(createReqDTO.getAppId());

        // 查询对应的支付交易单是否已经存在。如果是，则直接返回
        PayTransactionDO payTransaction = payTransactionMapper.selectByAppIdAndOrderId(
                createReqDTO.getAppId(), createReqDTO.getOrderId());
        if (payTransaction != null) {
            log.warn("[createTransaction][appId({}) orderId({}) 已经存在对应的支付交易单({})]", createReqDTO.getAppId(),
                    createReqDTO.getOrderId(), payTransaction.toString()); // 理论来说，不会出现这个情况
            return payTransaction.getId();
        }

        // 创建支付交易单
        payTransaction = PayTransactionConvert.INSTANCE.convert(createReqDTO)
            .setStatus(PayTransactionStatusEnum.WAITING.getValue())
            .setNotifyUrl(payAppRespDTO.getPayNotifyUrl());
        payTransactionMapper.insert(payTransaction);
        // 最终返回
        return payTransaction.getId();
    }

    @Override
    public PayTransactionSubmitRespDTO submitPayTransaction(PayTransactionSubmitReqDTO submitReqDTO) {
        // TODO 校验支付渠道是否有效
        // 校验 App 是否有效
        payAppService.validPayApp(submitReqDTO.getAppId());

        // 获得 PayTransactionDO ，并校验其是否存在
        PayTransactionDO payTransaction = payTransactionMapper.selectByAppIdAndOrderId(
                submitReqDTO.getAppId(), submitReqDTO.getOrderId());
        if (payTransaction == null) { // 是否存在
            throw ServiceExceptionUtil.exception(PAY_TRANSACTION_NOT_FOUND);
        }
        if (!PayTransactionStatusEnum.WAITING.getValue().equals(payTransaction.getStatus())) { // 校验状态，必须是待支付
            throw ServiceExceptionUtil.exception(PAY_TRANSACTION_STATUS_IS_NOT_WAITING);
        }

        // 插入 PayTransactionExtensionDO
        PayTransactionExtensionDO payTransactionExtensionDO = PayTransactionConvert.INSTANCE.convert(submitReqDTO)
                .setTransactionId(payTransaction.getId()).setTransactionCode(generateTransactionCode())
                .setStatus(PayTransactionStatusEnum.WAITING.getValue());
        payTransactionExtensionMapper.insert(payTransactionExtensionDO);

        // 调用三方接口
        AbstractThirdPayClient thirdPayClient = ThirdPayClientFactory.getThirdPayClient(submitReqDTO.getPayChannel());
        CommonResult<String> invokeResult = thirdPayClient.submitTransaction(payTransaction, payTransactionExtensionDO, null); // TODO 暂时传入 extra = null
        invokeResult.checkError();

        // TODO 轮询三方接口，是否已经支付的任务
        // 返回成功
        return new PayTransactionSubmitRespDTO().setId(payTransactionExtensionDO.getId()).setInvokeResponse(invokeResult.getData());
    }

    @Override
    public PayTransactionRespDTO getPayTransaction(PayTransactionGetReqDTO getReqDTO) {
        return PayTransactionConvert.INSTANCE.convert(payTransactionMapper.selectByAppIdAndOrderId(
                getReqDTO.getAppId(), getReqDTO.getOrderId()));
    }

    private String generateTransactionCode() {
//    wx
//    2014
//    10
//    27
//    20
//    09
//    39
//    5522657
//    a690389285100
        // 目前的算法
        // 时间序列，年月日时分秒 14 位
        // 纯随机，6 位 TODO 此处估计是会有问题的，后续在调整
        return DateUtil.format(new Date(), "yyyyMMddHHmmss") + // 时间序列
                MathUtil.random(100000, 999999) // 随机。为什么是这个范围，因为偷懒
                ;
    }

}
