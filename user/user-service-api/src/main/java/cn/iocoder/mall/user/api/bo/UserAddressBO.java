package cn.iocoder.mall.user.api.bo;

/**
 * @author Sin
 * @time 2019-04-06 13:28
 */
public class UserAddressBO {


    /**
     * 编号
     */
    private Integer id;
    /**
     * 用户编号
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
        return "UserAddressBO{" +
                "id=" + id +
                ", userId=" + userId +
                ", areaNo='" + areaNo + '\'' +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public UserAddressBO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public UserAddressBO setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public String getAreaNo() {
        return areaNo;
    }

    public UserAddressBO setAreaNo(String areaNo) {
        this.areaNo = areaNo;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserAddressBO setName(String name) {
        this.name = name;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public UserAddressBO setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public UserAddressBO setAddress(String address) {
        this.address = address;
        return this;
    }
}
