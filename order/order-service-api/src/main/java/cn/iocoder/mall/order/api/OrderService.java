package cn.iocoder.mall.order.api;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.order.api.bo.*;
import cn.iocoder.mall.order.api.dto.*;

import java.util.List;

/**
 * 订单 service
 *
 * @author Sin
 * @time 2019-03-16 13:15
 */
public interface OrderService {

    /**
     * 订单 page
     *
     * @param orderQueryDTO
     * @return
     */
    CommonResult<OrderPageBO> getOrderPage(OrderQueryDTO orderQueryDTO);

    /**
     * 获取订单items
     *
     * @param orderId
     * @return
     */
    CommonResult<List<OrderItemBO>> getOrderItems(Integer orderId);

    /**
     * 订单收件人信息
     *
     * @param orderId
     * @return
     */
    CommonResult<OrderRecipientBO> getOrderRecipientBO(Integer orderId);

    /**
     * 订单 - 创建
     *
     * @param orderCreateDTO
     * @return
     */
    CommonResult<OrderCreateBO> createOrder(OrderCreateDTO orderCreateDTO);

    /**
     * 订单item - 更新
     *
     * @param orderItemUpdateDTO
     *
     */
    CommonResult updateOrderItem(OrderItemUpdateDTO orderItemUpdateDTO);

    /**
     * 更新订单item - payAmount(实付金额)
     *
     * @param orderId
     * @param orderItemId
     * @param payAmount
     * @return
     */
    CommonResult updateOrderItemPayAmount(Integer orderId, Integer orderItemId, Integer payAmount);

    /**
     * 订单 - 取消订单
     *
     * @param orderId
     * @return
     */
    CommonResult cancelOrder(Integer orderId, Integer reasons, String otherReasons);

    /**
     * 订单发货
     *
     * @param orderDelivery
     * @return
     */
    CommonResult orderDelivery(OrderDeliveryDTO orderDelivery);

    /**
     * 更新订单 - 备注
     *
     * @param orderId
     * @param remake
     * @return
     */
    CommonResult updateOrderRemake(Integer orderId, String remake);

    /**
     * 删除订单item
     *
     * @param orderItemDeletedDTO
     * @return
     */
    CommonResult deleteOrderItem(OrderItemDeletedDTO orderItemDeletedDTO);

    /**
     * 用户确认订单
     *
     * @param userId
     * @param orderId
     * @return
     */
    CommonResult confirmReceiving(Integer userId, Integer orderId);

    /**
     * 更新订单 - 收件这信息
     *
     * 包含：
     * - 详细地址
     * - 区域编号
     * - 联系人电话
     * - 联系人姓名
     */
    CommonResult updateLogistics(OrderLogisticsUpdateDTO orderLogisticsDTO);

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
