package cn.iocoder.mall.admin.dao;

import cn.iocoder.mall.admin.dataobject.AdminRoleDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface AdminRoleMapper extends BaseMapper<AdminRoleDO> {

    List<AdminRoleDO> selectByAdminId(@Param("adminId") Integer adminId);

    default List<AdminRoleDO> selectListByAdminIds(Collection<Integer> adminIds) {
        return selectList(new QueryWrapper<AdminRoleDO>().in("admin_id", adminIds));
    }

    int updateToDeletedByAdminId(@Param("adminId") Integer adminId);

    int updateToDeletedByRoleId(@Param("roleId") Integer roleId);

    void insertList(@Param("adminRoleDOs") List<AdminRoleDO> adminRoleDOs);

}
