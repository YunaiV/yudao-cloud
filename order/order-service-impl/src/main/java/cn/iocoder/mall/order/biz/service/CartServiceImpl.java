package cn.iocoder.mall.order.biz.service;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.order.api.CartService;
import cn.iocoder.mall.order.api.bo.CalcOrderPriceBO;
import cn.iocoder.mall.order.api.bo.CartBO;
import cn.iocoder.mall.order.api.bo.CartItemBO;
import cn.iocoder.mall.order.api.bo.OrderCreateBO;
import cn.iocoder.mall.order.api.constant.OrderErrorCodeEnum;
import cn.iocoder.mall.order.api.dto.CalcOrderPriceDTO;
import cn.iocoder.mall.order.biz.convert.CartConvert;
import cn.iocoder.mall.product.api.ProductSpuService;
import cn.iocoder.mall.product.api.bo.ProductSkuDetailBO;
import com.alibaba.dubbo.config.annotation.Reference;
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

    @Override
    public CommonResult<Boolean> add(Integer userId, Integer skuId, Integer quantity) {
        return null;
    }

    @Override
    public CommonResult<Boolean> updateQuantity(Integer userId, Integer skuId, Integer quantity) {
        return null;
    }

    @Override
    public CommonResult<Boolean> updateSelected(Integer userId, Integer skuId) {
        return null;
    }

    @Override
    public CommonResult<Boolean> delete(Integer userId, List<Integer> skuIds) {
        return null;
    }

    @Override
    public CommonResult<Boolean> deleteAll(Integer userId) {
        return null;
    }

    @Override
    public CommonResult<Integer> count(Integer userId, String nobody, Integer shopId) {
        return null;
    }

    @Override
    public List<CartItemBO> list(Integer userId, Boolean selected) {
        return null;
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
    public CommonResult<CartBO> details(Integer userId) {
        return null;
    }

    @Override
    public CommonResult<OrderCreateBO> createOrder(Integer userId) {
        return null;
    }
}
