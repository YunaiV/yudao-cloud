package cn.iocoder.mall.user.dao;

import cn.iocoder.mall.user.dataobject.UserRegisterDO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegisterMapper {

    void insert(UserRegisterDO entity);

}