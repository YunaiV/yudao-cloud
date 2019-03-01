package cn.iocoder.mall.admin.dao;

import cn.iocoder.mall.admin.dataobject.AdminDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminMapper {

    AdminDO selectByUsername(@Param("username") String username);

    List<AdminDO> selectListByNicknameLike(@Param("nickname") String nickname,
                                           @Param("offset") Integer offset,
                                           @Param("limit") Integer limit);

    Integer selectCountByNicknameLike(@Param("nickname") String nickname);

}