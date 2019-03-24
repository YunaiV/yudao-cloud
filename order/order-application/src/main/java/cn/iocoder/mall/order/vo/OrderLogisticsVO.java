package cn.iocoder.mall.order.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 订单物流
 *
 * @author Sin
 * @time 2019-03-24 11:01
 */
@ApiModel("订单物流信息")
public class OrderLogisticsVO implements Serializable {

    /**
     * 订单 id
     */
    @NotNull(message = "订单id不能为空!")
    @ApiModelProperty("物流id")
    private Integer id;
    /**
     * 收件区域编号
     */
    @NotNull(message = "区域编号不能为空!")
    @ApiModelProperty("区域编号")
    private String areaNo;
    /**
     * 收件人名称
     */
    @NotNull(message = "收件人姓名不能为空!")
    @ApiModelProperty("收件人姓名")
    private String name;
    /**
     * 收件手机号
     */
    @NotNull(message = "手机号不能为空!")
    @Size(max = 11, min = 11, message = "手机号在11位!")
    @ApiModelProperty("手机号")
    private String mobile;
    /**
     * 收件详细地址
     */
    @NotNull(message = "收件详细地址不能为空")
    @Size(max = 250, min = 5, message = "收件地址应该在 5 ~ 250 个字符之间")
    @ApiModelProperty("手机地址")
    private String address;
    /**
     * 物流编号
     */
    @ApiModelProperty("物流订号")
    private String logisticsNo;

    @Override
    public String toString() {
        return "OrderLogisticsVO{" +
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

    public OrderLogisticsVO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getAreaNo() {
        return areaNo;
    }

    public OrderLogisticsVO setAreaNo(String areaNo) {
        this.areaNo = areaNo;
        return this;
    }

    public String getName() {
        return name;
    }

    public OrderLogisticsVO setName(String name) {
        this.name = name;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public OrderLogisticsVO setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public OrderLogisticsVO setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getLogisticsNo() {
        return logisticsNo;
    }

    public OrderLogisticsVO setLogisticsNo(String logisticsNo) {
        this.logisticsNo = logisticsNo;
        return this;
    }
}
