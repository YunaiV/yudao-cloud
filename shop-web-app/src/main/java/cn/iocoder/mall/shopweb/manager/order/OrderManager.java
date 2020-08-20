package cn.iocoder.mall.shopweb.manager.order;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.exception.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.util.CollectionUtils;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.orderservice.rpc.cart.CartRpc;
import cn.iocoder.mall.orderservice.rpc.cart.dto.CartItemListReqDTO;
import cn.iocoder.mall.orderservice.rpc.cart.dto.CartItemRespDTO;
import cn.iocoder.mall.productservice.enums.sku.ProductSkuDetailFieldEnum;
import cn.iocoder.mall.productservice.rpc.sku.ProductSkuRpc;
import cn.iocoder.mall.productservice.rpc.sku.dto.ProductSkuListQueryReqDTO;
import cn.iocoder.mall.productservice.rpc.sku.dto.ProductSkuRespDTO;
import cn.iocoder.mall.promotion.api.rpc.activity.PromotionActivityRpc;
import cn.iocoder.mall.promotion.api.rpc.activity.dto.PromotionActivityListReqDTO;
import cn.iocoder.mall.promotion.api.rpc.activity.dto.PromotionActivityRespDTO;
import cn.iocoder.mall.promotion.api.rpc.coupon.CouponCardRpc;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.card.CouponCardAvailableListReqDTO;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.card.CouponCardAvailableRespDTO;
import cn.iocoder.mall.promotion.api.rpc.price.PriceRpc;
import cn.iocoder.mall.promotion.api.rpc.price.dto.PriceProductCalcReqDTO;
import cn.iocoder.mall.promotion.api.rpc.price.dto.PriceProductCalcRespDTO;
import cn.iocoder.mall.shopweb.controller.order.vo.order.OrderConfirmCreateInfoRespVO;
import cn.iocoder.mall.shopweb.convert.order.CartConvert;
import cn.iocoder.mall.shopweb.convert.order.OrderConvert;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.stream.Collectors;

import static cn.iocoder.mall.shopweb.enums.ShopWebErrorCodeConstants.ORDER_PRODUCT_SKU_NOT_EXISTS;
import static cn.iocoder.mall.shopweb.enums.ShopWebErrorCodeConstants.ORDER_PRODUCT_SKU_QUANTITY_NOT_ENOUGH;

/**
 * Order Manager
 */
@Service
@Validated
public class OrderManager {

    @DubboReference(version = "${dubbo.consumer.PriceRpc.version}")
    private PriceRpc priceRpc;
    @DubboReference(version = "${dubbo.consumer.PromotionActivityRpc.version}")
    private PromotionActivityRpc promotionActivityRpc;
    @DubboReference(version = "${dubbo.consumer.ProductCategoryRpc.version}")
    private CartRpc cartRpc;
    @DubboReference(version = "${dubbo.consumer.ProductSkuRpc.version}")
    private ProductSkuRpc productSkuRpc;
    @DubboReference(version = "${dubbo.consumer.CouponCardRpc.version}")
    private CouponCardRpc couponCardRpc;

    public OrderConfirmCreateInfoRespVO getOrderConfirmCreateInfo(Integer userId, Integer skuId, Integer quantity, Integer couponCardId) {
        Map<Integer, Integer> skuMap = new HashMap<>();
        skuMap.put(skuId, quantity);
        return this.getOrderConfirmCreateInfo0(userId, skuMap, couponCardId);
    }

    public OrderConfirmCreateInfoRespVO getOrderConfirmCreateInfoFromCart(Integer userId, Integer couponCardId) {
        // 获得购物车的商品
        CommonResult<List<CartItemRespDTO>> listCartItemsResult = cartRpc.listCartItems(
                new CartItemListReqDTO().setUserId(userId).setSelected(true));
        listCartItemsResult.checkError();
        // 购物车为空时，构造空的 OrderConfirmCreateInfoRespVO 返回
        if (CollectionUtils.isEmpty(listCartItemsResult.getData())) {
            OrderConfirmCreateInfoRespVO result = new OrderConfirmCreateInfoRespVO();
            result.setItemGroups(Collections.emptyList());
            result.setFee(new OrderConfirmCreateInfoRespVO.Fee(0, 0, 0, 0));
            return result;
        }
        // 计算商品价格
        Map<Integer, Integer> skuMap = CollectionUtils.convertMap(listCartItemsResult.getData(),
                CartItemRespDTO::getSkuId, CartItemRespDTO::getQuantity);
        return this.getOrderConfirmCreateInfo0(userId, skuMap, couponCardId);
    }

