package cn.iocoder.mall.payservice.rpc.transaction.dto;

import cn.iocoder.common.framework.vo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 支付交易分页 Request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PayTransactionPageReqDTO extends PageParam {

    /**
     * 创建时间（开始）
     */
    private Date createBeginTime;
    /**
     * 创建时间（结束）
     */
    private Date createEndTime;
    /**
     * 支付时间（开始）
     */
    private Date paymentBeginTime;
    /**
     * 支付时间（结束）
     */
    private Date paymentEndTime;
    /**
     * 支付状态
     */
    private Integer status;
    /**
     * 是否有退款
     */
    private Boolean hasRefund;
    /**
     * 支付渠道
     */
    private Integer payChannel;
    /**
     * 商品标题
     *
     * 模糊匹配
     */
    private String orderSubject;

}
