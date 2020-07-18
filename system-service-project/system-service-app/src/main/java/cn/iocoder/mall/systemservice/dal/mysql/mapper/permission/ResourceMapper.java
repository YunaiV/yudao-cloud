package cn.iocoder.mall.systemservice.dal.mysql.mapper.permission;

import cn.iocoder.mall.mybatis.core.query.QueryWrapperX;
import cn.iocoder.mall.systemservice.dal.mysql.dataobject.permission.ResourceDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ResourceMapper extends BaseMapper<ResourceDO> {

    default ResourceDO selectByPermission(String permission) {
        return selectOne(new QueryWrapper<ResourceDO>().eq("permission", permission));
    }

    /**
     * 查询指定类型的资源列表
     *
     * @param type 资源类型，允许空
     * @return 资源列表
     */
    default List<ResourceDO> selectListByType(Integer type) {
        return selectList(new QueryWrapperX<ResourceDO>().eqIfPresent("type", type));
    }

    default ResourceDO selectByPidAndName(Integer pid, String name) {
        return selectOne(new QueryWrapperX<ResourceDO>().eqIfPresent("pid", pid)
            .eqIfPresent("name", name));
    }

    default List<ResourceDO> selectListByPermissions(Collection<String> permissions) {
        return selectList(new QueryWrapper<ResourceDO>().in("permission", permissions));
    }

    default List<ResourceDO> selectListByIdsAndType(Collection<Integer> ids, Integer type) {
        return selectList(new QueryWrapperX<ResourceDO>().inIfPresent("id", ids)
                .eqIfPresent("type", type));
    }

    default int selectCountByIdsAndType(Collection<Integer> ids, Integer type) {
        return selectCount(new QueryWrapperX<ResourceDO>().inIfPresent("id", ids)
                .eqIfPresent("type", type));
    }

    default int selectCountByPid(Integer pid) {
        return selectCount(new QueryWrapper<ResourceDO>().eq("pid", pid));
    }

}
