package cn.iocoder.mall.system.biz.dao.account;

import cn.iocoder.mall.system.biz.dataobject.account.AccountDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountMapper extends BaseMapper<AccountDO> {

    default AccountDO selectByUsername(String username) {
        return selectOne(new QueryWrapper<AccountDO>()
                .eq("username", username)
        );
    }

}
