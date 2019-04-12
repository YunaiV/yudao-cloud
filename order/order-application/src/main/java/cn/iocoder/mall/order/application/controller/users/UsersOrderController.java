package cn.iocoder.mall.order.application.controller.users;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.order.api.CartService;
import cn.iocoder.mall.order.api.OrderService;
import cn.iocoder.mall.order.api.bo.CalcOrderPriceBO;
import cn.iocoder.mall.order.api.bo.OrderCreateBO;
import cn.iocoder.mall.order.api.bo.OrderPageBO;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

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

    @GetMapping("order_page")
    public CommonResult<OrderPageBO> getOrderPage(@Validated OrderQueryDTO orderQueryDTO) {
        Integer userId = UserSecurityContextHolder.getContext().getUserId();
        orderQueryDTO.setUserId(userId);
        return orderService.getOrderPage(orderQueryDTO);
    }

    @PostMapping("create_order")
    public CommonResult<OrderCreateBO> createOrder(@RequestBody @Validated OrderCreatePO orderCreatePO) {
        Integer userId = UserSecurityContextHolder.getContext().getUserId();
        OrderCreateDTO orderCreateDTO = OrderConvertAPP.INSTANCE.convert(orderCreatePO);
        orderCreateDTO.setUserId(userId);
        return orderService.createOrder(orderCreateDTO);
    }

    @GetMapping("confirm_create_order")
    public CommonResult<UsersOrderConfirmCreateVO> getConfirmCreateOrder(@RequestParam("skuId") Integer skuId,
                                                                         @RequestParam("quantity") Integer quantity) {
        // 创建 CalcOrderPriceDTO 对象，并执行价格计算
        CalcOrderPriceDTO calcOrderPriceDTO = new CalcOrderPriceDTO()
                .setItems(Collections.singletonList(new CalcOrderPriceDTO.Item(skuId, quantity, true)));
        CommonResult<CalcOrderPriceBO> calcOrderPriceResult = cartService.calcOrderPrice(calcOrderPriceDTO);
        if (calcOrderPriceResult.isError()) {
            return CommonResult.error(calcOrderPriceResult);
        }
        // 执行数据拼装
        return CommonResult.success(CartConvert.INSTANCE.convert(calcOrderPriceResult.getData()));
    }

    @PostMapping("confirm_receiving")
    public CommonResult confirmReceiving(@RequestParam("orderId") Integer orderId) {
        Integer userId = UserSecurityContextHolder.getContext().getUserId();
        return orderService.confirmReceiving(userId, orderId);
    }

}
