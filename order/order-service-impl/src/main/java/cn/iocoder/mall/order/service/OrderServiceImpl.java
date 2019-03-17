package cn.iocoder.mall.order.service;

import cn.iocoder.mall.order.api.OrderService;
import cn.iocoder.mall.order.api.bo.OrderBO;
import cn.iocoder.mall.order.api.dto.OrderCreateDTO;
import cn.iocoder.mall.order.api.dto.OrderCreateItemDTO;
import cn.iocoder.mall.order.api.dto.OrderUpdateDTO;
import cn.iocoder.mall.order.dataobject.OrderDO;
import cn.iocoder.mall.order.dataobject.OrderItemDO;
import cn.iocoder.mall.order.convert.OrderConvert;
import cn.iocoder.mall.order.dao.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public OrderBO createOrder(OrderCreateDTO orderCreateDTO) {
        List<OrderCreateItemDTO> orderItemDTOList = orderCreateDTO.getOrderItems();
        OrderDO orderDO = OrderConvert.INSTANCE.convert(orderCreateDTO);
        List<OrderItemDO> orderItemDOList = OrderConvert.INSTANCE.convert(orderItemDTOList);
        orderMapper.insert(orderDO);
        String a = "";
//        orderConvert.con
//        for (OrderCreateItemDTO orderCreateItemDTO : orderItemDTOList) {
//            OrderItemDO orderItemDO = new OrderItemDO();
//            orderItemDO.setId();
//            orderItemDO.setCommodityId();
//            orderItemDO.setOrderId();
//            orderItemDO.setPrice();
//            orderItemDO.setQuantity();
//            orderItemDO.setStatus();
//        }
//        orderMapper.insert();
        return null;
    }

    @Override
    public void updateOrder(OrderUpdateDTO orderUpdateDTO) {

    }

    @Override
    public void deleteOrder(String orderId) {

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
