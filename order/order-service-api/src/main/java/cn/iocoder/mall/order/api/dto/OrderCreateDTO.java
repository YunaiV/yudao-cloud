package cn.iocoder.mall.order.api.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * 订单创建
 *
 * @author Sin
 * @time 2019-03-16 14:42
 */
public class OrderCreateDTO implements Serializable {

    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 收件区域编号
     */
    @NotNull
    private String areaNo;
    /**
     * 收件人名称
     */
    @NotNull
    private String name;
    /**
     * 收件手机号
     */
    @NotNull
    private String mobile;
    /**
     * 收件详细地址
     */
    @NotNull
    private String address;
    /**
     * 备注
     */
    private String remark;

    ///
    /// order item

    @NotNull
    @Size(max = 1000, min = 1)
    private List<OrderCreateItemDTO> orderItems;

    @Override
    public String toString() {
        return "OrderCreateDTO{" +
                "userId=" + userId +
                ", areaNo='" + areaNo + '\'' +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                ", remark='" + remark + '\'' +
                ", orderItems=" + orderItems +
                '}';
    }

    public Integer getUserId() {
        return userId;
    }

    public OrderCreateDTO setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public String getAreaNo() {
        return areaNo;
    }

    public OrderCreateDTO setAreaNo(String areaNo) {
        this.areaNo = areaNo;
        return this;
    }

    public String getName() {
        return name;
    }

    public OrderCreateDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public OrderCreateDTO setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public OrderCreateDTO setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public OrderCreateDTO setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public List<OrderCreateItemDTO> getOrderItems() {
        return orderItems;
    }

    public OrderCreateDTO setOrderItems(List<OrderCreateItemDTO> orderItems) {
        this.orderItems = orderItems;
        return this;
    }
}
