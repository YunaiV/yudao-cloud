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
     * 收件区域编号
     */
    @NotNull
    private String receiverAreaNo;
    /**
     * 收件手机号
     */
    @NotNull
    private String receiverMobile;
    /**
     * 收件详细地址
     */
    @NotNull
    private String receiverAddress;
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
                "receiverAreaNo='" + receiverAreaNo + '\'' +
                ", receiverMobile='" + receiverMobile + '\'' +
                ", receiverAddress='" + receiverAddress + '\'' +
                ", remark='" + remark + '\'' +
                ", orderItems=" + orderItems +
                '}';
    }

    public String getReceiverAreaNo() {
        return receiverAreaNo;
    }

    public OrderCreateDTO setReceiverAreaNo(String receiverAreaNo) {
        this.receiverAreaNo = receiverAreaNo;
        return this;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public OrderCreateDTO setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
        return this;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public OrderCreateDTO setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
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
