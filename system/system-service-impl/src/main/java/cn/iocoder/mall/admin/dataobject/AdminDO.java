package cn.iocoder.mall.admin.dataobject;

import cn.iocoder.common.framework.dataobject.DeletableDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 管理员实体
 */
@TableName(value = "admin")
@Data
@Accessors(chain = true)
public class AdminDO extends DeletableDO {

    /**
     * 管理员编号
     */
    private Integer id;
    /**
     * 登陆账号
     */
    private String username;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 密码
     *
     * TODO 芋艿 暂时最简单的 MD5
     */
    private String password;
    /**
     * 账号状态
     */
    private Integer status;

    // TODO 芋艿，最后登陆时间、最后登陆 IP
    // TODO 芋艿，登陆日志

}
