package cn.iocoder.mall.pay.api.message;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 支付交易单支付成功的消息对象
 */
@Data
@Accessors(chain = true)
public class PayTransactionSuccessMessage extends AbstractPayNotifySuccessMessage  {

    public static final String TOPIC = "PAY_TRANSACTION_SUCCESS";

    /**
     * 交易编号
     */
    private Integer transactionId;
    /**
     * 应用订单编号
     */
    private String orderId;

}
