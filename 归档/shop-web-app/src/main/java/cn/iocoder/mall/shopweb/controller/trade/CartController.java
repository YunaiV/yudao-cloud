package cn.iocoder.mall.shopweb.controller.trade;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.security.user.core.context.UserSecurityContextHolder;
import cn.iocoder.mall.shopweb.controller.trade.vo.cart.CartDetailVO;
import cn.iocoder.mall.shopweb.service.trade.CartManager;
import cn.iocoder.security.annotations.RequiresAuthenticate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

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
    @RequiresAuthenticate
    public CommonResult<Boolean> addCartItem(@RequestParam("skuId") Integer skuId,
                                             @RequestParam("quantity") Integer quantity) {
        cartManager.addCartItem(UserSecurityContextHolder.getUserId(), skuId, quantity);
        return success(true);
    }

    @GetMapping("sum-quantity")
    @ApiOperation("查询用户在购物车中的商品数量")
    @RequiresAuthenticate
    public CommonResult<Integer> sumCartItemQuantity() {
        return success(cartManager.sumCartItemQuantity(UserSecurityContextHolder.getUserId()));
    }

    @GetMapping("/get-detail")
    @ApiOperation("查询用户的购物车的商品列表")
    @RequiresAuthenticate
    public CommonResult<CartDetailVO> getCartDetail() {
        return success(cartManager.getCartDetail(UserSecurityContextHolder.getUserId()));
    }

    @PostMapping("update-quantity")
    @ApiOperation("更新购物车商品数量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "skuId", value = "商品 SKU 编号", required = true, example = "1"),
            @ApiImplicitParam(name = "quantity", value = "增加数量", required = true, example = "1024")
    })
    @RequiresAuthenticate
    public CommonResult<Boolean> updateCartItemQuantity(@RequestParam("skuId") Integer skuId,
                                                        @RequestParam("quantity") Integer quantity) {
        cartManager.updateCartItemQuantity(UserSecurityContextHolder.getUserId(), skuId, quantity);
        return success(true);
    }

    @PostMapping("update-selected")
    @ApiOperation("更新购物车商品是否选中")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "skuIds", value = "商品 SKU 编号数组", required = true, example = "1,3"),
            @ApiImplicitParam(name = "selected", value = "是否选中", required = true, example = "true")
    })
    @RequiresAuthenticate
    public CommonResult<Boolean> updateCartItemSelected(@RequestParam("skuIds") Set<Integer> skuIds,
                                                        @RequestParam("selected") Boolean selected) {
        cartManager.updateCartItemSelected(UserSecurityContextHolder.getUserId(), skuIds, selected);
        // 获得目前购物车明细
        return success(true);
    }

}
