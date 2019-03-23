package cn.iocoder.mall.order.api;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.order.api.bo.OrderBO;
import cn.iocoder.mall.order.api.dto.OrderCreateDTO;
import cn.iocoder.mall.order.api.dto.OrderItemDeletedDTO;
import cn.iocoder.mall.order.api.dto.OrderLogisticsDTO;
import cn.iocoder.mall.order.api.dto.OrderItemUpdateDTO;

/**
 * 订单 service
 *
 * @author Sin
 * @time 2019-03-16 13:15
 */
public interface OrderService {

    /**
     * 订单 - 创建
     *
     * @param orderCreateDTO
     * @return
     */
    CommonResult<OrderBO> createOrder(OrderCreateDTO orderCreateDTO);

    /**
     * 订单item - 更新
     *
     * @param orderItemUpdateDTO
     *
     */
    CommonResult updateOrderItem(OrderItemUpdateDTO orderItemUpdateDTO);

    /**
     * 订单item - 删除
     *
     * @param orderItemDeletedDTO
     * @return
     */
    CommonResult deleteOrderItem(OrderItemDeletedDTO orderItemDeletedDTO);

    /**
     * 更新订单 - 收件这信息
     *
     * 包含：
     * - 详细地址
     * - 区域编号
     * - 联系人电话
     * - 联系人姓名
     */
    CommonResult updateLogistics(OrderLogisticsDTO orderLogisticsDTO);

    /**
     * 删除订单
     *
     * @param id
     */
    CommonResult deleteOrder(Integer id);

    /**
     * 监听支付动作
     *
     *  mq 更新 payStatus
     */
    CommonResult listenerPayment();

    /**
     * 监听确认收货
     *
     * mq 更新 status
     */
    CommonResult listenerConfirmGoods();

    /**
     * 监听换货
     *
     * mq 更新 status
     */
    CommonResult listenerExchangeGoods();
}
