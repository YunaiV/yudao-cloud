package cn.iocoder.mall.tradeservice.rpc.order.dto;

import cn.iocoder.mall.tradeservice.enums.order.TradeOrderDetailFieldEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 交易订单 Response DTO
 */
@Data
@Accessors(chain = true)
public class TradeOrderRespDTO implements Serializable {

    // ========== 订单基本信息 ==========
    /**
     * 订单编号
     */
    private Integer id;
    /**
     * 用户编号
     */
    private Integer userId;
    /**
     * 订单单号
     */
    private String orderNo;
    /**
     * 订单状态
     */
    private Integer orderStatus;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private Date createTime;

    // ========== 价格 + 支付基本信息 ==========
    /**
     * 订单结束时间
     */
    private Date endTime;
    /**
     * 订单金额(总金额)，单位：分
     */
    private Integer buyPrice;
    /**
     * 优惠总金额，单位：分
     */
    private Integer discountPrice;
    /**
     * 物流金额，单位：分
     */
    private Integer logisticsPrice;
    /**
     * 最终金额，单位：分
     */
    private Integer presentPrice;
    /**
     * 支付金额，单位：分
     */
    private Integer payPrice;
    /**
     * 退款金额，单位：分
     */
    private Integer refundPrice;
    /**
     * 付款时间
     */
    private Date payTime;
    /**
     * 支付订单编号
     */
    private Integer payTransactionId;
    /**
     * 支付渠道
     */
    private Integer payChannel;

    // ========== 收件 + 物流基本信息 ==========
    /**
     * 配送类型
     */
    private Integer deliveryType;
    /**
     * 发货时间
     */
    private Date deliveryTime;
    /**
     * 收货时间
     */
    private Date receiveTime;
    /**
     * 收件人名称
     */
    private String receiverName;
    /**
     * 手机号
     */
    private String receiverMobile;
    /**
     * 地区编码
     */
    private Integer receiverAreaCode;
    /**
     * 收件详细地址
     */
    private String receiverDetailAddress;

    // ========== 售后基本信息 ==========
    /**
     * 售后状态
     */
    private Integer afterSaleStatus;

    // ========== 营销基本信息 ==========
    /**
     * 优惠劵编号
     */
    private Integer couponCardId;

    // ========== 商品基本信息 ==========

    /**
     * 订单项数组
     *
     * 需要设置 {@link TradeOrderDetailFieldEnum#ITEM} 才返回
     *
     * // TODO 芋艿，后续考虑怎么优化下，目前是内嵌了别的 dto
     */
    private List<TradeOrderItemRespDTO> orderItems;

}
