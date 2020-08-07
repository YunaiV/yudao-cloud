package cn.iocoder.mall.shopweb.controller.order;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.security.user.core.context.UserSecurityContextHolder;
import cn.iocoder.mall.shopweb.manager.order.cart.CartManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@Api(tags = "购物车 API")
@RestController
@RequestMapping("/cart")
@Validated
public class CartController {

    @Autowired
    private CartManager cartManager;

    @PostMapping("add")
    @ApiOperation("添加商品到购物车")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "skuId", value = "商品 SKU 编号", required = true, example = "1"),
            @ApiImplicitParam(name = "quantity", value = "增加数量", required = true, example = "1024")
    })
    public CommonResult<Boolean> addCartItem(@RequestParam("skuId") Integer skuId,
                                             @RequestParam("quantity") Integer quantity) {
        cartManager.addCartItem(UserSecurityContextHolder.getUserId(), skuId, quantity);
        return success(true);
    }

    @GetMapping("sum-quantity")
    @ApiOperation("查询用户在购物车中的商品数量")
    public CommonResult<Integer> sumCartItemQuantity() {
        return success(cartManager.sumCartItemQuantity(UserSecurityContextHolder.getUserId()));
    }

}
