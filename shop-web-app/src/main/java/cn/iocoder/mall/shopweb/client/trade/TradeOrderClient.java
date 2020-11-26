package cn.iocoder.mall.shopweb.client.trade;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.tradeservice.rpc.order.TradeOrderRpc;
import cn.iocoder.mall.tradeservice.rpc.order.dto.TradeOrderCreateReqDTO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class TradeOrderClient {

    @DubboReference(version = "${dubbo.consumer.TradeOrderRpc.version}")
    private TradeOrderRpc tradeOrderRpc;

    public Integer createTradeOrder(TradeOrderCreateReqDTO createReqDTO) {
        CommonResult<Integer> createTradeOrderResult = tradeOrderRpc.createTradeOrder(createReqDTO);
        createTradeOrderResult.checkError();
        return createTradeOrderResult.getData();
    }

}
