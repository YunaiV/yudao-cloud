package cn.iocoder.mall.order.biz.service;

import cn.iocoder.common.framework.constant.CommonStatusEnum;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.order.api.CartService;
import cn.iocoder.mall.order.api.bo.*;
import cn.iocoder.mall.order.api.constant.CartItemStatusEnum;
import cn.iocoder.mall.order.api.constant.OrderErrorCodeEnum;
import cn.iocoder.mall.order.api.dto.CalcOrderPriceDTO;
import cn.iocoder.mall.order.biz.convert.CartConvert;
import cn.iocoder.mall.order.biz.dao.CartMapper;
import cn.iocoder.mall.order.biz.dataobject.CartItemDO;
import cn.iocoder.mall.product.api.ProductSpuService;
import cn.iocoder.mall.product.api.bo.ProductSkuBO;
import cn.iocoder.mall.product.api.bo.ProductSkuDetailBO;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 购物车服务 Service 实现类
 */
@Service
@com.alibaba.dubbo.config.annotation.Service(validation = "true")
public class CartServiceImpl implements CartService {

    @Reference(validation = "true")
    private ProductSpuService productSpuService;

    @Autowired
    private CartMapper cartMapper;

    @Override
    @SuppressWarnings("Duplicates")
    public CommonResult<Boolean> add(Integer userId, Integer skuId, Integer quantity) {
        // 查询 SKU 是否合法
        CommonResult<ProductSkuBO> skuResult = productSpuService.getProductSku(skuId);
        if (skuResult.isError()) {
            return CommonResult.error(skuResult);
        }
        ProductSkuBO sku = skuResult.getData();
        if (sku == null
                || CommonStatusEnum.DISABLE.getValue().equals(sku.getStatus())) { // sku 被禁用
            return ServiceExceptionUtil.error(OrderErrorCodeEnum.CARD_ITEM_SKU_NOT_FOUND.getCode());
        }
        // TODO 芋艿，后续基于商品是否上下架进一步完善。
        // 查询 CartItemDO
        CartItemDO item = cartMapper.selectByUserIdAndSkuIdAndStatus(userId, skuId, CartItemStatusEnum.ENABLE.getValue());
        // 存在，则进行数量更新
        if (item != null) {
            return updateQuantity0(item, sku, quantity);
        }
        // 不存在，则进行插入
        return add0(userId, sku, quantity);
    }

    private CommonResult<Boolean> add0(Integer userId, ProductSkuBO sku, Integer quantity) {
        // 校验库存
        if (quantity > sku.getQuantity()) {
            return ServiceExceptionUtil.error(OrderErrorCodeEnum.CARD_ITEM_SKU_NOT_FOUND.getCode());
        }
        // 创建 CartItemDO 对象，并进行保存。
        CartItemDO item = new CartItemDO()
                // 基础字段
                .setStatus(CartItemStatusEnum.ENABLE.getValue()).setSelected(true)
                // 买家信息
                .setUserId(userId)
                // 商品信息
                .setSpuId(sku.getSpuId()).setSkuId(sku.getId()).setQuantity(quantity);
        item.setCreateTime(new Date());
        cartMapper.insert(item);
        // 返回成功
        return CommonResult.success(true);
    }

    @Override
    @SuppressWarnings("Duplicates")
    public CommonResult<Boolean> updateQuantity(Integer userId, Integer skuId, Integer quantity) {
        // 查询 SKU 是否合法
        CommonResult<ProductSkuBO> skuResult = productSpuService.getProductSku(skuId);
        if (skuResult.isError()) {
            return CommonResult.error(skuResult);
        }
        ProductSkuBO sku = skuResult.getData();
        if (sku == null
                || CommonStatusEnum.DISABLE.getValue().equals(sku.getStatus())) { // sku 被禁用
            return ServiceExceptionUtil.error(OrderErrorCodeEnum.CARD_ITEM_SKU_NOT_FOUND.getCode());
        }
        // 查询 CartItemDO
        CartItemDO item = cartMapper.selectByUserIdAndSkuIdAndStatus(userId, skuId, CartItemStatusEnum.ENABLE.getValue());
        if (item == null) {
            return ServiceExceptionUtil.error(OrderErrorCodeEnum.CARD_ITEM_NOT_FOUND.getCode());
        }
        // TODO 芋艿，后续基于商品是否上下架进一步完善。
        return updateQuantity0(item, sku, quantity);
    }

