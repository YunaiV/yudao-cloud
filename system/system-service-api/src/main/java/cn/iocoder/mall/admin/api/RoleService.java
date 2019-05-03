package cn.iocoder.mall.admin.api;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.api.bo.RoleBO;
import cn.iocoder.mall.admin.api.bo.RolePageBO;
import cn.iocoder.mall.admin.api.dto.RoleAddDTO;
import cn.iocoder.mall.admin.api.dto.RolePageDTO;
import cn.iocoder.mall.admin.api.dto.RoleUpdateDTO;

import java.util.List;
import java.util.Set;

public interface RoleService {

    CommonResult<RolePageBO> getRolePage(RolePageDTO rolePageDTO);

    /**
     * 获得指定管理员拥有的角色编号数组
     *
     * @param adminId 指定管理员
     * @return 角色编号数组
     */
    CommonResult<Set<Integer>> getRoleList(Integer adminId);

    /**
     * @return 返回角色列表
     */
    CommonResult<List<RoleBO>> getRoleList();

    CommonResult<RoleBO> addRole(Integer adminId, RoleAddDTO roleAddDTO);

    CommonResult<Boolean> updateRole(Integer adminId, RoleUpdateDTO roleUpdateDTO);

    CommonResult<Boolean> deleteRole(Integer adminId, Integer roleId);

    CommonResult<Boolean> assignResource(Integer adminId, Integer roleId, Set<Integer> resourceIds);

}