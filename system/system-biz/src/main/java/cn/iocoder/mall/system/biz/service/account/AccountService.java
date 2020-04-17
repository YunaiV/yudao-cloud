package cn.iocoder.mall.system.biz.service.account;

import cn.iocoder.mall.system.biz.bo.account.AccountBO;

/**
 * 账号 Service 接口
 */
public interface AccountService {

    AccountBO getByUsername(String username);

    boolean matchPassword(String rawPassword, String encodedPassword);

}
