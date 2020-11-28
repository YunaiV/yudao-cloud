package cn.iocoder.mall.payservice.service.transaction;

import cn.iocoder.mall.payservice.rpc.transaction.dto.PayTransactionCreateReqDTO;

/**
 * 支付交易单 Service 接口
 */
public interface PayTransactionService {

    /**
     * 创建支付交易单
     *
     * @param createReqDTO 创建信息
     * @return 支付交易单号
     */
    Integer createPayTransaction(PayTransactionCreateReqDTO createReqDTO);

}
