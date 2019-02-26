package cn.iocoder.mall.admin.dao;

import cn.iocoder.mall.admin.dataobject.RoleResourceDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleResourceMapper {

    List<RoleResourceDO> selectByResourceHandler(@Param("resourceHandler") String resourceHandler);

}