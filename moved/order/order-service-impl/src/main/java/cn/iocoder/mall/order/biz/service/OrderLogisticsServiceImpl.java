package cn.iocoder.mall.order.biz.service;

import cn.iocoder.common.framework.enums.DeletedStatusEnum;
import cn.iocoder.common.framework.util.DateUtil;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.order.api.OrderLogisticsService;
import cn.iocoder.mall.order.api.bo.OrderLastLogisticsInfoBO;
import cn.iocoder.mall.order.api.bo.OrderLogisticsInfoBO;
import cn.iocoder.mall.order.api.bo.OrderLogisticsInfoWithOrderBO;
import cn.iocoder.mall.order.api.constant.OrderErrorCodeEnum;
import cn.iocoder.mall.order.biz.convert.OrderLogisticsConvert;
import cn.iocoder.mall.order.biz.dao.OrderItemMapper;
import cn.iocoder.mall.order.biz.dao.OrderLogisticsDetailMapper;
import cn.iocoder.mall.order.biz.dao.OrderLogisticsMapper;
import cn.iocoder.mall.order.biz.dao.OrderMapper;
import cn.iocoder.mall.order.biz.dataobject.OrderDO;
import cn.iocoder.mall.order.biz.dataobject.OrderItemDO;
import cn.iocoder.mall.order.biz.dataobject.OrderLogisticsDO;
import cn.iocoder.mall.order.biz.dataobject.OrderLogisticsDetailDO;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 订单物流
 *
 * @author Sin
 * @time 2019-04-12 21:32
 */
@Service
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.OrderLogisticsService.version}")
public class OrderLogisticsServiceImpl implements OrderLogisticsService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private OrderLogisticsMapper orderLogisticsMapper;
    @Autowired
    private OrderLogisticsDetailMapper orderLogisticsDetailMapper;

    @Override
    public CommonResult<OrderLogisticsInfoBO> getLogisticsInfo(Integer id) {
        OrderLogisticsDO orderLogisticsDO = orderLogisticsMapper.selectById(id);
        if (orderLogisticsDO == null) {
            return CommonResult.success(null);
        }

        List<OrderLogisticsDetailDO> orderLogisticsDetailDOList = orderLogisticsDetailMapper
                .selectByOrderLogisticsId(orderLogisticsDO.getId());

        // 转换数据结构
        List<OrderLogisticsInfoBO.LogisticsDetail> logisticsDetails
                = OrderLogisticsConvert.INSTANCE.convert(orderLogisticsDetailDOList);

        OrderLogisticsInfoBO orderLogisticsInfo2BO = OrderLogisticsConvert.INSTANCE.convert(orderLogisticsDO);
        orderLogisticsInfo2BO.setDetails(logisticsDetails);
        return CommonResult.success(orderLogisticsInfo2BO);
    }

    @Override
    public CommonResult<OrderLastLogisticsInfoBO> getLastLogisticsInfo(Integer id) {
        OrderLogisticsDO orderLogisticsDO = orderLogisticsMapper.selectById(id);
        if (orderLogisticsDO == null) {
            return CommonResult.success(null);
        }

        OrderLogisticsDetailDO orderLastLogisticsDetailDO = orderLogisticsDetailMapper.selectLastByLogisticsId(id);

        // 转换数据结构
        OrderLastLogisticsInfoBO.LogisticsDetail lastLogisticsDetail
                = OrderLogisticsConvert.INSTANCE.convertLastLogisticsDetail(orderLastLogisticsDetailDO);

        OrderLastLogisticsInfoBO lastLogisticsInfoBO = OrderLogisticsConvert
                .INSTANCE.convertOrderLastLogisticsInfoBO(orderLogisticsDO);

        lastLogisticsInfoBO.setLastLogisticsDetail(lastLogisticsDetail);
        return CommonResult.success(lastLogisticsInfoBO);
    }

    @Override
    public CommonResult<OrderLogisticsInfoWithOrderBO> getOrderLogisticsInfo(Integer userId, Integer orderId) {
        OrderDO orderDO  = orderMapper.selectById(orderId);

        if (orderDO == null) {
            return ServiceExceptionUtil.error(OrderErrorCodeEnum.ORDER_NOT_EXISTENT.getCode());
        }

        if (!userId.equals(orderDO.getUserId())) {
            return ServiceExceptionUtil.error(OrderErrorCodeEnum.ORDER_NOT_USER_ORDER.getCode());
        }

        // 获取订单所发货的订单 id
        List<OrderItemDO> orderItemDOList = orderItemMapper.selectByDeletedAndOrderId(
                DeletedStatusEnum.DELETED_NO.getValue(), orderId);

        // 获取物流 信息
        Set<Integer> orderLogisticsIds = orderItemDOList.stream()
                .filter(o -> o.getOrderLogisticsId() != null)
                .map(o -> o.getOrderLogisticsId())
                .collect(Collectors.toSet());

        List<OrderLogisticsDO> orderLogisticsDOList = Collections.emptyList();
        List<OrderLogisticsDetailDO> orderLogisticsDetailDOList = Collections.emptyList();
        if (!CollectionUtils.isEmpty(orderLogisticsIds)) {
            orderLogisticsDOList = orderLogisticsMapper.selectByIds(orderLogisticsIds);
            orderLogisticsDetailDOList = orderLogisticsDetailMapper.selectByOrderLogisticsIds(orderLogisticsIds);
        }

        // 转换 return 的数据
        List<OrderLogisticsInfoWithOrderBO.Logistics> logistics
                = OrderLogisticsConvert.INSTANCE.convertLogistics(orderLogisticsDOList);

        List<OrderLogisticsInfoWithOrderBO.LogisticsDetail> logisticsDetails
                = OrderLogisticsConvert.INSTANCE.convertLogisticsDetail(orderLogisticsDetailDOList);

        logisticsDetails.stream().map(o -> {
            o.setLogisticsTimeText(DateUtil.format(o.getLogisticsTime(), "yyyy-MM-dd HH:mm"));
            return o;
        }).collect(Collectors.toList());

        Map<Integer, List<OrderLogisticsInfoWithOrderBO.LogisticsDetail>> logisticsDetailMultimap
                = logisticsDetails.stream().collect(
                Collectors.toMap(
                        o -> o.getOrderLogisticsId(),
                        item -> Lists.newArrayList(item),
                        (oldVal, newVal) -> {
                            oldVal.addAll(newVal);
                            return oldVal;
                        }
                )
        );

        logistics.stream().map(o -> {
            if (logisticsDetailMultimap.containsKey(o.getId())) {
                o.setDetails(logisticsDetailMultimap.get(o.getId()));
            }
            return o;
        }).collect(Collectors.toList());

        return CommonResult.success(
                new OrderLogisticsInfoWithOrderBO()
                        .setOrderId(orderId)
                        .setOrderNo(orderDO.getOrderNo())
                        .setLogistics(logistics)
        );
    }
}
