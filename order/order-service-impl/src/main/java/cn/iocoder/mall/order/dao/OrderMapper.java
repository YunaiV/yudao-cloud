package cn.iocoder.mall.order.dao;

import cn.iocoder.mall.order.dataobject.OrderDO;
import org.springframework.stereotype.Repository;

/**
 * 订单 mapper
 *
 * @author Sin
 * @time 2019-03-16 15:09
 */
@Repository
public interface OrderMapper {

    /**
     * 插入数据
     *
     * @param orderDO
     */
    void insert(OrderDO orderDO);

    /**
     * 更新 - 根据 id 更新
     *
     * @param orderDO
     * @return
     */
    int updateById(OrderDO orderDO);
}
