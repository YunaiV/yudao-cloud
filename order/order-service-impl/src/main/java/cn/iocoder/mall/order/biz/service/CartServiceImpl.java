package cn.iocoder.mall.order.biz.service;

import org.springframework.stereotype.Service;

/**
 * 购物车服务 Service 实现类
 */
@Service
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.CartService.version}")
public class CartServiceImpl {


//    @Override
//    @SuppressWarnings("Duplicates")
//    public CalcSkuPriceBO calcSkuPrice(Integer skuId) {
//        // 查询 SKU 是否合法
//        ProductSkuBO sku = productSpuService.getProductSku(skuId);
//        if (sku == null
//                || CommonStatusEnum.DISABLE.getValue().equals(sku.getStatus())) { // sku 被禁用
//            throw ServiceExceptionUtil.exception(OrderErrorCodeEnum.CARD_ITEM_SKU_NOT_FOUND.getCode());
//        }
//        // 查询促销活动
//        List<PromotionActivityBO> activityList = promotionActivityService.getPromotionActivityListBySpuId(sku.getSpuId(),
//                Arrays.asList(PromotionActivityStatusEnum.WAIT.getValue(), PromotionActivityStatusEnum.RUN.getValue()));
//        if (activityList.isEmpty()) { // 如果无促销活动，则直接返回默认结果即可
//            return new CalcSkuPriceBO().setOriginalPrice(sku.getPrice()).setBuyPrice(sku.getPrice());
//        }
//        // 如果有促销活动，则开始做计算 TODO 芋艿，因为现在暂时只有限时折扣 + 满减送。所以写的比较简单先
//        PromotionActivityBO fullPrivilege = findPromotionActivityByType(activityList, PromotionActivityTypeEnum.FULL_PRIVILEGE);
//        PromotionActivityBO timeLimitedDiscount = findPromotionActivityByType(activityList, PromotionActivityTypeEnum.TIME_LIMITED_DISCOUNT);
//        Integer presentPrice = calcSkuPriceByTimeLimitDiscount(sku, timeLimitedDiscount);
//        // 返回结果
//        return new CalcSkuPriceBO().setFullPrivilege(fullPrivilege).setTimeLimitedDiscount(timeLimitedDiscount)
//                .setOriginalPrice(sku.getPrice()).setBuyPrice(presentPrice);
//    }
//
//
//
//
//    private PromotionActivityBO findPromotionActivityByType(List<PromotionActivityBO> activityList, PromotionActivityTypeEnum type) {
//        return activityList.stream()
//                .filter(activity -> type.getValue().equals(activity.getActivityType()))
//                .findFirst().orElse(null);
//    }
//
//    private List<PromotionActivityBO> findPromotionActivityListByType(List<PromotionActivityBO> activityList, PromotionActivityTypeEnum type) {
//        return activityList.stream()
//                .filter(activity -> type.getValue().equals(activity.getActivityType()))
//                .collect(Collectors.toList());
//    }



}
