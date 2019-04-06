package cn.iocoder.mall.user.application.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 用户地址 add
 *
 * @author Sin
 * @time 2019-04-06 14:13
 */
@ApiModel("用户地址-更新")
public class UserAddressUpdatePO implements Serializable {


    /**
     * 收件区域编号
     */
    @ApiModelProperty("地址编号")
    @NotNull(message = "地址编号为空!")
    private Integer id;
    /**
     * 收件区域编号
     */
    @ApiModelProperty("区域编号")
    @NotNull(message = "区域编号不能为空!")
    private String areaNo;
    /**
     * 收件人名称
     */
    @ApiModelProperty("收件人名称")
    @NotNull(message = "请填写收人信息!")
    private String name;
    /**
     * 收件手机号
     */
    @ApiModelProperty("收件手机号")
    @NotNull(message = "手机号为不能为空!")
    @Size(min = 11, max = 11, message = "手机号为 11 位!")
    private String mobile;
    /**
     * 收件详细地址
     */
    @ApiModelProperty("收件详细地址")
    @NotNull(message = "详细地址不能为空")
    @Size(min = 10, max = 100, message = "地址在 10 ~ 100 字之间!")
    private String address;

    @Override
    public String toString() {
        return "UserAddressUpdatePO{" +
                "id=" + id +
                ", areaNo='" + areaNo + '\'' +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public UserAddressUpdatePO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getAreaNo() {
        return areaNo;
    }

    public UserAddressUpdatePO setAreaNo(String areaNo) {
        this.areaNo = areaNo;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserAddressUpdatePO setName(String name) {
        this.name = name;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public UserAddressUpdatePO setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public UserAddressUpdatePO setAddress(String address) {
        this.address = address;
        return this;
    }
}
