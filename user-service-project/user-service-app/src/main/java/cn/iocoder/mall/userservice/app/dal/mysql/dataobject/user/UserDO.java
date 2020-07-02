package cn.iocoder.mall.userservice.app.dal.mysql.dataobject.user;

import cn.iocoder.common.framework.constant.CommonStatusEnum;
import cn.iocoder.mall.mybatis.dataobject.DeletableDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户实体
 */
@TableName(value = "users")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class UserDO extends DeletableDO {

    /**
     * 用户编号
     */
    private Integer id;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 用户状态
     *
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 经过加密的密码串
     */
    private String password;
    /**
     * {@link #password} 的盐
     */
    private String passwordSalt;

}
