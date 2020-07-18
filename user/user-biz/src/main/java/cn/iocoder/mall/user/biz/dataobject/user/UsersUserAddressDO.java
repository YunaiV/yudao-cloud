package cn.iocoder.mall.user.biz.dataobject.user;

import cn.iocoder.mall.mybatis.core.dataobject.DeletableDO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户地址信息
 *
 * @author Sin
 * @time 2019-04-06 13:22
 */
@Data
// TODO FROM 芋艿 to 小范：如果继承了，需要添加 @EqualsAndHashCode(callSuper = true) 注解
@Accessors(chain = true)
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
     * 是否为默认
     */
    private Integer hasDefault;

}
