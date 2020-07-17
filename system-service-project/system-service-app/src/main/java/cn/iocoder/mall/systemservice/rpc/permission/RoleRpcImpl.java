package cn.iocoder.mall.systemservice.rpc.permission;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.systemservice.manager.permission.RoleManager;
import cn.iocoder.mall.systemservice.rpc.permission.dto.RolePageDTO;
import cn.iocoder.mall.systemservice.rpc.permission.dto.RoleUpdateDTO;
import cn.iocoder.mall.systemservice.rpc.permission.vo.RoleCreateDTO;
import cn.iocoder.mall.systemservice.rpc.permission.vo.RoleVO;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
* 角色 Rpc 实现类
*/
@Service(version = "${dubbo.provider.RoleRpc.version}")
public class RoleRpcImpl implements RoleRpc {

    @Autowired
    private RoleManager roleManager;

    @Override
    public CommonResult<Integer> createRole(RoleCreateDTO createDTO) {
        return success(roleManager.createRole(createDTO));
    }

    @Override
    public CommonResult<Boolean> updateRole(RoleUpdateDTO updateDTO) {
        roleManager.updateRole(updateDTO);
        return success(true);
    }

    @Override
    public CommonResult<Boolean> deleteRole(Integer roleId) {
        roleManager.deleteRole(roleId);
        return success(true);
    }

    @Override
    public CommonResult<RoleVO> getRole(Integer roleId) {
        return success(roleManager.getRole(roleId));
    }

    @Override
    public CommonResult<List<RoleVO>> listAllRoles() {
        return success(roleManager.listAllRoles());
    }

    @Override
    public CommonResult<List<RoleVO>> listRoles(Collection<Integer> roleIds) {
        return success(roleManager.listRoles(roleIds));
    }

    @Override
    public CommonResult<PageResult<RoleVO>> pageRole(RolePageDTO pageDTO) {
        return success(roleManager.pageRole(pageDTO));
    }

    @Override
    public CommonResult<Set<Integer>> listAdminRoleIds(Integer adminId) {
        return success(roleManager.listAdminRoleIds(adminId));
    }

}
