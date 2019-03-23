package cn.iocoder.mall.order.api.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 订单 query
 *
 * @author Sin
 * @time 2019-03-23 14:15
 */
public class OrderQueryDTO implements Serializable {

    /**
     * id
     */
    private Integer id;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 用户 id
     */
    private Integer userId;
    /**
     * 付款时间（待发货）
     */
    private Date startPaymentTime;
    private Date endPaymentTime;
    /**
     * 创建时间
     */
    private Date startCreateTime;
    private Date endCreateTime;
    /**
     * 删除状态
     */
    private Integer deleted;

    @NotNull(message = "页码不能为空")
    private Integer pageNo;
    @NotNull(message = "每页条数不能为空")
    private Integer pageSize;

    @Override
    public String toString() {
        return "OrderQueryDTO{" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                ", userId=" + userId +
                ", startPaymentTime=" + startPaymentTime +
                ", endPaymentTime=" + endPaymentTime +
                ", startCreateTime=" + startCreateTime +
                ", endCreateTime=" + endCreateTime +
                ", deleted=" + deleted +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public OrderQueryDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public OrderQueryDTO setOrderNo(String orderNo) {
        this.orderNo = orderNo;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public OrderQueryDTO setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public Date getStartPaymentTime() {
        return startPaymentTime;
    }

    public OrderQueryDTO setStartPaymentTime(Date startPaymentTime) {
        this.startPaymentTime = startPaymentTime;
        return this;
    }

    public Date getEndPaymentTime() {
        return endPaymentTime;
    }

    public OrderQueryDTO setEndPaymentTime(Date endPaymentTime) {
        this.endPaymentTime = endPaymentTime;
        return this;
    }

    public Date getStartCreateTime() {
        return startCreateTime;
    }

    public OrderQueryDTO setStartCreateTime(Date startCreateTime) {
        this.startCreateTime = startCreateTime;
        return this;
    }

    public Date getEndCreateTime() {
        return endCreateTime;
    }

    public OrderQueryDTO setEndCreateTime(Date endCreateTime) {
        this.endCreateTime = endCreateTime;
        return this;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public OrderQueryDTO setDeleted(Integer deleted) {
        this.deleted = deleted;
        return this;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public OrderQueryDTO setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public OrderQueryDTO setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }
}
