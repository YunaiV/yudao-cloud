package cn.iocoder.mall.systemservice.dal.mysql.mapper.admin;

import cn.iocoder.mall.mybatis.query.QueryWrapperX;
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

    default IPage<AdminDO> selectPage(AdminPageBO adminPageBO) {
        return selectPage(new Page<>(adminPageBO.getPageNo(), adminPageBO.getPageSize()),
                new QueryWrapperX<AdminDO>().likeIfPresent("name", adminPageBO.getName())
                        .eqIfPresent("department_id", adminPageBO.getDepartmentId()));
    }

}
