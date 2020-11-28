package cn.iocoder.mall.order.biz.dto.order;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 订单发货
 *
 * @author Sin
 * @time 2019-03-30 22:31
 */
@Data
@Accessors(chain = true)
public class OrderDeliveryDTO implements Serializable {

    /**
     * 订单id
     */
    private Integer orderId;
    // TODO 芋艿，物流方式。会存在无需物流的情况
    /**
     * 物流公司 (字典)
     */
    private Integer logistics;
    /**
     * 物流单编号
     */
    private String logisticsNo;

    ///
    /// 物理信息是跟 orderItem 走

    /**
     * 订单 orderItemId
     */
    private List<Integer> orderItemIds;

}
