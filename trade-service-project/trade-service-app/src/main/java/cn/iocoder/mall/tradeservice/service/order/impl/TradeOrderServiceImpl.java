package cn.iocoder.mall.tradeservice.service.order.impl;

import cn.iocoder.common.framework.exception.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.util.CollectionUtils;
import cn.iocoder.common.framework.util.DateUtil;
import cn.iocoder.common.framework.util.MathUtil;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.payservice.rpc.transaction.dto.PayTransactionCreateReqDTO;
import cn.iocoder.mall.productservice.enums.sku.ProductSkuDetailFieldEnum;
import cn.iocoder.mall.productservice.rpc.sku.dto.ProductSkuRespDTO;
import cn.iocoder.mall.promotion.api.rpc.price.dto.PriceProductCalcReqDTO;
import cn.iocoder.mall.promotion.api.rpc.price.dto.PriceProductCalcRespDTO;
import cn.iocoder.mall.tradeservice.client.pay.PayTransactionClient;
import cn.iocoder.mall.tradeservice.client.product.ProductSkuClient;
import cn.iocoder.mall.tradeservice.client.promotion.CouponCardClient;
import cn.iocoder.mall.tradeservice.client.promotion.PriceClient;
import cn.iocoder.mall.tradeservice.client.user.UserAddressClient;
import cn.iocoder.mall.tradeservice.config.TradeBizProperties;
import cn.iocoder.mall.tradeservice.convert.order.TradeOrderConvert;
import cn.iocoder.mall.tradeservice.dal.mysql.dataobject.order.TradeOrderDO;
import cn.iocoder.mall.tradeservice.dal.mysql.dataobject.order.TradeOrderItemDO;
import cn.iocoder.mall.tradeservice.dal.mysql.mapper.order.TradeOrderItemMapper;
import cn.iocoder.mall.tradeservice.dal.mysql.mapper.order.TradeOrderMapper;
import cn.iocoder.mall.tradeservice.enums.logistics.LogisticsDeliveryTypeEnum;
import cn.iocoder.mall.tradeservice.enums.order.TradeOrderAfterSaleStatusEnum;
import cn.iocoder.mall.tradeservice.enums.order.TradeOrderDetailFieldEnum;
import cn.iocoder.mall.tradeservice.enums.order.TradeOrderStatusEnum;
import cn.iocoder.mall.tradeservice.rpc.order.dto.TradeOrderCreateReqDTO;
import cn.iocoder.mall.tradeservice.rpc.order.dto.TradeOrderPageReqDTO;
import cn.iocoder.mall.tradeservice.rpc.order.dto.TradeOrderRespDTO;
import cn.iocoder.mall.tradeservice.service.order.TradeOrderService;
import cn.iocoder.mall.userservice.rpc.address.dto.UserAddressRespDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static cn.iocoder.common.framework.util.CollectionUtils.convertSet;
import static cn.iocoder.mall.tradeservice.enums.OrderErrorCodeConstants.*;
import static cn.iocoder.mall.userservice.enums.UserErrorCodeConstants.USER_ADDRESS_NOT_FOUND;

/**
 * 交易订单 Service 实现
 */
@Service
public class TradeOrderServiceImpl implements TradeOrderService {

    @Autowired
    private TradeOrderMapper tradeOrderMapper;
    @Autowired
    private TradeOrderItemMapper tradeOrderItemMapper;

    @Autowired // 注入自己，用于调用事务方法
    private TradeOrderServiceImpl self;

    @Autowired
    private UserAddressClient userAddressClient;
    @Autowired
    private ProductSkuClient productSkuClient;
    @Autowired
    private PriceClient priceClient;
    @Autowired
    private CouponCardClient couponCardClient;
    @Autowired
    private PayTransactionClient payTransactionClient;

    @Autowired
    private TradeBizProperties tradeBizProperties;

