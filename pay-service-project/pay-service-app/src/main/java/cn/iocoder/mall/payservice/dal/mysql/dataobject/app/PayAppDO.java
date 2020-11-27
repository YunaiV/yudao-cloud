package cn.iocoder.mall.payservice.dal.mysql.dataobject.app;

import cn.iocoder.mall.mybatis.core.dataobject.DeletableDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 支付应用
 *
 * 每个接入的业务都是一个应用，进行个性化的配置
 */
@Data
@EqualsAndHashCode(callSuper = true)
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
     */
    private String payNotifyUrl;
    /**
     * 退款异步通知地址
     */
    private String refundNotifyUrl;
    /**
     * 状态
     *
     * 枚举 {@link cn.iocoder.common.framework.enums.CommonStatusEnum}
     */
    private Integer status;

}
