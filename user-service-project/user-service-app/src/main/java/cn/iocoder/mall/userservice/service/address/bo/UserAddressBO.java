package cn.iocoder.mall.userservice.service.address.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
* 用户收件地址 BO
*/
@Data
@Accessors(chain = true)
public class UserAddressBO {

    /**
     * 收件地址编号
     */
    private Integer id;
    /**
     * 用户编号
     */
    private Integer userId;
    /**
     * 收件人名称
     */
    private String name;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 地区编码
     */
    private Integer areaCode;
    /**
     * 收件详细地址
     */
    private String detailAddress;
    /**
     * 地址类型
     */
    private Integer type;
    /**
     * 创建时间
     */
    private Date createTime;

}
