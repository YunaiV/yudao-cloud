package cn.iocoder.mall.promotionservice.manager.price;

import cn.iocoder.common.framework.exception.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.util.CollectionUtils;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.productservice.rpc.sku.ProductSkuRpc;
import cn.iocoder.mall.productservice.rpc.sku.dto.ProductSkuListQueryReqDTO;
import cn.iocoder.mall.productservice.rpc.sku.dto.ProductSkuRespDTO;
import cn.iocoder.mall.productservice.rpc.spu.ProductSpuRpc;
import cn.iocoder.mall.productservice.rpc.spu.dto.ProductSpuRespDTO;
import cn.iocoder.mall.promotion.api.enums.*;
import cn.iocoder.mall.promotion.api.enums.activity.PromotionActivityStatusEnum;
import cn.iocoder.mall.promotion.api.enums.activity.PromotionActivityTypeEnum;
import cn.iocoder.mall.promotion.api.rpc.activity.dto.PromotionActivityRespDTO;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.card.CouponCardRespDTO;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.template.CouponTemplateRespDTO;
import cn.iocoder.mall.promotion.api.rpc.price.dto.PriceProductCalcReqDTO;
import cn.iocoder.mall.promotion.api.rpc.price.dto.PriceProductCalcRespDTO;
import cn.iocoder.mall.promotionservice.service.activity.PromotionActivityService;
import cn.iocoder.mall.promotionservice.service.coupon.CouponCardService;
import cn.iocoder.mall.promotionservice.service.coupon.CouponTemplateService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.stream.Collectors;

import static cn.iocoder.mall.promotion.api.enums.PromotionErrorCodeConstants.*;

@Service
@Validated
public class PriceManager {

    @DubboReference(version = "${dubbo.consumer.ProductSkuRpc.version}")
    private ProductSkuRpc productSkuRpc;
    @DubboReference(version = "${dubbo.consumer.ProductSpuRpc.version}")
    private ProductSpuRpc productSpuRpc;

    @Autowired
    private PromotionActivityService promotionActivityService;
    @Autowired
    private CouponCardService couponCardService;
    @Autowired
    private CouponTemplateService couponTemplateService;

    public PriceProductCalcRespDTO calcProductPrice(PriceProductCalcReqDTO calcReqDTO) {
        // TODO 芋艿，补充一些表单校验。例如说，需要传入用户编号。
        // 校验商品都存在
        Map<Integer, PriceProductCalcReqDTO.Item> calcProductItemDTOMap = CollectionUtils.convertMap(
                calcReqDTO.getItems(), PriceProductCalcReqDTO.Item::getSkuId);
        CommonResult<List<ProductSkuRespDTO>> listProductSkusResult = productSkuRpc.listProductSkus(
                new ProductSkuListQueryReqDTO().setProductSkuIds(calcProductItemDTOMap.keySet()));
        listProductSkusResult.checkError();
        if (calcReqDTO.getItems().size() != listProductSkusResult.getData().size()) {
            throw ServiceExceptionUtil.exception(PRICE_PRODUCT_SKU_NOT_EXISTS);
        }
        // TODO 库存相关
        // 查询促销活动
        List<PromotionActivityRespDTO> activityRespDTOs = promotionActivityService.listPromotionActivitiesBySpuIds(
                CollectionUtils.convertSet(listProductSkusResult.getData(), ProductSkuRespDTO::getSpuId),
                Collections.singleton(PromotionActivityStatusEnum.RUN.getValue()));
        // 拼装结果（主要是计算价格）
        PriceProductCalcRespDTO calcRespDTO = new PriceProductCalcRespDTO();
        // 1. 创建初始的每一项的数组
        List<PriceProductCalcRespDTO.Item> calcItemRespDTOs = this.initCalcOrderPriceItems(
                listProductSkusResult.getData(), calcProductItemDTOMap);
        // 2. 计算【限时折扣】促销
        this.modifyPriceByTimeLimitDiscount(calcItemRespDTOs, activityRespDTOs);
        // 3. 计算【满减送】促销
        List<PriceProductCalcRespDTO.ItemGroup> itemGroups = this.groupByFullPrivilege(calcItemRespDTOs, activityRespDTOs);
        calcRespDTO.setItemGroups(itemGroups);
        // 4. 计算优惠劵 TODO 芋艿：未详细测试；
        if (calcReqDTO.getCouponCardId() != null) {
            Integer result = this.modifyPriceByCouponCard(calcReqDTO.getUserId(), calcReqDTO.getCouponCardId(), itemGroups);
            calcRespDTO.setCouponCardDiscountTotal(result);
        }
        // 5. 计算最终的价格
        int buyTotal = 0;
        int discountTotal = 0;
        int presentTotal = 0;
        for (PriceProductCalcRespDTO.ItemGroup itemGroup : calcRespDTO.getItemGroups()) {
            buyTotal += itemGroup.getItems().stream().mapToInt(item -> item.getSelected() ? item.getBuyTotal() : 0).sum();
            discountTotal += itemGroup.getItems().stream().mapToInt(item -> item.getSelected() ? item.getDiscountTotal() : 0).sum();
            presentTotal += itemGroup.getItems().stream().mapToInt(item -> item.getSelected() ? item.getPresentTotal() : 0).sum();
        }
        Assert.isTrue(buyTotal - discountTotal ==  presentTotal,
                String.format("价格合计( %d - %d == %d )不正确", buyTotal, discountTotal, presentTotal));
        calcRespDTO.setFee(new PriceProductCalcRespDTO.Fee(buyTotal, discountTotal, 0, presentTotal));
        return calcRespDTO;
    }

