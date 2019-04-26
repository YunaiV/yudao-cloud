package cn.iocoder.mall.pay.api.message;

/**
 * 支付退款成功的消息对象
 */
public class PayRefundSuccessMessage {

    public static final String TOPIC = "PAY_REFUND_SUCCESS";

    /**
     * 任务编号
     */
    private Integer id;
    /**
     * 退款单编号
     */
    private Integer refundId;
    /**
     * 交易编号
     */
    private Integer transactionId;
    /**
     * 应用编号
     */
    private String appId;
    /**
     * 应用订单编号
     */
    private String orderId;
    /**
     * 当前通知次数
     */
    private Integer notifyTimes;
    /**
     * 通知地址
     */
    private String notifyUrl;

    public Integer getId() {
        return id;
    }

    public PayRefundSuccessMessage setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getAppId() {
        return appId;
    }

    public PayRefundSuccessMessage setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public String getOrderId() {
        return orderId;
    }

    public PayRefundSuccessMessage setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public Integer getNotifyTimes() {
        return notifyTimes;
    }

    public PayRefundSuccessMessage setNotifyTimes(Integer notifyTimes) {
        this.notifyTimes = notifyTimes;
        return this;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public PayRefundSuccessMessage setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
        return this;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public PayRefundSuccessMessage setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
        return this;
    }

}
