package cn.iocoder.mall.pay.biz.dataobject;

import cn.iocoder.common.framework.dataobject.DeletableDO;

/**
 * 交易扩展表
 */
public class PayTransactionExtensionDO extends DeletableDO {

    /**
     * 编号，自增
     */
    private Integer id;
    /**
     * 交易编号 {@link PayTransactionDO#getId()}
     */
    private Integer transactionId;
    /**
     * 选择的支付渠道
     */
    private Integer payChannel;
    /**
     * 生成传输给第三方的订单号
     *
     * 唯一索引
     */
    private String transactionCode;
    /**
     * 扩展内容
     *
     * 异步通知的时候填充回调的数据
     */
    private String extensionData;
    /**
     * 发起交易的 IP
     */
    private String createIp;
    /**
     * 状态
     *
     * @see cn.iocoder.mall.pay.api.constant.PayTransactionStatusEnum
     * 注意，只包含上述枚举的 WAITTING 和 SUCCESS
     */
    private Integer status;

    public Integer getId() {
        return id;
    }

    public PayTransactionExtensionDO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public PayTransactionExtensionDO setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public Integer getPayChannel() {
        return payChannel;
    }

    public PayTransactionExtensionDO setPayChannel(Integer payChannel) {
        this.payChannel = payChannel;
        return this;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public PayTransactionExtensionDO setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
        return this;
    }

    public String getExtensionData() {
        return extensionData;
    }

    public PayTransactionExtensionDO setExtensionData(String extensionData) {
        this.extensionData = extensionData;
        return this;
    }

    public String getCreateIp() {
        return createIp;
    }

    public PayTransactionExtensionDO setCreateIp(String createIp) {
        this.createIp = createIp;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public PayTransactionExtensionDO setStatus(Integer status) {
        this.status = status;
        return this;
    }

}