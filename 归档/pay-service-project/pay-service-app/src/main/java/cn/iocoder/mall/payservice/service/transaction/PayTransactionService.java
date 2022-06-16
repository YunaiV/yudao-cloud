package cn.iocoder.mall.payservice.service.transaction;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.payservice.rpc.transaction.dto.*;

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

    /**
     * 提交支付交易单
     *
     * @param submitReqDTO 提交信息
     * @return 提交响应，包含三方支付的响应
     */
    PayTransactionSubmitRespDTO submitPayTransaction(PayTransactionSubmitReqDTO submitReqDTO);

    /**
     * 获得当支付交易单
     *
     * @param getReqDTO 获得条件
     * @return 支付交易单
     */
    PayTransactionRespDTO getPayTransaction(PayTransactionGetReqDTO getReqDTO);

    /**
     * 更新交易支付成功
     *
     * 该接口用于不同支付平台，支付成功后，回调该接口
     *
     * @param payChannel 支付渠道
     * @param params 回调参数。
     *               因为不同平台，能够提供的参数不同，所以使用 String 类型统一接收，然后在使用不同的 AbstractThirdPayClient 进行处理。
     * @return 是否支付成功
     */
    Boolean updateTransactionPaySuccess(Integer payChannel, String params);

    /**
     * 获得交易支付单分页
     *
     * @param pageReqDTO 分页条件
     * @return 交易支付单分页
     */
    PageResult<PayTransactionRespDTO> pagePayTransaction(PayTransactionPageReqDTO pageReqDTO);

    /**
     * 增加交易支付单的退款总金额
     *
     * @param payTransactionId 支付交易单
     * @param incr 新增的退款金额
     * @return 是否增加成功
     */
    boolean updateTransactionPriceTotalIncr(Integer payTransactionId, Integer incr);

}
