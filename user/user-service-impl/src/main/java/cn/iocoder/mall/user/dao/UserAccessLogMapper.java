package cn.iocoder.mall.user.dao;

import cn.iocoder.mall.user.dataobject.UserAccessLogDO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccessLogMapper {

    void insert(UserAccessLogDO entity);

}
