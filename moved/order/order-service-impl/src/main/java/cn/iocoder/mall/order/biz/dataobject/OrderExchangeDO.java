package cn.iocoder.mall.order.biz.dataobject;

import cn.iocoder.common.framework.dataobject.DeletableDO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 换货订单
 *
 * @author Sin
 * @time 2019-03-19 19:48
 */
@Data
@Accessors(chain = true)
public class OrderExchangeDO extends DeletableDO {

    /**
     * id
     */
    private Integer id;
    /**
     * 订单id
     */
    private Integer orderId;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 订单 item 编号
     */
    private Integer orderItemId;
    /**
     * 商品id（保存一个冗余，如果一个订单下存在多个商品，会有很大的作用）
     */
    private String skuId;
    /**
     * 换货商品id
     */
    private String exchangeSkuId;
    /**
     * 换货物流id
     */
    private Integer exchangeOrderLogisticsId;
    /**
     * 收件物流id
     */
    private Integer receiverOrderLogisticsId;

    ///
    /// 原因

    /**
     * 原因 (关联字典)
     *
     * {@link cn.iocoder.mall.order.biz.constants.OrderExchangeReasonEnum}
     */
    private Integer orderReasonId;
    /**
     * 原因（如果选择其他，原因保存在这）
     *
     * {@link cn.iocoder.mall.order.biz.constants.OrderExchangeReasonEnum#REASON_000}
     */
    private String reason;

    ///
    /// 时间信息

    /**
     * 创建时间
     * supper baseDO
     */
//    private Date createTime;
    /**
     * 付款时间
     */
    private Date paymentTime;
    /**
     * 发货时间
     */
    private Date deliveryTime;
    /**
     * 收货时间
     */
    private Date receiverTime;
    /**
     * 成交时间
     */
    private Date closingTime;

    ///
    /// 其他

    /**
     * 订单类型
     *
     * - 0、为 Order 订单 （对整个订单退货）
     * - 1、为 OrderItem 订单 （对订单某一个商品退货）
     */
    private Integer orderType;
    /**
     * 状态
     *
     * - 申请换货
     * - 申请成功
     * - 申请失败
     * - 换货中
     * - 换货成功
     */
    private Integer status;

}