    @Override
//    @GlobalTransactional TODO 芋艿，使用 seata 实现分布式事务
    public Integer createTradeOrder(TradeOrderCreateReqDTO createReqDTO) {
        // 获得收件地址
        UserAddressRespDTO userAddressRespDTO = userAddressClient.getUserAddress(createReqDTO.getUserAddressId(),
                createReqDTO.getUserId());
        if (userAddressRespDTO == null) {
            throw ServiceExceptionUtil.exception(USER_ADDRESS_NOT_FOUND);
        }
        // 获得商品信息
        List<ProductSkuRespDTO> listProductSkus = productSkuClient.listProductSkus(
                convertSet(createReqDTO.getOrderItems(), TradeOrderCreateReqDTO.OrderItem::getSkuId),
                ProductSkuDetailFieldEnum.SPU.getField());
        if (listProductSkus.size() != createReqDTO.getOrderItems().size()) { // 校验获得的数量，是否匹配
            throw ServiceExceptionUtil.exception(ORDER_GET_GOODS_INFO_INCORRECT);
        }
        // 价格计算
        PriceProductCalcRespDTO priceProductCalcRespDTO = priceClient.calcProductPrice(createReqDTO.getUserId(),
                createReqDTO.getOrderItems().stream().map(orderItem -> new PriceProductCalcReqDTO.Item().setSkuId(orderItem.getSkuId())
                        .setQuantity(orderItem.getQuantity()).setSelected(true)).collect(Collectors.toList()),
                createReqDTO.getCouponCardId());

        // TODO 芋艿，扣除库存

        // 标记优惠劵已使用
        if (createReqDTO.getCouponCardId() != null) {
            couponCardClient.useCouponCard(createReqDTO.getUserId(), createReqDTO.getCouponCardId());
        }

        // 创建交易订单（本地事务）
        TradeOrderDO tradeOrderDO = self.createTradeOrder0(createReqDTO, listProductSkus, priceProductCalcRespDTO, userAddressRespDTO);

        // 创建支付订单，对接支付服务
        createPayTransaction(tradeOrderDO, createReqDTO, listProductSkus);
        return tradeOrderDO.getId();
    }

    @Transactional
    public TradeOrderDO createTradeOrder0(TradeOrderCreateReqDTO createReqDTO, List<ProductSkuRespDTO> listProductSkus,
                                     PriceProductCalcRespDTO priceProductCalcRespDTO, UserAddressRespDTO userAddressRespDTO) {
        // 构建 TradeOrderDO 对象，并进行保存
        TradeOrderDO tradeOrderDO = new TradeOrderDO();
        // 1. 基本信息
        tradeOrderDO.setUserId(createReqDTO.getUserId()).setOrderNo(generateTradeOrderNo())
                .setOrderStatus(TradeOrderStatusEnum.WAITING_PAYMENT.getValue()).setRemark(createReqDTO.getRemark());
        // 2. 价格 + 支付基本信息
        tradeOrderDO.setBuyPrice(priceProductCalcRespDTO.getFee().getBuyTotal())
                .setDiscountPrice(priceProductCalcRespDTO.getFee().getDiscountTotal())
                .setLogisticsPrice(priceProductCalcRespDTO.getFee().getPostageTotal())
                .setPresentPrice(priceProductCalcRespDTO.getFee().getPresentTotal())
                .setPayPrice(0).setRefundPrice(0);
        // 3. 收件 + 物流基本信息
        tradeOrderDO.setDeliveryType(LogisticsDeliveryTypeEnum.EXPRESS.getDeliveryType())
                .setReceiverName(userAddressRespDTO.getName()).setReceiverMobile(userAddressRespDTO.getMobile())
                .setReceiverAreaCode(userAddressRespDTO.getAreaCode()).setReceiverDetailAddress(userAddressRespDTO.getDetailAddress());
        // 4. 售后基本信息
        tradeOrderDO.setAfterSaleStatus(TradeOrderAfterSaleStatusEnum.NULL.getStatus());
        // 5. 营销基本信息
        tradeOrderDO.setCouponCardId(createReqDTO.getCouponCardId());
        // 最终保存
        tradeOrderMapper.insert(tradeOrderDO);

        // 创建 TradeOrderItemDO 数组，并进行保存
        List<TradeOrderItemDO> tradeOrderItemDOs = new ArrayList<>(listProductSkus.size());
        Map<Integer, ProductSkuRespDTO> listProductSkuMap = CollectionUtils.convertMap(listProductSkus, ProductSkuRespDTO::getId);
        Map<Integer, PriceProductCalcRespDTO.Item> priceItemMap = new HashMap<>(); // 商品 SKU 价格的映射
        priceProductCalcRespDTO.getItemGroups().forEach(itemGroup ->
                itemGroup.getItems().forEach(item -> priceItemMap.put(item.getSkuId(), item)));
        for (TradeOrderCreateReqDTO.OrderItem orderItem : createReqDTO.getOrderItems()) {
            TradeOrderItemDO tradeOrderItemDO = new TradeOrderItemDO();
            tradeOrderItemDOs.add(tradeOrderItemDO);
            // 1. 基本信息
            tradeOrderItemDO.setOrderId(tradeOrderDO.getId()).setStatus(tradeOrderDO.getOrderStatus());
            // 2. 商品基本信息
            ProductSkuRespDTO productSkuRespDTO = listProductSkuMap.get(orderItem.getSkuId());
            tradeOrderItemDO.setSpuId(productSkuRespDTO.getSpuId()).setSkuId(productSkuRespDTO.getId())
                    .setSkuName(productSkuRespDTO.getSpu().getName())
                    .setSkuImage(CollectionUtils.getFirst(productSkuRespDTO.getSpu().getPicUrls()))
                    .setQuantity(orderItem.getQuantity());
            // 3. 价格 + 支付基本信息
            PriceProductCalcRespDTO.Item priceItem = priceItemMap.get(orderItem.getSkuId());
            tradeOrderItemDO.setOriginPrice(priceItem.getOriginPrice()).setBuyPrice(priceItem.getBuyPrice())
                    .setPresentPrice(priceItem.getPresentPrice()).setBuyTotal(priceItem.getBuyTotal())
                    .setDiscountTotal(priceItem.getDiscountTotal()).setPresentTotal(priceItem.getPresentTotal())
                    .setRefundTotal(0);
            // 4. 物流基本信息
            // 5. 售后基本信息
            tradeOrderItemDO.setAfterSaleStatus(TradeOrderAfterSaleStatusEnum.NULL.getStatus());
        }
        // 最终保存
        tradeOrderItemMapper.insertList(tradeOrderItemDOs);

        return tradeOrderDO;
    }

