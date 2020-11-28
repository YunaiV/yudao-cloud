package cn.iocoder.mall.payservice.rpc.transaction;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.payservice.rpc.transaction.dto.*;

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

    /**
     * 提交支付交易单
     *
     * @param submitReqDTO 提交信息
     * @return 提交响应，包含三方支付的响应
     */
    CommonResult<PayTransactionSubmitRespDTO> submitPayTransaction(PayTransactionSubmitReqDTO submitReqDTO);

    /**
     * 获得当支付交易单
     *
     * @param getReqDTO 获得条件
     * @return 支付交易单
     */
    CommonResult<PayTransactionRespDTO> getPayTransaction(PayTransactionGetReqDTO getReqDTO);

}