    private OrderConfirmCreateInfoRespVO getOrderConfirmCreateInfo0(Integer userId, Map<Integer, Integer> skuMap, Integer couponCardId) {
        // 校验商品都存在，并且库存足够
        this.checkProductSkus(skuMap);
        // 获得商品 SKU 信息
        Map<Integer, ProductSkuRespDTO> productSkuMap = this.checkProductSkus(skuMap);
        // 计算商品价格
        CommonResult<PriceProductCalcRespDTO> calcProductPriceResult = priceRpc.calcProductPrice(new PriceProductCalcReqDTO()
                .setUserId(userId).setCouponCardId(couponCardId)
                .setItems(skuMap.entrySet().stream().map(entry -> new PriceProductCalcReqDTO.Item(entry.getKey(), entry.getValue(), true))
                        .collect(Collectors.toList())));
        calcProductPriceResult.checkError();
        // 获得促销活动信息
        Map<Integer, PromotionActivityRespDTO> promotionActivityMap = this.getPromotionActivityMap(calcProductPriceResult.getData());
        // 拼接结果
        OrderConfirmCreateInfoRespVO createInfoRespVO = new OrderConfirmCreateInfoRespVO();
        createInfoRespVO.setFee(OrderConvert.INSTANCE.convert(calcProductPriceResult.getData().getFee()));
        createInfoRespVO.setItemGroups(new ArrayList<>(calcProductPriceResult.getData().getItemGroups().size()));
        for (PriceProductCalcRespDTO.ItemGroup itemGroupDTO : calcProductPriceResult.getData().getItemGroups()) {
            OrderConfirmCreateInfoRespVO.ItemGroup itemGroupVO = new OrderConfirmCreateInfoRespVO.ItemGroup();
            createInfoRespVO.getItemGroups().add(itemGroupVO);
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
        // 查询可用优惠劵信息
        CommonResult<List<CouponCardAvailableRespDTO>> listAvailableCouponCardsResult = couponCardRpc.listAvailableCouponCards(
                new CouponCardAvailableListReqDTO().setUserId(userId)
                        .setItems(OrderConvert.INSTANCE.convertList(calcProductPriceResult.getData().getItemGroups())));
        listAvailableCouponCardsResult.checkError();
        createInfoRespVO.setCouponCards(listAvailableCouponCardsResult.getData());
        return createInfoRespVO;
    }

    private Map<Integer, ProductSkuRespDTO> checkProductSkus(Map<Integer, Integer> skuMap) {
        // 获得商品 SKU 列表
        CommonResult<List<ProductSkuRespDTO>> listProductSkusResult = productSkuRpc.listProductSkus(new ProductSkuListQueryReqDTO()
                .setProductSkuIds(skuMap.keySet())
                .setFields(Arrays.asList(ProductSkuDetailFieldEnum.SPU.getField(), ProductSkuDetailFieldEnum.ATTR.getField())));
        listProductSkusResult.checkError();
        Map<Integer, ProductSkuRespDTO> productSkuMap = CollectionUtils.convertMap(listProductSkusResult.getData(), ProductSkuRespDTO::getId);
        // 校验商品 SKU 是否合法
        for (Map.Entry<Integer, Integer> entry : skuMap.entrySet()) {
            ProductSkuRespDTO productSku = productSkuMap.get(entry.getKey());
            if (productSku == null || !CommonStatusEnum.ENABLE.getValue().equals(productSku.getStatus())) {
                throw ServiceExceptionUtil.exception(ORDER_PRODUCT_SKU_NOT_EXISTS);
            }
            if (productSku.getQuantity() < entry.getValue()) {
                throw ServiceExceptionUtil.exception(ORDER_PRODUCT_SKU_QUANTITY_NOT_ENOUGH);
            }
        }
        return productSkuMap;
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

}
