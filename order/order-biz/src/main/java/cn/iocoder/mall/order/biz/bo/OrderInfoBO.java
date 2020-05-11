package cn.iocoder.mall.order.biz.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 订单 info
 *
 * @author Sin
 * @time 2019-04-14 15:36
 */
@Data
@Accessors(chain = true)
public class OrderInfoBO implements Serializable {

    /**
     * id
     */
    private Integer id;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 购买（商品）总金额，单位：分
     */
    private Integer buyPrice;
    /**
     * 优惠总金额，单位：分。
     */
    private Integer discountPrice;
    /**
     * 物流金额 (分)
     */
    private Integer logisticsPrice;
    /**
     * 最终金额，单位：分
     *
     * buyPrice + logisticsPrice -  discountPrice = presentPrice
     */
    private Integer presentPrice;
    /**
     * 实际已支付金额，单位：分
     *
     * 初始时，金额为 0 。等到支付成功后，会进行更新。
     */
    private Integer payAmount;

    /**
     * 付款时间（待发货）
     */
    private Date paymentTime;
    /**
     * 发货时间（待收货）
     */
    private Date deliveryTime;
    /**
     * 收货时间（已签收）
     */
    private Date receiverTime;
    /**
     * 成交时间（用户确认收货 -> status = 已完成）
     */
    private Date closingTime;
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
     * 状态(如果有多个商品分开发货需要全部商品发完才会改变状态)
     *
     * - 1、待付款
     * - 2、待发货
     * - 3、待收获
     * - 4、已完成
     * - 5、已关闭
     */
    private Integer status;
    /**
     * 转换的字典值
     */
    private String statusText;
    /**
     * 备注
     */
    private String remark;

    ///
    /// 其他信息

    /**
     * 手机人信息
     */
    private Recipient recipient;
    /**
     * 最新物流信息
     */
    private LogisticsDetail latestLogisticsDetail;
    /**
     * 订单 item
     */
    private List<OrderItem> orderItems;


    ///
    /// 其他字段

    /**
     * 是否退货
     */
    private Integer hasOrderReturn;

    @Data
    @Accessors(chain = true)
    public static class OrderItem {

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

    }

    @Data
    @Accessors(chain = true)
    public static class Recipient {

        /**
         * 编号
         */
        private Integer id;
        /**
         * 订单id
         */
        private Integer orderId;
        /**
         * 收件区域编号
         */
        private String areaNo;
        /**
         * 收件人名称
         */
        private String name;
        /**
         * 收件手机号
         */
        private String mobile;
        /**
         * 配送类型
         *
         * - 1 快递
         */
        private Integer type;
        /**
         * 收件详细地址
         */
        private String address;
    }

    @Data
    @Accessors(chain = true)
    public static class LogisticsDetail {

        /**
         * id
         */
        private Integer id;
        /**
         * 物流id
         */
        private Integer orderLogisticsId;
        /**
         * 物流时间
         */
        private Date logisticsTime;
        /**
         * 物流信息
         */
        private String logisticsInformation;
    }
}
