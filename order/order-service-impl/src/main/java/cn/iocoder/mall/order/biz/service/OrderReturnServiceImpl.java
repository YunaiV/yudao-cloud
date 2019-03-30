package cn.iocoder.mall.order.biz.service;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.order.api.OrderReturnService;
import cn.iocoder.mall.order.api.constant.OrderReturnStatusEnum;
import cn.iocoder.mall.order.api.dto.OrderReturnCreateDTO;
import cn.iocoder.mall.order.biz.convert.OrderReturnConvert;
import cn.iocoder.mall.order.biz.dao.OrderReturnMapper;
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
public class OrderReturnServiceImpl implements OrderReturnService {

    @Autowired
    private OrderReturnMapper orderReturnMapper;

    @Override
    public CommonResult createOrderReturn(OrderReturnCreateDTO orderReturnCreate) {



        OrderReturnDO orderReturnDO = OrderReturnConvert.INSTANCE.convert(orderReturnCreate);
        orderReturnDO
                .setCreateTime(new Date())
                .setStatus(OrderReturnStatusEnum.RETURN_APPLICATION.getValue());

        return null;
    }
}
