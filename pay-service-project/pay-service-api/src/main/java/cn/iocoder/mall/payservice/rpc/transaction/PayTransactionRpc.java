package cn.iocoder.mall.payservice.rpc.transaction;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.payservice.rpc.transaction.dto.PayTransactionCreateReqDTO;

/**
 * 支付交易单 RPC 接口
 */
public interface PayTransactionRpc {

    /**
     * 创建支付交易单
     *
     * @param createReqDTO 创建信息
     * @return 支付交易单号
     */
    CommonResult<Integer> createPayTransaction(PayTransactionCreateReqDTO createReqDTO);

}
