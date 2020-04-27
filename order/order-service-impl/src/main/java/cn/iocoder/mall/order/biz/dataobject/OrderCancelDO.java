package cn.iocoder.mall.order.biz.dataobject;

import cn.iocoder.common.framework.dataobject.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 订单关闭
 *
 *  - 注意：订单只有在用户为付款前取消。
 *
 *  - 取消订单，这里是取消整个订单，不能对订单 item 单独做取消
 *
 * @author Sin
 * @time 2019-03-30 16:20
 */
@Data
@Accessors(chain = true)
public class OrderCancelDO extends BaseDO {

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
     * 关闭订单原因（字典）
     */
    private Integer reason;
    /**
     * 原因（如果选择其他，原因保存在这）
     */
    private String otherReason;
}
