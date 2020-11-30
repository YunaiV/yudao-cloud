package cn.iocoder.mall.payservice.dal.mysql.dataobject.notify;

import cn.iocoder.mall.mybatis.core.dataobject.DeletableDO;
import cn.iocoder.mall.payservice.dal.mysql.dataobject.transaction.PayTransactionDO;
import cn.iocoder.mall.payservice.dal.mysql.dataobject.transaction.PayTransactionExtensionDO;
import cn.iocoder.mall.payservice.enums.notify.PayNotifyStatusEnum;
import cn.iocoder.mall.payservice.enums.notify.PayNotifyType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 支付通知 App 的任务 DO
 *
 * 目前包括支付通知、退款通知。
 */
@TableName("pay_notify_task")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PayNotifyTaskDO extends DeletableDO {

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
     * 应用编号
     */
    private String appId;
    /**
     * 类型
     *
     * 外键 {@link PayNotifyType}
     */
    private Integer type;
    /**
     * 通知状态
     *
     * 外键 {@link PayNotifyStatusEnum}
     */
    private Integer status;
    /**
     * 是否激活中，即处于正在 MQ 异步通知中
     *
     * @see cn.iocoder.mall.payservice.job.notify.PayNotifyRetryJob
     */
    private Boolean active;
    /**
     * 下一次通知时间
     */
    private Date nextNotifyTime;
    /**
     * 最后一次执行时间
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
    // TODO 芋艿，未来把 transaction 和 refund 优化成一个字段。现在为了方便。
    /**
     * 支付数据
     */
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private Transaction transaction;
    /**
     * 退款数据
     */
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private Refund refund;

    @Data
    @Accessors(chain = true)
    public static class Transaction {

        /**
         * 应用订单编号
         */
        private String orderId;
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

    }

    @Data
    @Accessors(chain = true)
    public static class Refund {

        /**
         * 应用订单编号
         */
        private String orderId;
        /**
         * 交易编号
         *
         * {@link PayTransactionDO#getId()}
         */
        private Integer transactionId;
        /**
         * 退款单编号
         */
        private Integer refundId;

    }

}
