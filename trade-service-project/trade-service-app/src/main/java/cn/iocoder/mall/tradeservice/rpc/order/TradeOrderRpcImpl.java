package cn.iocoder.mall.tradeservice.rpc.order;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.tradeservice.rpc.order.dto.TradeOrderCreateReqDTO;
import cn.iocoder.mall.tradeservice.rpc.order.dto.TradeOrderPageReqDTO;
import cn.iocoder.mall.tradeservice.rpc.order.dto.TradeOrderRespDTO;
import cn.iocoder.mall.tradeservice.service.order.TradeOrderService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
 * 交易订单 Rpc 实现
 */
@DubboService
public class TradeOrderRpcImpl implements TradeOrderRpc {

    @Autowired
    private TradeOrderService tradeOrderService;

    @Override
    public CommonResult<Integer> createTradeOrder(TradeOrderCreateReqDTO createReqDTO) {
        return success(tradeOrderService.createTradeOrder(createReqDTO));
    }

    @Override
    public CommonResult<TradeOrderRespDTO> getTradeOrder(Integer tradeOrderId, Collection<String> fields) {
        return success(tradeOrderService.getTradeOrder(tradeOrderId, fields));
    }

    @Override
    public CommonResult<PageResult<TradeOrderRespDTO>> pageTradeOrder(TradeOrderPageReqDTO pageDTO) {
        return success(tradeOrderService.pageTradeOrder(pageDTO));
    }

    @Override
    public CommonResult<Boolean> updateTradeOrderPaySuccess(String tradeOrderId, Integer payAmount) {
        tradeOrderService.updateTradeOrderPaySuccess(Integer.valueOf(tradeOrderId), payAmount);
        return success(true);
    }

}
