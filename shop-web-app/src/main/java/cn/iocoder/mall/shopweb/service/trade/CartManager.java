package cn.iocoder.mall.shopweb.service.trade;

import cn.iocoder.common.framework.util.CollectionUtils;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.tradeservice.rpc.cart.CartRpc;
import cn.iocoder.mall.tradeservice.rpc.cart.dto.*;
import cn.iocoder.mall.productservice.enums.sku.ProductSkuDetailFieldEnum;
import cn.iocoder.mall.productservice.rpc.sku.ProductSkuRpc;
import cn.iocoder.mall.productservice.rpc.sku.dto.ProductSkuListQueryReqDTO;
import cn.iocoder.mall.productservice.rpc.sku.dto.ProductSkuRespDTO;
import cn.iocoder.mall.promotion.api.rpc.activity.PromotionActivityRpc;
import cn.iocoder.mall.promotion.api.rpc.activity.dto.PromotionActivityListReqDTO;
import cn.iocoder.mall.promotion.api.rpc.activity.dto.PromotionActivityRespDTO;
import cn.iocoder.mall.promotion.api.rpc.price.PriceRpc;
import cn.iocoder.mall.promotion.api.rpc.price.dto.PriceProductCalcReqDTO;
import cn.iocoder.mall.promotion.api.rpc.price.dto.PriceProductCalcRespDTO;
import cn.iocoder.mall.shopweb.controller.trade.vo.cart.CartDetailVO;
import cn.iocoder.mall.shopweb.convert.trade.CartConvert;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.*;
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
    @DubboReference(version = "${dubbo.consumer.PromotionActivityRpc.version}")
    private PromotionActivityRpc promotionActivityRpc;
    @DubboReference(version = "${dubbo.consumer.ProductSkuRpc.version}")
    private ProductSkuRpc productSkuRpc;

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
     * 更新购物车商品数量
     *
     * @param userId 用户编号
     * @param skuId 商品 SKU 编号
     * @param quantity 数量
     */
    public void updateCartItemQuantity(Integer userId, Integer skuId, Integer quantity) {
        CommonResult<Boolean> updateCartItemQuantityResult = cartRpc.updateCartItemQuantity(new CartItemUpdateQuantityReqDTO()
            .setUserId(userId).setSkuId(skuId).setQuantity(quantity));
        updateCartItemQuantityResult.checkError();
    }

    /**
     * 更新购物车商品是否选中
     *
     * @param userId 用户编号
     * @param skuIds 商品 SKU 编号数组
     * @param selected 是否选中
     */
    public void updateCartItemSelected(Integer userId, Set<Integer> skuIds, Boolean selected) {
        CommonResult<Boolean> updateCartItemSelectedResult = cartRpc.updateCartItemSelected(new CartItemUpdateSelectedReqDTO()
            .setUserId(userId).setSkuIds(skuIds).setSelected(selected));
        updateCartItemSelectedResult.checkError();
    }

    /**
     * 查询用户的购物车的商品列表
     *
     * @return 商品列表
     */
    public CartDetailVO getCartDetail(Integer userId) {
        // 获得购物车的商品
        CommonResult<List<CartItemRespDTO>> listCartItemsResult = cartRpc.listCartItems(new CartItemListReqDTO().setUserId(userId));
        listCartItemsResult.checkError();
        // 购物车为空时，构造空的 UsersOrderConfirmCreateVO 返回
        if (CollectionUtils.isEmpty(listCartItemsResult.getData())) {
            CartDetailVO result = new CartDetailVO();
            result.setItemGroups(Collections.emptyList());
            result.setFee(new CartDetailVO.Fee(0, 0, 0, 0));
            return result;
        }
        // 计算商品价格
        CommonResult<PriceProductCalcRespDTO> calcProductPriceResult = priceRpc.calcProductPrice(new PriceProductCalcReqDTO().setUserId(userId)
                .setItems(listCartItemsResult.getData().stream()
                        .map(cartItem -> new PriceProductCalcReqDTO.Item(cartItem.getSkuId(), cartItem.getQuantity(), cartItem.getSelected()))
                        .collect(Collectors.toList())));
        calcProductPriceResult.checkError();
        // 获得促销活动信息
        Map<Integer, PromotionActivityRespDTO> promotionActivityMap = this.getPromotionActivityMap(calcProductPriceResult.getData());
        // 获得商品 SKU 信息
        Map<Integer, ProductSkuRespDTO> productSkuMap = this.getProductSkuMap(listCartItemsResult.getData());
        // 拼接结果
        CartDetailVO cartDetailVO = new CartDetailVO();
        cartDetailVO.setFee(CartConvert.INSTANCE.convert(calcProductPriceResult.getData().getFee()));
        cartDetailVO.setItemGroups(new ArrayList<>());
        for (PriceProductCalcRespDTO.ItemGroup itemGroupDTO : calcProductPriceResult.getData().getItemGroups()) {
            CartDetailVO.ItemGroup itemGroupVO = new CartDetailVO.ItemGroup();
            cartDetailVO.getItemGroups().add(itemGroupVO);
            // 活动信息
            if (itemGroupDTO.getActivityId() != null) {
                itemGroupVO.setActivity(promotionActivityMap.get(itemGroupDTO.getActivityId()))
                        .setActivityDiscountTotal(itemGroupDTO.getActivityDiscountTotal());
            }
            // 商品 SKU 信息
            itemGroupVO.setItems(new ArrayList<>());
            itemGroupDTO.getItems().forEach(item -> itemGroupVO.getItems().add(CartConvert.INSTANCE.convert(item,
                    productSkuMap.get(item.getSkuId()), promotionActivityMap.get(item.getActivityId()))));
        }
        return cartDetailVO;
    }

    private Map<Integer, PromotionActivityRespDTO> getPromotionActivityMap(PriceProductCalcRespDTO calcRespDTO) {
        // 获得所有促销活动编号
        Set<Integer> activeIds = new HashSet<>();
        calcRespDTO.getItemGroups().forEach(itemGroup -> {
            if (itemGroup.getActivityId() != null) {
                activeIds.add(itemGroup.getActivityId());
            }
            itemGroup.getItems().forEach(item -> {
                if (item.getActivityId() != null) {
                    activeIds.add(item.getActivityId());
                }
            });
        });
        if (CollectionUtils.isEmpty(activeIds)) {
            return Collections.emptyMap();
        }
        // 查询促销活动列表
        CommonResult<List<PromotionActivityRespDTO>> listPromotionActivitiesResult =
                promotionActivityRpc.listPromotionActivities(new PromotionActivityListReqDTO().setActiveIds(activeIds));
        listPromotionActivitiesResult.checkError();
        return CollectionUtils.convertMap(listPromotionActivitiesResult.getData(), PromotionActivityRespDTO::getId);
    }

    private Map<Integer, ProductSkuRespDTO> getProductSkuMap(List<CartItemRespDTO> itemRespDTOs) {
        CommonResult<List<ProductSkuRespDTO>> listProductSkusResult = productSkuRpc.listProductSkus(new ProductSkuListQueryReqDTO()
            .setProductSkuIds(CollectionUtils.convertSet(itemRespDTOs, CartItemRespDTO::getSkuId))
            .setFields(Arrays.asList(ProductSkuDetailFieldEnum.SPU.getField(), ProductSkuDetailFieldEnum.ATTR.getField())));
        listProductSkusResult.checkError();
        return CollectionUtils.convertMap(listProductSkusResult.getData(), ProductSkuRespDTO::getId);
    }

}
