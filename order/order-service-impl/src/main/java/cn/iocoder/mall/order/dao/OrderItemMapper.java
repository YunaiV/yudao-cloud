package cn.iocoder.mall.order.dao;

import cn.iocoder.mall.order.dataobject.OrderItemDO;
import org.springframework.stereotype.Repository;

/**
 * 订单 item mapper
 *
 * @author Sin
 * @time 2019-03-16 15:09
 */
@Repository
public interface OrderItemMapper {

    /**
     * 插入数据
     *
     * @param orderItemDO
     */
    void insert(OrderItemDO orderItemDO);
}
