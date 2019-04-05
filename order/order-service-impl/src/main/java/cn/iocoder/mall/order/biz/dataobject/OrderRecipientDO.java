package cn.iocoder.mall.order.biz.dataobject;

import cn.iocoder.common.framework.dataobject.BaseDO;

/**
 * 订单收件人信息 order_recipient (配送信息)
 *
 * @author Sin
 * @time 2019-03-31 11:37
 */
public class OrderRecipientDO extends BaseDO {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 订单id
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
     * 配送类型
     *
     * - 1 快递
     */
    private Integer type;
    /**
     * 收件详细地址
     */
    private String address;

    @Override
    public String toString() {
        return "OrderRecipientDO{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", areaNo='" + areaNo + '\'' +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", type=" + type +
                ", address='" + address + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public OrderRecipientDO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public OrderRecipientDO setOrderId(Integer orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getAreaNo() {
        return areaNo;
    }

    public OrderRecipientDO setAreaNo(String areaNo) {
        this.areaNo = areaNo;
        return this;
    }

    public String getName() {
        return name;
    }

    public OrderRecipientDO setName(String name) {
        this.name = name;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public OrderRecipientDO setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public OrderRecipientDO setType(Integer type) {
        this.type = type;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public OrderRecipientDO setAddress(String address) {
        this.address = address;
        return this;
    }
}
