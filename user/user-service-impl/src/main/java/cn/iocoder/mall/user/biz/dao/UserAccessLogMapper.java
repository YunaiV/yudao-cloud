package cn.iocoder.mall.user.biz.dao;

import cn.iocoder.mall.user.biz.dataobject.UserAccessLogDO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccessLogMapper {

    void insert(UserAccessLogDO entity);

}
