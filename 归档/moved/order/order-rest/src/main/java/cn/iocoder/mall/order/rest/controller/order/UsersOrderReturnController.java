package cn.iocoder.mall.order.rest.controller.order;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单退款/售后流程
 *
 * @author Sin
 * @time 2019-04-25 22:04
 */
@RestController
@RequestMapping("users/order_return")
public class UsersOrderReturnController {

//    @Reference(validation = "true", version = "${dubbo.provider.OrderReturnService.version}")
//    private OrderReturnService orderReturnService;
//
//    @Reference(validation = "true", version = "${dubbo.consumer.DataDictService.version}")
//    private DataDictService dataDictService;
//
//    @GetMapping("reason")
//    @ApiOperation("原因")
//    public CommonResult<List<DataDictBO>> orderReturnReason() {
//        return dataDictService.getDataDict(DictKeyConstants.ORDER_RETURN_REASON);
//    }
//
//    @PostMapping("apply")
//    @ApiOperation("订单售后")
//    public CommonResult orderReturnApply(@RequestBody OrderReturnApplyPO orderReturnApplyPO) {
//        OrderReturnApplyDTO applyDTO = OrderReturnConvert.INSTANCE.convert(orderReturnApplyPO);
//        return orderReturnService.orderReturnApply(applyDTO);
//    }
//
//    @GetMapping("info")
//    @ApiOperation("订单售后详细")
//    public CommonResult<OrderReturnInfoBO> orderApplyInfo(@RequestParam("orderId") Integer orderId) {
//        CommonResult<OrderReturnInfoBO> commonResult = orderReturnService.orderApplyInfo(orderId);
//
//        // 转换 字典值
//        if (commonResult.isSuccess()) {
//            CommonResult<DataDictBO> dataDictResult = dataDictService.getDataDict(
//                    DictKeyConstants.ORDER_RETURN_SERVICE_TYPE,
//                    commonResult.getData().getReturnInfo().getServiceType());
//
//            if (dataDictResult.isSuccess()) {
//                commonResult.getData().getReturnInfo().setServiceTypeText(dataDictResult.getData().getDisplayName());
//            }
//        }
//
//        return commonResult;
//    }
}
