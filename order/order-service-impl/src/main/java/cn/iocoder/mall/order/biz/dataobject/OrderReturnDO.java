package cn.iocoder.mall.order.biz.dataobject;

import cn.iocoder.common.framework.dataobject.DeletableDO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 退货订单
 *
 * @author Sin
 * @time 2019-03-19 19:48
 */
@Data
@Accessors(chain = true)
public class OrderReturnDO extends DeletableDO {

    /**
     * 编号自动增长
     */
    private Integer id;
    /**
     * 订单编号
     */
    private Integer orderId;
    /**
     * 订单号 （保存一个冗余）
     */
    private String orderNo;
    /**
     * 订单 item 编号
     */
    private Integer orderItemId;
    /**
     * 商品编号（保存一个冗余，如果一个订单下存在多个商品，会有很大的作用）
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
     * 物流id
     */
    private Integer orderLogisticsId;

    ///
    /// 退货原因

    /**
     * 退货原因(字典值)
     *
     * {@link cn.iocoder.mall.order.biz.constants.OrderReturnReasonEnum}
     */
    private Integer orderReason;
    /**
     * 原因（如果选择其他，原因保存在这）
     *
     * {@link cn.iocoder.mall.order.biz.constants.OrderReturnReasonEnum#REASON_000}
     */
    private String otherReasons;

    ///
    /// 时间信息

    /**
     * 创建时间
     */
    private Date createTime;
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
     * - 1、退货申请
     * - 2、申请成功
     * - 3、申请失败
     * - 4、退货中
     * - 5、退货成功
     */
    private Integer status;

}
