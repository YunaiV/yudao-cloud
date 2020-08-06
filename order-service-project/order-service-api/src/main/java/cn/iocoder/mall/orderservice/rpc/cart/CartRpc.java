package cn.iocoder.mall.orderservice.rpc.cart;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.orderservice.rpc.cart.dto.CartItemAddReqDTO;
import cn.iocoder.mall.orderservice.rpc.cart.dto.CartItemUpdateQuantityReqDTO;

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
     * @param updateQuantityReqDTO 更新商品数量
     * @return 成功
     */
    CommonResult<Boolean> updateCartItemSelected(CartItemUpdateQuantityReqDTO updateQuantityReqDTO);

}
