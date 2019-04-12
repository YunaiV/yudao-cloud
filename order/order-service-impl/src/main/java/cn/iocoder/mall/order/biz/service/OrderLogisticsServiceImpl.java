package cn.iocoder.mall.order.biz.service;

import cn.iocoder.common.framework.constant.DeletedStatusEnum;
import cn.iocoder.common.framework.util.DateUtil;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.order.api.OrderLogisticsService;
import cn.iocoder.mall.order.api.bo.OrderLogisticsBO;
import cn.iocoder.mall.order.api.bo.OrderLogisticsInfoBO;
import cn.iocoder.mall.order.api.constant.OrderErrorCodeEnum;
import cn.iocoder.mall.order.biz.convert.OrderLogisticsConvert;
import cn.iocoder.mall.order.biz.convert.OrderLogisticsDetailConvert;
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

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 订单物流
 *
 * @author Sin
 * @time 2019-04-12 21:32
 */
@Service
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
    public CommonResult<OrderLogisticsInfoBO> logisticsInfo(Integer userId, Integer orderId) {
        OrderDO orderDO  = orderMapper.selectById(orderId);

        if (orderDO == null) {
            return ServiceExceptionUtil.error(OrderErrorCodeEnum.ORDER_NOT_EXISTENT.getCode());
        }

        if (!userId.equals(orderDO.getUserId())) {
            return ServiceExceptionUtil.error(OrderErrorCodeEnum.ORDER_NOT_USER_ORDER.getCode());
        }

        // 获取订单所发货的订单 id
        List<OrderItemDO> orderItemDOList = orderItemMapper.selectByDeletedAndOrderId(
                orderId, DeletedStatusEnum.DELETED_NO.getValue());

        // 获取物流 信息
        Set<Integer> orderLogisticsIds = orderItemDOList.stream()
                .map(o -> o.getOrderLogisticsId()).collect(Collectors.toSet());

        List<OrderLogisticsDO> orderLogisticsDOList = orderLogisticsMapper.selectByIds(orderLogisticsIds);

        List<OrderLogisticsDetailDO> orderLogisticsDetailDOList
                = orderLogisticsDetailMapper.selectByOrderLogisticsIds(orderLogisticsIds);

        // 转换 return 的数据
        List<OrderLogisticsInfoBO.Logistics> logistics
                = OrderLogisticsConvert.INSTANCE.convertLogistics(orderLogisticsDOList);

        List<OrderLogisticsInfoBO.LogisticsDetail> logisticsDetails
                = OrderLogisticsDetailConvert.INSTANCE.convertLogisticsDetail(orderLogisticsDetailDOList);

        logisticsDetails.stream().map(o -> {
            o.setLogisticsTimeText(DateUtil.format(o.getLogisticsTime(), "yyyy-MM-dd HH:mm"));
            return o;
        }).collect(Collectors.toList());

        Map<Integer, List<OrderLogisticsInfoBO.LogisticsDetail>> logisticsDetailMultimap
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
                new OrderLogisticsInfoBO()
                        .setOrderId(orderId)
                        .setOrderNo(orderDO.getOrderNo())
                        .setLogistics(logistics)
        );
    }
}
