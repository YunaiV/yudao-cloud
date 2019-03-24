package cn.iocoder.mall.order.application.controller.admins;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.order.api.OrderService;
import cn.iocoder.mall.order.api.dto.OrderItemUpdateDTO;
import cn.iocoder.mall.order.api.dto.OrderLogisticsUpdateDTO;
import cn.iocoder.mall.order.api.dto.OrderPageBO;
import cn.iocoder.mall.order.api.dto.OrderQueryDTO;
import cn.iocoder.mall.order.application.convert.OrderConvertAPP;
import cn.iocoder.mall.order.application.vo.OrderItemUpdateVO;
import cn.iocoder.mall.order.application.vo.OrderLogisticsVO;
import cn.iocoder.mall.order.application.vo.OrderPageQueryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public CommonResult<List<OrderPageBO>> getOrderPage(@Validated OrderPageQueryVO orderPageQueryVO) {
        OrderQueryDTO orderQueryDTO = OrderConvertAPP.INSTANCE.convertPageBO(orderPageQueryVO);
        CommonResult<List<OrderPageBO>> result = orderService.getOrderPage(orderQueryDTO);
        return result;
    }

    @PutMapping("order_item/update")
    @ApiOperation("订单item更新")
    public CommonResult updateOrderItem(@RequestBody @Validated OrderItemUpdateVO orderItemUpdateVO) {
        OrderItemUpdateDTO dto = OrderConvertAPP.INSTANCE.convertPageBO(orderItemUpdateVO);
        return orderService.updateOrderItem(dto);
    }

    @PutMapping("logistics/update")
    @ApiOperation("订单物流更新")
    public CommonResult updateLogistics(@RequestBody @Validated OrderLogisticsVO orderLogisticsVO) {
        OrderLogisticsUpdateDTO dto = OrderConvertAPP.INSTANCE.convertPageBO(orderLogisticsVO);
        return orderService.updateLogistics(dto);
    }
}
