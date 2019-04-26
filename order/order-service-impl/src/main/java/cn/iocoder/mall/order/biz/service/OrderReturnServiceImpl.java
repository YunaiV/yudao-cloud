package cn.iocoder.mall.order.biz.service;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.order.api.OrderReturnService;
import cn.iocoder.mall.order.api.constant.OrderErrorCodeEnum;
import cn.iocoder.mall.order.api.constant.OrderReturnStatusEnum;
import cn.iocoder.mall.order.api.dto.OrderReturnApplyDTO;
import cn.iocoder.mall.order.biz.convert.OrderReturnConvert;
import cn.iocoder.mall.order.biz.dao.OrderMapper;
import cn.iocoder.mall.order.biz.dao.OrderReturnMapper;
import cn.iocoder.mall.order.biz.dataobject.OrderDO;
import cn.iocoder.mall.order.biz.dataobject.OrderReturnDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 订单退货 service
 *
 * @author Sin
 * @time 2019-03-30 15:35
 */
@Service
@com.alibaba.dubbo.config.annotation.Service(validation = "true")
public class OrderReturnServiceImpl implements OrderReturnService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderReturnMapper orderReturnMapper;

    @Override
    public CommonResult orderReturnApply(OrderReturnApplyDTO orderReturnDTO) {
        OrderDO checkOrder = orderMapper.selectById(orderReturnDTO.getOrderId());

        // 检查订单是否 存在
        if (checkOrder == null) {
            return ServiceExceptionUtil.error(OrderErrorCodeEnum.ORDER_NOT_EXISTENT.getCode());
        }

        // 转换 DO
        OrderReturnDO orderReturnDO = OrderReturnConvert.INSTANCE.convert(orderReturnDTO);
        orderReturnDO
                .setOrderId(checkOrder.getId())
                .setOrderNo(checkOrder.getOrderNo())
                .setStatus(OrderReturnStatusEnum.RETURN_APPLICATION.getValue())
                .setCreateTime(new Date());

        // 保存申请信息
        orderReturnMapper.insert(orderReturnDO);
        return CommonResult.success(null);
    }

    @Override
    public String updateRefundSuccess(String orderId, Integer refundPrice) {
        return "success";
    }
}
