package cn.iocoder.mall.order.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 订单退货 - 创建
 *
 * @author Sin
 * @time 2019-03-30 15:34
 */
@Data
@Accessors(chain = true)
public class OrderReturnCreateDTO implements Serializable {

    /**
     * 订单编号
     */
    private Integer orderId;
    /**
     * 订单 item 编号
     */
    private Integer orderItemId;
    /**
     * 退货原因(字典值)
     */
    private Integer orderReason;
    /**
     * 原因（如果选择其他，原因保存在这）
     */
    private String otherReasons;
    /**
     * 订单类型
     *
     * - 0、为 Order 订单 （对整个订单退货）
     * - 1、为 OrderItem 订单 （对订单某一个商品退货）
     */
    private Integer orderType;
}
