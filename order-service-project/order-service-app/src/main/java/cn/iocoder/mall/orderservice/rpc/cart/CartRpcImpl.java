package cn.iocoder.mall.orderservice.rpc.cart;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.orderservice.manager.cart.CartManager;
import cn.iocoder.mall.orderservice.rpc.cart.dto.CartItemAddReqDTO;
import cn.iocoder.mall.orderservice.rpc.cart.dto.CartItemUpdateQuantityReqDTO;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class CartRpcImpl implements CartRpc {

    @Autowired
    private CartManager cartManager;

    @Override
    public CommonResult<Boolean> addCartItem(CartItemAddReqDTO addItemReqDTO) {
        cartManager.addCartItem(addItemReqDTO);
        return CommonResult.success(true);
    }

    @Override
    public CommonResult<Boolean> updateCartItemSelected(CartItemUpdateQuantityReqDTO updateQuantityReqDTO) {
        cartManager.updateCartItemSelected(updateQuantityReqDTO);
        return CommonResult.success(true);
    }

}
