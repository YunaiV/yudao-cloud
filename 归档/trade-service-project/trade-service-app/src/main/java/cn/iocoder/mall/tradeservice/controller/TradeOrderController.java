package cn.iocoder.mall.tradeservice.controller;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.tradeservice.rpc.order.dto.TradeOrderCreateReqDTO;
import cn.iocoder.mall.tradeservice.rpc.order.dto.TradeOrderPageReqDTO;
import cn.iocoder.mall.tradeservice.rpc.order.dto.TradeOrderRespDTO;
import cn.iocoder.mall.tradeservice.service.order.TradeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
 * Title:
 * Description:
 *
 * @author zhuyang
 * @version 1.0 2021/10/9
 */
@RestController
@RequestMapping("/trade/order")
public class TradeOrderController {

    @Autowired
    private TradeOrderService tradeOrderService;

    /**
     * 创建交易订单
     *
     * @param createReqDTO 订单信息
     * @return 订单编号
     */
    @PostMapping("createTradeOrder")
    CommonResult<Integer> createTradeOrder(@RequestBody TradeOrderCreateReqDTO createReqDTO){
        return success(tradeOrderService.createTradeOrder(createReqDTO));
    }

    /**
     * 获得订单交易
     *
     * @param tradeOrderId 订单交易编号
     * @param fields 额外返回字段，可见 {@link cn.iocoder.mall.tradeservice.enums.order.TradeOrderDetailFieldEnum}
     * @return 订单交易
     */
    @GetMapping("getTradeOrder")
    CommonResult<TradeOrderRespDTO> getTradeOrder( @RequestParam("tradeOrderId")Integer tradeOrderId, @RequestParam("fields") Collection<String> fields){
        return success(tradeOrderService.getTradeOrder(tradeOrderId, fields));
    }

    /**
     * 获得交易订单分页
     *
     * @param pageDTO 订单交易分页查询
     * @return 订单交易分页结果
     */
    @PostMapping("pageTradeOrder")
    CommonResult<PageResult<TradeOrderRespDTO>> pageTradeOrder(@RequestBody TradeOrderPageReqDTO pageDTO){
        return success(tradeOrderService.pageTradeOrder(pageDTO));
    }

    /**
     * 更新交易订单支付成功
     *
     * 目前用于对接 pay-service 支付服务，回调该交易订单在三方支付平台，支付成功
     *
     * @param tradeOrderId 交易订单编号
     * @param payAmount 支付金额
     * @return 成功
     */
    @PostMapping("updateTradeOrderPaySuccess")
    CommonResult<Boolean> updateTradeOrderPaySuccess(@RequestParam("tradeOrderId") String tradeOrderId, @RequestParam("payAmount")Integer payAmount){
        tradeOrderService.updateTradeOrderPaySuccess(Integer.valueOf(tradeOrderId), payAmount);
        return success(true);
    }

}
