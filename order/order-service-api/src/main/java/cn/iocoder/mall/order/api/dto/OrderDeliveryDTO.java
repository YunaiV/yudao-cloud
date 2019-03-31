package cn.iocoder.mall.order.api.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 订单发货
 *
 * @author Sin
 * @time 2019-03-30 22:31
 */
public class OrderDeliveryDTO implements Serializable {

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

    ///
    /// 物理信息是跟 orderItem 走

    /**
     * 订单 orderItemId
     */
    private List<Integer> orderItemIds;

    @Override
    public String toString() {
        return "OrderDeliverGoodsDTO{" +
                "areaNo='" + areaNo + '\'' +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                ", logistics=" + logistics +
                ", logisticsNo='" + logisticsNo + '\'' +
                ", orderItemIds=" + orderItemIds +
                '}';
    }

    public String getAreaNo() {
        return areaNo;
    }

    public OrderDeliveryDTO setAreaNo(String areaNo) {
        this.areaNo = areaNo;
        return this;
    }

    public String getName() {
        return name;
    }

    public OrderDeliveryDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public OrderDeliveryDTO setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public OrderDeliveryDTO setAddress(String address) {
        this.address = address;
        return this;
    }

    public Integer getLogistics() {
        return logistics;
    }

    public OrderDeliveryDTO setLogistics(Integer logistics) {
        this.logistics = logistics;
        return this;
    }

    public String getLogisticsNo() {
        return logisticsNo;
    }

    public OrderDeliveryDTO setLogisticsNo(String logisticsNo) {
        this.logisticsNo = logisticsNo;
        return this;
    }

    public List<Integer> getOrderItemIds() {
        return orderItemIds;
    }

    public OrderDeliveryDTO setOrderItemIds(List<Integer> orderItemIds) {
        this.orderItemIds = orderItemIds;
        return this;
    }
}
