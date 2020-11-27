package cn.iocoder.mall.order.rest.controller.order;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单API(users)
 *
 * @author Sin
 * @time 2019-03-24 11:24
 */
@RestController
@RequestMapping("users/order")
@Api(description = "用户订单") // TODO FROM 芋艿 to 小范，description 已经废弃啦
public class UsersOrderController {


//    @PostMapping("create_order_from_cart")
//    @RequiresLogin
//    @ApiOperation("创建订单购物车")
//    public CommonResult<OrderCreateBO> createOrderFromCart(@RequestParam("userAddressId") Integer userAddressId,
//                                                           @RequestParam(value = "couponCardId", required = false) Integer couponCardId,
//                                                           @RequestParam(value = "remark", required = false) String remark,
//                                                           HttpServletRequest request) {
//        Integer userId = UserSecurityContextHolder.getContext().getUserId();
//        // 获得购物车中选中的商品
//        List<CartItemBO> cartItems = cartService.list(userId, true);
//        if (cartItems.isEmpty()) {
//            return ServiceExceptionUtil.error(OrderErrorCodeEnum.ORDER_CREATE_CART_IS_EMPTY.getCode());
//        }
//        // 创建 OrderCreateDTO 对象
//        OrderCreateDTO orderCreateDTO = OrderConvertAPP.INSTANCE.createOrderCreateDTO(userId, userAddressId,
//                remark, HttpUtil.getIp(request),
//                cartItems, couponCardId);
//        // 创建订单
//        CommonResult<OrderCreateBO> createResult = orderService.createOrder(orderCreateDTO);
//        if (createResult.isError()) {
//            return CommonResult.error(createResult);
//        }
//        // 清空购物车 // TODO 芋艿，需要标记删除的原因，即结果为创建为某个订单。
//        cartService.deleteList(userId, cartItems.stream().map(CartItemBO::getSkuId).collect(Collectors.toList()));
//        // 返回结果
//        return createResult;
//    }
//

//
//    @PostMapping("confirm_receiving")
//    @RequiresLogin
//    @ApiOperation("确认收货")
//    public CommonResult confirmReceiving(@RequestParam("orderId") Integer orderId) {
//        Integer userId = UserSecurityContextHolder.getContext().getUserId();
//        return orderService.confirmReceiving(userId, orderId);
//    }

}
