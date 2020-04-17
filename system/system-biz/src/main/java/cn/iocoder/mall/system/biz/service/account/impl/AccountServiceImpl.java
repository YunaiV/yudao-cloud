package cn.iocoder.mall.system.biz.service.account.impl;

import cn.iocoder.mall.system.biz.bo.account.AccountBO;
import cn.iocoder.mall.system.biz.convert.AccountConvert;
import cn.iocoder.mall.system.biz.dao.account.AccountMapper;
import cn.iocoder.mall.system.biz.dataobject.account.AccountDO;
import cn.iocoder.mall.system.biz.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public AccountBO getByUsername(String username) {
        AccountDO accountDO = accountMapper.selectByUsername(username);
        return AccountConvert.INSTANCE.convert(accountDO);
    }

    @Override
    public boolean matchPassword(String rawPassword, String encodedPassword) {
        return Objects.equals(rawPassword, encodedPassword);
    }

}
