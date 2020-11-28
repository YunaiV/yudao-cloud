package cn.iocoder.mall.payservice.service.transaction.impl;

import cn.iocoder.mall.payservice.convert.transaction.PayTransactionConvert;
import cn.iocoder.mall.payservice.dal.mysql.dataobject.transaction.PayTransactionDO;
import cn.iocoder.mall.payservice.dal.mysql.mapper.transaction.PayTransactionMapper;
import cn.iocoder.mall.payservice.enums.transaction.PayTransactionStatusEnum;
import cn.iocoder.mall.payservice.rpc.app.dto.PayAppRespDTO;
import cn.iocoder.mall.payservice.rpc.transaction.dto.PayTransactionCreateReqDTO;
import cn.iocoder.mall.payservice.service.app.PayAppService;
import cn.iocoder.mall.payservice.service.transaction.PayTransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

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

}
