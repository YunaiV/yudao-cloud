package cn.iocoder.mall.pay.dataobject;

import cn.iocoder.common.framework.dataobject.BaseDO;

/**
 * 支付交易通知 App 的日志 DO
 *
 * 通过该表，记录通知 App 时，产生的日志
 */
public class PayTransactionNotifyLogDO extends BaseDO {

    /**
     * 日志编号，自增
     */
    private Integer id;
    /**
     * 请求参数
     */
    private String request;
    /**
     * 响应结果
     */
    private String response;
    /**
     * 状态
     *
     * @see cn.iocoder.mall.pay.api.constant.PayTransactionNotifyStatusEnum
     */
    private Integer status;

    public Integer getId() {
        return id;
    }

    public PayTransactionNotifyLogDO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getRequest() {
        return request;
    }

    public PayTransactionNotifyLogDO setRequest(String request) {
        this.request = request;
        return this;
    }

    public String getResponse() {
        return response;
    }

    public PayTransactionNotifyLogDO setResponse(String response) {
        this.response = response;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public PayTransactionNotifyLogDO setStatus(Integer status) {
        this.status = status;
        return this;
    }

}