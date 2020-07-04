package cn.iocoder.mall.systemservice.dal.mysql.mapper.permission;

import cn.iocoder.mall.systemservice.dal.mysql.dataobject.permission.RoleResourceDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface RoleResourceMapper extends BaseMapper<RoleResourceDO> {

    /**
     * 批量插入。因为 MyBaits Plus 的批量插入是基于 Service 实现，所以只好写 XML
     *
     * @param roleResources 数组
     */
    int insertList(@Param("roleResources") List<RoleResourceDO> roleResources);

    default List<RoleResourceDO> selectListByResourceId(Integer resourceId) {
        return selectList(new QueryWrapper<RoleResourceDO>().eq("resource_id", resourceId));
    }

    default List<RoleResourceDO> selectListByResourceIds(Collection<Integer> resourceIds) {
        return selectList(new QueryWrapper<RoleResourceDO>().in("resource_id", resourceIds));
    }

    default List<RoleResourceDO> selectListByRoleId(Integer roleId) {
        return selectList(new QueryWrapper<RoleResourceDO>().eq("role_id", roleId));
    }

    default List<RoleResourceDO> selectListByRoleIds(Collection<Integer> roleIds) {
        return selectList(new QueryWrapper<RoleResourceDO>().in("role_id", roleIds));
    }

    default int deleteByResourceId(Integer resourceId) {
        return delete(new QueryWrapper<RoleResourceDO>().eq("resource_id", resourceId));
    }

    default int deleteByRoleId(Integer roleId) {
        return delete(new QueryWrapper<RoleResourceDO>().eq("role_id", roleId));
    }

}
