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

//
//    @PostMapping("confirm_receiving")
//    @RequiresLogin
//    @ApiOperation("确认收货")
//    public CommonResult confirmReceiving(@RequestParam("orderId") Integer orderId) {
//        Integer userId = UserSecurityContextHolder.getContext().getUserId();
//        return orderService.confirmReceiving(userId, orderId);
//    }

}