    private List<PriceProductCalcRespDTO.Item> initCalcOrderPriceItems(List<ProductSkuRespDTO> skus,
                                                                       Map<Integer, PriceProductCalcReqDTO.Item> calcProductItemDTOMap) {
        // 获得商品分类 Map
        CommonResult<List<ProductSpuRespDTO>> listProductSpusResult = productSpuRpc.listProductSpus(CollectionUtils.convertSet(skus, ProductSkuRespDTO::getSpuId));
        listProductSpusResult.checkError();
        Map<Integer, Integer> spuIdCategoryIdMap = CollectionUtils.convertMap(listProductSpusResult.getData(), // SPU 编号与 Category 编号的映射
                ProductSpuRespDTO::getId, ProductSpuRespDTO::getCid);
        // 生成商品列表
        List<PriceProductCalcRespDTO.Item> items = new ArrayList<>();
        for (ProductSkuRespDTO sku : skus) {
            PriceProductCalcRespDTO.Item item = new PriceProductCalcRespDTO.Item();
            items.add(item);
            // 将基本信息，复制到 item 中
            PriceProductCalcReqDTO.Item calcOrderItem = calcProductItemDTOMap.get(sku.getId());
            item.setSpuId(sku.getSpuId()).setSkuId(sku.getId());
            item.setCid(spuIdCategoryIdMap.get(sku.getSpuId()));
            item.setSelected(calcOrderItem.getSelected());
            item.setBuyQuantity(calcOrderItem.getQuantity());
            // 计算初始价格
            item.setOriginPrice(sku.getPrice());
            item.setBuyPrice(sku.getPrice());
            item.setPresentPrice(sku.getPrice());
            item.setBuyTotal(sku.getPrice() * calcOrderItem.getQuantity());
            item.setDiscountTotal(0);
            item.setPresentTotal(item.getBuyTotal());
        }
        return items;
    }

    private void modifyPriceByTimeLimitDiscount(List<PriceProductCalcRespDTO.Item> items, List<PromotionActivityRespDTO> activityList) {
        for (PriceProductCalcRespDTO.Item item : items) {
            // 获得符合条件的限时折扣
            PromotionActivityRespDTO timeLimitedDiscount = activityList.stream()
                    .filter(activity -> PromotionActivityTypeEnum.TIME_LIMITED_DISCOUNT.getValue().equals(activity.getActivityType())
                            && activity.getTimeLimitedDiscount().getItems().stream().anyMatch(item0 -> item0.getSpuId().equals(item.getSpuId())))
                    .findFirst().orElse(null);
            if (timeLimitedDiscount == null) {
                continue;
            }
            // 计算价格
            Integer newBuyPrice = calcSkuPriceByTimeLimitDiscount(item, timeLimitedDiscount);
            if (newBuyPrice.equals(item.getBuyPrice())) { // 未优惠
                continue;
            }
            // 设置优惠
            item.setActivityId(timeLimitedDiscount.getId());
            // 设置价格
            item.setBuyPrice(newBuyPrice);
            item.setBuyTotal(newBuyPrice * item.getBuyQuantity());
            item.setPresentTotal(item.getBuyTotal() - item.getDiscountTotal());
            item.setPresentPrice(item.getPresentTotal() / item.getBuyQuantity());
        }
    }

