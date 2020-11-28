package cn.iocoder.mall.order.rest.controller.order;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单物流 controller
 *
 * @author Sin
 * @time 2019-04-12 22:24
 */
@RestController
@RequestMapping("users/order_logistics")
@Api(description = "订单物流信息")
public class UsersOrderLogisticsController {

//    @Reference(validation = "true", version = "${dubbo.provider.OrderLogisticsService.version}")
//    private OrderLogisticsService orderLogisticsService;
//
//    @Reference(validation = "true", version = "${dubbo.consumer.DataDictService.version}")
//    private DataDictService dataDictService;
//
//    @GetMapping("info")
//    @ApiOperation("物流详细 - 物流通用")
//    public CommonResult<OrderLogisticsInfoBO> logistics(@RequestParam("logisticsId") Integer logisticsId) {
//        return orderLogisticsService.getLogisticsInfo(logisticsId);
//    }
//
//    @GetMapping("info_order")
//    @ApiOperation("物流详细 - 返回订单所关联的所有物流信息(订单用的)")
//    public CommonResult<OrderLogisticsInfoWithOrderBO> logisticsInfoWithOrder(@RequestParam("orderId") Integer orderId) {
//        Integer userId = UserSecurityContextHolder.getContext().getUserId();
//        CommonResult<OrderLogisticsInfoWithOrderBO> commonResult = orderLogisticsService.getOrderLogisticsInfo(userId, orderId);
//        if (commonResult.isSuccess()) {
//            OrderLogisticsInfoWithOrderBO orderLogisticsInfoBO = commonResult.getData();
//            List<OrderLogisticsInfoWithOrderBO.Logistics> logisticsList = orderLogisticsInfoBO.getLogistics();
//
//            // 获取字典值
//            Set<Integer> dictValues = logisticsList.stream().map(o -> o.getLogistics()).collect(Collectors.toSet());
//            if (!CollectionUtils.isEmpty(dictValues)) {
//                CommonResult<List<DataDictBO>> dictResult = dataDictService
//                        .getDataDictList(DictKeyConstants.ORDER_LOGISTICS_COMPANY, dictValues);
//
//                if (dictResult.isError()) {
//                    // 错误情况
//                    return ServiceExceptionUtil.error(OrderErrorCodeEnum.DICT_SERVER_INVOKING_FAIL.getCode());
//                }
//
//                // 转换结果字典值
//                Map<String, DataDictBO> dataDictBOMap = dictResult.getData()
//                        .stream().collect(Collectors.toMap(o -> o.getValue(), o -> o));
//
//                logisticsList.stream().map(o -> {
//                    String dicValue = o.getLogistics().toString();
//                    if (dataDictBOMap.containsKey(dicValue)) {
//                        o.setLogisticsText(dataDictBOMap.get(dicValue).getDisplayName());
//                    }
//                    return o;
//                }).collect(Collectors.toList());
//            }
//        }
//        return commonResult;
//    }
}
