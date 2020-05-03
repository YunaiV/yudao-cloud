package cn.iocoder.mall.user.rpc.request.user;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 用户地址 add
 *
 * @author Sin
 * @time 2019-04-06 13:25
 */
@Data
@Accessors(chain = true)
public class UserAddressAddRequest implements Serializable {

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
    /**
     * 是否默认
     *
     * - 1 不是
     * - 2 是
     */
    private Integer hasDefault;
}
