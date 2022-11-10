package cn.iocoder.mall.shopweb.client.trade;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.tradeservice.rpc.order.TradeOrderFeign;
import cn.iocoder.mall.tradeservice.rpc.order.dto.TradeOrderCreateReqDTO;
import cn.iocoder.mall.tradeservice.rpc.order.dto.TradeOrderPageReqDTO;
import cn.iocoder.mall.tradeservice.rpc.order.dto.TradeOrderRespDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class TradeOrderClient {

    @Autowired
    private TradeOrderFeign tradeOrderFeign;

    public Integer createTradeOrder(TradeOrderCreateReqDTO createReqDTO) {
        CommonResult<Integer> createTradeOrderResult = tradeOrderFeign.createTradeOrder(createReqDTO);
        createTradeOrderResult.checkError();
        return createTradeOrderResult.getData();
    }

    public PageResult<TradeOrderRespDTO> pageTradeOrder(TradeOrderPageReqDTO pageReqDTO) {
        CommonResult<PageResult<TradeOrderRespDTO>> pageTradeOrderResult = tradeOrderFeign.pageTradeOrder(pageReqDTO);
        pageTradeOrderResult.checkError();
        return pageTradeOrderResult.getData();
    }

    public TradeOrderRespDTO getTradeOrder(Integer tradeOrderId, String... fields) {
        CommonResult<TradeOrderRespDTO> getTradeOrderResult = tradeOrderFeign.getTradeOrder(tradeOrderId, Arrays.asList(fields));
        getTradeOrderResult.checkError();
        return getTradeOrderResult.getData();
    }

}
