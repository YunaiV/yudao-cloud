package cn.iocoder.mall.order.api;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.order.api.bo.OrderLogisticsBO;
import cn.iocoder.mall.order.api.bo.OrderLogisticsInfoBO;

import java.util.List;

/**
 * 订单物流信息
 *
 * @author Sin
 * @time 2019-04-12 21:29
 */
public interface OrderLogisticsService {

    /**
     * 物流信息
     *
     * @param userId
     * @param orderId
     * @return
     */
    CommonResult<OrderLogisticsInfoBO> logisticsInfo(Integer userId, Integer orderId);

}
