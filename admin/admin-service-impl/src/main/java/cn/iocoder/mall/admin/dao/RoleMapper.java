package cn.iocoder.mall.admin.dao;

import cn.iocoder.mall.admin.dataobject.RoleDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleMapper {

    void insert(RoleDO roleDO);

    int update(RoleDO roleDO);

    RoleDO selectById(@Param("id") Integer id);

    List<RoleDO> selectListByNameLike(@Param("name") String name,
                                      @Param("offset") Integer offset,
                                      @Param("limit") Integer limit);

    Integer selectCountByNameLike(@Param("name") String name);

    List<RoleDO> selectListByIds(@Param("ids") Set<Integer> ids);

}