package cn.iocoder.mall.order.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author Sin
 * @time 2019-03-23 10:22
 */
@Data
@Accessors(chain = true)
public class OrderItemDeletedDTO implements Serializable {

    /**
     * 订单id
     */
    private Integer orderId;
    /**
     * 订单item id
     */
    private List<Integer> orderItemIds;
}
