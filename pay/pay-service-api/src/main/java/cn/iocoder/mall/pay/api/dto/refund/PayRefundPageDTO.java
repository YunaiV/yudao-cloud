package cn.iocoder.mall.pay.api.dto.refund;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 支付退款分页 DTO
 */
@Data
@Accessors(chain = true)
public class PayRefundPageDTO {

    /**
     * 创建时间（开始）
     */
    private Date createBeginTime;
    /**
     * 创建时间（结束）
     */
    private Date createEndTime;
    /**
     * 完成时间（开始）
     */
    private Date finishBeginTime;
    /**
     * 完成时间（结束）
     */
    private Date finishEndTime;
    /**
     * 退款状态
     */
    private Integer status;
    /**
     * 支付渠道
     */
    private Integer payChannel;

    @NotNull(message = "页码不能为空")
    private Integer pageNo;
    @NotNull(message = "每页条数不能为空")
    private Integer pageSize;

}
