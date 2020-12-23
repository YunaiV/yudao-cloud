package cn.iocoder.mall.tradeservice.rpc.cart;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.tradeservice.service.cart.CartManager;
import cn.iocoder.mall.tradeservice.rpc.cart.dto.*;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
 * 购物车 Rpc 实现
 */
@DubboService
public class CartRpcImpl implements CartRpc {

    @Autowired
    private CartManager cartManager;

    @Override
    public CommonResult<Boolean> addCartItem(CartItemAddReqDTO addItemReqDTO) {
        cartManager.addCartItem(addItemReqDTO);
        return success(true);
    }

    @Override
    public CommonResult<Boolean> updateCartItemQuantity(CartItemUpdateQuantityReqDTO updateQuantityReqDTO) {
        cartManager.updateCartItemSelected(updateQuantityReqDTO);
        return success(true);
    }

    @Override
    public CommonResult<Boolean> updateCartItemSelected(CartItemUpdateSelectedReqDTO updateSelectedReqDTO) {
        cartManager.updateCartItemSelected(updateSelectedReqDTO);
        return success(true);
    }

    @Override
    public CommonResult<Boolean> deleteCartItems(CartItemDeleteListReqDTO deleteListReqDTO) {
        cartManager.deleteCartItems(deleteListReqDTO);
        return success(true);
    }

    @Override
    public CommonResult<Integer> sumCartItemQuantity(Integer userId) {
        return success(cartManager.sumCartItemQuantity(userId));
    }

    @Override
    public CommonResult<List<CartItemRespDTO>> listCartItems(CartItemListReqDTO listReqDTO) {
        return success(cartManager.listCartItems(listReqDTO));
    }

}
