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

import java.util.List;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
* 角色 Rpc 实现类
*/
@Service(version = "${dubbo.provider.RoleRpc.version}", validation = "false")
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
    public CommonResult<List<RoleVO>> listRole(List<Integer> roleIds) {
        return success(roleManager.listRole(roleIds));
    }

    @Override
    public CommonResult<PageResult<RoleVO>> pageRole(RolePageDTO pageDTO) {
        return success(roleManager.pageRole(pageDTO));
    }

}
