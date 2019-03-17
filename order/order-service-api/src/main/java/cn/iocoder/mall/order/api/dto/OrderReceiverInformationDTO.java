package cn.iocoder.mall.order.api.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 订单收件人信息
 *
 * @author Sin
 * @time 2019-03-17 20:22
 */
public class OrderReceiverInformationDTO implements Serializable {

    /**
     * 订单 id
     */
    private Integer id;
    /**
     * 收件区域编号
     */
    @NotNull
    private String receiverAreaNo;
    /**
     * 收件人名称
     */
    @NotNull
    private String receiverName;
    /**
     * 收件手机号
     */
    @NotNull
    @Size(max = 11, min = 11)
    // TODO: 2019-03-17 Sin 此处需要添加 手机号校验，需要添加新的注解
    private String receiverMobile;
    /**
     * 收件详细地址
     */
    @NotNull
    @Size(max = 250, min = 10, message = "收件地址应该在 10 ~ 250 个字符之间")
    private String receiverAddress;

    @Override
    public String toString() {
        return "OrderReceiverInformationDTO{" +
                "id=" + id +
                ", receiverAreaNo='" + receiverAreaNo + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverMobile='" + receiverMobile + '\'' +
                ", receiverAddress='" + receiverAddress + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public OrderReceiverInformationDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getReceiverAreaNo() {
        return receiverAreaNo;
    }

    public OrderReceiverInformationDTO setReceiverAreaNo(String receiverAreaNo) {
        this.receiverAreaNo = receiverAreaNo;
        return this;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public OrderReceiverInformationDTO setReceiverName(String receiverName) {
        this.receiverName = receiverName;
        return this;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public OrderReceiverInformationDTO setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
        return this;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public OrderReceiverInformationDTO setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
        return this;
    }
}
