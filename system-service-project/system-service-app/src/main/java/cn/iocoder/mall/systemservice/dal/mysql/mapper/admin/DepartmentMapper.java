package cn.iocoder.mall.systemservice.dal.mysql.mapper.admin;

import cn.iocoder.mall.mybatis.core.query.QueryWrapperX;
import cn.iocoder.mall.systemservice.dal.mysql.dataobject.admin.DepartmentDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentMapper extends BaseMapper<DepartmentDO> {

    default DepartmentDO selectByPidAndName(Integer pid, String name) {
        return selectOne(new QueryWrapperX<DepartmentDO>().eqIfPresent("pid", pid)
                .eqIfPresent("name", name));
    }

}
