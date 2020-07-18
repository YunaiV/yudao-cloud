package cn.iocoder.mall.order.biz.dataobject;

import cn.iocoder.mall.mybatis.core.dataobject.DeletableDO;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 订单 item
 *
 * @author Sin
 * @time 2019-03-16 14:03
 */
@Data
@Accessors(chain = true)
public class OrderItemDO extends DeletableDO {

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
     * 物流id
     */
    private Integer orderLogisticsId;
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
    // 如上字段，举个例子：
    // 假设购买三个，即 quantity = 3 。
    // originPrice = 15
    // 使用限时折扣（单品优惠）8 折，buyPrice = 12
    // 开始算总的价格
    // buyTotal = buyPrice * quantity = 12 * 3 = 36
    // discountTotal ，假设有满减送（分组优惠）满 20 减 10 ，并且使用优惠劵满 1.01 减 1 ，则 discountTotal = 10 + 1 = 11
    // presentTotal = buyTotal - discountTotal = 24 - 11 = 13
    // 最终 presentPrice = presentTotal / quantity = 13 / 3 = 4.33

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

}
