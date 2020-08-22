package cn.iocoder.mall.order.biz.service;

import cn.iocoder.common.framework.enums.DeletedStatusEnum;
import cn.iocoder.common.framework.util.DateUtil;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.order.api.OrderService;
import cn.iocoder.mall.order.api.bo.*;
import cn.iocoder.mall.order.api.constant.*;
import cn.iocoder.mall.order.api.dto.*;
import cn.iocoder.mall.order.biz.constants.OrderDeliveryTypeEnum;
import cn.iocoder.mall.order.biz.constants.OrderRecipientTypeEnum;
import cn.iocoder.mall.order.biz.convert.*;
import cn.iocoder.mall.order.biz.dao.*;
import cn.iocoder.mall.order.biz.dataobject.*;
import cn.iocoder.mall.pay.api.PayTransactionService;
import cn.iocoder.mall.pay.api.bo.transaction.PayTransactionBO;
import cn.iocoder.mall.pay.api.dto.transaction.PayTransactionCreateDTO;
import cn.iocoder.mall.product.api.ProductSpuService;
import cn.iocoder.mall.product.api.bo.ProductSkuDetailBO;
import cn.iocoder.mall.promotion.api.CouponService;
import cn.iocoder.mall.user.api.UserAddressService;
import cn.iocoder.mall.user.api.bo.UserAddressBO;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.Reference;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
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
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.OrderService.version}")
public class OrderServiceImpl implements OrderService {

    /**
     * 支付过期时间 120 分钟
     */
    public static final int PAY_EXPIRE_TIME = 120;

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private OrderLogisticsMapper orderLogisticsMapper;
    @Autowired
    private OrderLogisticsDetailMapper orderLogisticsDetailMapper;
    @Autowired
    private OrderRecipientMapper orderRecipientMapper;
    @Autowired
    private OrderCancelMapper orderCancelMapper;
    @Autowired
    private OrderReturnMapper orderReturnMapper;

    @Autowired
    private CartServiceImpl cartService;

    @Reference(validation = "true", version = "${dubbo.consumer.PromotionActivityService.version}")
    private ProductSpuService productSpuService;
    @Reference(validation = "true", version = "${dubbo.consumer.UserAddressService.version}")
    private UserAddressService userAddressService;
    @Reference(validation = "true", version = "${dubbo.consumer.PayTransactionService.version}")
    private PayTransactionService payTransactionService;
    @Reference(validation = "true", version = "${dubbo.consumer.CouponService.version}")
    private CouponService couponService;

