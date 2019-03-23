package cn.iocoder.mall.order.api.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 订单收件人信息
 *
 * @author Sin
 * @time 2019-03-17 20:22
 */
public class OrderLogisticsDTO implements Serializable {

    /**
     * 订单 id
     */
    private Integer id;
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
    @Size(max = 11, min = 11)
    // TODO: 2019-03-17 Sin 此处需要添加 手机号校验，需要添加新的注解
    private String mobile;
    /**
     * 收件详细地址
     */
    @NotNull
    @Size(max = 250, min = 10, message = "收件地址应该在 10 ~ 250 个字符之间")
    private String address;
    /**
     * 物流编号
     */
    @NotNull
    private String logisticsNo;

    @Override
    public String toString() {
        return "OrderLogisticsDTO{" +
                "id=" + id +
                ", areaNo='" + areaNo + '\'' +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                ", logisticsNo='" + logisticsNo + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public OrderLogisticsDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getAreaNo() {
        return areaNo;
    }

    public OrderLogisticsDTO setAreaNo(String areaNo) {
        this.areaNo = areaNo;
        return this;
    }

    public String getName() {
        return name;
    }

    public OrderLogisticsDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public OrderLogisticsDTO setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public OrderLogisticsDTO setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getLogisticsNo() {
        return logisticsNo;
    }

    public OrderLogisticsDTO setLogisticsNo(String logisticsNo) {
        this.logisticsNo = logisticsNo;
        return this;
    }
}
