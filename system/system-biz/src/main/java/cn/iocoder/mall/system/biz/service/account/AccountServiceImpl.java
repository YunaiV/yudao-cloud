package cn.iocoder.mall.system.biz.service.account;

import cn.iocoder.common.framework.constant.CommonStatusEnum;
import cn.iocoder.mall.system.biz.bo.account.AccountBO;
import cn.iocoder.mall.system.biz.convert.account.AccountConvert;
import cn.iocoder.mall.system.biz.dao.account.AccountMapper;
import cn.iocoder.mall.system.biz.dataobject.account.AccountDO;
import cn.iocoder.mall.system.biz.dto.account.AccountCreateDTO;
import cn.iocoder.mall.system.biz.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public AccountBO getByMobile(String mobile) {
        AccountDO accountDO = accountMapper.selectByMobile(mobile);
        return AccountConvert.INSTANCE.convert(accountDO);
    }

    @Override
    public boolean matchPassword(String rawPassword, String encodedPassword) {
        return Objects.equals(rawPassword, encodedPassword);
    }

    @Override
    public AccountBO create(AccountCreateDTO createDTO) {
        // 插入
        AccountDO accountDO = AccountConvert.INSTANCE.convert(createDTO);
        accountDO.setStatus(CommonStatusEnum.ENABLE.getValue());
        accountDO.setCreateTime(new Date());
        accountMapper.insert(accountDO);
        // 转换返回
        return AccountConvert.INSTANCE.convert(accountDO);
    }

}