    /**
     * 计算指定 SKU 在限时折扣下的价格
     *
     * @param sku                 SKU
     * @param timeLimitedDiscount 限时折扣促销。
     *                            传入的该活动，要保证该 SKU 在该促销下一定有优惠。
     * @return 计算后的价格
     */
    private Integer calcSkuPriceByTimeLimitDiscount(PriceProductCalcRespDTO.Item sku, PromotionActivityRespDTO timeLimitedDiscount) {
        if (timeLimitedDiscount == null) {
            return sku.getBuyPrice();
        }
        // 获得对应的优惠项
        PromotionActivityRespDTO.TimeLimitedDiscount.Item item = timeLimitedDiscount.getTimeLimitedDiscount().getItems().stream()
                .filter(item0 -> item0.getSpuId().equals(sku.getSpuId()))
                .findFirst().orElse(null);
        if (item == null) {
            throw new IllegalArgumentException(String.format("折扣活动(%s) 不存在商品(%s) 的优惠配置",
                    timeLimitedDiscount.toString(), sku.toString()));
        }
        // 计算价格
        if (PreferentialTypeEnum.PRICE.getValue().equals(item.getPreferentialType())) { // 减价
            int presentPrice = sku.getBuyPrice() - item.getPreferentialValue();
            return presentPrice >= 0 ? presentPrice : sku.getBuyPrice(); // 如果计算优惠价格小于 0 ，则说明无法使用优惠。
        }
        if (PreferentialTypeEnum.DISCOUNT.getValue().equals(item.getPreferentialType())) { // 打折
            return sku.getBuyPrice() * item.getPreferentialValue() / 100;
        }
        throw new IllegalArgumentException(String.format("折扣活动(%s) 的优惠类型不正确", timeLimitedDiscount.toString()));
    }

    private List<PriceProductCalcRespDTO.ItemGroup> groupByFullPrivilege(List<PriceProductCalcRespDTO.Item> items, List<PromotionActivityRespDTO> activityList) {
        List<PriceProductCalcRespDTO.ItemGroup> itemGroups = new ArrayList<>();
        // 获得所有满减送促销
        List<PromotionActivityRespDTO> fullPrivileges = activityList.stream()
                .filter(activity -> PromotionActivityTypeEnum.FULL_PRIVILEGE.getValue().equals(activity.getActivityType()))
                .collect(Collectors.toList());
        // 基于满减送促销，进行分组
        if (!fullPrivileges.isEmpty()) {
            items = new ArrayList<>(items); // 因为下面会修改数组，进行浅拷贝，避免影响传入的 items 。
            for (PromotionActivityRespDTO fullPrivilege : fullPrivileges) {
                // 创建 fullPrivilege 对应的分组
                PriceProductCalcRespDTO.ItemGroup itemGroup = new PriceProductCalcRespDTO.ItemGroup()
                        .setActivityId(fullPrivilege.getId())
                        .setItems(new ArrayList<>());
                // 筛选商品到分组中
                for (Iterator<PriceProductCalcRespDTO.Item> iterator = items.iterator(); iterator.hasNext(); ) {
                    PriceProductCalcRespDTO.Item item = iterator.next();
                    if (!isSpuMatchFullPrivilege(item.getSpuId(), fullPrivilege)) {
                        continue;
                    }
                    itemGroup.getItems().add(item);
                    iterator.remove();
                }
                // 如果匹配到，则添加到 itemGroups 中
                if (!itemGroup.getItems().isEmpty()) {
                    itemGroups.add(itemGroup);
                }
            }
        }
        // 处理未参加活动的商品，形成一个分组
        if (!items.isEmpty()) {
            itemGroups.add(new PriceProductCalcRespDTO.ItemGroup().setItems(items));
        }
        // 计算每个分组的价格
        Map<Integer, PromotionActivityRespDTO> activityMap = CollectionUtils.convertMap(activityList, PromotionActivityRespDTO::getId);
        for (PriceProductCalcRespDTO.ItemGroup itemGroup : itemGroups) {
            itemGroup.setActivityDiscountTotal(calcSkuPriceByFullPrivilege(itemGroup, activityMap.get(itemGroup.getActivityId())));
        }
        // 返回结果
        return itemGroups;
    }

    private boolean isSpuMatchFullPrivilege(Integer spuId, PromotionActivityRespDTO activity) {
        Assert.isTrue(PromotionActivityTypeEnum.FULL_PRIVILEGE.getValue().equals(activity.getActivityType()),
                "传入的必须的促销活动必须是满减送");
        PromotionActivityRespDTO.FullPrivilege fullPrivilege = activity.getFullPrivilege();
        if (RangeTypeEnum.ALL.getValue().equals(fullPrivilege.getRangeType())) {
            return true;
        } else if (RangeTypeEnum.PRODUCT_INCLUDE_PART.getValue().equals(fullPrivilege.getRangeType())) {
            return fullPrivilege.getRangeValues().contains(spuId);
        }
        throw new IllegalArgumentException(String.format("促销活动(%s) 可用范围的类型是不正确", activity.toString()));
    }

