package cn.iocoder.mall.tradeservice.service.order;

import cn.iocoder.mall.tradeservice.rpc.order.dto.TradeOrderCreateReqDTO;

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

}
