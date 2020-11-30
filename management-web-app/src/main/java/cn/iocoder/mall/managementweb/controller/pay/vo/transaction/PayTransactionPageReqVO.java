package cn.iocoder.mall.managementweb.controller.pay.vo.transaction;

import cn.iocoder.common.framework.vo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@ApiModel("支付交易分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PayTransactionPageReqVO extends PageParam {

    @ApiModelProperty(value = "创建时间（开始）", example = "2019-10-10 11:12:13")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createBeginTime;
    @ApiModelProperty(value = "创建时间（结束）", example = "2019-10-10 11:12:13")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createEndTime;

    @ApiModelProperty(value = "创建时间（）", example = "2019-10-10 11:12:13")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date paymentBeginTime;
    @ApiModelProperty(value = "创建时间（）", example = "2019-10-10 11:12:13")
    private Date paymentEndTime;

    @ApiModelProperty(value = "支付状态", example = "1", notes = "参见 PayTransactionStatusEnum 枚举")
    private Integer status;

    @ApiModelProperty(value = "是否退款", example = "true")
    private Boolean hasRefund;

    @ApiModelProperty(value = "支付渠道", example = "1", notes = "参见 PayChannelEnum 枚举")
    private Integer payChannel;

    @ApiModelProperty(value = "商品标题", example = "芋头")
    private String orderSubject;

}
