package cn.iocoder.mall.order.biz.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 订单退货 info
 *
 * @author Sin
 * @time 2019-04-27 10:19
 */
@Data
@Accessors(chain = true)
public class OrderReturnInfoBO implements Serializable {

    /**
     * 退货信息
     */
    private ReturnInfo returnInfo;
    /**
     * 订单 item
     */
    private List<OrderItem> orderItems;
    /**
     * 最后一个物流信息/最新物流信息
     */
    private OrderLastLogisticsInfoBO lastLogisticsInfo;

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
         * 最终总金额，单位：分。
         */
        private Integer presentTotal;
    }

    @Data
    @Accessors(chain = true)
    public static class ReturnInfo {

        /**
         * 编号自动增长
         */
        private Integer id;
        /**
         * 服务号
         */
        private String serviceNumber;
        /**
         * 订单编号
         */
        private Integer orderId;
        /**
         * 订单号 （保存一个冗余）
         */
        private String orderNo;
        /**
         * 物流id
         */
        private Integer orderLogisticsId;

        ///
        /// 退货原因

        /**
         * 退货金额
         */
        private Integer refundPrice;
        /**
         * 退货原因(字典值)
         */
        private Integer reason;
        /**
         * 问题描述
         */
        private String describe;

        ///
        /// 时间信息

        /**
         * 同意时间
         */
        private Date approvalTime;
        /**
         * 物流时间（填写物流单号时间）
         */
        private Date logisticsTime;
        /**
         * 收货时间
         */
        private Date receiverTime;
        /**
         * 成交时间（确认时间）
         */
        private Date closingTime;
        /**
         * 退款类型
         *
         * - 1、退货退款
         * - 2、退款
         */
        private Integer serviceType;
        /**
         * 退款类型 转换值
         */
        private String serviceTypeText;
        /**
         * 状态
         *
         * - 1、退货申请
         * - 2、申请成功
         * - 3、申请失败
         * - 4、退货中
         * - 5、退货成功
         */
        private Integer status;
    }
}
