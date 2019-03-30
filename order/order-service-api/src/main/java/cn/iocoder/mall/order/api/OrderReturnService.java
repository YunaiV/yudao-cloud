package cn.iocoder.mall.order.api;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.order.api.dto.OrderReturnCreateDTO;

/**
 * 订单退货
 *
 * @author Sin
 * @time 2019-03-30 15:33
 */
public interface OrderReturnService {

    /**
     * 订单退货 - 创建
     *
     * @param orderReturnCreate
     * @return
     */
    CommonResult createOrderReturn(OrderReturnCreateDTO orderReturnCreate);
}
