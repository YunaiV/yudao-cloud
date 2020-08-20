package cn.iocoder.mall.shopweb.controller.order;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.security.user.core.context.UserSecurityContextHolder;
import cn.iocoder.mall.shopweb.controller.order.vo.order.OrderConfirmCreateInfoRespVO;
import cn.iocoder.mall.shopweb.manager.order.OrderManager;
import cn.iocoder.security.annotations.RequiresAuthenticate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@Api(tags = "订单 API")
@RestController
@RequestMapping("/order")
@Validated
public class OrderController {

    @Autowired
    private OrderManager orderManager;

    @GetMapping("confirm-create-order-info")
    @ApiOperation("基于商品，确认创建订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "skuId", required = true, value = "商品 SKU 编号", example = "1024"),
            @ApiImplicitParam(name = "quantity", required = true, value = "购买数量", example = "2"),
            @ApiImplicitParam(name = "couponCardId", value = "优惠劵编号", example = "1"),
    })
    @RequiresAuthenticate
    public CommonResult<OrderConfirmCreateInfoRespVO> getOrderConfirmCreateInfo(@RequestParam("skuId") Integer skuId,
                                                                                @RequestParam("quantity") Integer quantity,
                                                                                @RequestParam(value = "couponCardId", required = false) Integer couponCardId) {
        return success(orderManager.getOrderConfirmCreateInfo(UserSecurityContextHolder.getUserId(), skuId, quantity, couponCardId));
    }

    @GetMapping("confirm-create-order-info-from-cart")
    @ApiOperation("基于购物车，确认创建订单")
    @ApiImplicitParam(name = "couponCardId", value = "优惠劵编号", example = "1")
    @RequiresAuthenticate
    public CommonResult<OrderConfirmCreateInfoRespVO> getOrderConfirmCreateInfoFromCart(@RequestParam(value = "couponCardId", required = false) Integer couponCardId) {
        return success(orderManager.getOrderConfirmCreateInfoFromCart(UserSecurityContextHolder.getUserId(), couponCardId));
    }

}
