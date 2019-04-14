package cn.iocoder.mall.order.application.controller.users;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.order.api.OrderLogisticsService;
import cn.iocoder.mall.order.api.bo.OrderLogisticsInfoBO;
import cn.iocoder.mall.user.sdk.context.UserSecurityContextHolder;
import com.alibaba.dubbo.config.annotation.Reference;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
public class OrderLogisticsController {

    @Reference(validation = "true")
    private OrderLogisticsService orderLogisticsService;

    @GetMapping("logistics_info")
    @ApiOperation("物流详细 - 返回订单所关联的所有物流信息")
    public CommonResult<OrderLogisticsInfoBO> logisticsInfo(@RequestParam("orderId") Integer orderId) {
        Integer userId = UserSecurityContextHolder.getContext().getUserId();
        return orderLogisticsService.logisticsInfo(userId, orderId);
    }
}