    private Integer calcSkuPriceByFullPrivilege(PriceProductCalcRespDTO.ItemGroup itemGroup, PromotionActivityRespDTO activity) {
        if (itemGroup.getActivityId() == null) {
            return null;
        }
        Assert.isTrue(PromotionActivityTypeEnum.FULL_PRIVILEGE.getValue().equals(activity.getActivityType()),
                "传入的必须的满减送活动必须是满减送");
        // 获得优惠信息
        List<PriceProductCalcRespDTO.Item> items = itemGroup.getItems().stream().filter(PriceProductCalcRespDTO.Item::getSelected)
                .collect(Collectors.toList());
        Integer itemCnt = items.stream().mapToInt(PriceProductCalcRespDTO.Item::getBuyQuantity).sum();
        Integer originalTotal = items.stream().mapToInt(PriceProductCalcRespDTO.Item::getPresentTotal).sum();
        List<PromotionActivityRespDTO.FullPrivilege.Privilege> privileges = activity.getFullPrivilege().getPrivileges().stream()
                .filter(privilege -> {
                    if (MeetTypeEnum.PRICE.getValue().equals(privilege.getMeetType())) {
                        return originalTotal >= privilege.getMeetValue();
                    }
                    if (MeetTypeEnum.QUANTITY.getValue().equals(privilege.getMeetType())) {
                        return itemCnt >= privilege.getMeetValue();
                    }
                    throw new IllegalArgumentException(String.format("满减送活动(%s) 的匹配(%s)不正确",
                            activity.toString(), privilege.toString()));
                }).collect(Collectors.toList());
        // 获得不到优惠信息，返回原始价格
        if (privileges.isEmpty()) {
            return null;
        }
        // 获得到优惠信息，进行价格计算
        PromotionActivityRespDTO.FullPrivilege.Privilege privilege = privileges.get(privileges.size() - 1);
        Integer presentTotal;
        if (PreferentialTypeEnum.PRICE.getValue().equals(privilege.getPreferentialType())) { // 减价
            // 计算循环次数。这样，后续优惠的金额就是相乘了
            Integer cycleCount = 1;
            if (activity.getFullPrivilege().getCycled()) {
                if (MeetTypeEnum.PRICE.getValue().equals(privilege.getMeetType())) {
                    cycleCount = originalTotal / privilege.getMeetValue();
                } else if (MeetTypeEnum.QUANTITY.getValue().equals(privilege.getMeetType())) {
                    cycleCount = itemCnt / privilege.getMeetValue();
                }
            }
            presentTotal = originalTotal - cycleCount * privilege.getMeetValue();
            if (presentTotal < 0) { // 如果计算优惠价格小于 0 ，则说明无法使用优惠。
                presentTotal = originalTotal;
            }
        } else if (PreferentialTypeEnum.DISCOUNT.getValue().equals(privilege.getPreferentialType())) { // 打折
            presentTotal = originalTotal * privilege.getPreferentialValue() / 100;
        } else {
            throw new IllegalArgumentException(String.format("满减送促销(%s) 的优惠类型不正确", activity.toString()));
        }
        int discountTotal = originalTotal - presentTotal;
        if (discountTotal == 0) {
            return null;
        }
        // 按比例，拆分 presentTotal
        splitDiscountPriceToItems(items, discountTotal, presentTotal);
        // 返回优惠金额
        return originalTotal - presentTotal;
    }

