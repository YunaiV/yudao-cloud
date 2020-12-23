package cn.iocoder.mall.systemservice.rpc.permission;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.systemservice.rpc.permission.dto.RolePageDTO;
import cn.iocoder.mall.systemservice.rpc.permission.dto.RoleUpdateDTO;
import cn.iocoder.mall.systemservice.rpc.permission.vo.RoleCreateDTO;
import cn.iocoder.mall.systemservice.rpc.permission.vo.RoleVO;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
* 角色 Rpc 接口
*/
public interface RoleRpc {

    /**
    * 创建角色
    *
    * @param createDTO 创建角色 DTO
    * @return 角色编号
    */
    CommonResult<Integer> createRole(RoleCreateDTO createDTO);

    /**
    * 更新角色
    *
    * @param updateDTO 更新角色 DTO
    */
    CommonResult<Boolean> updateRole(RoleUpdateDTO updateDTO);

    /**
    * 删除角色
    *
    * @param roleId 角色编号
    */
    CommonResult<Boolean> deleteRole(Integer roleId);

    /**
    * 获得角色
    *
    * @param roleId 角色编号
    * @return 角色
    */
    CommonResult<RoleVO> getRole(Integer roleId);

    /**
     * 获得所有角色
     *
     * @return 角色列表
     */
    CommonResult<List<RoleVO>> listAllRoles();

    /**
    * 获得角色列表
    *
    * @param roleIds 角色编号列表
    * @return 角色列表
    */
    CommonResult<List<RoleVO>> listRoles(Collection<Integer> roleIds);

    /**
    * 获得角色分页
    *
    * @param pageDTO 角色分页查询
    * @return 角色分页结果
    */
    CommonResult<PageResult<RoleVO>> pageRole(RolePageDTO pageDTO);

    /**
     * 获得管理员拥有的角色编号列表
     *
     * @param adminId 管理员编号
     * @return 角色编号列表
     */
    CommonResult<Set<Integer>> listAdminRoleIds(Integer adminId);

}
