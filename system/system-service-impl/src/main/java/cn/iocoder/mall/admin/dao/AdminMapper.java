package cn.iocoder.mall.admin.dao;

import cn.iocoder.common.framework.mybatis.QueryWrapperX;
import cn.iocoder.mall.system.api.dto.admin.AdminPageDTO;
import cn.iocoder.mall.admin.dataobject.AdminDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper extends BaseMapper<AdminDO> {

    default AdminDO selectByUsername(@Param("username") String username) {
        return selectOne(new QueryWrapper<AdminDO>().eq("username", username));
    }

    default IPage<AdminDO> selectPage(AdminPageDTO adminPageDTO) {
        return selectPage(new Page<>(adminPageDTO.getPageNo(), adminPageDTO.getPageSize()),
                new QueryWrapperX<AdminDO>().likeIfPresent("nickname", adminPageDTO.getNickname())
                                            .eqIfPresent("deptment_id", adminPageDTO.getDeptmentId()));
    }

    default int updateDeptByDeptId(@Param("fromDeptId")Integer fromDeptId, @Param("toDeptId")Integer toDeptId){
        QueryWrapper<AdminDO> query = new QueryWrapper<AdminDO>()
                .eq("deptment_id", fromDeptId);
        return update(new AdminDO().setDeptmentId(toDeptId), query);
    }

}
