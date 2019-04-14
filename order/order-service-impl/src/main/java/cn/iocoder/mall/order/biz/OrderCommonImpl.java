package cn.iocoder.mall.order.biz;

import cn.iocoder.mall.order.biz.dataobject.OrderItemDO;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 订单常用
 *
 * @author Sin
 * @time 2019-03-23 11:53
 */
@Component
public class OrderCommonImpl implements OrderCommon {

    @Override
    public Integer calculatedPrice(List<OrderItemDO> items) {
        if (CollectionUtils.isEmpty(items)) {
            return 0;
        }
        AtomicInteger totalPrice = new AtomicInteger(0);
        items.forEach(orderItemDO -> {
            totalPrice.addAndGet(orderItemDO.getPrice() * orderItemDO.getQuantity());
        });
        return totalPrice.get();
    }

    @Override
    public Integer calculatedAmount(List<OrderItemDO> items) {
        if (CollectionUtils.isEmpty(items)) {
            return 0;
        }
        AtomicInteger totalAmount = new AtomicInteger(0);
        items.forEach(orderItemDO -> {
            totalAmount.addAndGet(orderItemDO.getPayAmount() * orderItemDO.getQuantity());
        });
        return totalAmount.get();
    }

    @Override
    public Integer calculatedLogisticsPrice(List<OrderItemDO> items) {
        if (CollectionUtils.isEmpty(items)) {
            return 0;
        }
        AtomicInteger totalAmount = new AtomicInteger(0);
        items.forEach(orderItemDO -> {
            totalAmount.addAndGet(orderItemDO.getLogisticsPrice());
        });
        return totalAmount.get();
    }
}
