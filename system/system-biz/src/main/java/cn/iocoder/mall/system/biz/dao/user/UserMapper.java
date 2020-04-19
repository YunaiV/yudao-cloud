package cn.iocoder.mall.system.biz.dao.user;

import cn.iocoder.mall.system.biz.dataobject.user.UserDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<UserDO> {

    default UserDO selectByAccountId(Integer accountId) {
        return selectOne(new QueryWrapper<UserDO>()
                .eq("account_id", accountId)
        );
    }

}
