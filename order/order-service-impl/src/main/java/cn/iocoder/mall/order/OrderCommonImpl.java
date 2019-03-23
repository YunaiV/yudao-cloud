package cn.iocoder.mall.order;

import cn.iocoder.mall.order.dataobject.OrderItemDO;
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
    public Integer calculatedAmount(List<OrderItemDO> items) {
        if (CollectionUtils.isEmpty(items)) {
            return 0;
        }
        AtomicInteger totalAmount = new AtomicInteger(0);
        items.forEach(orderItemDO -> {
            totalAmount.addAndGet(orderItemDO.getPrice() * orderItemDO.getQuantity());
        });
        return totalAmount.get();
    }
}
