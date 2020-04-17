package cn.iocoder.mall.system.biz.bo.account;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 账号模块 - 用户名登陆 BO
 */
@Data
@Accessors(chain = true)
public class AccountUsernameAuthorizeBO {

    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;

}
