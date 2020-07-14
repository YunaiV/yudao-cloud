package cn.iocoder.mall.systemservice.dal.mysql.mapper.permission;

import cn.iocoder.mall.systemservice.dal.mysql.dataobject.permission.AdminRoleDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface AdminRoleMapper extends BaseMapper<AdminRoleDO> {

    default List<AdminRoleDO> selectByAdminId(Integer adminId) {
        return selectList(new QueryWrapper<AdminRoleDO>().eq("admin_id", adminId));
    }

    default List<AdminRoleDO> selectListByAdminId(Integer adminId) {
        return selectList(new QueryWrapper<AdminRoleDO>().eq("admin_id", adminId));
    }

    default List<AdminRoleDO> selectListByAdminIds(Collection<Integer> adminIds) {
        return selectList(new QueryWrapper<AdminRoleDO>().in("admin_id", adminIds));
    }

    default int deleteByAdminId(Integer adminId) {
        return delete(new QueryWrapper<AdminRoleDO>().eq("admin_id", adminId));
    }

    default int deleteByRoleId(Integer roleId) {
        return delete(new QueryWrapper<AdminRoleDO>().eq("role_id", roleId));
    }

    /**
     * 批量插入。因为 MyBaits Plus 的批量插入是基于 Service 实现，所以只好写 XML
     *
     * @param adminRoleDOs 数组
     */
    int insertList(@Param("adminRoleDOs") List<AdminRoleDO> adminRoleDOs);

}
