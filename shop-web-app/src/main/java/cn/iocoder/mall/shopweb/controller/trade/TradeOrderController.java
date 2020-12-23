package cn.iocoder.mall.shopweb.controller.trade;

import cn.iocoder.common.framework.util.HttpUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.security.user.core.context.UserSecurityContextHolder;
import cn.iocoder.mall.shopweb.controller.trade.vo.order.*;
import cn.iocoder.mall.shopweb.service.trade.TradeOrderService;
import cn.iocoder.security.annotations.RequiresAuthenticate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@Api(tags = "交易订单 API")
@RestController
@RequestMapping("/trade-order")
@Validated
public class TradeOrderController {

    @Autowired
    private TradeOrderService tradeOrderService;

    @GetMapping("confirm-create-order-info")
    @ApiOperation("基于商品，确认创建订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "skuId", required = true, value = "商品 SKU 编号", example = "1024"),
            @ApiImplicitParam(name = "quantity", required = true, value = "购买数量", example = "2"),
            @ApiImplicitParam(name = "couponCardId", value = "优惠劵编号", example = "1"),
    })
    @RequiresAuthenticate
    public CommonResult<TradeOrderConfirmCreateInfoRespVO> getTradeOrderConfirmCreateInfo(
            @RequestParam("skuId") Integer skuId,
            @RequestParam("quantity") Integer quantity,
            @RequestParam(value = "couponCardId", required = false) Integer couponCardId) {
        return success(tradeOrderService.getOrderConfirmCreateInfo(UserSecurityContextHolder.getUserId(), skuId, quantity, couponCardId));
    }

    @GetMapping("confirm-create-order-info-from-cart")
    @ApiOperation("基于购物车，确认创建订单")
    @ApiImplicitParam(name = "couponCardId", value = "优惠劵编号", example = "1")
    @RequiresAuthenticate
    public CommonResult<TradeOrderConfirmCreateInfoRespVO> getTradeOrderConfirmCreateInfoFromCart(
            @RequestParam(value = "couponCardId", required = false) Integer couponCardId) {
        return success(tradeOrderService.getOrderConfirmCreateInfoFromCart(UserSecurityContextHolder.getUserId(), couponCardId));
    }

    @PostMapping("create")
    @ApiOperation("基于商品，创建订单")
    @RequiresAuthenticate
    public CommonResult<Integer> createTradeOrder(@RequestBody TradeOrderCreateReqVO createReqVO,
                                                  HttpServletRequest servletRequest) {
        return success(tradeOrderService.createTradeOrder(UserSecurityContextHolder.getUserId(),
                HttpUtil.getIp(servletRequest), createReqVO));
    }

    @GetMapping("create-from-cart")
    @ApiOperation("基于购物车，创建订单")
    @RequiresAuthenticate
    public CommonResult<Integer> createTradeOrder(TradeOrderCreateFromCartReqVO createReqVO) {
        return null;
    }

    @GetMapping("/get")
    @ApiOperation("获得交易订单")
    @ApiImplicitParam(name = "tradeOrderId", value = "交易订单编号", required = true)
    public CommonResult<TradeOrderRespVO> getTradeOrder(@RequestParam("tradeOrderId") Integer tradeOrderId) {
        return success(tradeOrderService.getTradeOrder(tradeOrderId));
    }

    @GetMapping("/page")
    @ApiOperation("获得订单交易分页")
    public CommonResult<PageResult<TradeOrderRespVO>> pageTradeOrder(TradeOrderPageReqVO pageVO) {
        return success(tradeOrderService.pageTradeOrder(UserSecurityContextHolder.getUserId(), pageVO));
    }

}
