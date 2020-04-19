package cn.iocoder.mall.system.biz.service.account;

import cn.iocoder.mall.system.biz.bo.account.AccountBO;
import cn.iocoder.mall.system.biz.dto.account.AccountCreateDTO;

/**
 * 账号 Service 接口
 */
public interface AccountService {

    AccountBO getByUsername(String username);

    AccountBO getByMobile(String mobile);

    boolean matchPassword(String rawPassword, String encodedPassword);

    AccountBO create(AccountCreateDTO createDTO);

}