    @Override
    public CommonResult<OrderPageBO> getOrderPage(OrderQueryDTO orderQueryDTO) {

        int totalCount = orderMapper.selectPageCount(orderQueryDTO);
        if (totalCount == 0) { // TODO FROM 芋艿 TO 小范 Collections.EMPTY_LIST 改成 Collections.emptyList()
            return CommonResult.success(new OrderPageBO().setOrders(Collections.EMPTY_LIST).setTotal(0));
        }

        // 获取订单数据
        List<OrderDO> orderDOList = orderMapper.selectPage(orderQueryDTO);

        if (CollectionUtils.isEmpty(orderDOList)) {
            return CommonResult.success(new OrderPageBO().setOrders(Collections.EMPTY_LIST).setTotal(totalCount));
        }

        // 获取订单 id
        Set<Integer> orderIds = orderDOList.stream()
                .map(orderDO -> orderDO.getId()) // TODO FROM 芋艿 to 小范，记得用 Lambda
                .collect(Collectors.toSet());

        // 获取配送信息
        List<OrderRecipientDO> orderRecipientDOList = orderRecipientMapper.selectByOrderIds(orderIds);
        List<OrderRecipientBO> orderRecipientBOList = OrderRecipientConvert.INSTANCE.convert(orderRecipientDOList);
        Map<Integer, OrderRecipientBO> orderRecipientBOMap
                = orderRecipientBOList.stream().collect(Collectors.toMap(OrderRecipientBO::getOrderId, obj -> obj));

        // 获取 订单的 items
        List<OrderItemDO> orderItemDOList = orderItemMapper
                .selectByDeletedAndOrderIds(orderIds, DeletedStatusEnum.DELETED_NO.getValue());

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
                .selectByDeletedAndOrderId(DeletedStatusEnum.DELETED_NO.getValue(), orderId);

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
    public CommonResult<OrderInfoBO> info(Integer userId, Integer orderId) {
        OrderDO orderDO = orderMapper.selectById(orderId);
        if (orderDO == null) {
            return ServiceExceptionUtil.error(OrderErrorCodeEnum.ORDER_NOT_EXISTENT.getCode());
        }

        List<OrderItemDO> itemDOList = orderItemMapper
                .selectByDeletedAndOrderId(DeletedStatusEnum.DELETED_NO.getValue(), orderId);

        List<OrderInfoBO.OrderItem> orderItems
                = OrderItemConvert.INSTANCE.convertOrderInfoWithOrderItem(itemDOList);

        Set<Integer> orderLogisticsIds = itemDOList.stream()
                .filter(o -> o.getOrderLogisticsId() != null)
                .map(o -> o.getOrderLogisticsId())
                .collect(Collectors.toSet());



        // 收件人信息
        OrderRecipientDO orderRecipientDO = orderRecipientMapper.selectByOrderId(orderId);

        // 订单物流信息
        OrderLogisticsDetailDO orderLogisticsDetailDO = null;
        if (!CollectionUtils.isEmpty(orderLogisticsIds)) {
            orderLogisticsDetailDO = orderLogisticsDetailMapper.selectLast(orderLogisticsIds);
        }

        // 检查是否申请退货
        OrderReturnDO orderReturnDO = orderReturnMapper.selectByOrderId(orderId);

        // convert 信息
        OrderInfoBO.LogisticsDetail logisticsDetail
                = OrderLogisticsDetailConvert.INSTANCE.convertLogisticsDetail(orderLogisticsDetailDO);

        OrderInfoBO.Recipient recipient = OrderRecipientConvert.INSTANCE.convertOrderInfoRecipient(orderRecipientDO);
        OrderInfoBO orderInfoBO = OrderConvert.INSTANCE.convert(orderDO);
        orderInfoBO.setRecipient(recipient);
        orderInfoBO.setOrderItems(orderItems);
        orderInfoBO.setLatestLogisticsDetail(logisticsDetail);

        // 是否退货
        if (orderReturnDO != null) {
            orderInfoBO.setHasOrderReturn(orderReturnDO.getStatus());
        } else {
            orderInfoBO.setHasOrderReturn(-1);
        }
        return CommonResult.success(orderInfoBO);
    }

    @Override
    @GlobalTransactional
//    @Transactional // 使用 Seata 分布事务后，无需在使用 @Transactional 注解。
    public CommonResult<OrderCreateBO> createOrder(OrderCreateDTO orderCreateDTO) {
        Integer userId = orderCreateDTO.getUserId();
        List<OrderCreateDTO.OrderItem> orderItemDTOList = orderCreateDTO.getOrderItems();
        List<OrderItemDO> orderItemDOList = OrderItemConvert.INSTANCE.convert(orderItemDTOList);

        // 获取商品信息
        Set<Integer> skuIds = orderItemDOList.stream().map(OrderItemDO::getSkuId).collect(Collectors.toSet());
        List<ProductSkuDetailBO> productList = productSpuService.getProductSkuDetailList(skuIds);
        if (orderItemDTOList.size() != productList.size()) { // 校验获得的数量，是否匹配
            return ServiceExceptionUtil.error(OrderErrorCodeEnum.ORDER_GET_GOODS_INFO_INCORRECT.getCode());
        }

        // 价格计算
        CalcOrderPriceBO calcOrderPrice = calcOrderPrice(productList, orderCreateDTO);

        // 设置 orderItem
        Map<Integer, ProductSkuDetailBO> productSpuBOMap = productList
                .stream().collect(Collectors.toMap(ProductSkuDetailBO::getId, o -> o)); // 商品 SKU 信息的集合
        Map<Integer, CalcOrderPriceBO.Item> priceItemMap = new HashMap<>(); // 商品 SKU 价格的映射
        calcOrderPrice.getItemGroups().forEach(itemGroup ->
                itemGroup.getItems().forEach(item -> priceItemMap.put(item.getId(), item)));
        // 遍历 orderItemDOList 数组，将商品信息、商品价格，设置到其中
        for (OrderItemDO orderItemDO : orderItemDOList) {
            ProductSkuDetailBO productSkuDetailBO = productSpuBOMap.get(orderItemDO.getSkuId());
            if (productSkuDetailBO.getQuantity() <= 0) {
                return ServiceExceptionUtil.error(OrderErrorCodeEnum.ORDER_INSUFFICIENT_INVENTORY.getCode());
            }
            if (productSkuDetailBO.getPrice() <= 0) {
                return ServiceExceptionUtil.error(OrderErrorCodeEnum.ORDER_GOODS_AMOUNT_INCORRECT.getCode());
            }
            // 设置 SKU 信息
            orderItemDO.setSkuImage(Optional.ofNullable(productSkuDetailBO.getSpu().getPicUrls().get(0)).get());
            orderItemDO.setSkuName(productSkuDetailBO.getSpu().getName());
            // 设置价格信息
            CalcOrderPriceBO.Item priceItem = priceItemMap.get(orderItemDO.getSkuId());
            Assert.notNull(priceItem, "商品计算价格为空");
            orderItemDO.setOriginPrice(priceItem.getOriginPrice())
                .setBuyPrice(priceItem.getBuyPrice())
                .setPresentPrice(priceItem.getPresentPrice())
                .setBuyTotal(priceItem.getBuyTotal())
                .setDiscountTotal(priceItem.getDiscountTotal())
                .setPresentTotal(priceItem.getPresentTotal());
        }

        // 标记优惠劵已使用
        if (orderCreateDTO.getCouponCardId() != null) {
            couponService.useCouponCard(userId, orderCreateDTO.getCouponCardId());
        }

        // TODO 芋艿，扣除库存

        // order

        // TODO: 2019-04-11 Sin 订单号需要生成规则
        // TODO FROM 芋艿 to 小范：可以考虑抽象成一个方法，下面几个也是。
        String orderNo = UUID.randomUUID().toString().replace("-", "").substring(0, 16);
//        Integer totalAmount = orderCommon.calculatedAmount(orderItemDOList);
//        Integer totalPrice = orderCommon.calculatedPrice(orderItemDOList);
//        Integer totalLogisticsPrice = orderCommon.calculatedLogisticsPrice(orderItemDOList);
        OrderDO orderDO = new OrderDO()
                .setUserId(userId)
                .setOrderNo(orderNo)
                .setBuyPrice(calcOrderPrice.getFee().getBuyTotal())
                .setDiscountPrice(calcOrderPrice.getFee().getDiscountTotal())
                .setLogisticsPrice(calcOrderPrice.getFee().getPostageTotal())
                .setPresentPrice(calcOrderPrice.getFee().getPresentTotal())
                .setPayAmount(0)
                .setClosingTime(null)
                .setDeliveryTime(null)
                .setPaymentTime(null)
                .setStatus(OrderStatusEnum.WAITING_PAYMENT.getValue())
                .setHasReturnExchange(OrderHasReturnExchangeEnum.NO.getValue())
                .setRemark(Optional.ofNullable(orderCreateDTO.getRemark()).orElse(""));
        orderDO.setDeleted(DeletedStatusEnum.DELETED_NO.getValue());
//        orderDO.setCreateTime(new Date());
//        orderDO.setUpdateTime(null);
        orderMapper.insert(orderDO);

        // 收件人信息
        CommonResult<UserAddressBO> userAddressResult = userAddressService.getAddress(userId, orderCreateDTO.getUserAddressId());
        if (userAddressResult.isError()) {
            return ServiceExceptionUtil.error(OrderErrorCodeEnum.ORDER_GET_USER_ADDRESS_FAIL.getCode());
        }
        UserAddressBO userAddressBO = userAddressResult.getData();
        OrderRecipientDO orderRecipientDO = OrderRecipientConvert.INSTANCE.convert(userAddressBO);
        orderRecipientDO
                .setOrderId(orderDO.getId())
                .setType(OrderRecipientTypeEnum.EXPRESS.getValue())
                .setCreateTime(new Date())
                .setUpdateTime(null);
        orderRecipientMapper.insert(orderRecipientDO);

        // order item
        orderItemDOList.forEach(orderItemDO -> {
            orderItemDO
                    .setOrderId(orderDO.getId())
                    .setOrderNo(orderDO.getOrderNo())
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
        });
        // 一次性插入
        orderItemMapper.insert(orderItemDOList);

        // 创建预订单
        createPayTransaction(orderDO, orderItemDOList, orderCreateDTO.getIp());

//        if (commonResult.isError()) {
//            //手动开启事务回滚
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//            return ServiceExceptionUtil.error(OrderErrorCodeEnum.ORDER_GET_PAY_FAIL.getValue());
//        }

        // TODO: 2019-03-17 Sin 需要发送 创建成果 MQ 消息，业务扩展和统计
        return CommonResult.success(
                new OrderCreateBO()
                        .setId(orderDO.getId())
                        .setOrderNo(orderDO.getOrderNo())
                        .setPayAmount(orderDO.getPayAmount())
        );
    }

    private CalcOrderPriceBO calcOrderPrice(List<ProductSkuDetailBO> skus, OrderCreateDTO orderCreateDTO) {
        // 创建计算的 DTO
        CalcOrderPriceDTO calcOrderPriceDTO = new CalcOrderPriceDTO()
                .setUserId(orderCreateDTO.getUserId())
                .setItems(new ArrayList<>(skus.size()))
                .setCouponCardId(orderCreateDTO.getCouponCardId());
        for (OrderCreateDTO.OrderItem item : orderCreateDTO.getOrderItems()) {
            calcOrderPriceDTO.getItems().add(new CalcOrderPriceDTO.Item(item.getSkuId(), item.getQuantity(), true));
        }
        // 执行计算
        return cartService.calcOrderPrice(calcOrderPriceDTO);
    }

    private PayTransactionBO createPayTransaction(OrderDO order, List<OrderItemDO> orderItems, String ip) {
        // TODO sin 支付订单 orderSubject 暂时取第一个子订单商品信息
        String orderSubject = orderItems.get(0).getSkuName();
        Date expireTime = DateUtil.addDate(Calendar.MINUTE, PAY_EXPIRE_TIME);
        return payTransactionService.createTransaction(
                new PayTransactionCreateDTO()
                        .setCreateIp(ip)
                        .setAppId(PayAppId.APP_ID_SHOP_ORDER)
                        .setOrderId(order.getId().toString())
                        .setExpireTime(expireTime)
                        .setPrice(order.getPresentPrice())
                        .setOrderSubject(orderSubject)
                        .setOrderMemo("测试备注") // TODO 芋艿，后面补充
                        .setOrderDescription("测试描述") // TODO 芋艿，后面补充
        );
    }

    @Override // TODO 芋艿，需要确认下这个方法的用途。因为涉及修改价格和数量。
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
        orderItemMapper.updateById(new OrderItemDO().setId(orderItemId)
//                .setPayAmount(payAmount) TODO 芋艿，这里要修改
        );

        // 再重新计算订单金额
        List<OrderItemDO> orderItemDOList = orderItemMapper
                .selectByDeletedAndOrderId(DeletedStatusEnum.DELETED_NO.getValue(), orderId);
//        Integer price = orderCommon.calculatedPrice(orderItemDOList);
//        Integer amount = orderCommon.calculatedAmount(orderItemDOList);
        Integer price = -1; // TODO 芋艿，这里要修改，价格
        Integer amount = -1;
        orderMapper.updateById(
                new OrderDO()
                        .setId(orderId)
//                        .setPrice(price) TODO 芋艿，这里要修改
                        .setPayAmount(amount)
        );
        return CommonResult.success(null);
    }

