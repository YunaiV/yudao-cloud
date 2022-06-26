package cn.iocoder.mall.userservice.dal.mysql.dataobject.user;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.mall.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户实体
 *
 * uk_mobile 索引：基于 {@link #mobile} 字段
 */
@TableName(value = "users")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class UserDO extends BaseDO {

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
    /**
     * 注册 IP
     */
    private String createIp;

}
