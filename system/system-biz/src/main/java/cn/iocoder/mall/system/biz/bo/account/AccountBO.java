package cn.iocoder.mall.system.biz.bo.account;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 账号信息 BO
 */
@Data
@Accessors(chain = true)
public class AccountBO {

    /**
     * 账号编号
     */
    private Integer id;
    /**
     * 登陆账号
     */
    private String username;
    /**
     * 登陆密码
     */
    private String password;

}
