package cn.iocoder.mall.shopweb.client.trade;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.tradeservice.rpc.order.TradeOrderRpc;
import cn.iocoder.mall.tradeservice.rpc.order.dto.TradeOrderCreateReqDTO;
import cn.iocoder.mall.tradeservice.rpc.order.dto.TradeOrderPageReqDTO;
import cn.iocoder.mall.tradeservice.rpc.order.dto.TradeOrderRespDTO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class TradeOrderClient {

    @DubboReference(version = "${dubbo.consumer.TradeOrderRpc.version}")
    private TradeOrderRpc tradeOrderRpc;

    public Integer createTradeOrder(TradeOrderCreateReqDTO createReqDTO) {
        CommonResult<Integer> createTradeOrderResult = tradeOrderRpc.createTradeOrder(createReqDTO);
        createTradeOrderResult.checkError();
        return createTradeOrderResult.getData();
    }

    public PageResult<TradeOrderRespDTO> pageTradeOrder(TradeOrderPageReqDTO pageReqDTO) {
        CommonResult<PageResult<TradeOrderRespDTO>> pageTradeOrderResult = tradeOrderRpc.pageTradeOrder(pageReqDTO);
        pageTradeOrderResult.checkError();
        return pageTradeOrderResult.getData();
    }

    public TradeOrderRespDTO getTradeOrder(Integer tradeOrderId, String... fields) {
        CommonResult<TradeOrderRespDTO> getTradeOrderResult = tradeOrderRpc.getTradeOrder(tradeOrderId, Arrays.asList(fields));
        getTradeOrderResult.checkError();
        return getTradeOrderResult.getData();
    }

}
