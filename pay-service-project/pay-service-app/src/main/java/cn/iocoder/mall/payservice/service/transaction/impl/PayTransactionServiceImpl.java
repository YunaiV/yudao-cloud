package cn.iocoder.mall.payservice.service.transaction.impl;

import cn.iocoder.common.framework.exception.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.util.DateUtil;
import cn.iocoder.common.framework.util.MathUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.payservice.client.thirdpay.AbstractThirdPayClient;
import cn.iocoder.mall.payservice.client.thirdpay.ThirdPayClientFactory;
import cn.iocoder.mall.payservice.client.thirdpay.dto.ThirdPayTransactionSuccessRespDTO;
import cn.iocoder.mall.payservice.convert.transaction.PayTransactionConvert;
import cn.iocoder.mall.payservice.dal.mysql.dataobject.transaction.PayTransactionDO;
import cn.iocoder.mall.payservice.dal.mysql.dataobject.transaction.PayTransactionExtensionDO;
import cn.iocoder.mall.payservice.dal.mysql.mapper.transaction.PayTransactionExtensionMapper;
import cn.iocoder.mall.payservice.dal.mysql.mapper.transaction.PayTransactionMapper;
import cn.iocoder.mall.payservice.enums.transaction.PayTransactionStatusEnum;
import cn.iocoder.mall.payservice.rpc.app.dto.PayAppRespDTO;
import cn.iocoder.mall.payservice.rpc.transaction.dto.*;
import cn.iocoder.mall.payservice.service.app.PayAppService;
import cn.iocoder.mall.payservice.service.notify.PayNotifyService;
import cn.iocoder.mall.payservice.service.transaction.PayTransactionService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

import static cn.iocoder.mall.payservice.enums.PayErrorCodeConstants.*;

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
    @Autowired
    private PayNotifyService payNotifyService;

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
            .setStatus(PayTransactionStatusEnum.WAITING.getStatus())
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
        if (!PayTransactionStatusEnum.WAITING.getStatus().equals(payTransaction.getStatus())) { // 校验状态，必须是待支付
            throw ServiceExceptionUtil.exception(PAY_TRANSACTION_STATUS_IS_NOT_WAITING);
        }

        // 插入 PayTransactionExtensionDO
        PayTransactionExtensionDO payTransactionExtensionDO = PayTransactionConvert.INSTANCE.convert(submitReqDTO)
                .setTransactionId(payTransaction.getId()).setTransactionCode(generateTransactionCode())
                .setStatus(PayTransactionStatusEnum.WAITING.getStatus());
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

    @Override
    @Transactional
    public Boolean updateTransactionPaySuccess(Integer payChannel, String params) {
        // TODO 芋艿，记录回调日志
        // 解析传入的参数，成 ThirdPayTransactionSuccessRespDTO 对象
        AbstractThirdPayClient thirdPayClient = ThirdPayClientFactory.getThirdPayClient(payChannel);
        CommonResult<ThirdPayTransactionSuccessRespDTO> paySuccessResult = thirdPayClient.parseTransactionSuccessParams(params);
        paySuccessResult.checkError();

        // TODO 芋艿，先最严格的校验。即使调用方重复调用，实际哪个订单已经被重复回调的支付，也返回 false 。也没问题，因为实际已经回调成功了。
        // 1.1 查询 PayTransactionExtensionDO
        PayTransactionExtensionDO extension = payTransactionExtensionMapper.selectByTransactionCode(paySuccessResult.getData().getTransactionCode());
        if (extension == null) {
            throw ServiceExceptionUtil.exception(PAY_TRANSACTION_EXTENSION_NOT_FOUND);
        }
        if (!PayTransactionStatusEnum.WAITING.getStatus().equals(extension.getStatus())) { // 校验状态，必须是待支付
            throw ServiceExceptionUtil.exception(PAY_TRANSACTION_EXTENSION_STATUS_IS_NOT_WAITING);
        }
        // 1.2 更新 PayTransactionExtensionDO
        PayTransactionExtensionDO updatePayTransactionExtension = new PayTransactionExtensionDO()
                .setId(extension.getId())
                .setStatus(PayTransactionStatusEnum.SUCCESS.getStatus())
                .setExtensionData(params);
        int updateCounts = payTransactionExtensionMapper.update(updatePayTransactionExtension, PayTransactionStatusEnum.WAITING.getStatus());
        if (updateCounts == 0) { // 校验状态，必须是待支付
            throw ServiceExceptionUtil.exception(PAY_TRANSACTION_EXTENSION_STATUS_IS_NOT_WAITING);
        }
        log.info("[updateTransactionPaySuccess][PayTransactionExtensionDO({}) 更新为已支付]", extension.getId());

        // 2.1 判断 PayTransactionDO 是否处于待支付
        PayTransactionDO transaction = payTransactionMapper.selectById(extension.getTransactionId());
        if (transaction == null) {
            throw ServiceExceptionUtil.exception(PAY_TRANSACTION_NOT_FOUND);
        }
        if (!PayTransactionStatusEnum.WAITING.getStatus().equals(transaction.getStatus())) { // 校验状态，必须是待支付
            throw ServiceExceptionUtil.exception(PAY_TRANSACTION_STATUS_IS_NOT_WAITING);
        }
        // 2.2 更新 PayTransactionDO
        PayTransactionDO updatePayTransaction = new PayTransactionDO()
                .setId(transaction.getId())
                .setStatus(PayTransactionStatusEnum.SUCCESS.getStatus())
                .setExtensionId(extension.getId())
                .setPayChannel(payChannel)
                .setPaymentTime(paySuccessResult.getData().getPaymentTime())
                .setNotifyTime(new Date())
                .setTradeNo(paySuccessResult.getData().getTradeNo());
        updateCounts = payTransactionMapper.update(updatePayTransaction, PayTransactionStatusEnum.WAITING.getStatus());
        if (updateCounts == 0) { // 校验状态，必须是待支付
            throw ServiceExceptionUtil.exception(PAY_TRANSACTION_STATUS_IS_NOT_WAITING);
        }
        log.info("[updateTransactionPaySuccess][PayTransactionDO({}) 更新为已支付]", transaction.getId());

        // 3 新增 PayNotifyTaskDO 注释原因，参见 PayRefundSuccessConsumer 类。
        payNotifyService.addPayTransactionNotifyTask(transaction, extension);
        // 返回结果
        return true;
    }

    @Override
    public PageResult<PayTransactionRespDTO> pagePayTransaction(PayTransactionPageReqDTO pageReqDTO) {
        IPage<PayTransactionDO> payTransactionDOPage = payTransactionMapper.selectPage(pageReqDTO);
        return PayTransactionConvert.INSTANCE.convertPage(payTransactionDOPage);
    }

    @Override
    public boolean updateTransactionPriceTotalIncr(Integer payTransactionId, Integer incr) {
        return payTransactionMapper.updatePriceTotalIncr(payTransactionId, incr) > 0;
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

//    CommonResult cancelTransaction(); // TODO 1. params 2. result

}
