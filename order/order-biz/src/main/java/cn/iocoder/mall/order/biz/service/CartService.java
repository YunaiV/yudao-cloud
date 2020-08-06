package cn.iocoder.mall.order.biz.service;

public interface CartService {


//    // ========== 购物车与订单相关的逻辑 ==========
//
//    /**
//     * 计算订单金额，返回计算结果
//     *
//     * @param calcOrderPriceDTO 计算订单金额 DTO
//     * @return 计算订单金额结果
//     */
//    CalcOrderPriceBO calcOrderPrice(CalcOrderPriceDTO calcOrderPriceDTO);
//
//    /**
//     * 计算指定商品 SKU 的金额，并返回计算结果
//     *
//     * TODO 芋艿，此处只会计算，限时折扣带来的价格变化。
//     *
//     * @param skuId 商品 SKU 编号
//     * @return 计算订单金额结果
//     */
//    CalcSkuPriceBO calcSkuPrice(Integer skuId); // ========== 购物车 Item 的逻辑 ==========
//
//    /**
//     * 添加商品至购物车
//     *
//     * @param userId 用户编号
//     * @param skuId 商品 SKU 编号
//     * @param quantity 数量
//     * @return 是否成功
//     */
//    Boolean add(Integer userId, Integer skuId, Integer quantity);
//
//    /**
//     * 购物车更新商品数量
//     *
//     * @param userId 用户编号
//     * @param skuId 商品 SKU 编号
//     * @param quantity 数量
//     * @return 是否成功
//     */
//    Boolean updateQuantity(Integer userId, Integer skuId, Integer quantity);
//
//    /**
//     * 购物车更新商品是否选中
//     *
//     * @param userId 用户编号
//     * @param skuIds 商品 SKU 编号数组
//     * @param selected 是否选中
//     * @return 是否成功
//     */
//    Boolean updateSelected(Integer userId, Collection<Integer> skuIds, Boolean selected);
//
//    /**
//     * 购物车删除商品
//     *
//     * @param userId 用户编号
//     * @param skuIds 商品 SKU 编号的数组
//     *
//     * @return 是否成功
//     */
//    Boolean deleteList(Integer userId, List<Integer> skuIds);
//
//    /**
//     * 清空购物车
//     *
//     * @param userId 用户编号
//     * @return 是否成功
//     */
//    Boolean deleteAll(Integer userId);
//
//    /**
//     * 查询用户在购物车中的商品数量
//     *
//     * @param userId 用户编号
//     * @return 商品数量
//     */
//    Integer count(Integer userId);
//
//    /**
//     * 显示买家购物车中的商品列表，并根据 selected 进行过滤。
//     *
//     * @param userId 用户编号
//     * @param selected 是否选中。若为空，则不进行筛选
//     * @return 购物车中商品列表信息
//     */
//    List<CartItemBO> list(Integer userId, @Nullable Boolean selected);
//
//    // ========== 购物车与订单相关的逻辑 ==========
//
//    /**
//     * 计算订单金额，返回计算结果
//     *
//     * @param calcOrderPriceDTO 计算订单金额 DTO
//     * @return 计算订单金额结果
//     */
//    CalcOrderPriceBO calcOrderPrice(CalcOrderPriceDTO calcOrderPriceDTO);
//
//    /**
//     * 计算指定商品 SKU 的金额，并返回计算结果
//     *
//     * TODO 芋艿，此处只会计算，限时折扣带来的价格变化。
//     *
//     * @param skuId 商品 SKU 编号
//     * @return 计算订单金额结果
//     */
//    CalcSkuPriceBO calcSkuPrice(Integer skuId);

}
