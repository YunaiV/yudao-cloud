package cn.iocoder.mall.order.dataobject;

import java.io.Serializable;

/**
 * 订单物流信息
 *
 * @author Sin
 * @time 2019-03-19 20:47
 */
public class OrderLogisticsDO implements Serializable {

    /**
     * id
     */
    private Integer id;
    /**
     * 收件区域编号
     */
    private String areaNo;
    /**
     * 收件人名称
     */
    private String name;
    /**
     * 收件手机号
     */
    private String mobile;
    /**
     * 收件详细地址
     */
    private String address;
    /**
     * 物流编号
     */
    private String logisticsNo;

    @Override
    public String toString() {
        return "OrderLogisticsDO{" +
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

    public OrderLogisticsDO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getAreaNo() {
        return areaNo;
    }

    public OrderLogisticsDO setAreaNo(String areaNo) {
        this.areaNo = areaNo;
        return this;
    }

    public String getName() {
        return name;
    }

    public OrderLogisticsDO setName(String name) {
        this.name = name;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public OrderLogisticsDO setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public OrderLogisticsDO setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getLogisticsNo() {
        return logisticsNo;
    }

    public OrderLogisticsDO setLogisticsNo(String logisticsNo) {
        this.logisticsNo = logisticsNo;
        return this;
    }
}
