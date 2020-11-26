package cn.iocoder.mall.tradeservice.rpc.cart;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.tradeservice.rpc.cart.dto.*;

import java.util.List;

/**
 * 购物车 Rpc 接口
 */
public interface CartRpc {

    /**
     * 添加商品到购物车
     *
     * @param addReqDTO 添加商品信息
     * @return 成功
     */
    CommonResult<Boolean> addCartItem(CartItemAddReqDTO addReqDTO);

    /**
     * 更新购物车商品数量
     *
     * @param updateQuantityReqDTO 更新商品数量 DTO
     * @return 成功
     */
    CommonResult<Boolean> updateCartItemQuantity(CartItemUpdateQuantityReqDTO updateQuantityReqDTO);

    /**
     * 更新购物车商品是否选中
     *
     * @param updateSelectedReqDTO 更新商品是否选中 DTO
     * @return 成功
     */
    CommonResult<Boolean> updateCartItemSelected(CartItemUpdateSelectedReqDTO updateSelectedReqDTO);

    /**
     * 删除购物车商品列表
     *
     * @param deleteListReqDTO 删除商品列表 DTO
     * @return 成功
     */
    CommonResult<Boolean> deleteCartItems(CartItemDeleteListReqDTO deleteListReqDTO);

    /**
     * 查询用户在购物车中的商品数量
     *
     * @param userId 用户编号
     * @return 商品数量
     */
    CommonResult<Integer> sumCartItemQuantity(Integer userId);

    /**
     * 查询用户在购物车种的商品列表
     *
     * @param listReqDTO 查询条件 DTO
     * @return 购物车中商品列表信息
     */
    CommonResult<List<CartItemRespDTO>> listCartItems(CartItemListReqDTO listReqDTO);

}
