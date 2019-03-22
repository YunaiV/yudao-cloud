package cn.iocoder.mall.order.service;

import cn.iocoder.common.framework.constant.DeleteStatusEnum;
import cn.iocoder.mall.order.api.OrderService;
import cn.iocoder.mall.order.api.bo.OrderBO;
import cn.iocoder.mall.order.api.constant.OrderHasReturnExchangeEnum;
import cn.iocoder.mall.order.api.constant.OrderStatusEnum;
import cn.iocoder.mall.order.api.dto.OrderCreateDTO;
import cn.iocoder.mall.order.api.dto.OrderCreateItemDTO;
import cn.iocoder.mall.order.api.dto.OrderReceiverInformationDTO;
import cn.iocoder.mall.order.api.dto.OrderUpdateDTO;
import cn.iocoder.mall.order.convert.OrderConvert;
import cn.iocoder.mall.order.dao.OrderItemMapper;
import cn.iocoder.mall.order.dao.OrderLogisticsMapper;
import cn.iocoder.mall.order.dao.OrderMapper;
import cn.iocoder.mall.order.dataobject.OrderDO;
import cn.iocoder.mall.order.dataobject.OrderItemDO;
import cn.iocoder.mall.order.dataobject.OrderLogisticsDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 订单 service impl
 *
 * @author Sin
 * @time 2019-03-16 15:08
 */
@Service
@com.alibaba.dubbo.config.annotation.Service(validation = "true")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private OrderLogisticsMapper orderLogisticsMapper;

    @Override
    @Transactional
    public OrderBO createOrder(OrderCreateDTO orderCreateDTO) {
        List<OrderCreateItemDTO> orderItemDTOList = orderCreateDTO.getOrderItems();
        OrderLogisticsDO orderLogisticsDO = OrderConvert.INSTANCE.convert(orderCreateDTO);
        List<OrderItemDO> orderItemDOList = OrderConvert.INSTANCE.convert(orderItemDTOList);

        // 物流信息
        orderLogisticsDO
                .setLogisticsNo("")
                .setCreateTime(new Date())
                .setUpdateTime(null);
        orderLogisticsMapper.insert(orderLogisticsDO);

        // order
        OrderDO orderDO = new OrderDO();
        orderDO.setOrderLogisticsId(orderLogisticsDO.getId());
        orderDO.setOrderNo(UUID.randomUUID().toString().replace("-", ""));
        orderDO.setPrice(-1); // 先设置一个默认值，金额在下面计算
        orderDO.setCreateTime(new Date());
        orderDO.setUpdateTime(null);
        orderDO.setDeleted(DeleteStatusEnum.DELETE_NO.getValue());

        orderDO.setClosingTime(null);
        orderDO.setDeliveryTime(null);
        orderDO.setPaymentTime(null);
        orderDO.setStatus(OrderStatusEnum.WAIT_SHIPMENT.getValue());
        orderDO.setHasReturnExchange(OrderHasReturnExchangeEnum.NO.getValue());
        orderDO.setRemark(Optional.ofNullable(orderCreateDTO.getRemark()).orElse(""));
        orderMapper.insert(orderDO);

        // order item
        AtomicInteger totalPrice = new AtomicInteger();
        orderItemDOList.forEach(orderItemDO -> {
            int goodsPrice = 1000; // 商品单价
            int price = orderItemDO.getQuantity() * goodsPrice;
            totalPrice.addAndGet(price);
            orderItemDO
                    .setOrderId(orderDO.getId())
                    .setOrderNo(orderDO.getOrderNo())
                    .setPrice(goodsPrice)
                    .setPaymentTime(null)
                    .setDeliveryTime(null)
                    .setReceiverTime(null)
                    .setClosingTime(null)
                    .setHasReturnExchange(OrderStatusEnum.WAITING_PAYMENT.getValue())
                    .setStatus(OrderStatusEnum.WAITING_PAYMENT.getValue())
                    .setDeleted(DeleteStatusEnum.DELETE_NO.getValue())
                    .setCreateTime(new Date())
                    .setUpdateTime(new Date());

            orderItemMapper.insert(orderItemDO);
        });

        // 更新订单金额
        orderMapper.updateById(
                new OrderDO()
                        .setId(orderDO.getId())
                        .setPrice(totalPrice.get())
        );

        // TODO: 2019-03-17 Sin 需要发送 创建成果 MQ 消息

        return new OrderBO()
                .setId(orderDO.getId())
                .setOrderNo(orderDO.getOrderNo())
                .setPrice(orderDO.getPrice());
    }

    @Override
    public void updateOrder(OrderUpdateDTO orderUpdateDTO) {

    }

    @Override
    public void updateOrderReceiverInformation(OrderReceiverInformationDTO orderReceiverInfoDTO) {
        // TODO: 2019-03-17 需要做校验 手机号
        OrderDO orderDO = OrderConvert.INSTANCE.convert(orderReceiverInfoDTO);
        orderMapper.updateById(orderDO);
    }

    @Override
    public void deleteOrder(Integer id) {
        // 删除订单操作，一般用于 用户端删除，是否存在检查可以过掉
        orderMapper.updateById((OrderDO) new OrderDO()
                .setId(id)
                .setDeleted(DeleteStatusEnum.DELETE_YES.getValue())
        );
    }

    @Override
    public void listenerPayment() {

    }

    @Override
    public void listenerConfirmGoods() {

    }

    @Override
    public void listenerExchangeGoods() {

    }
}
