package cn.iocoder.mall.pay.biz.dataobject;

import cn.iocoder.common.framework.dataobject.DeletableDO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 支付应用（业务线）DO
 */
@Data
@Accessors(chain = true)
public class PayAppDO extends DeletableDO {

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
     * TODO 芋艿，修改成 payNotifyUrl
     */
    private String notifyUrl;
    /**
     * 退款异步通知地址
     */
    private String refundNotifyUrl;
    /**
     * 状态
     */
    private Integer status;

}
