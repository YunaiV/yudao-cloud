package cn.iocoder.mall.admin.dao;

import cn.iocoder.common.framework.mybatis.QueryWrapperX;
import cn.iocoder.mall.admin.dataobject.ResourceDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ResourceMapper extends BaseMapper<ResourceDO> {

    @Deprecated
    List<ResourceDO> selectListByTypeAndRoleIds(@Param("type") Integer type,
                                                @Param("roleIds") Set<Integer> roleIds);

    default List<ResourceDO> selectListByPermission(String permission) {
        return selectList(new QueryWrapperX<ResourceDO>().like("permissions", permission));
    }

    default List<ResourceDO> selectListByType(Integer type) {
        return selectList(new QueryWrapperX<ResourceDO>().eqIfPresent("type", type));
    }

    default List<ResourceDO> selectListByIds(Set<Integer> ids) {
        return selectList(new QueryWrapper<ResourceDO>().in("id", ids));
    }

    default int selectCountByPid(Integer pid) {
        return selectCount(new QueryWrapper<ResourceDO>().eq("pid", pid));
    }

}
