package cn.iocoder.mall.tradeservice.service.cart;

import cn.iocoder.common.framework.exception.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.util.CollectionUtils;
import cn.iocoder.mall.tradeservice.convert.cart.CartConvert;
import cn.iocoder.mall.tradeservice.dal.mysql.dataobject.cart.CartItemDO;
import cn.iocoder.mall.tradeservice.dal.mysql.mapper.cart.CartItemMapper;
import cn.iocoder.mall.tradeservice.service.cart.bo.CartItemAddBO;
import cn.iocoder.mall.tradeservice.service.cart.bo.CartItemBO;
import cn.iocoder.mall.tradeservice.service.cart.bo.CartItemListQueryBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.mall.tradeservice.enums.OrderErrorCodeConstants.CARD_ITEM_NOT_FOUND;
import static cn.iocoder.mall.tradeservice.enums.OrderErrorCodeConstants.CARD_ITEM_SKU_QUANTITY_NOT_ENOUGH;

/**
 * 购物车 Service
 */
@Service
@Validated
public class CartService {

    @Autowired
    private CartItemMapper cartItemMapper;

    /**
     * 添加商品到购物车
     *
     * @param addBO 添加商品信息
     * @param skuQuantity 商品 SKU 的库存，主要用于库存校验
     */
    public void addCartItem(@Valid CartItemAddBO addBO, Integer skuQuantity) {
        // 查询 CartItemDO
        CartItemDO itemDO = cartItemMapper.selectByUserIdAndSkuId(addBO.getUserId(), addBO.getSkuId());
        // 存在，则进行数量更新
        if (itemDO != null) {
            if (addBO.getQuantity() + itemDO.getQuantity() > skuQuantity) {
                // 校验库存
                throw ServiceExceptionUtil.exception(CARD_ITEM_SKU_QUANTITY_NOT_ENOUGH);
            }
            cartItemMapper.updateById(new CartItemDO().setId(itemDO.getId()).setSelected(true)
                    .setQuantity(addBO.getQuantity() + itemDO.getQuantity()));
            return;
        }
        // 不存在，则进行插入
        if (addBO.getQuantity() > skuQuantity) {
            // 校验库存
            throw ServiceExceptionUtil.exception(CARD_ITEM_SKU_QUANTITY_NOT_ENOUGH);
        }
        cartItemMapper.insert(CartConvert.INSTANCE.convert(addBO).setSelected(true));
    }

    /**
     * 更新购物车商品数量
     *
     * @param userId 用户编号
     * @param skuId 商品 SKU 编号
     * @param quantity 数量
     * @param skuQuantity 商品 SKU 的库存，主要用于库存校验
     */
    public void updateCartItemQuantity(Integer userId, Integer skuId, Integer quantity, Integer skuQuantity) {
        if (quantity > skuQuantity) {
            // 校验库存
            throw ServiceExceptionUtil.exception(CARD_ITEM_SKU_QUANTITY_NOT_ENOUGH);
        }
        // 查询 CartItemDO
        CartItemDO itemDO = cartItemMapper.selectByUserIdAndSkuId(userId, skuId);
        if (itemDO == null) {
            throw ServiceExceptionUtil.exception(CARD_ITEM_NOT_FOUND);
        }
        // 更新数量
        cartItemMapper.updateById(new CartItemDO().setId(itemDO.getId()).setQuantity(quantity));
    }

    /**
     * 更新购物车商品是否选中
     *
     * @param userId 用户编号
     * @param skuIds 商品 SKU 编号数组
     * @param selected 是否选中
     */
    public void updateCartItemSelected(Integer userId, Collection<Integer> skuIds, Boolean selected) {
        // 查询 CartItemDO 列表
        List<CartItemDO> itemDOs = cartItemMapper.selectListByUserIdAndSkuIds(userId, skuIds);
        if (skuIds.size() != itemDOs.size()) {
            throw ServiceExceptionUtil.exception(CARD_ITEM_NOT_FOUND);
        }
        // 更新选中
        cartItemMapper.updateByIds(CollectionUtils.convertSet(itemDOs, CartItemDO::getId),
                new CartItemDO().setSelected(selected));
    }

    /**
     * 购物车删除商品
     *
     * @param userId 用户编号
     * @param skuIds 商品 SKU 编号的数组
     */
    public void deleteCartItems(Integer userId, List<Integer> skuIds) {
        // 查询 CartItemDO 列表
        List<CartItemDO> itemDOs = cartItemMapper.selectListByUserIdAndSkuIds(userId, skuIds);
        if (CollectionUtils.isEmpty(itemDOs)) {
            return;
        }
        // 批量标记删除
        cartItemMapper.deleteBatchIds(CollectionUtils.convertSet(itemDOs, CartItemDO::getId));
    }

    /**
     * 查询用户在购物车中的商品数量
     *
     * @param userId 用户编号
     * @return 商品数量
     */
    public Integer sumCartItemQuantity(Integer userId) {
        return cartItemMapper.selectSumQuantityByUserId(userId);
    }

    /**
     * 查询用户在购物车种的商品列表
     *
     * @param queryBO 查询条件 BO
     * @return 购物车中商品列表信息
     */
    public List<CartItemBO> listCartItems(CartItemListQueryBO queryBO) {
        List<CartItemDO> cartItemDOs = cartItemMapper.selectList(queryBO);
        return CartConvert.INSTANCE.convertList(cartItemDOs);
    }

}
