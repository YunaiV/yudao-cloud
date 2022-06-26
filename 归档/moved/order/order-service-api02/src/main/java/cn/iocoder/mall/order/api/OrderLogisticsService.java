package cn.iocoder.mall.order.api;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.order.api.bo.OrderLastLogisticsInfoBO;
import cn.iocoder.mall.order.api.bo.OrderLogisticsInfoBO;
import cn.iocoder.mall.order.api.bo.OrderLogisticsInfoWithOrderBO;

/**
 * 订单物流信息
 *
 * @author Sin
 * @time 2019-04-12 21:29
 */
public interface OrderLogisticsService {


    /**
     * 获取物流信息 - 根据id
     *
     * @param id
     * @return
     */
    CommonResult<OrderLogisticsInfoBO> getLogisticsInfo(Integer id);


    /**
     * 获取 last 物流信息 - 根据id
     *
     * @param id
     * @return
     */
    CommonResult<OrderLastLogisticsInfoBO> getLastLogisticsInfo(Integer id);

    /**
     * 获取物流信息 - 根据 orderId
     *
     * @param userId
     * @param orderId
     * @return
     */
    CommonResult<OrderLogisticsInfoWithOrderBO> getOrderLogisticsInfo(Integer userId, Integer orderId);

}
