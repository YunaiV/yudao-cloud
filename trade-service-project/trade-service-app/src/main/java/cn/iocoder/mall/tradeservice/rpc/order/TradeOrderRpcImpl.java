package cn.iocoder.mall.tradeservice.rpc.order;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.tradeservice.rpc.order.dto.TradeOrderCreateReqDTO;
import cn.iocoder.mall.tradeservice.service.order.TradeOrderService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

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

}
