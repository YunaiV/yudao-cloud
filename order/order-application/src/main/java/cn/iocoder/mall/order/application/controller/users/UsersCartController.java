package cn.iocoder.mall.order.application.controller.users;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.order.api.CartService;
import cn.iocoder.mall.order.api.OrderService;

public class UsersCartController {

    // TODO 注入
    private CartService cartService;
    // TODO 注入
    private OrderService orderService;

    public CommonResult<Object> confirmOrder() {
        // 查询购物车列表（选中的）
//        cartService.list(userId, true);
        // 查询确认订单信息的明细

        return null;
    }

}
