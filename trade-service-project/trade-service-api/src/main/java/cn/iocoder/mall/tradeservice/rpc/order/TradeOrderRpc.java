package cn.iocoder.mall.tradeservice.rpc.order;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.tradeservice.rpc.order.dto.TradeOrderCreateReqDTO;

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

}
