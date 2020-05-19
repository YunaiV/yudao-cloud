package cn.iocoder.mall.order.rest.controller.order;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单API(admins)
 *
 * @author Sin
 * @time 2019-03-24 10:22
 */
@RestController
@RequestMapping("admins/order")
@Api(value = "订单 API(admins)")
public class AdminsOrderController {

//    @Reference(validation = "true", version = "${dubbo.provider.OrderService.version}")
//    private OrderService orderService;
//
//    @GetMapping("page")
//    @ApiOperation("订单列表")
//    public CommonResult<OrderPageBO> getOrderPage(@Validated OrderPageQueryPO orderPageQueryVO) {
//        OrderQueryDTO orderQueryDTO = OrderConvertAPP.INSTANCE.convert(orderPageQueryVO);
//        return orderService.getOrderPage(orderQueryDTO);
//    }
//
//    @GetMapping("order_items")
//    @ApiOperation("订单列表")
//    public CommonResult<List<OrderItemBO>> getOrderItems(@RequestParam("orderId") Integer orderId) {
//        return orderService.getOrderItems(orderId);
//    }
//
//    @GetMapping("order_recipient_info")
//    @ApiOperation("订单收件人信息")
//    public CommonResult<OrderRecipientBO> getOrderRecipientBO(@RequestParam("orderId") Integer orderId) {
//        return orderService.getOrderRecipientBO(orderId);
//    }
//
//    @PostMapping("order_deliver")
//    @ApiOperation("订单发货")
//    public CommonResult<OrderRecipientBO> orderDeliver(@RequestBody @Validated OrderDeliverPO orderDeliverPO) {
//        return orderService.orderDelivery(OrderDeliveryConvert.INSTANCE.convert(orderDeliverPO));
//    }
//
//    @PutMapping("update_remark")
//    @ApiOperation("更新-更新订单备注")
//    public CommonResult updateRemark(@RequestParam("orderId") Integer orderId,
//                                     @RequestParam("remark") String remark) {
//        return orderService.updateOrderRemake(orderId, remark);
//    }
//
//    @PutMapping("cancel_order")
//    @ApiOperation("取消订单")
//    public CommonResult cancelOrder(
//            @RequestParam("orderId") Integer orderId,
//            @RequestParam("reasons") Integer reasons,
//            @RequestParam(value = "otherReasons", required = false) String otherReasons) {
//        return orderService.cancelOrder(orderId, reasons, otherReasons);
//    }
//
//    @PutMapping("order_item/update_pay_amount")
//    @ApiOperation("更新-订单item实付金额")
//    public CommonResult updateOrderItemPayAmount(@RequestParam("orderId") Integer orderId,
//                                                 @RequestParam("orderItemId") Integer orderItemId,
//                                                 @RequestParam("payAmount") Integer payAmount) {
//        return orderService.updateOrderItemPayAmount(orderId, orderItemId, payAmount);
//    }
//
//    @PutMapping("order_item/update")
//    @ApiOperation("更新-订单item")
//    public CommonResult updateOrderItem(@RequestBody @Validated OrderItemUpdatePO orderItemUpdateVO) {
//        OrderItemUpdateDTO dto = OrderConvertAPP.INSTANCE.convert(orderItemUpdateVO);
//        return orderService.updateOrderItem(dto);
//    }
//
//    @PutMapping("logistics/update")
//    @ApiOperation("更新-订单物流")
//    public CommonResult updateLogistics(@RequestBody @Validated OrderLogisticsPO orderLogisticsVO) {
//        OrderLogisticsUpdateDTO dto = OrderConvertAPP.INSTANCE.convert(orderLogisticsVO);
//        return orderService.updateLogistics(dto);
//    }
}
