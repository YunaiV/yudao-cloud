package cn.iocoder.mall.pay.api.dto.transaction;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 支付交易分页 DTO
 */
@Data
@Accessors(chain = true)
public class PayTransactionPageDTO {

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

    @NotNull(message = "页码不能为空")
    private Integer pageNo;
    @NotNull(message = "每页条数不能为空")
    private Integer pageSize;

}
