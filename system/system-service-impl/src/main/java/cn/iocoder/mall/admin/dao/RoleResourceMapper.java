package cn.iocoder.mall.admin.dao;

import cn.iocoder.mall.admin.dataobject.RoleResourceDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleResourceMapper {

    int insertList(@Param("roleResources") List<RoleResourceDO> resourceDOs);

    List<RoleResourceDO> selectByResourceHandler(@Param("resourceHandler") String resourceHandler);

    List<RoleResourceDO> selectByResourceId(@Param("resourceId") Integer resourceId);

    int updateToDeletedByResourceId(@Param("resourceId") Integer resourceId);

    int updateToDeletedByRoleId(@Param("roleId") Integer roleId);

}