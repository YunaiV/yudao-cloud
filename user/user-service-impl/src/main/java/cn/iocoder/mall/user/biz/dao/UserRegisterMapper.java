package cn.iocoder.mall.user.biz.dao;

import cn.iocoder.mall.user.biz.dataobject.UserRegisterDO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegisterMapper {

    void insert(UserRegisterDO entity);

}