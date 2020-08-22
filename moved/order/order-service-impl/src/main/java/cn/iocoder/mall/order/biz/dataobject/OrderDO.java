package cn.iocoder.mall.order.biz.dataobject;

import cn.iocoder.common.framework.dataobject.DeletableDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 订单
 *
 * @author Sin
 * @time 2019-03-16 13:49
 */
@Data
@Accessors(chain = true)
@TableName(value = "orders")
public class OrderDO extends DeletableDO {

    /**
     * id
     */
    private Integer id;
    /**
     * 用户编号
     */
    private Integer userId;
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

    ///
    /// 时间信息

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
     * 备注
     */
    private String remark;

}