    @Override
    @Transactional // TODO 芋艿，要校验下 userId 。不然可以取消任何用户的订单列。
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
        // TODO FROM 芋艿 TO 小范，更新的时候，where 里面带下 status 避免并发的问题
        orderItemMapper.updateByOrderId(
                orderId,
                new OrderItemDO().setStatus(OrderStatusEnum.CLOSED.getValue())
        );

        // 关闭订单，修改状态 order
        orderMapper.updateById(new OrderDO().setId(orderId).setStatus(OrderStatusEnum.CLOSED.getValue()));
        // 保存取消订单原因
        orderCancelMapper.insert(orderCancelDO);
        return CommonResult.success(null);
    }

    @Override
    @Transactional // TODO FROM 芋艿 TO 小范：泛型，一定要明确哈。
    public CommonResult orderDelivery(OrderDeliveryDTO orderDelivery) {
        List<Integer> orderItemIds = orderDelivery.getOrderItemIds();

        // 获取所有订单 items // TODO FROM 芋艿 TO 小范，deleted 是默认条件，所以 by 里面可以不带哈
        List<OrderItemDO> allOrderItems = orderItemMapper.selectByDeletedAndOrderId(orderDelivery.getOrderId(), DeletedStatusEnum.DELETED_NO.getValue());

        // 当前需要发货订单，检查 id 和 status
        List<OrderItemDO> needDeliveryOrderItems = allOrderItems.stream()
                .filter(orderItemDO -> orderItemIds.contains(orderItemDO.getId())
                        && OrderStatusEnum.WAIT_SHIPMENT.getValue() == orderItemDO.getStatus())
                .collect(Collectors.toList()); // TODO 芋艿，如果这里只是比对数字，可以用 Lambda 求和，不需要弄成一个集合的
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
        // TODO FROM 芋艿 TO 小范，更新的时候，where 里面带下 status 避免并发的问题，然后判断下更新数量，不对，就抛出异常。
        orderItemMapper.updateByIds(
                orderItemIds,
                new OrderItemDO()
                        .setOrderLogisticsId(orderLogisticsDO.getId())
                        .setStatus(OrderStatusEnum.ALREADY_SHIPMENT.getValue())
        );

        // 子订单是否全部发货，如果发完，就更新 order
        List<OrderItemDO> unShippedOrderItems = allOrderItems.stream()
                .filter(orderItemDO -> OrderStatusEnum.WAIT_SHIPMENT.getValue() == orderItemDO.getStatus()
                        && !orderItemIds.contains(orderItemDO.getId()))
                .collect(Collectors.toList());
        if (unShippedOrderItems.size() <= 0) {
            // TODO FROM 芋艿 TO 小范，更新的时候，where 里面带下 status 避免并发的问题
            orderMapper.updateById(
                    new OrderDO()
                            .setId(orderDelivery.getOrderId())
                            .setStatus(OrderStatusEnum.ALREADY_SHIPMENT.getValue())
            );
        }
        // 返回成功
        return CommonResult.success(null);
    }

    @Override
    public CommonResult updateOrderRemake(Integer orderId, String remake) {
        // 此处不做订单校验，直接设置备注即可
        orderMapper.updateById(new OrderDO().setId(orderId).setRemark(remake));
        return CommonResult.success(null);
    }

    @Override
    @Transactional // TODO FROM 芋艿 to 小范，先不做这个功能，电商一班不存在这个功能哈。
    public CommonResult deleteOrderItem(OrderItemDeletedDTO orderItemDeletedDTO) {
        Integer orderId = orderItemDeletedDTO.getOrderId();
        List<Integer> orderItemIds = orderItemDeletedDTO.getOrderItemIds();

        // 获取当前有效的订单 item
        List<OrderItemDO> orderItemDOList = orderItemMapper
                .selectByDeletedAndOrderId(DeletedStatusEnum.DELETED_NO.getValue(), orderId);

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
//        Integer totalAmount = orderCommon.calculatedAmount(effectiveOrderItems);
        Integer totalAmount = -1; // TODO 芋艿，需要修改下，价格相关
        orderMapper.updateById(
                new OrderDO()
                        .setId(orderId)
                        .setPayAmount(totalAmount)
        );
        return CommonResult.success(null);
    }

    @Override
    public CommonResult confirmReceiving(Integer userId, Integer orderId) {
        OrderDO orderDO = orderMapper.selectById(orderId);

        // 是否该用户的订单
        if (!userId.equals(orderDO.getUserId())) {
            return ServiceExceptionUtil.error(OrderErrorCodeEnum.ORDER_NOT_USER_ORDER.getCode());
        }

        if (OrderStatusEnum.ALREADY_SHIPMENT.getValue() != orderDO.getStatus()) {
            return ServiceExceptionUtil.error(OrderErrorCodeEnum.ORDER_UNABLE_CONFIRM_ORDER.getCode());
        }

        // TODO FROM 芋艿 TO 小范，更新的时候，where 里面带下 status 避免并发的问题
        orderMapper.updateById(
                new OrderDO()
                        .setId(orderId)
                        .setReceiverTime(new Date())
                        .setStatus(OrderStatusEnum.COMPLETED.getValue())

        );

        orderItemMapper.updateByOrderId(
                orderId,
                new OrderItemDO()
                        .setStatus(OrderStatusEnum.COMPLETED.getValue())
                        .setReceiverTime(new Date())
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
    public String updatePaySuccess(String orderId, Integer payAmount) {
        OrderDO order = orderMapper.selectById(Integer.valueOf(orderId));
        if (order == null) { // 订单不存在
            return ServiceExceptionUtil.error(OrderErrorCodeEnum.ORDER_NOT_EXISTENT.getCode()).getMessage();
        }
        if (!order.getStatus().equals(OrderStatusEnum.WAITING_PAYMENT.getValue())) { // 状态不处于等待支付
            return ServiceExceptionUtil.error(OrderErrorCodeEnum.ORDER_STATUS_NOT_WAITING_PAYMENT.getCode()).getMessage();
        }
        if (!order.getPresentPrice().equals(payAmount)) { // 支付金额不正确
            return ServiceExceptionUtil.error(OrderErrorCodeEnum.ORDER_PAY_AMOUNT_ERROR.getCode()).getMessage();
        }
        // 更新 OrderDO 状态为已支付，等待发货
        OrderDO updateOrderObj = new OrderDO()
                .setStatus(OrderStatusEnum.WAIT_SHIPMENT.getValue())
                .setPayAmount(payAmount)
                .setPaymentTime(new Date());
        int updateCount = orderMapper.updateByIdAndStatus(order.getId(), order.getStatus(), updateOrderObj);
        if (updateCount <= 0) {
            return ServiceExceptionUtil.error(OrderErrorCodeEnum.ORDER_STATUS_NOT_WAITING_PAYMENT.getCode()).getMessage();
        }
        // TODO FROM 芋艿 to 小范，把更新 OrderItem 给补全。
        return "success";
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
