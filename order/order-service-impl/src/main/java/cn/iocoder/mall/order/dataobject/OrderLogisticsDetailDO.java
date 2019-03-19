package cn.iocoder.mall.order.dataobject;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单物流 - 详细信息
 *
 * - 同步第三方物流信息
 *
 * @author Sin
 * @time 2019-03-19 20:48
 */
public class OrderLogisticsDetailDO implements Serializable {

    /**
     * id
     */
    private Integer id;
    /**
     * 物流id
     */
    private Integer orderLogisticsId;
    /**
     * 物流时间
     */
    private Date logisticsTime;
    /**
     * 物流信息
     */
    private String logisticsInformation;
    /**
     * 创建时间（同步时间）
     */
    private Date createTime;

    @Override
    public String toString() {
        return "OrderLogisticsDetailDO{" +
                "id=" + id +
                ", orderLogisticsId=" + orderLogisticsId +
                ", logisticsTime=" + logisticsTime +
                ", logisticsInformation='" + logisticsInformation + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public OrderLogisticsDetailDO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getOrderLogisticsId() {
        return orderLogisticsId;
    }

    public OrderLogisticsDetailDO setOrderLogisticsId(Integer orderLogisticsId) {
        this.orderLogisticsId = orderLogisticsId;
        return this;
    }

    public Date getLogisticsTime() {
        return logisticsTime;
    }

    public OrderLogisticsDetailDO setLogisticsTime(Date logisticsTime) {
        this.logisticsTime = logisticsTime;
        return this;
    }

    public String getLogisticsInformation() {
        return logisticsInformation;
    }

    public OrderLogisticsDetailDO setLogisticsInformation(String logisticsInformation) {
        this.logisticsInformation = logisticsInformation;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public OrderLogisticsDetailDO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
}
