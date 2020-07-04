package cn.iocoder.mall.systemservice.dal.mysql.mapper.admin;

import cn.iocoder.mall.systemservice.dal.mysql.dataobject.admin.AdminDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper extends BaseMapper<AdminDO> {

    default AdminDO selectByUsername(String username) {
        return selectOne(new QueryWrapper<AdminDO>()
                .eq("username", username)
        );
    }

//    default IPage<AdminDO> selectPage(AdminPageDTO adminPageDTO) {
//        return selectPage(new Page<>(adminPageDTO.getPageNo(), adminPageDTO.getPageSize()),
//                new QueryWrapperX<AdminDO>().likeIfPresent("name", adminPageDTO.getName())
//                        .eqIfPresent("department_id", adminPageDTO.getDepartmentId()));
//    }

}
