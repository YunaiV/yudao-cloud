package cn.iocoder.mall.order.biz.dataobject;

import cn.iocoder.mall.mybatis.core.dataobject.BaseDO;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 退货订单
 *
 * @author Sin
 * @time 2019-03-19 19:48
 */
@Data
@Accessors(chain = true)
public class OrderReturnDO extends BaseDO {

    // TODO FROM 芋艿 TO 小范，存储下支付中心的退款单号

    /**
     * 编号自动增长
     */
    private Integer id;
    /**
     * 服务号
     */
    // TODO FROM 芋艿 to 小范，换个名字，看着怪怪的 哈哈哈哈。
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
     *
     * {@link cn.iocoder.mall.order.biz.constants.OrderReturnReasonEnum}
     */
    private Integer reason;
    /**
     * 问题描述
     */
    // TODO FROM 芋艿 to 小范，describe 是动词，换成名词 description
    private String describe;

    ///
    /// 时间信息

    /**
     * 同意时间
     */
    private Date approvalTime;
    /**
     * 拒绝时间
     */
    private Date refuseTime;
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
     * 服务类型
     *
     * - 1、退货退款
     * - 2、退款
     */
    private Integer serviceType;
    /**
     * 状态
     *
     * - 1、退货申请
     * - 2、申请成功
     * - 3、申请失败
     * - 4、退货中
     * - 5、已收货
     * - 6、退货成功
     */
    private Integer status;
}
