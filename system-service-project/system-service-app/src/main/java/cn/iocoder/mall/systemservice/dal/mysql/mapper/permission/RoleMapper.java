package cn.iocoder.mall.systemservice.dal.mysql.mapper.permission;

import cn.iocoder.mall.mybatis.query.QueryWrapperX;
import cn.iocoder.mall.systemservice.dal.mysql.dataobject.permission.RoleDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface RoleMapper extends BaseMapper<RoleDO> {

//    default IPage<RoleDO> selectPage(RolePageDTO rolePageDTO) {
//        return selectPage(new Page<>(rolePageDTO.getPageNo(), rolePageDTO.getPageSize()),
//                new QueryWrapperX<RoleDO>().likeIfPresent("name", rolePageDTO.getName()));
//    }

    default List<RoleDO> selectListByIds(Collection<Integer> ids) {
        return selectList(new QueryWrapperX<RoleDO>().inIfPresent("id", ids));
    }

    default RoleDO selectByName(String name) {
        return selectOne(new QueryWrapperX<RoleDO>().eqIfPresent("name", name));
    }

    default RoleDO selectByCode(String code) {
        return selectOne(new QueryWrapperX<RoleDO>().eqIfPresent("code", code));
    }

}
