package cn.iocoder.mall.order.application.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单 page 查询 vo
 *
 * @author Sin
 * @time 2019-03-24 10:40
 */
@ApiModel("订单查询")
public class OrderPageQueryPO implements Serializable {

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

    @Override
    public String toString() {
        return "OrderPageQueryVO{" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                ", userId=" + userId +
                ", startPaymentTime=" + startPaymentTime +
                ", endPaymentTime=" + endPaymentTime +
                ", startCreateTime=" + startCreateTime +
                ", endCreateTime=" + endCreateTime +
                ", startClosingTime=" + startClosingTime +
                ", endClosingTime=" + endClosingTime +
                ", status=" + status +
                ", deleted=" + deleted +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public OrderPageQueryPO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public OrderPageQueryPO setOrderNo(String orderNo) {
        this.orderNo = orderNo;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public OrderPageQueryPO setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public Date getStartPaymentTime() {
        return startPaymentTime;
    }

    public OrderPageQueryPO setStartPaymentTime(Date startPaymentTime) {
        this.startPaymentTime = startPaymentTime;
        return this;
    }

    public Date getEndPaymentTime() {
        return endPaymentTime;
    }

    public OrderPageQueryPO setEndPaymentTime(Date endPaymentTime) {
        this.endPaymentTime = endPaymentTime;
        return this;
    }

    public Date getStartCreateTime() {
        return startCreateTime;
    }

    public OrderPageQueryPO setStartCreateTime(Date startCreateTime) {
        this.startCreateTime = startCreateTime;
        return this;
    }

    public Date getEndCreateTime() {
        return endCreateTime;
    }

    public OrderPageQueryPO setEndCreateTime(Date endCreateTime) {
        this.endCreateTime = endCreateTime;
        return this;
    }

    public Date getStartClosingTime() {
        return startClosingTime;
    }

    public OrderPageQueryPO setStartClosingTime(Date startClosingTime) {
        this.startClosingTime = startClosingTime;
        return this;
    }

    public Date getEndClosingTime() {
        return endClosingTime;
    }

    public OrderPageQueryPO setEndClosingTime(Date endClosingTime) {
        this.endClosingTime = endClosingTime;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public OrderPageQueryPO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public OrderPageQueryPO setDeleted(Integer deleted) {
        this.deleted = deleted;
        return this;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public OrderPageQueryPO setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public OrderPageQueryPO setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }
}