    private Integer modifyPriceByCouponCard(Integer userId, Integer couponCardId, List<PriceProductCalcRespDTO.ItemGroup> itemGroups) {
        Assert.isTrue(couponCardId != null, "优惠劵编号不能为空");
        // 查询优惠劵
        CouponCardRespDTO couponCard = couponCardService.getCouponCard(userId, couponCardId);
        if (couponCard == null) {
            throw ServiceExceptionUtil.exception(COUPON_CARD_NOT_EXISTS);
        }
        CouponTemplateRespDTO couponTemplate = couponTemplateService.getCouponTemplate(couponCardId);
        if (couponTemplate == null) {
            throw ServiceExceptionUtil.exception(COUPON_TEMPLATE_NOT_EXISTS);
        }
        // 获得匹配的商品
        List<PriceProductCalcRespDTO.Item> items = new ArrayList<>();
        if (RangeTypeEnum.ALL.getValue().equals(couponTemplate.getRangeType())) {
            itemGroups.forEach(itemGroup -> items.addAll(itemGroup.getItems()));
        } else if (RangeTypeEnum.PRODUCT_INCLUDE_PART.getValue().equals(couponTemplate.getRangeType())) {
            itemGroups.forEach(itemGroup -> items.forEach(item -> {
                if (couponTemplate.getRangeValues().contains(item.getSpuId())) {
                    items.add(item);
                }
            }));
        } else if (RangeTypeEnum.PRODUCT_EXCLUDE_PART.getValue().equals(couponTemplate.getRangeType())) {
            itemGroups.forEach(itemGroup -> items.forEach(item -> {
                if (!couponTemplate.getRangeValues().contains(item.getSpuId())) {
                    items.add(item);
                }
            }));
        } else if (RangeTypeEnum.CATEGORY_INCLUDE_PART.getValue().equals(couponTemplate.getRangeType())) {
            itemGroups.forEach(itemGroup -> items.forEach(item -> {
                if (couponTemplate.getRangeValues().contains(item.getCid())) {
                    items.add(item);
                }
            }));
        } else if (RangeTypeEnum.CATEGORY_EXCLUDE_PART.getValue().equals(couponTemplate.getRangeType())) {
            itemGroups.forEach(itemGroup -> items.forEach(item -> {
                if (!couponTemplate.getRangeValues().contains(item.getCid())) {
                    items.add(item);
                }
            }));
        }
        // 判断是否符合条件
        int originalTotal = items.stream().mapToInt(PriceProductCalcRespDTO.Item::getPresentTotal).sum(); // 此处，指的是以优惠劵视角的原价
        if (originalTotal == 0 || originalTotal < couponCard.getPriceAvailable()) {
            throw ServiceExceptionUtil.exception(COUPON_CARD_NOT_MATCH); // TODO 芋艿，这种情况，会出现错误码的提示，无法格式化出来。另外，这块的最佳实践，找人讨论下。
        }
        // 计算价格
        // 获得到优惠信息，进行价格计算
        int presentTotal;
        if (PreferentialTypeEnum.PRICE.getValue().equals(couponCard.getPreferentialType())) { // 减价
            // 计算循环次数。这样，后续优惠的金额就是相乘了
            presentTotal = originalTotal - couponCard.getPriceOff();
            Assert.isTrue(presentTotal > 0, "计算后，价格为负数：" + presentTotal);
        } else if (PreferentialTypeEnum.DISCOUNT.getValue().equals(couponCard.getPreferentialType())) { // 打折
            presentTotal = originalTotal * couponCard.getPercentOff() / 100;
            if (couponCard.getDiscountPriceLimit() != null // 空，代表不限制优惠上限
                    && originalTotal - presentTotal > couponCard.getDiscountPriceLimit()) {
                presentTotal = originalTotal - couponCard.getDiscountPriceLimit();
            }
        } else {
            throw new IllegalArgumentException(String.format("优惠劵(%s) 的优惠类型不正确", couponCard.toString()));
        }
        int discountTotal = originalTotal - presentTotal;
        Assert.isTrue(discountTotal > 0, "计算后，不产生优惠：" + discountTotal);
        // 按比例，拆分 presentTotal
        splitDiscountPriceToItems(items, discountTotal, presentTotal);
        // 返回优惠金额
        return originalTotal - presentTotal;
    }

    private void splitDiscountPriceToItems(List<PriceProductCalcRespDTO.Item> items, Integer discountTotal, Integer presentTotal) {
        for (int i = 0; i < items.size(); i++) {
            PriceProductCalcRespDTO.Item item = items.get(i);
            Integer discountPart;
            if (i < items.size() - 1) { // 减一的原因，是因为拆分时，如果按照比例，可能会出现.所以最后一个，使用反减
                discountPart = (int) (discountTotal * (1.0D * item.getPresentTotal() / presentTotal));
                discountTotal -= discountPart;
            } else {
                discountPart = discountTotal;
            }
            Assert.isTrue(discountPart > 0, "优惠金额必须大于 0");
            item.setDiscountTotal(item.getDiscountTotal() + discountPart);
            item.setPresentTotal(item.getBuyTotal() - item.getDiscountTotal());
            item.setPresentPrice(item.getPresentTotal() / item.getBuyQuantity());
        }
    }

}
