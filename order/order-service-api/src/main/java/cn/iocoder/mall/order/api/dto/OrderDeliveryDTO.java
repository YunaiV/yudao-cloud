package cn.iocoder.mall.order.api.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 订单发货
 *
 * @author Sin
 * @time 2019-03-30 22:31
 */
public class OrderDeliveryDTO implements Serializable {

    /**
     * 订单id
     */
    private Integer orderId;
    /**
     * 物流 (字典)
     */
    private Integer logistics;
    /**
     * 物流编号
     */
    private String logisticsNo;

    ///
    /// 物理信息是跟 orderItem 走

    /**
     * 订单 orderItemId
     */
    private List<Integer> orderItemIds;
}
