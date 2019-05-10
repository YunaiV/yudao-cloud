package cn.iocoder.mall.admin.dao;

import cn.iocoder.mall.admin.dataobject.AccessLogDO;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessLogMapper {

    void insert(AccessLogDO entity);

}
