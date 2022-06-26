package cn.iocoder.mall.tradeservice.controller;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.tradeservice.rpc.cart.dto.*;
import cn.iocoder.mall.tradeservice.service.cart.CartManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@RestController
@RequestMapping("/trade/cart")
public class CartController {
    @Autowired
    private CartManager cartManager;

    /**
     * 添加商品到购物车
     *
     * @param addReqDTO 添加商品信息
     * @return 成功
     */
    @PostMapping("addCartItem")
    CommonResult<Boolean> addCartItem(@RequestBody CartItemAddReqDTO addReqDTO){
        cartManager.addCartItem(addReqDTO);
        return success(true);
    }

    /**
     * 更新购物车商品数量
     *
     * @param updateQuantityReqDTO 更新商品数量 DTO
     * @return 成功
     */
    @PostMapping("updateCartItemQuantity")
    CommonResult<Boolean> updateCartItemQuantity(@RequestBody CartItemUpdateQuantityReqDTO updateQuantityReqDTO){
        cartManager.updateCartItemSelected(updateQuantityReqDTO);
        return success(true);
    }

    /**
     * 更新购物车商品是否选中
     *
     * @param updateSelectedReqDTO 更新商品是否选中 DTO
     * @return 成功
     */
    @PostMapping("updateCartItemSelected")
    CommonResult<Boolean> updateCartItemSelected(@RequestBody CartItemUpdateSelectedReqDTO updateSelectedReqDTO){
        cartManager.updateCartItemSelected(updateSelectedReqDTO);
        return success(true);
    }

    /**
     * 删除购物车商品列表
     *
     * @param deleteListReqDTO 删除商品列表 DTO
     * @return 成功
     */
    @PostMapping("deleteCartItems")
    CommonResult<Boolean> deleteCartItems(@RequestBody CartItemDeleteListReqDTO deleteListReqDTO){
        cartManager.deleteCartItems(deleteListReqDTO);
        return success(true);
    }
    @GetMapping("/sumCartItemQuantity")
    public CommonResult<Integer> sumCartItemQuantity(@RequestParam("userId") Integer userId) {
        return success(cartManager.sumCartItemQuantity(userId));
    }
    @PostMapping("/listCartItems")
    public CommonResult<List<CartItemRespDTO>> listCartItems(@RequestBody CartItemListReqDTO listReqDTO) {
        return success(cartManager.listCartItems(listReqDTO));
    }
}
