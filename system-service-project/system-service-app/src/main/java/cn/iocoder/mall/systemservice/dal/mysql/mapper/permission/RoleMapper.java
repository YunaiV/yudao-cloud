package cn.iocoder.mall.systemservice.dal.mysql.mapper.permission;

import cn.iocoder.mall.mybatis.core.query.QueryWrapperX;
import cn.iocoder.mall.systemservice.dal.mysql.dataobject.permission.RoleDO;
import cn.iocoder.mall.systemservice.service.permission.bo.RolePageBO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMapper extends BaseMapper<RoleDO> {

    default IPage<RoleDO> selectPage(RolePageBO pageBO) {
        return selectPage(new Page<>(pageBO.getPageNo(), pageBO.getPageSize()),
                new QueryWrapperX<RoleDO>().likeIfPresent("name", pageBO.getName()));
    }

//    default List<RoleDO> selectListByIds(Collection<Integer> ids) {
//        return selectList(new QueryWrapperX<RoleDO>().inIfPresent("id", ids));
//    }

    default RoleDO selectByName(String name) {
        return selectOne(new QueryWrapperX<RoleDO>().eqIfPresent("name", name));
    }

    default RoleDO selectByCode(String code) {
        return selectOne(new QueryWrapperX<RoleDO>().eqIfPresent("code", code));
    }

}
