package cn.iocoder.mall.order.biz.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 订单创建 BO
 *
 * @author Sin
 * @time 2019-03-16 14:38
 */
@Data
@Accessors(chain = true)
public class OrderCreateBO implements Serializable {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 订单金额
     */
    private Integer payAmount;
}
