package cn.iocoder.mall.system.biz.dao.authorization;

import cn.iocoder.common.framework.mybatis.QueryWrapperX;
import cn.iocoder.mall.system.biz.dataobject.authorization.ResourceDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public interface ResourceMapper extends BaseMapper<ResourceDO> {

    // TODO 芋艿，后续改造。
    List<ResourceDO> selectListByTypeAndRoleIds(@Param("type") Integer type,
                                                @Param("roleIds") Set<Integer> roleIds);

    default ResourceDO selectByPermission(String permission) {
        return selectOne(new QueryWrapper<ResourceDO>().eq("permission", permission));
    }

    default List<ResourceDO> selectListByPermissions(Collection<String> permissions) {
        return selectList(new QueryWrapper<ResourceDO>().in("permission", permissions));
    }

    default List<ResourceDO> selectListByType(Integer type) {
        return selectList(new QueryWrapperX<ResourceDO>().eqIfPresent("type", type));
    }

    default int selectCountByPid(Integer pid) {
        return selectCount(new QueryWrapper<ResourceDO>().eq("pid", pid));
    }

}
