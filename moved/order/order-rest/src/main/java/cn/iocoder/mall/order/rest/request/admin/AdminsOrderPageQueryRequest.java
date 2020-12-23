package cn.iocoder.mall.order.rest.request.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 订单 page 查询 vo
 *
 * @author Sin
 * @time 2019-03-24 10:40
 */
@Data
@Accessors(chain = true)
@ApiModel("订单查询")
public class AdminsOrderPageQueryRequest implements Serializable {

    /**
     * id
     */
    @ApiModelProperty("订单id")
    private Integer id;
    /**
     * 订单号
     */
    @ApiModelProperty("订单号")
    private String orderNo;
    /**
     * 用户 id
     */
    @ApiModelProperty("用户id")
    private Integer userId;
    /**
     * 付款时间（待发货）
     */
    @ApiModelProperty("start付款时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startPaymentTime;
    @ApiModelProperty("end付款时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endPaymentTime;
    /**
     * 创建时间
     */
    @ApiModelProperty("start订单创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startCreateTime;
    @ApiModelProperty("end订单创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endCreateTime;
    /**
     * 结束时间
     */
    @ApiModelProperty("start成交时间")
    private Date startClosingTime;
    @ApiModelProperty("end成交时间")
    private Date endClosingTime;
    /**
     * 删除状态
     */
    @ApiModelProperty("订单状态")
    private Integer status;
    @ApiModelProperty("删除状态")
    private Integer deleted;
    @ApiModelProperty("分页pageNo")
    private Integer pageNo;
    @ApiModelProperty("每页大小")
    private Integer pageSize;
}
