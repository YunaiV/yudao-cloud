package cn.iocoder.mall.system.biz.dao.authorization;

import cn.iocoder.mall.mybatis.query.QueryWrapperX;
import cn.iocoder.mall.system.biz.dataobject.authorization.ResourceDO;
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

    default List<ResourceDO> selectListByPermissions(Collection<String> permissions) {
        return selectList(new QueryWrapper<ResourceDO>().in("permission", permissions));
    }

    default List<ResourceDO> selectListByIdsAndType(Collection<Integer> ids, Integer type) {
        return selectList(new QueryWrapperX<ResourceDO>().inIfPresent("id", ids)
                .eqIfPresent("type", type));
    }

    default int selectCountByPid(Integer pid) {
        return selectCount(new QueryWrapper<ResourceDO>().eq("pid", pid));
    }

}
