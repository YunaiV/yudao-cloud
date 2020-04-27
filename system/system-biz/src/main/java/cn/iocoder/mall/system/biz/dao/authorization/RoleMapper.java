package cn.iocoder.mall.system.biz.dao.authorization;

import cn.iocoder.mall.mybatis.query.QueryWrapperX;
import cn.iocoder.mall.system.biz.dataobject.authorization.RoleDO;
import cn.iocoder.mall.system.biz.dto.authorization.RolePageDTO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper extends BaseMapper<RoleDO> {

    default List<RoleDO> selectList() {
        return selectList(new QueryWrapper<>());
    }

    default IPage<RoleDO> selectPage(RolePageDTO rolePageDTO) {
        return selectPage(new Page<>(rolePageDTO.getPageNo(), rolePageDTO.getPageSize()),
                new QueryWrapperX<RoleDO>().likeIfPresent("name", rolePageDTO.getName()));
    }

}
