package cn.iocoder.mall.admin.dao;

import cn.iocoder.mall.admin.dataobject.AdminDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper {

    AdminDO selectByUsername(@Param("username") String username);

}