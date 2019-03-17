package cn.iocoder.mall.order.api;

import cn.iocoder.mall.order.api.bo.OrderBO;
import cn.iocoder.mall.order.api.dto.OrderCreateDTO;
import cn.iocoder.mall.order.api.dto.OrderReceiverInformationDTO;
import cn.iocoder.mall.order.api.dto.OrderUpdateDTO;

/**
 * 订单 service
 *
 * @author Sin
 * @time 2019-03-16 13:15
 */
public interface OrderService {

    /**
     * 订单创建
     *
     * @param orderCreateDTO
     * @return
     */
    OrderBO createOrder(OrderCreateDTO orderCreateDTO);

    /**
     * 订单更新
     *
     * @param orderUpdateDTO
     */
    void updateOrder(OrderUpdateDTO orderUpdateDTO);

    /**
     * 更新订单 - 收件这信息
     *
     * 包含：
     * - 详细地址
     * - 区域编号
     * - 联系人电话
     * - 联系人姓名
     */
    void updateOrderReceiverInformation(OrderReceiverInformationDTO orderReceiverInfoDTO);

    /**
     * 删除订单
     *
     * @param id
     */
    void deleteOrder(Integer id);

    /**
     * 监听支付动作
     *
     *  mq 更新 payStatus
     */
    void listenerPayment();

    /**
     * 监听确认收货
     *
     * mq 更新 status
     */
    void listenerConfirmGoods();

    /**
     * 监听换货
     *
     * mq 更新 status
     */
    void listenerExchangeGoods();
}
