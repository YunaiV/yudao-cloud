package cn.iocoder.mall.order.application.controller.admins;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.order.api.OrderService;
import cn.iocoder.mall.order.api.bo.OrderPageBO;
import cn.iocoder.mall.order.api.dto.*;
import cn.iocoder.mall.order.application.convert.OrderConvertAPP;
import cn.iocoder.mall.order.application.vo.OrderItemUpdateVO;
import cn.iocoder.mall.order.application.vo.OrderLogisticsVO;
import cn.iocoder.mall.order.application.vo.OrderPageQueryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 订单API(admins)
 *
 * @author Sin
 * @time 2019-03-24 10:22
 */
@RestController
@RequestMapping("admins/order")
@Api(description = "订单API(admins)")
public class AdminsOrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("page")
    @ApiOperation("订单列表")
    public CommonResult<OrderPageBO> getOrderPage(@Validated OrderPageQueryVO orderPageQueryVO) {
        OrderQueryDTO orderQueryDTO = OrderConvertAPP.INSTANCE.convertPageBO(orderPageQueryVO);
        return orderService.getOrderPage(orderQueryDTO);
    }

    @PutMapping("order_item/update_pay_amount")
    @ApiOperation("更新-订单item实付金额")
    public CommonResult updateOrderItemPayAmount(@RequestParam("orderId") Integer orderId,
                                                 @RequestParam("orderItemId") Integer orderItemId,
                                                 @RequestParam("payAmount") Integer payAmount) {
        return orderService.updateOrderItemPayAmount(orderId, orderItemId, payAmount);
    }

    @PutMapping("order_item/update")
    @ApiOperation("更新-订单item")
    public CommonResult updateOrderItem(@RequestBody @Validated OrderItemUpdateVO orderItemUpdateVO) {
        OrderItemUpdateDTO dto = OrderConvertAPP.INSTANCE.convertPageBO(orderItemUpdateVO);
        return orderService.updateOrderItem(dto);
    }

    @PutMapping("logistics/update")
    @ApiOperation("更新-订单物流")
    public CommonResult updateLogistics(@RequestBody @Validated OrderLogisticsVO orderLogisticsVO) {
        OrderLogisticsUpdateDTO dto = OrderConvertAPP.INSTANCE.convertPageBO(orderLogisticsVO);
        return orderService.updateLogistics(dto);
    }
}
