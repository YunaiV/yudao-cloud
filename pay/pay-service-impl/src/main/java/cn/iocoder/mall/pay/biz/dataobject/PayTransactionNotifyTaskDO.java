package cn.iocoder.mall.pay.biz.dataobject;

import cn.iocoder.common.framework.dataobject.DeletableDO;
import cn.iocoder.mall.pay.biz.service.PayServiceImpl;

import java.util.Date;

/**
 * 支付交易通知 App 的任务 DO
 */
public class PayTransactionNotifyTaskDO extends DeletableDO {

    /**
     * 通知频率，单位为秒。
     *
     * 算上首次的通知，实际是一共 1 + 8 = 9 次。
     */
    public static final Integer[] NOTIFY_FREQUENCY = new Integer[]{
            15, 15, 30, 180,
            1800, 1800, 1800, 3600
    };

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
     * 下一次通知时间
     */
    private Date nextNotifyTime;
    /**
     * 最后一次执行时间
     *
     * 这个字段，需要结合 {@link #nextNotifyTime} 一起使用。
     *
     * 1. 初始时，{@link PayServiceImpl#updateTransactionPaySuccess(Integer, String)}
     *      nextNotifyTime 为当前时间 + 15 秒
     *      lastExecuteTime 为空
     *      并发送给 MQ ，执行执行
     *
     * 2. MQ 消费时，更新 lastExecuteTime 为当时时间
     *
     * 3. 定时任务，扫描 nextNotifyTime < lastExecuteTime 的任务
     *      nextNotifyTime 为当前时间 + N 秒。具体的 N ，由第几次通知决定
     *      lastExecuteTime 为当前时间
     */
    private Date lastExecuteTime;
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

    public Date getNextNotifyTime() {
        return nextNotifyTime;
    }

    public PayTransactionNotifyTaskDO setNextNotifyTime(Date nextNotifyTime) {
        this.nextNotifyTime = nextNotifyTime;
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

    public Date getLastExecuteTime() {
        return lastExecuteTime;
    }

    public PayTransactionNotifyTaskDO setLastExecuteTime(Date lastExecuteTime) {
        this.lastExecuteTime = lastExecuteTime;
        return this;
    }

}