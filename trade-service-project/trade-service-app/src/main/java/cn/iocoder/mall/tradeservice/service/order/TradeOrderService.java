package cn.iocoder.mall.tradeservice.service.order;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.tradeservice.rpc.order.dto.TradeOrderCreateReqDTO;
import cn.iocoder.mall.tradeservice.rpc.order.dto.TradeOrderPageReqDTO;
import cn.iocoder.mall.tradeservice.rpc.order.dto.TradeOrderRespDTO;

import java.util.Collection;

/**
 * 交易订单 Service 接口
 */
public interface TradeOrderService {

    /**
     * 创建交易订单
     *
     * @param createReqDTO 订单信息
     * @return 订单编号
     */
    Integer createTradeOrder(TradeOrderCreateReqDTO createReqDTO);

    /**
     * 获得订单交易
     *
     * @param tradeOrderId 订单交易编号
     * @param fields 额外返回字段，可见 {@link cn.iocoder.mall.tradeservice.enums.order.TradeOrderDetailFieldEnum}
     * @return 订单交易
     */
    TradeOrderRespDTO getTradeOrder(Integer tradeOrderId, Collection<String> fields);

    /**
     * 获得订单交易分页
     *
     * @param pageReqDTO 订单交易分页查询
     * @return 订单交易分页结果
     */
    PageResult<TradeOrderRespDTO> pageTradeOrder(TradeOrderPageReqDTO pageReqDTO);

    /**
     * 更新交易订单支付成功
     *
     * @param tradeOrderId 交易订单编号
     * @param payAmount 支付金额
     */
    void updateTradeOrderPaySuccess(Integer tradeOrderId, Integer payAmount);

}
