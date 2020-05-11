package cn.iocoder.mall.order.biz.service.impl;

import cn.iocoder.mall.order.biz.service.CartService;
import org.springframework.stereotype.Service;

/**
 * 购物车服务 Service 实现类
 */
@Service
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.CartService.version}")
public class CartServiceImpl implements CartService {

//    @Reference(validation = "true", version = "${dubbo.consumer.PromotionActivityService.version}")
//    private ProductSpuService productSpuService;
//    @Reference(validation = "true", version = "${dubbo.consumer.PromotionActivityService.version}")
//    private PromotionActivityService promotionActivityService;
//    @Reference(validation = "true", version = "${dubbo.consumer.CouponService.version}")
//    private CouponService couponService;
//
//    @Autowired
//    private CartMapper cartMapper;
//
//    @Override
//    @SuppressWarnings("Duplicates")
//    public Boolean add(Integer userId, Integer skuId, Integer quantity) {
//        // 查询 SKU 是否合法
//        ProductSkuBO sku = productSpuService.getProductSku(skuId);
//        if (sku == null
//                || CommonStatusEnum.DISABLE.getValue().equals(sku.getStatus())) { // sku 被禁用
//            throw ServiceExceptionUtil.exception(OrderErrorCodeEnum.CARD_ITEM_SKU_NOT_FOUND.getCode());
//        }
//        // TODO 芋艿，后续基于商品是否上下架进一步完善。
//        // 查询 CartItemDO
//        CartItemDO item = cartMapper.selectByUserIdAndSkuIdAndStatus(userId, skuId, CartItemStatusEnum.ENABLE.getValue());
//        // 存在，则进行数量更新
//        if (item != null) {
//            return updateQuantity0(item, sku, quantity);
//        }
//        // 不存在，则进行插入
//        return add0(userId, sku, quantity);
//    }
//
//    private Boolean add0(Integer userId, ProductSkuBO sku, Integer quantity) {
//        // 校验库存
//        if (quantity > sku.getQuantity()) {
//            throw ServiceExceptionUtil.exception(OrderErrorCodeEnum.CARD_ITEM_SKU_NOT_FOUND.getCode());
//        }
//        // 创建 CartItemDO 对象，并进行保存。
//        CartItemDO item = new CartItemDO()
//                // 基础字段
//                .setStatus(CartItemStatusEnum.ENABLE.getValue()).setSelected(true)
//                // 买家信息
//                .setUserId(userId)
//                // 商品信息
//                .setSpuId(sku.getSpuId()).setSkuId(sku.getId()).setQuantity(quantity);
//        item.setCreateTime(new Date());
//        cartMapper.insert(item);
//        // 返回成功
//        return true;
//    }
//
//    @Override
//    @SuppressWarnings("Duplicates")
//    public Boolean updateQuantity(Integer userId, Integer skuId, Integer quantity) {
//        // 查询 SKU 是否合法
//        ProductSkuBO sku = productSpuService.getProductSku(skuId);
//        if (sku == null
//                || CommonStatusEnum.DISABLE.getValue().equals(sku.getStatus())) { // sku 被禁用
//            throw ServiceExceptionUtil.exception(OrderErrorCodeEnum.CARD_ITEM_SKU_NOT_FOUND.getCode());
//        }
//        // 查询 CartItemDO
//        CartItemDO item = cartMapper.selectByUserIdAndSkuIdAndStatus(userId, skuId, CartItemStatusEnum.ENABLE.getValue());
//        if (item == null) {
//            throw ServiceExceptionUtil.exception(OrderErrorCodeEnum.CARD_ITEM_NOT_FOUND.getCode());
//        }
//        // TODO 芋艿，后续基于商品是否上下架进一步完善。
//        return updateQuantity0(item, sku, quantity);
//    }
//
//    private Boolean updateQuantity0(CartItemDO item, ProductSkuBO sku, Integer quantity) {
//        // 校验库存
//        if (item.getQuantity() + quantity > sku.getQuantity()) {
//            throw ServiceExceptionUtil.exception(OrderErrorCodeEnum.CARD_ITEM_SKU_NOT_FOUND.getCode());
//        }
//        // 更新 CartItemDO
//        cartMapper.updateQuantity(item.getId(), quantity);
//        // 返回成功
//        return true;
//    }
//
//    @Override
//    public Boolean updateSelected(Integer userId, Collection<Integer> skuIds, Boolean selected) {
//        // 更新 CartItemDO 们
//        cartMapper.updateListByUserIdAndSkuId(userId, skuIds, selected, null);
//        // 返回成功
//        return true;
//    }
//
//    @Override
//    public Boolean deleteList(Integer userId, List<Integer> skuIds) {
//        // 更新 CartItemDO 们
//        cartMapper.updateListByUserIdAndSkuId(userId, skuIds, null, CartItemStatusEnum.DELETE_BY_MANUAL.getValue());
//        // 返回成功
//        return true;
//    }
//
//    @Override
//    public Boolean deleteAll(Integer userId) {
//        return null;
//    }
//
//    @Override
//    public Integer count(Integer userId) {
//        return cartMapper.selectQuantitySumByUserIdAndStatus(userId, CartItemStatusEnum.ENABLE.getValue());
//    }
//
//    @Override
//    public List<CartItemBO> list(Integer userId, Boolean selected) {
//        List<CartItemDO> items = cartMapper.selectByUserIdAndStatusAndSelected(userId, CartItemStatusEnum.ENABLE.getValue(), selected);
//        return CartConvert.INSTANCE.convert(items);
//    }
//
//    @Override
//    public CalcOrderPriceBO calcOrderPrice(CalcOrderPriceDTO calcOrderPriceDTO) {
//        // TODO 芋艿，补充一些表单校验。例如说，需要传入用户编号。
//        // 校验商品都存在
//        Map<Integer, CalcOrderPriceDTO.Item> calcOrderItemMap = calcOrderPriceDTO.getItems().stream()
//                .collect(Collectors.toMap(CalcOrderPriceDTO.Item::getSkuId, item -> item)); // KEY：skuId
//        List<ProductSkuDetailBO> skus = productSpuService.getProductSkuDetailList(calcOrderItemMap.keySet());
//        if (skus.size() != calcOrderPriceDTO.getItems().size()) {
//            throw ServiceExceptionUtil.exception(OrderErrorCodeEnum.ORDER_ITEM_SOME_NOT_EXISTS.getCode());
//        }
//        // TODO 库存相关
//        // 查询促销活动
//        List<PromotionActivityBO> activityList = promotionActivityService.getPromotionActivityListBySpuIds(
//                skus.stream().map(sku -> sku.getSpu().getId()).collect(Collectors.toSet()),
//                Collections.singletonList(PromotionActivityStatusEnum.RUN.getValue()));
//        // 拼装结果（主要是计算价格）
//        CalcOrderPriceBO calcOrderPriceBO = new CalcOrderPriceBO();
//        // 1. 创建初始的每一项的数组
//        List<CalcOrderPriceBO.Item> items = initCalcOrderPriceItems(skus, calcOrderItemMap);
//        // 2. 计算【限时折扣】促销
//        modifyPriceByTimeLimitDiscount(items, activityList);
//        // 3. 计算【满减送】促销
//        List<CalcOrderPriceBO.ItemGroup> itemGroups = groupByFullPrivilege(items, activityList);
//        calcOrderPriceBO.setItemGroups(itemGroups);
//        // 4. 计算优惠劵
//        if (calcOrderPriceDTO.getCouponCardId() != null) {
//            Integer result = modifyPriceByCouponCard(calcOrderPriceDTO.getUserId(), calcOrderPriceDTO.getCouponCardId(), itemGroups);
//            calcOrderPriceBO.setCouponCardDiscountTotal(result);
//        }
//        // 5. 计算最终的价格
//        int buyTotal = 0;
//        int discountTotal = 0;
//        int presentTotal = 0;
//        for (CalcOrderPriceBO.ItemGroup itemGroup : calcOrderPriceBO.getItemGroups()) {
//            buyTotal += itemGroup.getItems().stream().mapToInt(item -> item.getSelected() ? item.getBuyTotal() : 0).sum();
//            discountTotal += itemGroup.getItems().stream().mapToInt(item -> item.getSelected() ? item.getDiscountTotal() : 0).sum();
//            presentTotal += itemGroup.getItems().stream().mapToInt(item -> item.getSelected() ? item.getPresentTotal() : 0).sum();
//        }
//        Assert.isTrue(buyTotal - discountTotal ==  presentTotal,
//                String.format("价格合计( %d - %d == %d )不正确", buyTotal, discountTotal, presentTotal));
//        calcOrderPriceBO.setFee(new CalcOrderPriceBO.Fee(buyTotal, discountTotal, 0, presentTotal));
//        // 返回
//        return calcOrderPriceBO;
//    }
//
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
//    private List<CalcOrderPriceBO.Item> initCalcOrderPriceItems(List<ProductSkuDetailBO> skus,
//                                                                Map<Integer, CalcOrderPriceDTO.Item> calcOrderItemMap) {
//        List<CalcOrderPriceBO.Item> items = new ArrayList<>();
//        for (ProductSkuDetailBO sku : skus) {
//            CalcOrderPriceBO.Item item = CartConvert.INSTANCE.convert(sku);
//            items.add(item);
//            // 将是否选中，购物数量，复制到 item 中
//            CalcOrderPriceDTO.Item calcOrderItem = calcOrderItemMap.get(sku.getId());
//            item.setSelected(calcOrderItem.getSelected());
//            item.setBuyQuantity(calcOrderItem.getQuantity());
//            // 计算初始价格
//            item.setOriginPrice(sku.getPrice());
//            item.setBuyPrice(sku.getPrice());
//            item.setPresentPrice(sku.getPrice());
//            item.setBuyTotal(sku.getPrice() * calcOrderItem.getQuantity());
//            item.setDiscountTotal(0);
//            item.setPresentTotal(item.getBuyTotal());
//        }
//        return items;
//    }
//
//    private void modifyPriceByTimeLimitDiscount(List<CalcOrderPriceBO.Item> items, List<PromotionActivityBO> activityList) {
//        for (CalcOrderPriceBO.Item item : items) {
//            // 获得符合条件的限时折扣
//            PromotionActivityBO timeLimitedDiscount = activityList.stream()
//                    .filter(activity -> PromotionActivityTypeEnum.TIME_LIMITED_DISCOUNT.getValue().equals(activity.getActivityType())
//                            && activity.getTimeLimitedDiscount().getItems().stream().anyMatch(item0 -> item0.getSpuId().equals(item.getSpu().getId())))
//                    .findFirst().orElse(null);
//            if (timeLimitedDiscount == null) {
//                continue;
//            }
//            // 计算价格
//            ProductSkuBO sku = new ProductSkuBO().setId(item.getId()).setSpuId(item.getSpu().getId()).setPrice(item.getPrice());
//            Integer newPrice = calcSkuPriceByTimeLimitDiscount(sku, timeLimitedDiscount);
//            if (newPrice.equals(item.getPrice())) {
//                continue;
//            }
//            // 设置优惠
//            item.setActivity(timeLimitedDiscount);
//            // 设置价格
//            item.setBuyPrice(newPrice);
//            item.setBuyTotal(newPrice * item.getBuyQuantity());
//            item.setPresentTotal(item.getBuyTotal() - item.getDiscountTotal());
//            item.setPresentPrice(item.getPresentTotal() / item.getBuyQuantity());
//        }
//    }
//
//    private List<CalcOrderPriceBO.ItemGroup> groupByFullPrivilege(List<CalcOrderPriceBO.Item> items, List<PromotionActivityBO> activityList) {
//        List<CalcOrderPriceBO.ItemGroup> itemGroups = new ArrayList<>();
//        // 获得所有满减送促销
//        List<PromotionActivityBO> fullPrivileges = activityList.stream()
//                .filter(activity -> PromotionActivityTypeEnum.FULL_PRIVILEGE.getValue().equals(activity.getActivityType()))
//                .collect(Collectors.toList());
//        // 基于满减送促销，进行分组
//        if (!fullPrivileges.isEmpty()) {
//            items = new ArrayList<>(items); // 因为下面会修改数组，进行浅拷贝，避免影响传入的 items 。
//            for (PromotionActivityBO fullPrivilege : fullPrivileges) {
//                // 创建 fullPrivilege 对应的分组
//                CalcOrderPriceBO.ItemGroup itemGroup = new CalcOrderPriceBO.ItemGroup()
//                        .setActivity(fullPrivilege)
//                        .setItems(new ArrayList<>());
//                // 筛选商品到分组中
//                for (Iterator<CalcOrderPriceBO.Item> iterator = items.iterator(); iterator.hasNext(); ) {
//                    CalcOrderPriceBO.Item item = iterator.next();
//                    if (!isSpuMatchFullPrivilege(item.getSpu().getId(), fullPrivilege)) {
//                        continue;
//                    }
//                    itemGroup.getItems().add(item);
//                    iterator.remove();
//                }
//                // 如果匹配到，则添加到 itemGroups 中
//                if (!itemGroup.getItems().isEmpty()) {
//                    itemGroups.add(itemGroup);
//                }
//            }
//        }
//        // 处理未参加活动的商品，形成一个分组
//        if (!items.isEmpty()) {
//            itemGroups.add(new CalcOrderPriceBO.ItemGroup().setItems(items));
//        }
//        // 计算每个分组的价格
//        for (CalcOrderPriceBO.ItemGroup itemGroup : itemGroups) {
//            itemGroup.setActivityDiscountTotal(calcSkuPriceByFullPrivilege(itemGroup));
//        }
//        // 返回结果
//        return itemGroups;
//    }
//
//    private Integer modifyPriceByCouponCard(Integer userId, Integer couponCardId, List<CalcOrderPriceBO.ItemGroup> itemGroups) {
//        Assert.isTrue(couponCardId != null, "优惠劵编号不能为空");
//        // 查询优惠劵
//        CouponCardDetailBO couponCard = couponService.getCouponCardDetail(userId, couponCardId);
//        // 获得匹配的商品
//        List<CalcOrderPriceBO.Item> items = new ArrayList<>();
//        if (RangeTypeEnum.ALL.getValue().equals(couponCard.getRangeType())) {
////            totalPrice = spus.stream().mapToInt(spu -> spu.getPrice() * spu.getQuantity()).sum();
//            itemGroups.forEach(itemGroup -> items.addAll(itemGroup.getItems()));
//        } else if (RangeTypeEnum.PRODUCT_INCLUDE_PART.getValue().equals(couponCard.getRangeType())) {
//            itemGroups.forEach(itemGroup -> items.forEach(item -> {
//                if (couponCard.getRangeValues().contains(item.getSpu().getId())) {
//                    items.add(item);
//                }
//            }));
//        } else if (RangeTypeEnum.PRODUCT_EXCLUDE_PART.getValue().equals(couponCard.getRangeType())) {
//            itemGroups.forEach(itemGroup -> items.forEach(item -> {
//                if (!couponCard.getRangeValues().contains(item.getSpu().getId())) {
//                    items.add(item);
//                }
//            }));
//        } else if (RangeTypeEnum.CATEGORY_INCLUDE_PART.getValue().equals(couponCard.getRangeType())) {
//            itemGroups.forEach(itemGroup -> items.forEach(item -> {
//                if (couponCard.getRangeValues().contains(item.getSpu().getCid())) {
//                    items.add(item);
//                }
//            }));
//        } else if (RangeTypeEnum.CATEGORY_EXCLUDE_PART.getValue().equals(couponCard.getRangeType())) {
//            itemGroups.forEach(itemGroup -> items.forEach(item -> {
//                if (!couponCard.getRangeValues().contains(item.getSpu().getCid())) {
//                    items.add(item);
//                }
//            }));
//        }
//        // 判断是否符合条件
//        int originalTotal = items.stream().mapToInt(CalcOrderPriceBO.Item::getPresentTotal).sum(); // 此处，指的是以优惠劵视角的原价
//        if (originalTotal == 0 || originalTotal < couponCard.getPriceAvailable()) {
//            throw ServiceExceptionUtil.exception(PromotionErrorCodeEnum.COUPON_CARD_NOT_MATCH.getCode()); // TODO 芋艿，这种情况，会出现错误码的提示，无法格式化出来。另外，这块的最佳实践，找人讨论下。
//        }
//        // 计算价格
//        // 获得到优惠信息，进行价格计算
//        int presentTotal;
//        if (PreferentialTypeEnum.PRICE.getValue().equals(couponCard.getPreferentialType())) { // 减价
//            // 计算循环次数。这样，后续优惠的金额就是相乘了
//            presentTotal = originalTotal - couponCard.getPriceOff();
//            Assert.isTrue(presentTotal > 0, "计算后，价格为负数：" + presentTotal);
//        } else if (PreferentialTypeEnum.DISCOUNT.getValue().equals(couponCard.getPreferentialType())) { // 打折
//            presentTotal = originalTotal * couponCard.getPercentOff() / 100;
//            if (couponCard.getDiscountPriceLimit() != null // 空，代表不限制优惠上限
//                    && originalTotal - presentTotal > couponCard.getDiscountPriceLimit()) {
//                presentTotal = originalTotal - couponCard.getDiscountPriceLimit();
//            }
//        } else {
//            throw new IllegalArgumentException(String.format("优惠劵(%s) 的优惠类型不正确", couponCard.toString()));
//        }
//        int discountTotal = originalTotal - presentTotal;
//        Assert.isTrue(discountTotal > 0, "计算后，不产生优惠：" + discountTotal);
//        // 按比例，拆分 presentTotal
//        splitDiscountPriceToItems(items, discountTotal, presentTotal);
//        // 返回优惠金额
//        return originalTotal - presentTotal;
//    }
//
//    /**
//     * 计算指定 SKU 在限时折扣下的价格
//     *
//     * @param sku                 SKU
//     * @param timeLimitedDiscount 限时折扣促销。
//     *                            传入的该活动，要保证该 SKU 在该促销下一定有优惠。
//     * @return 计算后的价格
//     */
//    private Integer calcSkuPriceByTimeLimitDiscount(ProductSkuBO sku, PromotionActivityBO timeLimitedDiscount) {
//        if (timeLimitedDiscount == null) {
//            return sku.getPrice();
//        }
//        // 获得对应的优惠项
//        PromotionActivityBO.TimeLimitedDiscount.Item item = timeLimitedDiscount.getTimeLimitedDiscount().getItems().stream()
//                .filter(item0 -> item0.getSpuId().equals(sku.getSpuId()))
//                .findFirst().orElse(null);
//        if (item == null) {
//            throw new IllegalArgumentException(String.format("折扣活动(%s) 不存在商品(%s) 的优惠配置",
//                    timeLimitedDiscount.toString(), sku.toString()));
//        }
//        // 计算价格
//        if (PreferentialTypeEnum.PRICE.getValue().equals(item.getPreferentialType())) { // 减价
//            int presentPrice = sku.getPrice() - item.getPreferentialValue();
//            return presentPrice >= 0 ? presentPrice : sku.getPrice(); // 如果计算优惠价格小于 0 ，则说明无法使用优惠。
//        }
//        if (PreferentialTypeEnum.DISCOUNT.getValue().equals(item.getPreferentialType())) { // 打折
//            return sku.getPrice() * item.getPreferentialValue() / 100;
//        }
//        throw new IllegalArgumentException(String.format("折扣活动(%s) 的优惠类型不正确", timeLimitedDiscount.toString()));
//    }
//
//    private Integer calcSkuPriceByFullPrivilege(CalcOrderPriceBO.ItemGroup itemGroup) {
//        if (itemGroup.getActivity() == null) {
//            return null;
//        }
//        PromotionActivityBO activity = itemGroup.getActivity();
//        Assert.isTrue(PromotionActivityTypeEnum.FULL_PRIVILEGE.getValue().equals(activity.getActivityType()),
//                "传入的必须的满减送活动必须是满减送");
//        // 获得优惠信息
//        List<CalcOrderPriceBO.Item> items = itemGroup.getItems().stream().filter(CalcOrderPriceBO.Item::getSelected)
//                .collect(Collectors.toList());
//        Integer itemCnt = items.stream().mapToInt(CalcOrderPriceBO.Item::getBuyQuantity).sum();
//        Integer originalTotal = items.stream().mapToInt(CalcOrderPriceBO.Item::getPresentTotal).sum();
//        List<PromotionActivityBO.FullPrivilege.Privilege> privileges = activity.getFullPrivilege().getPrivileges().stream()
//                .filter(privilege -> {
//                    if (MeetTypeEnum.PRICE.getValue().equals(privilege.getMeetType())) {
//                        return originalTotal >= privilege.getMeetValue();
//                    }
//                    if (MeetTypeEnum.QUANTITY.getValue().equals(privilege.getMeetType())) {
//                        return itemCnt >= privilege.getMeetValue();
//                    }
//                    throw new IllegalArgumentException(String.format("满减送活动(%s) 的匹配(%s)不正确", itemGroup.getActivity().toString(), privilege.toString()));
//                }).collect(Collectors.toList());
//        // 获得不到优惠信息，返回原始价格
//        if (privileges.isEmpty()) {
//            return null;
//        }
//        // 获得到优惠信息，进行价格计算
//        PromotionActivityBO.FullPrivilege.Privilege privilege = privileges.get(privileges.size() - 1);
//        Integer presentTotal;
//        if (PreferentialTypeEnum.PRICE.getValue().equals(privilege.getPreferentialType())) { // 减价
//            // 计算循环次数。这样，后续优惠的金额就是相乘了
//            Integer cycleCount = 1;
//            if (activity.getFullPrivilege().getCycled()) {
//                if (MeetTypeEnum.PRICE.getValue().equals(privilege.getMeetType())) {
//                    cycleCount = originalTotal / privilege.getMeetValue();
//                } else if (MeetTypeEnum.QUANTITY.getValue().equals(privilege.getMeetType())) {
//                    cycleCount = itemCnt / privilege.getMeetValue();
//                }
//            }
//            presentTotal = originalTotal - cycleCount * privilege.getMeetValue();
//            if (presentTotal < 0) { // 如果计算优惠价格小于 0 ，则说明无法使用优惠。
//                presentTotal = originalTotal;
//            }
//        } else if (PreferentialTypeEnum.DISCOUNT.getValue().equals(privilege.getPreferentialType())) { // 打折
//            presentTotal = originalTotal * privilege.getPreferentialValue() / 100;
//        } else {
//            throw new IllegalArgumentException(String.format("满减送促销(%s) 的优惠类型不正确", activity.toString()));
//        }
//        int discountTotal = originalTotal - presentTotal;
//        if (discountTotal == 0) {
//            return null;
//        }
//        // 按比例，拆分 presentTotal
////        for (int i = 0; i < items.size(); i++) {
////            CalcOrderPriceBO.Item item = items.get(i);
////            Integer discountPart;
////            if (i < items.size() - 1) { // 减一的原因，是因为拆分时，如果按照比例，可能会出现.所以最后一个，使用反减
////                discountPart = (int) (discountTotal * (1.0D * item.getPresentTotal() / presentTotal));
////                discountTotal -= discountPart;
////            } else {
////                discountPart = discountTotal;
////            }
////            Assert.isTrue(discountPart > 0, "优惠金额必须大于 0");
////            item.setDiscountTotal(item.getDiscountTotal() + discountPart);
////            item.setPresentTotal(item.getBuyTotal() - item.getDiscountTotal());
////            item.setPresentPrice(item.getPresentTotal() / item.getBuyQuantity());
////        }
//        splitDiscountPriceToItems(items, discountTotal, presentTotal);
//        // 返回优惠金额
//        return originalTotal - presentTotal;
//    }
//
//    private void splitDiscountPriceToItems(List<CalcOrderPriceBO.Item> items, Integer discountTotal, Integer presentTotal) {
//        for (int i = 0; i < items.size(); i++) {
//            CalcOrderPriceBO.Item item = items.get(i);
//            Integer discountPart;
//            if (i < items.size() - 1) { // 减一的原因，是因为拆分时，如果按照比例，可能会出现.所以最后一个，使用反减
//                discountPart = (int) (discountTotal * (1.0D * item.getPresentTotal() / presentTotal));
//                discountTotal -= discountPart;
//            } else {
//                discountPart = discountTotal;
//            }
//            Assert.isTrue(discountPart > 0, "优惠金额必须大于 0");
//            item.setDiscountTotal(item.getDiscountTotal() + discountPart);
//            item.setPresentTotal(item.getBuyTotal() - item.getDiscountTotal());
//            item.setPresentPrice(item.getPresentTotal() / item.getBuyQuantity());
//        }
//    }
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
//
//    private boolean isSpuMatchFullPrivilege(Integer spuId, PromotionActivityBO activity) {
//        Assert.isTrue(PromotionActivityTypeEnum.FULL_PRIVILEGE.getValue().equals(activity.getActivityType()),
//                "传入的必须的促销活动必须是满减送");
//        PromotionActivityBO.FullPrivilege fullPrivilege = activity.getFullPrivilege();
//        if (RangeTypeEnum.ALL.getValue().equals(fullPrivilege.getRangeType())) {
//            return true;
//        } else if (RangeTypeEnum.PRODUCT_INCLUDE_PART.getValue().equals(fullPrivilege.getRangeType())) {
//            return fullPrivilege.getRangeValues().contains(spuId);
//        } else {
//            throw new IllegalArgumentException(String.format("促销活动(%s) 可用范围的类型是不正确", activity.toString()));
//        }
//    }

}