    private CommonResult<Boolean> updateQuantity0(CartItemDO item, ProductSkuBO sku, Integer quantity) {
        // 校验库存
        if (item.getQuantity() + quantity > sku.getQuantity()) {
            return ServiceExceptionUtil.error(OrderErrorCodeEnum.CARD_ITEM_SKU_NOT_FOUND.getCode());
        }
        // 更新 CartItemDO
        cartMapper.updateQuantity(item.getId(), quantity);
        // 返回成功
        return CommonResult.success(true);
    }

    @Override
    public CommonResult<Boolean> updateSelected(Integer userId, Collection<Integer> skuIds, Boolean selected) {
        // 更新 CartItemDO 们
        cartMapper.updateListByUserIdAndSkuId(userId, skuIds, selected, null);
        // 返回成功
        return CommonResult.success(true);
    }

    @Override
    public CommonResult<Boolean> deleteList(Integer userId, List<Integer> skuIds) {
        // 更新 CartItemDO 们
        cartMapper.updateListByUserIdAndSkuId(userId, skuIds, null, CartItemStatusEnum.DELETE_BY_MANUAL.getValue());
        // 返回成功
        return CommonResult.success(true);
    }

    @Override
    public CommonResult<Boolean> deleteAll(Integer userId) {
        return null;
    }

    @Override
    public CommonResult<Integer> count(Integer userId) {
        return CommonResult.success(cartMapper.selectQuantitySumByUserIdAndStatus(userId, CartItemStatusEnum.ENABLE.getValue()));
    }

    @Override
    public CommonResult<List<CartItemBO>> list(Integer userId, Boolean selected) {
        List<CartItemDO> items = cartMapper.selectByUserIdAndStatusAndSelected(userId, CartItemStatusEnum.ENABLE.getValue(), selected);
        return CommonResult.success(CartConvert.INSTANCE.convert(items));
    }

    @Override
    public CommonResult<CalcOrderPriceBO> calcOrderPrice(CalcOrderPriceDTO calcOrderPriceDTO) {
        // 校验商品都存在
        Map<Integer, CalcOrderPriceDTO.Item> calcOrderItemMap = calcOrderPriceDTO.getItems().stream()
                .collect(Collectors.toMap(CalcOrderPriceDTO.Item::getSkuId, item -> item));
        List<ProductSkuDetailBO> skus = productSpuService.getProductSkuDetailList(calcOrderItemMap.keySet()).getData();
        if (skus.size() != calcOrderPriceDTO.getItems().size()) {
            return ServiceExceptionUtil.error(OrderErrorCodeEnum.ORDER_ITEM_SOME_NOT_EXISTS.getCode());
        }
        // TODO 库存相关
        // TODO 获得促销活动
        // TODO 处理促销相关信息
        // 拼装结果
        CalcOrderPriceBO calcOrderPriceBO = new CalcOrderPriceBO();
        // 1. 商品分组
        CalcOrderPriceBO.ItemGroup itemGroup0 = new CalcOrderPriceBO.ItemGroup()
                .setItems(new ArrayList<>());
        for (ProductSkuDetailBO sku : skus) {
            CalcOrderPriceBO.Item item = CartConvert.INSTANCE.convert(sku);
            // 将是否选中，购物数量，复制到 item 中
            CalcOrderPriceDTO.Item calcOrderItem = calcOrderItemMap.get(sku.getId());
            item.setSelected(calcOrderItem.getSelected());
            item.setBuyQuantity(calcOrderItem.getQuantity());
            // 添加到 itemGroup 中
            itemGroup0.getItems().add(item);
        }
        calcOrderPriceBO.setItemGroups(Collections.singletonList(itemGroup0));
        // 2. 计算价格
        CalcOrderPriceBO.Fee fee = new CalcOrderPriceBO.Fee(0, 0, 0, 0);
        for (CalcOrderPriceBO.ItemGroup itemGroup : calcOrderPriceBO.getItemGroups()) {
            int originalTotal = 0;
            for (CalcOrderPriceBO.Item item : itemGroup.getItems()) {
                if (!item.getSelected()) { // 未选中，则不计算到其中
                    continue;
                }
                originalTotal += item.getPrice() * item.getBuyQuantity();
            }
            fee.setOriginalTotal(fee.getOriginalTotal() + originalTotal);
            fee.setPresentTotal(fee.getOriginalTotal()); // TODO 芋艿，后续要计算优惠价格
        }
        calcOrderPriceBO.setFee(fee);
        // 返回
        return CommonResult.success(calcOrderPriceBO);
    }

    @Override
    public CommonResult<CalcSkuPriceBO> calcSkuPrice(Integer skuId) {
        return null;
    }

    @Override
    public CommonResult<CartBO> details(Integer userId) {
        return null;
    }

    @Override
    public CommonResult<OrderCreateBO> createOrder(Integer userId) {
        return null;
    }
}
