package cn.iocoder.mall.admin.dao;

import cn.iocoder.mall.admin.dataobject.AdminDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminMapper extends BaseMapper<AdminDO> {

    default AdminDO selectByUsername(@Param("username") String username) {
        return selectOne(new QueryWrapper<AdminDO>().eq("username", username));
    }

    List<AdminDO> selectListByNicknameLike(@Param("nickname") String nickname,
                                           @Param("offset") Integer offset,
                                           @Param("limit") Integer limit);

    Integer selectCountByNicknameLike(@Param("nickname") String nickname);

    int update(AdminDO admin);

}
