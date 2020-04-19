package cn.iocoder.mall.system.biz.dto.account;

import lombok.Data;
import lombok.experimental.Accessors;

// TODO 注释
@Data
@Accessors(chain = true)
public class AccountCreateDTO {

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
     * 创建 IP
     */
    private String createIp;

}
