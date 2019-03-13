package cn.iocoder.mall.pay.client;

import java.util.Date;

/**
 * 交易支付成功结果
 */
public class TransactionPaySuccessBO {

    /**
     * 生成传输给第三方的订单号
     *
     * 唯一索引
     */
    private String transactionCode;
    /**
     * 第三方的流水号
     */
    private String tradeNo;
    /**
     * 第三方支付成功的时间
     */
    private Date paymentTime;

    public String getTransactionCode() {
        return transactionCode;
    }

    public TransactionPaySuccessBO setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
        return this;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public TransactionPaySuccessBO setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
        return this;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public TransactionPaySuccessBO setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
        return this;
    }

}