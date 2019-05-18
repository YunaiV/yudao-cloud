package cn.iocoder.mall.pay.api.bo.refund;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 支付退款 Page BO
 */
@Data
@Accessors(chain = true)
public class PayRefundPageBO implements Serializable {

    /**
     * 支付退款数组
     */
    private List<PayRefundBO> list;
    /**
     * 总量
     */
    private Integer total;

}
