package cn.iocoder.mall.pay.dataobject;

import cn.iocoder.common.framework.dataobject.BaseDO;

/**
 * 交易扩展表
 */
public class PayTransactionExtensionDO extends BaseDO {

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
     */
    private String transactionCode;
    /**
     * 发起调用的次数
     *
     * TODO 芋艿，需要去请教下
     */
    private Integer callNum;
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

    public Integer getCallNum() {
        return callNum;
    }

    public PayTransactionExtensionDO setCallNum(Integer callNum) {
        this.callNum = callNum;
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

}