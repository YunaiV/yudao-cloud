package cn.iocoder.mall.shopweb.manager.order.cart;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.orderservice.rpc.cart.CartRpc;
import cn.iocoder.mall.orderservice.rpc.cart.dto.CartItemAddReqDTO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

/**
 * 购物车 Manager
 */
@Service
public class CartManager {

    @DubboReference(version = "${dubbo.consumer.ProductCategoryRpc.version}")
    private CartRpc cartRpc;

    /**
     * 添加商品到购物车
     *
     * @param userId 用户编号
     * @param skuId 商品 SKU 编号
     * @param quantity 增加数量
     */
    public void addCartItem(Integer userId, Integer skuId, Integer quantity) {
        CommonResult<Boolean> addCartItemResult = cartRpc.addCartItem(new CartItemAddReqDTO().setUserId(userId)
            .setSkuId(skuId).setQuantity(quantity));
        addCartItemResult.checkError();
    }

    /**
     * 查询用户在购物车中的商品数量
     *
     * @param userId 用户编号
     * @return 商品数量
     */
    public Integer sumCartItemQuantity(Integer userId) {
        CommonResult<Integer> sumCartItemQuantityResult = cartRpc.sumCartItemQuantity(userId);
        sumCartItemQuantityResult.checkError();
        return sumCartItemQuantityResult.getData();
    }

}
