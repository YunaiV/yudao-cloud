package cn.iocoder.mall.userservice.dal.mysql.dataobject.address;

import cn.iocoder.mall.mybatis.core.dataobject.DeletableDO;
import cn.iocoder.mall.userservice.enums.address.UserAddressType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户收件地址
 *
 * @author Sin
 * @time 2019-04-06 13:22
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class UsersUserAddressDO extends DeletableDO {

    /**
     * 编号
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
     * 收件手机号
     */
    private String mobile;
    /**
     * 省份编号
     */
    private Integer provinceCode;
    /**
     * 城市编号
     */
    private Integer cityCode;
    /**
     * 区域编号
     */
    private Integer countyCode;
    /**
     * 收件详细地址
     */
    private String detailAddress;
    /**
     * 地址类型，主要分为默认地址，和普通地址
     *
     * 外键 {@link UserAddressType}
     */
    private Integer type;

}
