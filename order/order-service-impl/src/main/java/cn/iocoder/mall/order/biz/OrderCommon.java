package cn.iocoder.mall.order.biz;

import cn.iocoder.mall.order.biz.dataobject.OrderItemDO;

import java.util.List;

/**
 * 订单常用
 *
 * @author Sin
 * @time 2019-03-23 11:51
 */
public interface OrderCommon {


    /**
     * 计算总价格
     *
     * @param items
     * @return
     */
    Integer calculatedPrice(List<OrderItemDO> items);

    /**
     * 计算订单实付金额
     *
     * @param items
     * @return
     */
    Integer calculatedAmount(List<OrderItemDO> items);

    /**
     * 计算物流金额
     *
     * @param items
     * @return
     */
    Integer calculatedLogisticsPrice(List<OrderItemDO> items);
}
