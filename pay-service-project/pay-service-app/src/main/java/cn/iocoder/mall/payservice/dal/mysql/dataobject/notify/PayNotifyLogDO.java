package cn.iocoder.mall.payservice.dal.mysql.dataobject.notify;

import cn.iocoder.mall.mybatis.core.dataobject.DeletableDO;
import cn.iocoder.mall.payservice.enums.notify.PayNotifyStatusEnum;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 支付通知 App 的日志 DO
 *
 * 通过该表，记录通知 App 时，产生的日志
 */
@TableName("pay_notify_log")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PayNotifyLogDO extends DeletableDO {

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
     * 外键 {@link PayNotifyStatusEnum}
     */
    private Integer status;

}
