package cn.iocoder.mall.order.service;

import cn.iocoder.mall.order.api.OrderService;
import cn.iocoder.mall.order.api.bo.OrderBO;
import cn.iocoder.mall.order.api.constants.OrderDeleteStatusEnum;
import cn.iocoder.mall.order.api.constants.OrderPayStatusEnum;
import cn.iocoder.mall.order.api.constants.OrderStatusEnum;
import cn.iocoder.mall.order.api.dto.OrderCreateDTO;
import cn.iocoder.mall.order.api.dto.OrderCreateItemDTO;
import cn.iocoder.mall.order.api.dto.OrderReceiverInformationDTO;
import cn.iocoder.mall.order.api.dto.OrderUpdateDTO;
import cn.iocoder.mall.order.convert.OrderConvert;
import cn.iocoder.mall.order.dao.OrderItemMapper;
import cn.iocoder.mall.order.dao.OrderMapper;
import cn.iocoder.mall.order.dataobject.OrderDO;
import cn.iocoder.mall.order.dataobject.OrderItemDO;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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

    @Override
    @Transactional
    public OrderBO createOrder(OrderCreateDTO orderCreateDTO) {
        List<OrderCreateItemDTO> orderItemDTOList = orderCreateDTO.getOrderItems();
        OrderDO orderDO = OrderConvert.INSTANCE.convert(orderCreateDTO);
        List<OrderItemDO> orderItemDOList = OrderConvert.INSTANCE.convert(orderItemDTOList);

        // order
        orderDO.setOrderNo(UUID.randomUUID().toString().replace("-", ""));
        orderDO.setPrice(1000);
        orderDO.setCreateTime(new Date());
        orderDO.setClosingTime(null);
        orderDO.setDeliveryTime(null);
        orderDO.setPaymentTime(null);
        orderDO.setStatus(OrderStatusEnum.WAIT_SHIPMENT.getValue());
        orderDO.setPayStatus(OrderPayStatusEnum.WAITING_PAYMENT.getValue());
        orderDO.setDeleteStatus(OrderDeleteStatusEnum.DELETE_NO.getValue());
        orderMapper.insert(orderDO);

        // order item
        int goodsPrice = 1000;
        orderItemDOList.forEach(orderItemDO -> {
            int price = orderItemDO.getQuantity() * goodsPrice;
            orderItemDO
                    .setStatus(OrderStatusEnum.WAIT_SHIPMENT.getValue())
                    .setOrderId(orderDO.getId())
                    .setPrice(price)
                    .setDeliveryTime(null);

            orderItemMapper.insert(orderItemDO);
        });

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
        orderMapper.updateById(new OrderDO()
                .setId(id)
                .setDeleteStatus(OrderDeleteStatusEnum.DELETE_YES.getValue())
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
