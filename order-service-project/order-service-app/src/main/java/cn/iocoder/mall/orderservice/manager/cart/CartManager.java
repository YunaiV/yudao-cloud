package cn.iocoder.mall.orderservice.manager.cart;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.exception.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.orderservice.convert.cart.CartConvert;
import cn.iocoder.mall.orderservice.rpc.cart.dto.CartItemAddReqDTO;
import cn.iocoder.mall.orderservice.rpc.cart.dto.CartItemUpdateQuantityReqDTO;
import cn.iocoder.mall.orderservice.service.cart.CartService;
import cn.iocoder.mall.productservice.rpc.sku.ProductSkuRpc;
import cn.iocoder.mall.productservice.rpc.sku.dto.ProductSkuRespDTO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static cn.iocoder.mall.orderservice.enums.OrderErrorCodeConstants.CARD_ITEM_SKU_NOT_FOUND;

/**
 * 购物车 Manager
 */
@Service
public class CartManager {

    @DubboReference(version = "${dubbo.consumer.ProductSkuRpc.version}")
    private ProductSkuRpc productSkuRpc;

    @Autowired
    private CartService cartService;

    /**
     * 添加商品到购物车
     *
     * @param addReqDTO 添加商品信息
     */
    public void addCartItem(CartItemAddReqDTO addReqDTO) {
        // 校验商品 SKU 是否合法
        ProductSkuRespDTO skuDTO = this.checkProductSku(addReqDTO.getSkuId());
        // 添加购物车项
        cartService.addCartItem(CartConvert.INSTANCE.convert(addReqDTO), skuDTO.getQuantity());
    }

    /**
     * 更新购物车商品数量
     *
     * @param updateQuantityReqDTO 更新商品数量
     */
    public void updateCartItemSelected(CartItemUpdateQuantityReqDTO updateQuantityReqDTO) {
        // 校验商品 SKU 是否合法
        ProductSkuRespDTO skuDTO = this.checkProductSku(updateQuantityReqDTO.getSkuId());
        // 更新购物车商品数量
        cartService.updateCartItemQuantity(updateQuantityReqDTO.getUserId(), updateQuantityReqDTO.getSkuId(),
                updateQuantityReqDTO.getQuantity(), skuDTO.getQuantity());
    }

    private ProductSkuRespDTO checkProductSku(Integer skuId) {
        CommonResult<ProductSkuRespDTO> getProductSkuResult = productSkuRpc.getProductSku(skuId);
        getProductSkuResult.checkError();
        ProductSkuRespDTO skuDTO = getProductSkuResult.getData();
        if (skuDTO == null || CommonStatusEnum.DISABLE.getValue().equals(skuDTO.getStatus())) {
            throw ServiceExceptionUtil.exception(CARD_ITEM_SKU_NOT_FOUND);
        }
        return skuDTO;
    }

}
