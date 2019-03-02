package cn.iocoder.mall.admin.dao;

import cn.iocoder.mall.admin.dataobject.AdminRoleDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRoleMapper {

    List<AdminRoleDO> selectByAdminId(@Param("adminId") Integer adminId);

    int updateToDeletedByAdminId(@Param("adminId") Integer adminId);

    int updateToDeletedByRoleId(@Param("roleId") Integer roleId);

}