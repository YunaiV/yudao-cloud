package cn.iocoder.mall.admin.dao;

import cn.iocoder.mall.admin.dataobject.AdminAccessLogDO;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminAccessLogMapper {

    void insert(AdminAccessLogDO entity);

}
