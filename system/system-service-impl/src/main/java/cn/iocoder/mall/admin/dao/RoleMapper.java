package cn.iocoder.mall.admin.dao;

import cn.iocoder.common.framework.mybatis.QueryWrapperX;
import cn.iocoder.mall.admin.api.dto.role.RolePageDTO;
import cn.iocoder.mall.admin.dataobject.RoleDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface RoleMapper extends BaseMapper<RoleDO> {

    default List<RoleDO> selectListByIds(Collection<Integer> ids) {
        return selectList(new QueryWrapper<RoleDO>().in("id", ids));
    }

    default List<RoleDO> selectList() {
        return selectList(new QueryWrapper<>());
    }

    default IPage<RoleDO> selectPage(RolePageDTO rolePageDTO) {
        return selectPage(new Page<>(rolePageDTO.getPageNo(), rolePageDTO.getPageSize()),
                new QueryWrapperX<RoleDO>().likeIfPresent("name", rolePageDTO.getName()));
    }

}
