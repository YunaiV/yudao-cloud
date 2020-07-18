package cn.iocoder.mall.systemservice.dal.mysql.mapper.admin;

import cn.iocoder.mall.mybatis.core.query.QueryWrapperX;
import cn.iocoder.mall.systemservice.dal.mysql.dataobject.admin.AdminDO;
import cn.iocoder.mall.systemservice.service.admin.bo.AdminPageBO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper extends BaseMapper<AdminDO> {

    default AdminDO selectByUsername(String username) {
        return selectOne(new QueryWrapper<AdminDO>()
                .eq("username", username)
        );
    }

    default Integer selectCountByDepartmentId(Integer departmentId) {
        return selectCount(new QueryWrapper<AdminDO>()
                .eq("department_id", departmentId)
        );
    }

    default IPage<AdminDO> selectPage(AdminPageBO pageBO) {
        return selectPage(new Page<>(pageBO.getPageNo(), pageBO.getPageSize()),
                new QueryWrapperX<AdminDO>().likeIfPresent("name", pageBO.getName())
                        .eqIfPresent("department_id", pageBO.getDepartmentId()));
    }

}
