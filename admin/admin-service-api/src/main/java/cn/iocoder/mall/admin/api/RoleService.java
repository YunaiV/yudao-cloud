package cn.iocoder.mall.admin.api;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.api.bo.RoleBO;
import cn.iocoder.mall.admin.api.bo.RolePageBO;
import cn.iocoder.mall.admin.api.dto.RoleAddDTO;
import cn.iocoder.mall.admin.api.dto.RolePageDTO;
import cn.iocoder.mall.admin.api.dto.RoleUpdateDTO;

import java.util.Set;

public interface RoleService {

    CommonResult<RolePageBO> getRolePage(RolePageDTO rolePageDTO);

    CommonResult<RoleBO> addRole(Integer adminId, RoleAddDTO roleAddDTO);

    CommonResult<Boolean> updateRole(Integer adminId, RoleUpdateDTO roleUpdateDTO);

    CommonResult<Boolean> deleteRole(Integer adminId, Integer roleId);

    CommonResult<Boolean> assignResource(Integer adminId, Integer roleId, Set<Integer> resourceIds);

}