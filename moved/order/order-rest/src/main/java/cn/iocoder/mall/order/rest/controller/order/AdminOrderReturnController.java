package cn.iocoder.mall.order.rest.controller.order;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单退货
 *
 * @author Sin
 * @time 2019-05-06 21:31
 */
@RestController
@RequestMapping("admins/order_return")
@Api("订单退货(admins api)")
public class AdminOrderReturnController {

//    @Reference(validation = "true", version = "${dubbo.provider.OrderReturnService.version}")
//    private OrderReturnService orderReturnService;
//
//    @GetMapping("list")
//    public CommonResult<OrderReturnListBO> list(@Validated OrderReturnQueryPO queryPO) {
//        OrderReturnQueryDTO queryDTO = OrderReturnConvert.INSTANCE.convert(queryPO);
//        return orderReturnService.orderReturnList(queryDTO);
//    }
//
//    @PostMapping("agree")
//    public CommonResult agree(@RequestParam("id") Integer id) {
//        return orderReturnService.orderReturnAgree(id);
//    }
//
//    @PostMapping("refuse")
//    public CommonResult refuse(@RequestParam("id") Integer id) {
//        return orderReturnService.orderReturnRefuse(id);
//    }
//
//    @PostMapping("confirm_receipt")
//    public CommonResult confirmReceipt(@RequestParam("id") Integer id) {
//        return orderReturnService.confirmReceipt(id);
//    }
//
//    @PostMapping("confirm_refund")
//    public CommonResult confirmRefund(HttpServletRequest request, @RequestParam("id") Integer id) {
//        String ip = HttpUtil.getIp(request);
//        return orderReturnService.refund(id, ip);
//    }
}
