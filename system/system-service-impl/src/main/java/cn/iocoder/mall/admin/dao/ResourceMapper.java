package cn.iocoder.mall.admin.dao;

import cn.iocoder.mall.admin.dataobject.ResourceDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ResourceMapper extends BaseMapper<ResourceDO> {

    ResourceDO selectByTypeAndHandler(@Param("type") Integer type,
                                      @Param("handler") String handler);

    List<ResourceDO> selectListByTypeAndRoleIds(@Param("type") Integer type,
                                                @Param("roleIds") Set<Integer> roleIds);

    List<ResourceDO> selectListByType(@Param("type") Integer type);

    List<ResourceDO> selectListByIds(@Param("ids") Set<Integer> ids);

    int selectCountByPid(@Param("pid") Integer pid);

}
