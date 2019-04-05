package cn.iocoder.mall.order.biz.dataobject;

import cn.iocoder.common.framework.dataobject.BaseDO;

/**
 * 订单物流信息
 *
 * @author Sin
 * @time 2019-03-19 20:47
 */
public class OrderLogisticsDO extends BaseDO {

    /**
     * id
     */
    private Integer id;
    /**
     * 订单编号
     */
    private Integer orderId;
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
     * 物流 (字典)
     */
    private Integer logistics;
    /**
     * 物流编号
     */
    private String logisticsNo;

    @Override
    public String toString() {
        return "OrderLogisticsDO{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", areaNo='" + areaNo + '\'' +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                ", logistics=" + logistics +
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

    public Integer getOrderId() {
        return orderId;
    }

    public OrderLogisticsDO setOrderId(Integer orderId) {
        this.orderId = orderId;
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

    public Integer getLogistics() {
        return logistics;
    }

    public OrderLogisticsDO setLogistics(Integer logistics) {
        this.logistics = logistics;
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
