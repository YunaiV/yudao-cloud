package cn.iocoder.mall.admin.api;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.api.bo.ResourceBO;
import cn.iocoder.mall.admin.api.dto.ResourceAddDTO;
import cn.iocoder.mall.admin.api.dto.ResourceUpdateDTO;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Set;

public interface ResourceService {

    /**
     * 查询指定类型 + 指定角色的资源列表
     *
     * @param type    指定类型。
     * @param roleIds 指定角色的数组。
     * @return 资源列表
     */
    List<ResourceBO> getResourcesByTypeAndRoleIds(Integer type, Set<Integer> roleIds);

    /**
     * 查询指定类型的资源列表
     *
     * @param type 指定类型。可以为空，此时不做为过滤条件
     * @return 资源列表
     */
    List<ResourceBO> getResourcesByType(@Nullable Integer type);

    CommonResult<ResourceBO> addResource(Integer adminId, ResourceAddDTO resourceAddDTO);

    CommonResult<Boolean> updateResource(Integer adminId, ResourceUpdateDTO resourceUpdateDTO);

    CommonResult<Boolean> deleteResource(Integer adminId, Integer resourceId);

}