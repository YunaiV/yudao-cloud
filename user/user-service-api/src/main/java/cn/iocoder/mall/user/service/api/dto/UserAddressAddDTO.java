package cn.iocoder.mall.user.service.api.dto;

import java.io.Serializable;

/**
 * 用户地址 add
 *
 * @author Sin
 * @time 2019-04-06 13:25
 */
public class UserAddressAddDTO implements Serializable {

    /**
     * 收件区域编号
     */
    private Integer userId;
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

    @Override
    public String toString() {
        return "UserAddressAddDTO{" +
                "userId=" + userId +
                ", areaNo='" + areaNo + '\'' +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public Integer getUserId() {
        return userId;
    }

    public UserAddressAddDTO setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public String getAreaNo() {
        return areaNo;
    }

    public UserAddressAddDTO setAreaNo(String areaNo) {
        this.areaNo = areaNo;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserAddressAddDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public UserAddressAddDTO setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public UserAddressAddDTO setAddress(String address) {
        this.address = address;
        return this;
    }
}
