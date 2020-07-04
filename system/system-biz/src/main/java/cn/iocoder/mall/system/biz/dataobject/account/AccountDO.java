package cn.iocoder.mall.system.biz.dataobject.account;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.mall.mybatis.dataobject.DeletableDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 账号实体
 */
@TableName(value = "account")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AccountDO extends DeletableDO {

    /**
     * 账号编号
     */
    private Integer id;
    /**
     * 登陆账号
     */
    private String username;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 密码
     *
     * // TODO 芋艿 暂时明文
     */
    private String password;
    /**
     * 账号状态
     *
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;

    /**
     * 创建 IP
     */
    private String createIp;
    /**
     * 最后登陆时间
     */
    private Date lastLoginTime;
    /**
     * 最后登陆 IP
     */
    private String lastLoginIp;
    /**
     * 登陆次数
     */
    private Integer loginTimes;

}
