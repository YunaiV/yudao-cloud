package cn.iocoder.mall.order.biz.service;

import cn.iocoder.common.framework.constant.DeletedStatusEnum;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.order.api.OrderService;
import cn.iocoder.mall.order.api.bo.*;
import cn.iocoder.mall.order.api.constant.OrderErrorCodeEnum;
import cn.iocoder.mall.order.api.constant.OrderHasReturnExchangeEnum;
import cn.iocoder.mall.order.api.constant.OrderStatusEnum;
import cn.iocoder.mall.order.api.dto.*;
import cn.iocoder.mall.order.biz.OrderCommon;
import cn.iocoder.mall.order.biz.constants.OrderDeliveryTypeEnum;
import cn.iocoder.mall.order.biz.constants.OrderRecipientTypeEnum;
import cn.iocoder.mall.order.biz.convert.OrderConvert;
import cn.iocoder.mall.order.biz.convert.OrderItemConvert;
import cn.iocoder.mall.order.biz.convert.OrderLogisticsConvert;
import cn.iocoder.mall.order.biz.convert.OrderRecipientConvert;
import cn.iocoder.mall.order.biz.dao.*;
import cn.iocoder.mall.order.biz.dataobject.*;
import com.google.common.collect.Lists;
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
    private OrderRecipientMapper orderRecipientMapper;
    @Autowired
    private OrderCancelMapper orderCancelMapper;
    @Autowired
    private OrderCommon orderCommon;

    @Override
    public CommonResult<OrderPageBO> getOrderPage(OrderQueryDTO orderQueryDTO) {

        int totalCount = orderMapper.selectPageCount(orderQueryDTO);
        if (totalCount == 0) {
            return CommonResult.success(new OrderPageBO().setOrders(Collections.EMPTY_LIST).setTotal(0));
        }

        // 获取订单数据
        List<OrderDO> orderDOList = orderMapper.selectPage(orderQueryDTO);

        // 获取订单 id
        Set<Integer> orderIds = orderDOList.stream()
                .map(orderDO -> orderDO.getId())
                .collect(Collectors.toSet());

        // 获取物流信息
        List<OrderRecipientDO> orderRecipientDOList = orderRecipientMapper.selectByOrderIds(orderIds);
        List<OrderRecipientBO> orderRecipientBOList = OrderRecipientConvert.INSTANCE.convert(orderRecipientDOList);
        Map<Integer, OrderRecipientBO> orderRecipientBOMap
                = orderRecipientBOList.stream().collect(Collectors.toMap(OrderRecipientBO::getOrderId, obj -> obj));

        // 获取 订单的 items
        List<OrderItemDO> orderItemDOList = orderItemMapper
                .selectByOrderIdsAndDeleted(orderIds, DeletedStatusEnum.DELETED_NO.getValue());

        List<OrderItemBO> orderItemBOList = OrderItemConvert.INSTANCE.convertOrderItemDO(orderItemDOList);
        Map<Integer, List<OrderItemBO>> orderItemBOMultimap = orderItemBOList.stream().collect(
                Collectors.toMap(
                        OrderItemBO::getOrderId,
                        item -> Lists.newArrayList(item),
                        (oldVal, newVal) -> {
                            oldVal.addAll(newVal);
                            return oldVal;
                        }
                )
        );

        // 转换 orderDO 为 OrderBO，并设置 item
        List<OrderBO> orderPageBOList = OrderConvert.INSTANCE.convertPageBO(orderDOList);
        List<OrderBO> result = orderPageBOList.stream().map(orderBO -> {
            if (orderItemBOMultimap.containsKey(orderBO.getId())) {
                orderBO.setOrderItems(orderItemBOMultimap.get(orderBO.getId()));
            }
            if (orderRecipientBOMap.containsKey(orderBO.getId())) {
                orderBO.setOrderRecipient(orderRecipientBOMap.get(orderBO.getId()));
            }
            return orderBO;
        }).collect(Collectors.toList());

        return CommonResult.success(
                new OrderPageBO()
                        .setTotal(totalCount)
                        .setOrders(result)
        );
    }

    @Override
    public CommonResult<List<OrderItemBO>> getOrderItems(Integer orderId) {
        if (orderMapper.selectById(orderId) == null) {
            return ServiceExceptionUtil.error(OrderErrorCodeEnum.ORDER_NOT_EXISTENT.getCode());
        }

        List<OrderItemDO> orderItemDOList = orderItemMapper
                .selectByOrderIdAndDeleted(orderId, DeletedStatusEnum.DELETED_NO.getValue());

        List<OrderItemBO> orderItemBOList = OrderItemConvert.INSTANCE.convertOrderItemBO(orderItemDOList);
        return CommonResult.success(orderItemBOList);
    }

    @Override
    public CommonResult<OrderRecipientBO> getOrderRecipientBO(Integer orderId) {
        if (orderMapper.selectById(orderId) == null) {
            return ServiceExceptionUtil.error(OrderErrorCodeEnum.ORDER_NOT_EXISTENT.getCode());
        }

        OrderRecipientDO orderRecipientDO = orderRecipientMapper.selectByOrderId(orderId);
        OrderRecipientBO orderRecipientBO = OrderRecipientConvert.INSTANCE.convert(orderRecipientDO);
        return CommonResult.success(orderRecipientBO);
    }

    @Override
    @Transactional
    public CommonResult<OrderCreateBO> createOrder(Integer userId, OrderCreateDTO orderCreateDTO) {
        List<OrderCreateItemDTO> orderItemDTOList = orderCreateDTO.getOrderItems();
        OrderRecipientDO orderRecipientDO = OrderRecipientConvert.INSTANCE.convert(orderCreateDTO);
        List<OrderItemDO> orderItemDOList = OrderItemConvert.INSTANCE.convert(orderItemDTOList);

        // TODO: 2019-03-24 sin 校验商品是否存在
//        for (OrderItemDO orderItemDO : orderItemDOList) {
//            CommonResult<ProductSpuDetailBO> result = productSpuService.getProductSpu(orderItemDO.getSkuId());
//
//            // 有任何商品获取失败，或者为 null，都直接返回失败。
//            if (result.isError()) {
//                return ServiceExceptionUtil.error(OrderErrorCodeEnum.ORDER_GET_SKU_FAIL.getCode());
//            }
//
//            if (result.getData() == null) {
//                return ServiceExceptionUtil.error(OrderErrorCodeEnum.ORDER_GET_SKU_NOT_EXISTENT.getCode());
//            }
//
//            ProductSpuDetailBO spuDetailBO = result.getData();
//            orderItemDO.setPrice(1000);
//        }

        // order
        OrderDO orderDO = new OrderDO()
                .setUserId(userId)
                .setOrderNo(UUID.randomUUID().toString().replace("-", ""))
                .setPayAmount(-1) // 先设置一个默认值，金额在下面计算
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

        // 收件人信息
        orderRecipientDO
                .setOrderId(orderDO.getId())
                .setType(OrderRecipientTypeEnum.EXPRESS.getValue())
                .setCreateTime(new Date())
                .setUpdateTime(null);

        orderRecipientMapper.insert(orderRecipientDO);

        // order item
        orderItemDOList.forEach(orderItemDO -> {
            int goodsPrice = 1000; // 商品单价
            orderItemDO
                    .setOrderId(orderDO.getId())
                    .setOrderNo(orderDO.getOrderNo())
                    .setPrice(goodsPrice)
                    .setPayAmount(orderItemDO.getQuantity() * orderItemDO.getPrice())
                    .setSkuName("夏季衣服-默认数据")
                    .setSkuImage("//img.alicdn.com/tps/i4/TB1TiGwKXXXXXXRXFXXqVMCNVXX-400-400.jpg_350x350q90.jpg_.webp")
                    .setPaymentTime(null)
                    .setDeliveryTime(null)
                    .setReceiverTime(null)
                    .setClosingTime(null)
                    .setHasReturnExchange(OrderStatusEnum.WAITING_PAYMENT.getValue())
                    .setDeliveryType(OrderDeliveryTypeEnum.NONE.getValue())
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
                        .setPayAmount(totalAmount)
        );

        // TODO: 2019-03-17 Sin 需要发送 创建成果 MQ 消息

        return CommonResult.success(
                new OrderCreateBO()
                        .setId(orderDO.getId())
                        .setOrderNo(orderDO.getOrderNo())
                        .setPayAmount(orderDO.getPayAmount())
        );
    }

    @Override
    public CommonResult updateOrderItem(OrderItemUpdateDTO orderUpdateDTO) {
        OrderItemDO orderItemDO = OrderItemConvert.INSTANCE.convert(orderUpdateDTO);
        orderItemMapper.updateById(orderItemDO);

        // TODO: 2019-03-24 sin 需要重新计算金额
        // TODO: 2019-03-24 sin 需要记录日志
        return CommonResult.success(null);
    }

    @Override
    @Transactional
    public CommonResult updateOrderItemPayAmount(Integer orderId, Integer orderItemId, Integer payAmount) {
        OrderDO orderDO = orderMapper.selectById(orderId);
        if (orderDO == null) {
            return ServiceExceptionUtil.error(OrderErrorCodeEnum.ORDER_NOT_EXISTENT.getCode());
        }
        if (payAmount < 0) {
            return ServiceExceptionUtil.error(OrderErrorCodeEnum.ORDER_PAY_AMOUNT_NOT_NEGATIVE.getCode());
        }

        // 先更新金额
        orderItemMapper.updateById(new OrderItemDO().setId(orderItemId).setPayAmount(payAmount));

        // 再重新计算订单金额
        List<OrderItemDO> orderItemDOList = orderItemMapper.selectByOrderIdAndDeleted(orderId, DeletedStatusEnum.DELETED_NO.getValue());
        Integer orderPayAmount = orderCommon.calculatedAmount(orderItemDOList);
        orderMapper.updateById(new OrderDO().setId(orderId).setPayAmount(orderPayAmount));
        return CommonResult.success(null);
    }

    @Override
    @Transactional
    public CommonResult cancelOrder(Integer orderId, Integer reason, String otherReason) {
        // 关闭订单，在用户还未付款的时候可操作
        OrderDO orderDO = orderMapper.selectById(orderId);
        if (orderDO == null) {
            return ServiceExceptionUtil.error(OrderErrorCodeEnum.ORDER_NOT_EXISTENT.getCode());
        }

        // 检查专题，只有待付款状态才能操作
        if (!orderDO.getStatus().equals(OrderStatusEnum.WAITING_PAYMENT.getValue())) {
            return ServiceExceptionUtil.error(OrderErrorCodeEnum.ORDER_STATUS_NOT_CANCEL.getCode());
        }

        OrderCancelDO orderCancelDO
                = (OrderCancelDO) new OrderCancelDO()
                .setOrderId(orderDO.getId())
                .setOrderNo(orderDO.getOrderNo())
                .setReason(reason)
                .setOtherReason(otherReason)
                .setCreateTime(new Date())
                .setUpdateTime(null);

        // 关闭订单，修改状态 item
        orderItemMapper.updateByOrderId(
                new OrderItemDO()
                        .setOrderId(orderId)
                        .setStatus(OrderStatusEnum.CLOSED.getValue())
        );

        // 关闭订单，修改状态 order
        orderMapper.updateById(new OrderDO().setId(orderId).setStatus(OrderStatusEnum.CLOSED.getValue()));
        // 保存取消订单原因
        orderCancelMapper.insert(orderCancelDO);
        return CommonResult.success(null);
    }

    @Override
    @Transactional
    public CommonResult orderDelivery(OrderDeliveryDTO orderDelivery) {
        List<Integer> orderItemIds = orderDelivery.getOrderItemIds();

        // 获取所有订单 items
        List<OrderItemDO> allOrderItems = orderItemMapper
                .selectByOrderIdAndDeleted(orderDelivery.getOrderId(), DeletedStatusEnum.DELETED_NO.getValue());

        // 当前需要发货订单，检查 id 和 status
        List<OrderItemDO> needDeliveryOrderItems = allOrderItems.stream()
                .filter(orderItemDO -> orderItemIds.contains(orderItemDO.getId())
                        && OrderStatusEnum.WAIT_SHIPMENT.getValue() == orderItemDO.getStatus())
                .collect(Collectors.toList());

        List<OrderItemDO> deliveredOrderItems = allOrderItems.stream()
                .filter(orderItemDO -> OrderStatusEnum.WAIT_SHIPMENT.getValue() == orderItemDO.getStatus())
                .collect(Collectors.toList());

        // 发货订单，检查
        if (needDeliveryOrderItems.size() != orderItemIds.size()) {
            return ServiceExceptionUtil.error(OrderErrorCodeEnum.ORDER_DELIVERY_INCORRECT_DATA.getCode());
        }

        OrderRecipientDO orderRecipientDO = orderRecipientMapper.selectByOrderId(orderDelivery.getOrderId());
        OrderLogisticsDO orderLogisticsDO = OrderLogisticsConvert.INSTANCE.convert(orderRecipientDO);

        // 保存物流信息
        orderLogisticsDO
                .setLogisticsNo(orderDelivery.getLogisticsNo())
                .setLogistics(orderDelivery.getLogistics())
                .setCreateTime(new Date())
                .setUpdateTime(null);

        orderLogisticsMapper.insert(orderLogisticsDO);

        // 关联订单item 和 物流信息
        orderItemMapper.updateByIds(
                orderItemIds,
                new OrderItemDO()
                        .setOrderLogisticsId(orderLogisticsDO.getId())
                        .setStatus(OrderStatusEnum.ALREADY_SHIPMENT.getValue())
        );

        // 子订单是否全部发货，如果发完，就更新 order
        if (deliveredOrderItems.size() <= 0) {
            orderMapper.updateById(
                    new OrderDO()
                            .setId(orderDelivery.getOrderId())
                            .setStatus(OrderStatusEnum.ALREADY_SHIPMENT.getValue())
            );
        }

        return CommonResult.success(null);
    }

    @Override
    public CommonResult updateOrderRemake(Integer orderId, String remake) {
        // 此处不做订单校验，直接设置备注即可
        orderMapper.updateById(new OrderDO().setId(orderId).setRemark(remake));
        return CommonResult.success(null);
    }

    @Override
    @Transactional
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
                        .setPayAmount(totalAmount)
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
