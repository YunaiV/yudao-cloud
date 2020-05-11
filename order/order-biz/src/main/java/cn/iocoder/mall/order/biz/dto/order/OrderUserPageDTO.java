package cn.iocoder.mall.order.biz.dto.order;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户订单 page
 *
 * @author Sin
 * @time 2019-04-08 17:50
 */
@Data
@Accessors(chain = true)
public class OrderUserPageDTO implements Serializable {

    /**
     * id
     */
    private Integer id;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 用户 id
     */
    private Integer userId;
    /**
     * 付款时间（待发货）
     */
    private Date startPaymentTime;
    private Date endPaymentTime;
    /**
     * 创建时间
     */
    private Date startCreateTime;
    private Date endCreateTime;
    /**
     * 状态
     */
    private Integer status;
}
