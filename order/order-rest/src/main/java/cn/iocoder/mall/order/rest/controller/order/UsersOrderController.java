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

//    @Reference(validation = "true", version = "${dubbo.provider.OrderReturnService.version}")
//    private OrderService orderService;
//
//    @Reference(validation = "true", version = "${dubbo.provider.CartService.version}")
//    private CartService cartService;
//
//    @Reference(validation = "true", version = "${dubbo.consumer.DataDictService.version}")
//    private DataDictService dataDictService;
//
//    @Reference(validation = "true", version = "${dubbo.consumer.CouponService.version}")
//    private CouponService couponService;
//
//    @GetMapping("order_page")
//    @RequiresLogin
//    @ApiOperation("订单分页")
//    public CommonResult<OrderPageBO> getOrderPage(@Validated OrderQueryDTO orderQueryDTO) {
//        Integer userId = UserSecurityContextHolder.getContext().getUserId();
//        orderQueryDTO.setUserId(userId);
//        return orderService.getOrderPage(orderQueryDTO);
//    }
//
//    @PostMapping("create_order")
//    @RequiresLogin
//    @ApiOperation("创建订单")
//    public CommonResult<OrderCreateBO> createOrder(@RequestBody @Validated OrderCreatePO orderCreatePO,
//                                                   HttpServletRequest request) {
//        Integer userId = UserSecurityContextHolder.getContext().getUserId();
//        OrderCreateDTO orderCreateDTO = OrderConvertAPP.INSTANCE.convert(orderCreatePO);
//        orderCreateDTO.setUserId(userId).setIp(HttpUtil.getIp(request));
//        return orderService.createOrder(orderCreateDTO);
//    }
//
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
//
//    @GetMapping("info")
//    @RequiresLogin
//    @ApiOperation("订单详情")
//    public CommonResult<OrderInfoBO> orderInfo(@RequestParam("orderId") Integer orderId) {
//        Integer userId = UserSecurityContextHolder.getContext().getUserId();
//        CommonResult<OrderInfoBO> commonResult = orderService.info(userId, orderId);
//
//        OrderInfoBO orderInfoBO = commonResult.getData();
//        if (orderInfoBO != null) {
//            CommonResult<DataDictBO> dictResult = dataDictService
//                    .getDataDict(DictKeyConstants.ORDER_STATUS, orderInfoBO.getStatus());
//            orderInfoBO.setStatusText(dictResult.getData().getDisplayName());
//        }
//        return commonResult;
//    }

}
