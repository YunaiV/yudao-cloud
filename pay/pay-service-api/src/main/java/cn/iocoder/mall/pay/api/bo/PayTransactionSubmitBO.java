package cn.iocoder.mall.pay.api.bo;

/**
 * 支付交易提交结果 BO
 */
public class PayTransactionSubmitBO {

    /**
     * 支付交易拓展单编号
     */
    private Integer id;
    /**
     * 调用三方平台的响应结果
     */
    private String invokeResponse;

    public Integer getId() {
        return id;
    }

    public PayTransactionSubmitBO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getInvokeResponse() {
        return invokeResponse;
    }

    public PayTransactionSubmitBO setInvokeResponse(String invokeResponse) {
        this.invokeResponse = invokeResponse;
        return this;
    }

}