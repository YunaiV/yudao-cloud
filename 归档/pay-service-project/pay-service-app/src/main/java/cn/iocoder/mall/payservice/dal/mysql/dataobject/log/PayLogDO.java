package cn.iocoder.mall.payservice.dal.mysql.dataobject.log;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 交易日志 DO
 *
 * 通过该日志，我们可以追溯整个执行过程
 *
 * TODO 芋艿，后面在捉摸
 */
@Data
@Accessors(chain = true)
public class PayLogDO {

    /**
     * 编号，自增
     */
    private Integer id;
    /**
     * 应用编号
     */
    private String appId;
    /**
     * 业务线订单编号
     */
    private Integer orderId;
}
