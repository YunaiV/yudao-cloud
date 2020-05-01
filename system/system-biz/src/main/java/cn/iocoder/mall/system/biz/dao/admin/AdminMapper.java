package cn.iocoder.mall.system.biz.dao.admin;

import cn.iocoder.mall.mybatis.query.QueryWrapperX;
import cn.iocoder.mall.system.biz.dataobject.admin.AdminDO;
import cn.iocoder.mall.system.biz.dto.admin.AdminPageDTO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper extends BaseMapper<AdminDO> {

    default AdminDO selectByAccountId(Integer accountId) {
        return selectOne(new QueryWrapper<AdminDO>()
                .eq("account_id", accountId)
        );
    }

    default IPage<AdminDO> selectPage(AdminPageDTO adminPageDTO) {
        return selectPage(new Page<>(adminPageDTO.getPageNo(), adminPageDTO.getPageSize()),
                new QueryWrapperX<AdminDO>().likeIfPresent("name", adminPageDTO.getName())
                        .eqIfPresent("department_id", adminPageDTO.getDepartmentId()));
    }

}
