package cn.iocoder.mall.order.dao;

import cn.iocoder.mall.order.dataobject.OrderDO;
import cn.iocoder.mall.order.dataobject.OrderLogisticsDO;
import org.springframework.stereotype.Repository;

/**
 * 订单 item mapper
 *
 * @author Sin
 * @time 2019-03-16 15:09
 */
@Repository
public interface OrderLogisticsMapper {

    /**
     * 插入数据
     *
     * @param orderLogisticsDO
     */
    void insert(OrderLogisticsDO orderLogisticsDO);
}
