package cn.iocoder.mall.pay.biz.dataobject;

import cn.iocoder.common.framework.dataobject.BaseDO;

import java.util.Date;

/**
 * 支付交易通知 App 的任务 DO
 */
public class PayTransactionNotifyTaskDO extends BaseDO {

    /**
     * 编号，自增
     */
    private Integer id;
    /**
     * 交易编号
     *
     * {@link PayTransactionDO#getId()}
     */
    private Integer transactionId;
    /**
     * 交易拓展编号
     *
     * {@link PayTransactionExtensionDO#getId()}
     */
    private Integer transactionExtensionId;
    /**
     * 应用编号
     */
    private String appId;
    /**
     * 应用订单编号
     */
    private String orderId;
    /**
     * 通知状态
     *
     * @see cn.iocoder.mall.pay.api.constant.PayTransactionNotifyStatusEnum
     */
    private Integer status;
    /**
     * 最后一次通知时间
     */
    private Date lastNotifyTime;
    /**
     * 当前通知次数
     */
    private Integer notifyTimes;
    /**
     * 最大可通知次数
     */
    private Integer maxNotifyTimes;
    /**
     * 通知地址
     */
    private String notifyUrl;

    public Integer getTransactionId() {
        return transactionId;
    }

    public PayTransactionNotifyTaskDO setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public String getAppId() {
        return appId;
    }

    public PayTransactionNotifyTaskDO setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public String getOrderId() {
        return orderId;
    }

    public PayTransactionNotifyTaskDO setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public PayTransactionNotifyTaskDO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Date getLastNotifyTime() {
        return lastNotifyTime;
    }

    public PayTransactionNotifyTaskDO setLastNotifyTime(Date lastNotifyTime) {
        this.lastNotifyTime = lastNotifyTime;
        return this;
    }

    public Integer getNotifyTimes() {
        return notifyTimes;
    }

    public PayTransactionNotifyTaskDO setNotifyTimes(Integer notifyTimes) {
        this.notifyTimes = notifyTimes;
        return this;
    }

    public Integer getMaxNotifyTimes() {
        return maxNotifyTimes;
    }

    public PayTransactionNotifyTaskDO setMaxNotifyTimes(Integer maxNotifyTimes) {
        this.maxNotifyTimes = maxNotifyTimes;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public PayTransactionNotifyTaskDO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getTransactionExtensionId() {
        return transactionExtensionId;
    }

    public PayTransactionNotifyTaskDO setTransactionExtensionId(Integer transactionExtensionId) {
        this.transactionExtensionId = transactionExtensionId;
        return this;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public PayTransactionNotifyTaskDO setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
        return this;
    }
}