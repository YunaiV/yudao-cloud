package cn.iocoder.mall.userservice.dal.mysql.mapper.user;

import cn.iocoder.mall.userservice.dal.mysql.dataobject.user.UserDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<UserDO> {

    default UserDO selectByMobile(String mobile) {
        return selectOne(new QueryWrapper<UserDO>()
                .eq("mobile", mobile)
        );
    }

}
