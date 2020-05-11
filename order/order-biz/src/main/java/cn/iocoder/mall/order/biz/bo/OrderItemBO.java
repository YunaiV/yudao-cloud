package cn.iocoder.mall.order.biz.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单 item
 *
 * @author Sin
 * @time 2019-03-28 21:11
 */
@Data
@Accessors(chain = true)
public class OrderItemBO implements Serializable {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 订单编号
     */
    private Integer orderId;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 商品编号
     */
    private Integer skuId;
    /**
     * 商品名称
     */
    private String skuName;
    /**
     * 商品图片
     */
    private String skuImage;
    /**
     * 数量
     */
    private Integer quantity;
    /**
     * 原始单价，单位：分。
     */
    private Integer originPrice;
    /**
     * 购买单价，单位：分
     */
    private Integer buyPrice;
    /**
     * 最终价格，单位：分。
     */
    private Integer presentPrice;
    /**
     * 购买总金额，单位：分
     *
     * 用途类似 {@link #presentTotal}
     */
    private Integer buyTotal;
    /**
     * 优惠总金额，单位：分。
     */
    private Integer discountTotal;
    /**
     * 最终总金额，单位：分。
     *
     * 注意，presentPrice * quantity 不一定等于 presentTotal 。
     * 因为，存在无法整除的情况。
     * 举个例子，presentPrice = 8.33 ，quantity = 3 的情况，presentTotal 有可能是 24.99 ，也可能是 25 。
     * 所以，需要存储一个该字段。
     */
    private Integer presentTotal;

    ///
    /// 时间信息

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
     * 是否退货
     *
     * - 1、没有
     * - 2、换货
     * - 3、退货
     * - 4、换货 + 退货
     */
    private Integer hasReturnExchange;
    /**
     * 发货方式
     *
     * - 1 未选择
     * - 2 在线下单
     * - 3 自己联系快递
     * - 4 无物流
     */
    private Integer deliveryType;
    /**
     * 状态
     *
     * - 1、待付款
     * - 2、待发货
     * - 3、已发货
     * - 4、已完成
     * - 5、已关闭
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 删除状态
     */
    private Integer deleted;
}
