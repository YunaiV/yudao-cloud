package cn.iocoder.mall.tradeservice.rpc.cart;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.tradeservice.rpc.cart.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Title:
 * Description:
 *
 * @author zhuyang
 * @version 1.0 2021/10/9
 */
@FeignClient(value = "trade-service")
public interface CartFeign {
    /**
     * 添加商品到购物车
     *
     * @param addReqDTO 添加商品信息
     * @return 成功
     */
    @PostMapping("addCartItem")
    CommonResult<Boolean> addCartItem(@RequestBody CartItemAddReqDTO addReqDTO);

    /**
     * 更新购物车商品数量
     *
     * @param updateQuantityReqDTO 更新商品数量 DTO
     * @return 成功
     */
    @PostMapping("updateCartItemQuantity")
    CommonResult<Boolean> updateCartItemQuantity(@RequestBody CartItemUpdateQuantityReqDTO updateQuantityReqDTO);

    /**
     * 更新购物车商品是否选中
     *
     * @param updateSelectedReqDTO 更新商品是否选中 DTO
     * @return 成功
     */
    @PostMapping("updateCartItemSelected")
    CommonResult<Boolean> updateCartItemSelected(@RequestBody CartItemUpdateSelectedReqDTO updateSelectedReqDTO);

    /**
     * 删除购物车商品列表
     *
     * @param deleteListReqDTO 删除商品列表 DTO
     * @return 成功
     */
    @PostMapping("deleteCartItems")
    CommonResult<Boolean> deleteCartItems(@RequestBody CartItemDeleteListReqDTO deleteListReqDTO);
    @GetMapping("/sumCartItemQuantity")
    public CommonResult<Integer> sumCartItemQuantity(@RequestParam("userId") Integer userId) ;
    @PostMapping("/listCartItems")
    public CommonResult<List<CartItemRespDTO>> listCartItems(@RequestBody CartItemListReqDTO listReqDTO) ;
}
