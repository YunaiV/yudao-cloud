package cn.iocoder.mall.pay.biz.dataobject;

import cn.iocoder.common.framework.dataobject.DeletableDO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 支付交易通知 App 的日志 DO
 *
 * 通过该表，记录通知 App 时，产生的日志
 */
@Data
@Accessors(chain = true)
public class PayTransactionNotifyLogDO extends DeletableDO {

    /**
     * 日志编号，自增
     */
    private Integer id;
    /**
     * 通知编号
     */
    private Integer notifyId;
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

}
