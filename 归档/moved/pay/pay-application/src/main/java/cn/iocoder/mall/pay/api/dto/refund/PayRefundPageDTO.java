package cn.iocoder.mall.pay.api.dto.refund;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 支付退款分页 DTO
 */
@Data
@Accessors(chain = true)
public class PayRefundPageDTO {

    /**
     * 创建时间（开始）
     */
    private LocalDateTime createBeginTime;
    /**
     * 创建时间（结束）
     */
    private LocalDateTime createEndTime;
    /**
     * 完成时间（开始）
     */
    private LocalDateTime finishBeginTime;
    /**
     * 完成时间（结束）
     */
    private LocalDateTime finishEndTime;
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
