package cn.iocoder.mall.tradeservice.service.cart;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.exception.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.tradeservice.convert.cart.CartConvert;
import cn.iocoder.mall.tradeservice.rpc.cart.dto.*;
import cn.iocoder.mall.tradeservice.service.cart.CartService;
import cn.iocoder.mall.tradeservice.service.cart.bo.CartItemBO;
import cn.iocoder.mall.productservice.rpc.sku.ProductSkuRpc;
import cn.iocoder.mall.productservice.rpc.sku.dto.ProductSkuRespDTO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static cn.iocoder.mall.tradeservice.enums.OrderErrorCodeConstants.CARD_ITEM_SKU_NOT_FOUND;

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
        cartService.addCartItem(CartConvert.INSTANCE.convert(addReqDTO).setSpuId(skuDTO.getSpuId()), skuDTO.getQuantity());
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

    /**
     * 更新购物车商品是否选中
     *
     * @param updateSelectedReqDTO 更新商品是否选中 DTO
     */
    public void updateCartItemSelected(CartItemUpdateSelectedReqDTO updateSelectedReqDTO) {
        cartService.updateCartItemSelected(updateSelectedReqDTO.getUserId(),
                updateSelectedReqDTO.getSkuIds(), updateSelectedReqDTO.getSelected());
    }

    /**
     * 删除购物车商品列表
     *
     * @param deleteListReqDTO 删除商品列表 DTO
     */
    public void deleteCartItems(CartItemDeleteListReqDTO deleteListReqDTO) {
        cartService.deleteCartItems(deleteListReqDTO.getUserId(),
                deleteListReqDTO.getSkuIds());
    }

    /**
     * 查询用户在购物车中的商品数量
     *
     * @param userId 用户编号
     * @return 商品数量
     */
    public Integer sumCartItemQuantity(Integer userId) {
        return cartService.sumCartItemQuantity(userId);
    }

    /**
     * 查询用户在购物车种的商品列表
     *
     * @param listReqDTO 查询条件 DTO
     * @return 购物车中商品列表信息
     */
    public List<CartItemRespDTO> listCartItems(CartItemListReqDTO listReqDTO) {
        List<CartItemBO> cartItemBOs = cartService.listCartItems(CartConvert.INSTANCE.convert(listReqDTO));
        return CartConvert.INSTANCE.convertList02(cartItemBOs);
    }

    /**
     * 校验商品 SKU 是否合法
     * 1. 是否存在
     * 2. 是否下架
     *
     * @param skuId 商品 SKU 编号
     * @return 商品 SKU 信息
     */
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
