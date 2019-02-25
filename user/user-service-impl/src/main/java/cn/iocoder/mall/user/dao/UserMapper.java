package cn.iocoder.mall.user.dao;

import cn.iocoder.mall.user.dataobject.UserDO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    void insert(UserDO entity);

    UserDO selectByMobile(String mobile);

}