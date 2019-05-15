package cn.iocoder.mall.admin.api;

import cn.iocoder.mall.admin.api.bo.resource.ResourceBO;
import cn.iocoder.mall.admin.api.dto.resource.ResourceAddDTO;
import cn.iocoder.mall.admin.api.dto.resource.ResourceUpdateDTO;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Set;

public interface ResourceService {

    /**
     * 查询指定类型 + 指定角色的资源列表
     *
     * @param type    指定类型。可以为空，此时不作为过滤条件
     * @param roleIds 指定角色的数组。
     * @return 资源列表
     */
    List<ResourceBO> getResourcesByTypeAndRoleIds(@Nullable Integer type, Set<Integer> roleIds);

    /**
     * 查询指定类型的资源列表
     *
     * @param type 指定类型。可以为空，此时不做为过滤条件
     * @return 资源列表
     */
    List<ResourceBO> getResourcesByType(@Nullable Integer type);

    ResourceBO addResource(Integer adminId, ResourceAddDTO resourceAddDTO);

    Boolean updateResource(Integer adminId, ResourceUpdateDTO resourceUpdateDTO);

    Boolean deleteResource(Integer adminId, Integer resourceId);

}
