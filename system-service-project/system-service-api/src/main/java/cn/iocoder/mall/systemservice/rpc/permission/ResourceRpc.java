package cn.iocoder.mall.systemservice.rpc.permission;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.systemservice.rpc.permission.dto.ResourceCreateDTO;
import cn.iocoder.mall.systemservice.rpc.permission.dto.ResourceUpdateDTO;
import cn.iocoder.mall.systemservice.rpc.permission.vo.ResourceVO;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

/**
* 资源 Rpc 接口
*/
public interface ResourceRpc {

    /**
    * 创建资源
    *
    * @param createDTO 创建资源 DTO
    * @return 资源
    */
    CommonResult<Integer> createResource(ResourceCreateDTO createDTO);

    /**
    * 更新资源
    *
    * @param updateDTO 更新资源 DTO
    */
    CommonResult<Boolean> updateResource(ResourceUpdateDTO updateDTO);

    /**
    * 删除资源
    *
    * @param resourceId 资源编号
    */
    CommonResult<Boolean> deleteResource(Integer resourceId);

    /**
    * 获得资源
    *
    * @param resourceId 资源编号
    * @return 资源
    */
    CommonResult<ResourceVO> getResource(Integer resourceId);

    /**
     * 获得资源全列表
     *
     * @return 资源列表
     */
    CommonResult<List<ResourceVO>> listResource();

    /**
    * 获得资源列表
    *
    * @param resourceIds 资源编号列表
    * @return 资源列表
    */
    CommonResult<List<ResourceVO>> listResource(List<Integer> resourceIds);

    /**
     * 获得指定角色的资源列表
     *
     * @param roleIds 角色编号列表
     * @param type 资源类型
     * @return 资源列表
     */
    CommonResult<List<ResourceVO>> listRoleResource(@NotNull(message = "角色编号列表不能为空") Collection<Integer> roleIds, Integer type);

}