    private void createPayTransaction(TradeOrderDO tradeOrderDO, TradeOrderCreateReqDTO createReqDTO,
                                      List<ProductSkuRespDTO> listProductSkus) {
        // 创建支付单
        String orderSubject = listProductSkus.get(0).getSpu().getName();
        Date expireTime = DateUtil.addDate(Calendar.MINUTE, tradeBizProperties.getPayExpireTime());
        Integer payTransactionId = payTransactionClient.createPayTransaction(
                new PayTransactionCreateReqDTO().setUserId(createReqDTO.getUserId())
                        .setCreateIp(createReqDTO.getIp()).setAppId(tradeBizProperties.getPayAppId())
                        .setOrderId(tradeOrderDO.getId().toString()).setExpireTime(expireTime)
                        .setPrice(tradeOrderDO.getPresentPrice()).setOrderSubject(orderSubject)
                        .setOrderMemo("测试备注") // TODO 芋艿，后面补充
                        .setOrderDescription("测试描述") // TODO 芋艿，后面补充
        );

        // 更新
        tradeOrderMapper.updateById(new TradeOrderDO().setId(tradeOrderDO.getId()).setPayTransactionId(payTransactionId));
    }

    private String generateTradeOrderNo() {
//    wx
//    2014
//    10
//    27
//    20
//    09
//    39
//    5522657
//    a690389285100
        // 目前的算法
        // 时间序列，年月日时分秒 14 位
        // 纯随机，6 位 TODO 此处估计是会有问题的，后续在调整
        return DateUtil.format(new Date(), "yyyyMMddHHmmss") + // 时间序列
                MathUtil.random(100000, 999999) // 随机。为什么是这个范围，因为偷懒
                ;
    }

