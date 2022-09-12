package cn.iocoder.mall.shopweb.service.trade;

import cn.iocoder.common.framework.util.CollectionUtils;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.productservice.enums.sku.ProductSkuDetailFieldEnum;
import cn.iocoder.mall.productservice.rpc.sku.ProductSkuFeign;
import cn.iocoder.mall.productservice.rpc.sku.dto.ProductSkuListQueryReqDTO;
import cn.iocoder.mall.productservice.rpc.sku.dto.ProductSkuRespDTO;
import cn.iocoder.mall.promotion.api.rpc.activity.PromotionActivityFeign;
import cn.iocoder.mall.promotion.api.rpc.activity.dto.PromotionActivityListReqDTO;
import cn.iocoder.mall.promotion.api.rpc.activity.dto.PromotionActivityRespDTO;
import cn.iocoder.mall.promotion.api.rpc.price.PriceFeign;
import cn.iocoder.mall.promotion.api.rpc.price.dto.PriceProductCalcReqDTO;
import cn.iocoder.mall.promotion.api.rpc.price.dto.PriceProductCalcRespDTO;
import cn.iocoder.mall.shopweb.controller.trade.vo.cart.CartDetailVO;
import cn.iocoder.mall.shopweb.convert.trade.CartConvert;
import cn.iocoder.mall.tradeservice.rpc.cart.CartFeign;
import cn.iocoder.mall.tradeservice.rpc.cart.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 购物车 Manager
 */
@Service
public class CartManager {

    /**
     * 查询用户的购物车的商品列表
     *
     * @return 商品列表
     */
    public CartDetailVO getCartDetail(Integer userId) {
        // 获得购物车的商品
        CommonResult<List<CartItemRespDTO>> listCartItemsResult = cartFeign.listCartItems(new CartItemListReqDTO().setUserId(userId));
        listCartItemsResult.checkError();
        // 购物车为空时，构造空的 UsersOrderConfirmCreateVO 返回
        if (CollectionUtils.isEmpty(listCartItemsResult.getData())) {
            CartDetailVO result = new CartDetailVO();
            result.setItemGroups(Collections.emptyList());
            result.setFee(new CartDetailVO.Fee(0, 0, 0, 0));
            return result;
        }
        // 计算商品价格
        CommonResult<PriceProductCalcRespDTO> calcProductPriceResult = priceFeign.calcProductPrice(new PriceProductCalcReqDTO().setUserId(userId)
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
                promotionActivityFeign.listPromotionActivities(new PromotionActivityListReqDTO().setActiveIds(activeIds));
        listPromotionActivitiesResult.checkError();
        return CollectionUtils.convertMap(listPromotionActivitiesResult.getData(), PromotionActivityRespDTO::getId);
    }

    private Map<Integer, ProductSkuRespDTO> getProductSkuMap(List<CartItemRespDTO> itemRespDTOs) {
        CommonResult<List<ProductSkuRespDTO>> listProductSkusResult = productSkuFeign.listProductSkus(new ProductSkuListQueryReqDTO()
            .setProductSkuIds(CollectionUtils.convertSet(itemRespDTOs, CartItemRespDTO::getSkuId))
            .setFields(Arrays.asList(ProductSkuDetailFieldEnum.SPU.getField(), ProductSkuDetailFieldEnum.ATTR.getField())));
        listProductSkusResult.checkError();
        return CollectionUtils.convertMap(listProductSkusResult.getData(), ProductSkuRespDTO::getId);
    }

}
