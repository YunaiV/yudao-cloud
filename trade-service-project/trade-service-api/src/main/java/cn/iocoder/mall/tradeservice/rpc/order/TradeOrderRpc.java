package cn.iocoder.mall.tradeservice.rpc.order;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.tradeservice.rpc.order.dto.TradeOrderCreateReqDTO;
import cn.iocoder.mall.tradeservice.rpc.order.dto.TradeOrderPageReqDTO;
import cn.iocoder.mall.tradeservice.rpc.order.dto.TradeOrderRespDTO;

import java.util.Collection;

/**
 * 交易订单 Rpc 接口
 */
public interface TradeOrderRpc {

    /**
     * 创建交易订单
     *
     * @param createReqDTO 订单信息
     * @return 订单编号
     */
    CommonResult<Integer> createTradeOrder(TradeOrderCreateReqDTO createReqDTO);

    /**
     * 获得订单交易
     *
     * @param tradeOrderId 订单交易编号
     * @param fields 额外返回字段，可见 {@link cn.iocoder.mall.tradeservice.enums.order.TradeOrderDetailFieldEnum}
     * @return 订单交易
     */
    CommonResult<TradeOrderRespDTO> getTradeOrder(Integer tradeOrderId, Collection<String> fields);

    /**
     * 获得交易订单分页
     *
     * @param pageDTO 订单交易分页查询
     * @return 订单交易分页结果
     */
    CommonResult<PageResult<TradeOrderRespDTO>> pageTradeOrder(TradeOrderPageReqDTO pageDTO);

    // TODO 芋艿：需要重构成入参是 DTO，方便后续升级；返回是 CommonResult，用于返回失败的原因

    /**
     * 更新交易订单支付成功
     *
     * 目前用于对接 pay-service 支付服务，回调该交易订单在三方支付平台，支付成功
     *
     * @param tradeOrderId 交易订单编号
     * @param payAmount 支付金额
     * @return 成功
     */
    CommonResult<Boolean> updateTradeOrderPaySuccess(String tradeOrderId, Integer payAmount);

}