    @Override
    public TradeOrderRespDTO getTradeOrder(Integer tradeOrderId, Collection<String> fields) {
        // 查询交易订单
        TradeOrderDO tradeOrderDO = tradeOrderMapper.selectById(tradeOrderId);
        if (tradeOrderDO == null) {
            return null;
        }
        TradeOrderRespDTO tradeOrderRespDTO = TradeOrderConvert.INSTANCE.convert(tradeOrderDO);
        // 查询交易订单项
        if (fields.contains(TradeOrderDetailFieldEnum.ITEM.getField())) {
            List<TradeOrderItemDO> tradeOrderItemDOs = tradeOrderItemMapper.selectListByOrderIds(
                    Collections.singleton(tradeOrderDO.getId()));
            tradeOrderRespDTO.setOrderItems(TradeOrderConvert.INSTANCE.convertList(tradeOrderItemDOs));
        }
        // 返回
        return tradeOrderRespDTO;
    }

    @Override
    public PageResult<TradeOrderRespDTO> pageTradeOrder(TradeOrderPageReqDTO pageReqDTO) {
        // 查询交易订单分页
        IPage<TradeOrderDO> tradeOrderDOPage = tradeOrderMapper.selectPage(pageReqDTO);
        PageResult<TradeOrderRespDTO> pageResult = TradeOrderConvert.INSTANCE.convertPage(tradeOrderDOPage);
        if (CollectionUtils.isEmpty(pageResult.getList())) {
            return pageResult;
        }
        // 查询交易订单项们
        if (pageReqDTO.getFields().contains(TradeOrderDetailFieldEnum.ITEM.getField())) {
            List<TradeOrderItemDO> tradeOrderItemDOs = tradeOrderItemMapper.selectListByOrderIds(
                    convertSet(tradeOrderDOPage.getRecords(), TradeOrderDO::getId));
            Map<Integer, List<TradeOrderItemDO>> tradeOrderItemDOMultiMap = CollectionUtils.convertMultiMap(
                    tradeOrderItemDOs, TradeOrderItemDO::getOrderId);
            pageResult.getList().forEach(tradeOrderRespDTO -> tradeOrderRespDTO.setOrderItems(
                    TradeOrderConvert.INSTANCE.convertList(tradeOrderItemDOMultiMap.get(tradeOrderRespDTO.getId()))));
        }
        // 返回
        return pageResult;
    }


    @Override
    @Transactional
    public void updateTradeOrderPaySuccess(Integer tradeOrderId, Integer payAmount) {
//        if (true) {
//            throw new IllegalArgumentException("测试失败的情况");
//        }
        // 校验交易订单，是否可以
        TradeOrderDO tradeOrderDO = tradeOrderMapper.selectById(tradeOrderId);
        if (tradeOrderDO == null) { // 订单不存在
            throw ServiceExceptionUtil.exception(ORDER_NOT_EXISTENT);
        }
        if (!tradeOrderDO.getOrderStatus().equals(TradeOrderStatusEnum.WAITING_PAYMENT.getValue())) { // 状态不处于等待支付
            throw ServiceExceptionUtil.exception(ORDER_STATUS_NOT_WAITING_PAYMENT);
        }
        if (!tradeOrderDO.getPresentPrice().equals(payAmount)) { // 支付金额不正确
            throw ServiceExceptionUtil.exception(ORDER_PAY_AMOUNT_ERROR);
        }

        // 更新 TradeOrderDO 状态为已支付，等待发货
        TradeOrderDO updateOrderObj = new TradeOrderDO().setId(tradeOrderId)
                .setOrderStatus(TradeOrderStatusEnum.WAIT_SHIPMENT.getValue())
                .setPayPrice(payAmount)
                .setPayTime(new Date());
        int updateCount = tradeOrderMapper.update(updateOrderObj, TradeOrderStatusEnum.WAITING_PAYMENT.getValue());
        if (updateCount <= 0) {
            throw ServiceExceptionUtil.exception(ORDER_STATUS_NOT_WAITING_PAYMENT);
        }

        // 更新 TradeOrderItemDO 状态为已支付，等待发货
        TradeOrderItemDO updateOrderItemObj = new TradeOrderItemDO()
                .setStatus(TradeOrderStatusEnum.WAIT_SHIPMENT.getValue());
        tradeOrderItemMapper.updateListByOrderId(updateOrderItemObj, tradeOrderId,
                TradeOrderStatusEnum.WAITING_PAYMENT.getValue());
    }

}
