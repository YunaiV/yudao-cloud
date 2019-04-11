package cn.iocoder.mall.order.application.controller.users;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.order.api.CartService;
import cn.iocoder.mall.order.api.OrderService;
import cn.iocoder.mall.order.api.bo.CartItemBO;
import cn.iocoder.mall.order.application.vo.UsersOrderConfirmCreateVO;
import cn.iocoder.mall.user.sdk.context.UserSecurityContextHolder;
import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("users/cart")
public class UsersCartController {

    @Reference(validation = "true")
    private CartService cartService;
    @Reference(validation = "true")
    private OrderService orderService;

    @PostMapping("add")
    public CommonResult<Integer> add(@Param("skuId") Integer skuId,
                                     @Param("quantity") Integer quantity) {
        // 添加到购物车
        CommonResult<Boolean> addResult = cartService.add(UserSecurityContextHolder.getContext().getUserId(),
                skuId, quantity);
        // 添加失败，则直接返回错误
        if (addResult.isError()) {
            return CommonResult.error(addResult);
        }
        // 获得目前购物车商品总数量
        return cartService.count(UserSecurityContextHolder.getContext().getUserId());
    }

    @PostMapping("update_quantity")
    public CommonResult<Integer> updateQuantity(@Param("skuId") Integer skuId,
                                                @Param("quantity") Integer quantity) {
        // 添加到购物车
        CommonResult<Boolean> updateQuantityResult = cartService.updateQuantity(UserSecurityContextHolder.getContext().getUserId(),
                skuId, quantity);
        // 添加失败，则直接返回错误
        if (updateQuantityResult.isError()) {
            return CommonResult.error(updateQuantityResult);
        }
        // 获得目前购物车商品总数量
        // TODO 芋艿，需要改成价格计算
        return cartService.count(UserSecurityContextHolder.getContext().getUserId());
    }

    @PostMapping("update_selected")
    public CommonResult<Integer> updateSelected(@Param("skuId") Integer skuId,
                                                @Param("selected") Boolean selected) {
        // 添加到购物车
        CommonResult<Boolean> updateSelectedResult = cartService.updateSelected(UserSecurityContextHolder.getContext().getUserId(),
                skuId, selected);
        // 添加失败，则直接返回错误
        if (updateSelectedResult.isError()) {
            return CommonResult.error(updateSelectedResult);
        }
        // 获得目前购物车商品总数量
        // TODO 芋艿，需要改成价格计算
        return cartService.count(UserSecurityContextHolder.getContext().getUserId());
    }

    @GetMapping("count")
    public CommonResult<Integer> count() {
        return cartService.count(UserSecurityContextHolder.getContext().getUserId());
    }

    @GetMapping("/list")
    public CommonResult<UsersOrderConfirmCreateVO> list() {
        // 获得购物车中所有的
        List<CartItemBO> cartItems = cartService.list(UserSecurityContextHolder.getContext().getUserId(), null);
        // 购物车为空时，构造空的 UsersOrderConfirmCreateVO 返回
        if (cartItems.isEmpty()) {
            UsersOrderConfirmCreateVO result = new UsersOrderConfirmCreateVO();
            result.setItemGroups(Collections.emptyList());
            result.setFee(new UsersOrderConfirmCreateVO.Fee(0, 0, 0, 0));
            return CommonResult.success(result);
        }
        // 购物车非空时，获得具体结果

        return null;
    }

    @GetMapping("/confirm_create_order")
    public CommonResult<UsersOrderConfirmCreateVO> getConfirmCreateOrder() {
        // 获得购物车中选中的
        List<CartItemBO> cartItems = cartService.list(UserSecurityContextHolder.getContext().getUserId(), true);
        // 购物车为空时，构造空的 UsersOrderConfirmCreateVO 返回
        if (cartItems.isEmpty()) {
            UsersOrderConfirmCreateVO result = new UsersOrderConfirmCreateVO();
            result.setItemGroups(Collections.emptyList());
            result.setFee(new UsersOrderConfirmCreateVO.Fee(0, 0, 0, 0));
            return CommonResult.success(result);
        }
        // 购物车非空时，获得具体结果
//        return CommonResult.success(CartConvert.INSTANCE.convert(calcOrderPriceResult.getData()));
        return null;
    }

    public CommonResult<Object> confirmOrder() {
        // 查询购物车列表（选中的）
//        cartService.list(userId, true);
        // 查询确认订单信息的明细

        return null;
    }

}
