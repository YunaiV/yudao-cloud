package cn.iocoder.mall.order.application.controller.users;

import cn.iocoder.common.framework.util.HttpUtil;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.api.DataDictService;
import cn.iocoder.mall.admin.api.bo.DataDictBO;
import cn.iocoder.mall.order.api.CartService;
import cn.iocoder.mall.order.api.OrderService;
import cn.iocoder.mall.order.api.bo.*;
import cn.iocoder.mall.order.api.constant.DictKeyConstants;
import cn.iocoder.mall.order.api.constant.OrderErrorCodeEnum;
import cn.iocoder.mall.order.api.dto.CalcOrderPriceDTO;
import cn.iocoder.mall.order.api.dto.OrderCreateDTO;
import cn.iocoder.mall.order.api.dto.OrderQueryDTO;
import cn.iocoder.mall.order.application.convert.CartConvert;
import cn.iocoder.mall.order.application.convert.OrderConvertAPP;
import cn.iocoder.mall.order.application.po.user.OrderCreatePO;
import cn.iocoder.mall.order.application.vo.UsersOrderConfirmCreateVO;
import cn.iocoder.mall.user.sdk.context.UserSecurityContextHolder;
import com.alibaba.dubbo.config.annotation.Reference;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单API(users)
 *
 * @author Sin
 * @time 2019-03-24 11:24
 */
@RestController
@RequestMapping("users/order")
@Api(description = "用户订单")
public class UsersOrderController {

    @Reference(validation = "true")
    private OrderService orderService;
    @Reference(validation = "true")
    private CartService cartService;
    @Reference(validation = "true")
    private DataDictService dataDictService;

    @GetMapping("order_page")
    @ApiOperation("订单分页")
    public CommonResult<OrderPageBO> getOrderPage(@Validated OrderQueryDTO orderQueryDTO) {
        Integer userId = UserSecurityContextHolder.getContext().getUserId();
        orderQueryDTO.setUserId(userId);
        return orderService.getOrderPage(orderQueryDTO);
    }

    @PostMapping("create_order")
    @ApiOperation("创建订单")
    public CommonResult<OrderCreateBO> createOrder(@RequestBody @Validated OrderCreatePO orderCreatePO) {
        Integer userId = UserSecurityContextHolder.getContext().getUserId();
        OrderCreateDTO orderCreateDTO = OrderConvertAPP.INSTANCE.convert(orderCreatePO);
        orderCreateDTO.setUserId(userId);
        return orderService.createOrder(orderCreateDTO);
    }

    @PostMapping("create_order_from_cart")
    @ApiOperation("创建订单购物车")
    public CommonResult<OrderCreateBO> createOrderFromCart(@RequestParam("userAddressId") Integer userAddressId,
                                                           @RequestParam(value = "remark", required = false) String remark,
                                                           HttpServletRequest request) {
        Integer userId = UserSecurityContextHolder.getContext().getUserId();
        // 获得购物车中选中的商品
        List<CartItemBO> cartItems = cartService.list(userId, true).getData();
        if (cartItems.isEmpty()) {
            return ServiceExceptionUtil.error(OrderErrorCodeEnum.ORDER_CREATE_CART_IS_EMPTY.getCode());
        }
        // 创建 OrderCreateDTO 对象
        OrderCreateDTO orderCreateDTO = OrderConvertAPP.INSTANCE.createOrderCreateDTO(userId, userAddressId,
                remark, HttpUtil.getIp(request),
                cartItems);
        // 创建订单
        CommonResult<OrderCreateBO> createResult= orderService.createOrder(orderCreateDTO);
        if (createResult.isError()) {
            return CommonResult.error(createResult);
        }
        // 清空购物车 // TODO 芋艿，需要标记删除的原因，即结果为创建为某个订单。
        cartService.deleteList(userId, cartItems.stream().map(CartItemBO::getSkuId).collect(Collectors.toList()));
        // 返回结果
        return createResult;
    }

    @GetMapping("confirm_create_order")
    @ApiOperation("确认创建订单")
    public CommonResult<UsersOrderConfirmCreateVO> getConfirmCreateOrder(@RequestParam("skuId") Integer skuId,
                                                                         @RequestParam("quantity") Integer quantity,
                                                                         @RequestParam(value = "couponCardId", required = false) Integer couponCardId) {
        // 创建 CalcOrderPriceDTO 对象，并执行价格计算
        CalcOrderPriceDTO calcOrderPriceDTO = new CalcOrderPriceDTO()
                .setUserId(UserSecurityContextHolder.getContext().getUserId())
                .setItems(Collections.singletonList(new CalcOrderPriceDTO.Item(skuId, quantity, true)))
                .setCouponCardId(couponCardId);
        CommonResult<CalcOrderPriceBO> calcOrderPriceResult = cartService.calcOrderPrice(calcOrderPriceDTO);
        if (calcOrderPriceResult.isError()) {
            return CommonResult.error(calcOrderPriceResult);
        }
        // 执行数据拼装
        return CommonResult.success(CartConvert.INSTANCE.convert(calcOrderPriceResult.getData()));
    }

    @PostMapping("confirm_receiving")
    @ApiOperation("确认收货")
    public CommonResult confirmReceiving(@RequestParam("orderId") Integer orderId) {
        Integer userId = UserSecurityContextHolder.getContext().getUserId();
        return orderService.confirmReceiving(userId, orderId);
    }

    @GetMapping("info")
    @ApiOperation("订单详情")
    public CommonResult<OrderInfoBO> orderInfo(@RequestParam("orderId") Integer orderId) {
        Integer userId = UserSecurityContextHolder.getContext().getUserId();
        CommonResult<OrderInfoBO> commonResult = orderService.info(userId, orderId);

        OrderInfoBO orderInfoBO = commonResult.getData();
        CommonResult<DataDictBO> dictResult = dataDictService
                .getDataDict(DictKeyConstants.ORDER_STATUS, orderInfoBO.getStatus());
        orderInfoBO.setStatusText(dictResult.getData().getDisplayName());
        return commonResult;
    }
}
