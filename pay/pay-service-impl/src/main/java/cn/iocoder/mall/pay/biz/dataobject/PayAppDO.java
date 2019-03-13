package cn.iocoder.mall.pay.biz.dataobject;

import cn.iocoder.common.framework.dataobject.BaseDO;

/**
 * 支付应用（业务线）DO
 */
public class PayAppDO extends BaseDO {

    /**
     * 应用编号
     */
    private String id;
    /**
     * 应用名
     */
    private String name;
    /**
     * 异步通知地址
     */
    private String notifyUrl;
    /**
     * 状态
     */
    private Integer status;

    public String getId() {
        return id;
    }

    public PayAppDO setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PayAppDO setName(String name) {
        this.name = name;
        return this;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public PayAppDO setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public PayAppDO setStatus(Integer status) {
        this.status = status;
        return this;
    }

}