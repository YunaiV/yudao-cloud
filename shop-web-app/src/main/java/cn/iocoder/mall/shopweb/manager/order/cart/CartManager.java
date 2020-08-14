package cn.iocoder.mall.shopweb.manager.order.cart;

import cn.iocoder.common.framework.util.CollectionUtils;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.orderservice.rpc.cart.CartRpc;
import cn.iocoder.mall.orderservice.rpc.cart.dto.CartItemAddReqDTO;
import cn.iocoder.mall.orderservice.rpc.cart.dto.CartItemListReqDTO;
import cn.iocoder.mall.orderservice.rpc.cart.dto.CartItemRespDTO;
import cn.iocoder.mall.promotion.api.rpc.price.PriceRpc;
import cn.iocoder.mall.promotion.api.rpc.price.dto.PriceProductCalcReqDTO;
import cn.iocoder.mall.promotion.api.rpc.price.dto.PriceProductCalcRespDTO;
import cn.iocoder.mall.shopweb.controller.order.vo.cart.CartDetailVO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 购物车 Manager
 */
@Service
public class CartManager {

    @DubboReference(version = "${dubbo.consumer.ProductCategoryRpc.version}")
    private CartRpc cartRpc;
    @DubboReference(version = "${dubbo.consumer.PriceRpc.version}")
    private PriceRpc priceRpc;

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

    /**
     * 查询用户的购物车的商品列表
     *
     * @return 商品列表
     */
    public CartDetailVO getCartDetail(Integer userId) {
        // 获得购物车中选中的
        CommonResult<List<CartItemRespDTO>> listCartItemsResult = cartRpc.listCartItems(new CartItemListReqDTO().setUserId(userId));
        listCartItemsResult.checkError();
        // 购物车为空时，构造空的 UsersOrderConfirmCreateVO 返回
        if (CollectionUtils.isEmpty(listCartItemsResult.getData())) {
            CartDetailVO result = new CartDetailVO();
            result.setItemGroups(Collections.emptyList());
            result.setFee(new CartDetailVO.Fee(0, 0, 0, 0));
            return result;
        }
        // 计算选中的商品价格
        CommonResult<PriceProductCalcRespDTO> calcProductPriceResult = priceRpc.calcProductPrice(new PriceProductCalcReqDTO().setUserId(userId)
                .setItems(listCartItemsResult.getData().stream()
                        .filter(CartItemRespDTO::getSelected)
                        .map(cartItem -> new PriceProductCalcReqDTO.Item(cartItem.getSkuId(), cartItem.getQuantity()))
                        .collect(Collectors.toList())));
        calcProductPriceResult.checkError();
        // 拼接结果

        // 执行数据拼装
        return null;
    }

}
