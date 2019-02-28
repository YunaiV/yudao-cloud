package cn.iocoder.mall.admin.dao;

import cn.iocoder.mall.admin.dataobject.ResourceDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ResourceMapper {

    ResourceDO selectByTypeAndHandler(@Param("type") Integer type,
                                      @Param("handler") String handler);

    List<ResourceDO> selectListByTypeAndRoleIds(@Param("type") Integer type,
                                                @Param("roleIds") Set<Integer> roleIds);

    List<ResourceDO> selectListByType(@Param("type") Integer type);

    ResourceDO selectByName(@Param("name") String name);

    ResourceDO selectById(@Param("id") Integer id);

    void insert(ResourceDO resource);

    int update(ResourceDO resource);
}