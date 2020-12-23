package cn.iocoder.mall.order.rest.request.users;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 订单售后
 *
 * @author Sin
 * @time 2019-04-25 20:59
 */
@Data
@Accessors(chain = true)
public class OrderReturnApplyRequest implements Serializable {

    /**
     * 订单编号
     */
    @NotNull(message = "orderId 不能为空!")
    private Integer orderId;
    /**
     * 退款类型
     *
     * - 1、退货退款
     * - 2、退款
     *
     * / TODO FROM 芋艿 to xiaofeng：可以瞅瞅 @InEnum 注解，直接校验退货类型
     */
    @NotNull(message = "退货类型不能为空!")
    private Integer returnType;
    /**
     * 退货原因(字典值)
     *
     * {@link cn.iocoder.mall.order.biz.constants.OrderReturnReasonEnum}
     */
    @NotNull(message = "必须选择退货原因")
    private Integer reason;
    /**
     * 原因（如果选择其他，原因保存在这）
     *
     * {@link cn.iocoder.mall.order.biz.constants.OrderReturnReasonEnum#REASON_000}
     */
    @Size(max = 200)
    private String describe;

}
