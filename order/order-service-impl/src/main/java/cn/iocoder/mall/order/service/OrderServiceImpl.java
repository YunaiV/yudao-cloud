package cn.iocoder.mall.order.service;

import cn.iocoder.common.framework.constant.DeletedStatusEnum;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.order.OrderCommon;
import cn.iocoder.mall.order.api.OrderService;
import cn.iocoder.mall.order.api.bo.OrderBO;
import cn.iocoder.mall.order.api.constant.OrderErrorCodeEnum;
import cn.iocoder.mall.order.api.constant.OrderHasReturnExchangeEnum;
import cn.iocoder.mall.order.api.constant.OrderStatusEnum;
import cn.iocoder.mall.order.api.dto.*;
import cn.iocoder.mall.order.convert.OrderConvert;
import cn.iocoder.mall.order.convert.OrderItemConvert;
import cn.iocoder.mall.order.convert.OrderLogisticsConvert;
import cn.iocoder.mall.order.dao.OrderItemMapper;
import cn.iocoder.mall.order.dao.OrderLogisticsMapper;
import cn.iocoder.mall.order.dao.OrderMapper;
import cn.iocoder.mall.order.dataobject.OrderDO;
import cn.iocoder.mall.order.dataobject.OrderItemDO;
import cn.iocoder.mall.order.dataobject.OrderLogisticsDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    private OrderCommon orderCommon;

    @Override
    public CommonResult<List<OrderPageBO>> getOrderPage(OrderQueryDTO orderQueryDTO) {

        int offset = orderQueryDTO.getPageNo() * orderQueryDTO.getPageSize();
        int pageSize = orderQueryDTO.getPageSize();

        int totalCount = orderMapper.selectPageCount(orderQueryDTO);
        if (totalCount == 0) {
            return CommonResult.success(Collections.EMPTY_LIST);
        }

        List<OrderDO> orderDOList = orderMapper.selectPage(orderQueryDTO, offset, pageSize);
        List<OrderPageBO> orderPageBOList = OrderConvert.INSTANCE.convertPageBO(orderDOList);
        return CommonResult.success(orderPageBOList);
    }

    @Override
    @Transactional
    public CommonResult<OrderBO> createOrder(Integer userId, OrderCreateDTO orderCreateDTO) {
        List<OrderCreateItemDTO> orderItemDTOList = orderCreateDTO.getOrderItems();
        OrderLogisticsDO orderLogisticsDO = OrderLogisticsConvert.INSTANCE.convert(orderCreateDTO);
        List<OrderItemDO> orderItemDOList = OrderItemConvert.INSTANCE.convert(orderItemDTOList);

        // 物流信息
        orderLogisticsDO
                .setLogisticsNo("")
                .setCreateTime(new Date())
                .setUpdateTime(null);
        orderLogisticsMapper.insert(orderLogisticsDO);

        // order
        OrderDO orderDO = new OrderDO()
                .setUserId(userId)
                .setOrderLogisticsId(orderLogisticsDO.getId())
                .setOrderNo(UUID.randomUUID().toString().replace("-", ""))
                .setMoney(-1) // 先设置一个默认值，金额在下面计算
                .setClosingTime(null)
                .setDeliveryTime(null)
                .setPaymentTime(null)
                .setStatus(OrderStatusEnum.WAIT_SHIPMENT.getValue())
                .setHasReturnExchange(OrderHasReturnExchangeEnum.NO.getValue())
                .setRemark(Optional.ofNullable(orderCreateDTO.getRemark()).orElse(""));

        orderDO.setDeleted(DeletedStatusEnum.DELETED_NO.getValue());
        orderDO.setCreateTime(new Date());
        orderDO.setUpdateTime(null);
        orderMapper.insert(orderDO);

        // order item
        orderItemDOList.forEach(orderItemDO -> {
            int goodsPrice = 1000; // 商品单价
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
                    .setDeleted(DeletedStatusEnum.DELETED_NO.getValue())
                    .setCreateTime(new Date())
                    .setUpdateTime(null);

            orderItemMapper.insert(orderItemDO);
        });

        // 更新订单金额
        Integer totalAmount = orderCommon.calculatedAmount(orderItemDOList);
        orderMapper.updateById(
                new OrderDO()
                        .setId(orderDO.getId())
                        .setMoney(totalAmount)
        );

        // TODO: 2019-03-17 Sin 需要发送 创建成果 MQ 消息

        return CommonResult.success(
                new OrderBO()
                        .setId(orderDO.getId())
                        .setOrderNo(orderDO.getOrderNo())
                        .setMoney(orderDO.getMoney())
        );
    }

    @Override
    public CommonResult updateOrderItem(OrderItemUpdateDTO orderUpdateDTO) {
        OrderItemDO orderItemDO = OrderItemConvert.INSTANCE.convert(orderUpdateDTO);
        orderItemMapper.updateById(orderItemDO);
        return CommonResult.success(null);
    }

    @Override
    public CommonResult deleteOrderItem(OrderItemDeletedDTO orderItemDeletedDTO) {
        Integer orderId = orderItemDeletedDTO.getOrderId();
        List<Integer> orderItemIds = orderItemDeletedDTO.getOrderItemIds();

        // 获取当前有效的订单 item
        List<OrderItemDO> orderItemDOList = orderItemMapper
                .selectByOrderIdAndDeleted(orderId, DeletedStatusEnum.DELETED_NO.getValue());

        List<OrderItemDO> effectiveOrderItems = orderItemDOList.stream()
                .filter(orderItemDO -> !orderItemIds.contains(orderItemDO.getId()))
                .collect(Collectors.toList());

        // 检查订单 item，必须要有一个 item
        if (CollectionUtils.isEmpty(effectiveOrderItems)) {
            return ServiceExceptionUtil.error(OrderErrorCodeEnum.ORDER_ITEM_ONLY_ONE.getCode());
        }

        // 更新订单 item
        orderItemMapper.updateByIds(
                orderItemIds,
                (OrderItemDO) new OrderItemDO()
                        .setDeleted(DeletedStatusEnum.DELETED_YES.getValue())
        );

        // 更新订单 amount
        Integer totalAmount = orderCommon.calculatedAmount(effectiveOrderItems);
        orderMapper.updateById(
                new OrderDO()
                        .setId(orderId)
                        .setMoney(totalAmount)
        );
        return CommonResult.success(null);
    }

    @Override
    public CommonResult updateLogistics(OrderLogisticsUpdateDTO orderLogisticsDTO) {
        OrderLogisticsDO orderLogisticsDO = OrderLogisticsConvert.INSTANCE.convert(orderLogisticsDTO);
        orderLogisticsMapper.updateById(orderLogisticsDO);
        return CommonResult.success(null);
    }

    @Override
    public CommonResult deleteOrder(Integer id) {
        // 删除订单操作，一般用于 用户端删除，是否存在检查可以过掉
        orderMapper.updateById((OrderDO) new OrderDO()
                .setId(id)
                .setDeleted(DeletedStatusEnum.DELETED_YES.getValue())
        );
        return CommonResult.success(null);
    }

    @Override
    public CommonResult listenerPayment() {
        return null;
    }

    @Override
    public CommonResult listenerConfirmGoods() {
        return null;
    }

    @Override
    public CommonResult listenerExchangeGoods() {
        return null;
    }
}
