package cn.iocoder.mall.systemservice.rpc.permission;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.systemservice.rpc.permission.dto.ResourceCreateDTO;
import cn.iocoder.mall.systemservice.rpc.permission.dto.ResourceUpdateDTO;
import cn.iocoder.mall.systemservice.rpc.permission.dto.RolePageDTO;
import cn.iocoder.mall.systemservice.rpc.permission.dto.RoleUpdateDTO;
import cn.iocoder.mall.systemservice.rpc.permission.vo.ResourceVO;
import cn.iocoder.mall.systemservice.rpc.permission.vo.RoleCreateDTO;
import cn.iocoder.mall.systemservice.rpc.permission.vo.RoleVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
* 部门 Rpc 接口
*/
@FeignClient("system-service")
public interface RoleFeign {


    @PostMapping("/system/role/createRole")
    public CommonResult<Integer> createRole(@RequestBody RoleCreateDTO createDTO) ;

    @PostMapping("/system/role/updateRole")
    public CommonResult<Boolean> updateRole(@RequestBody RoleUpdateDTO updateDTO);

    @GetMapping("/system/role/deleteRole")
    public CommonResult<Boolean> deleteRole(@RequestParam("roleId")Integer roleId) ;

    @GetMapping("/system/role/getRole")
    public CommonResult<RoleVO> getRole(@RequestParam("roleId")Integer roleId);

    @GetMapping("/system/role/listAllRoles")
    public CommonResult<List<RoleVO>> listAllRoles() ;

    @GetMapping("/system/role/listRoles")
    public CommonResult<List<RoleVO>> listRoles(@RequestParam("roleIds")Collection<Integer> roleIds) ;

    @PostMapping("/system/role/pageRole")
    public CommonResult<PageResult<RoleVO>> pageRole(@RequestBody RolePageDTO pageDTO);

    @GetMapping("/system/role/listAdminRoleIds")
    public CommonResult<Set<Integer>> listAdminRoleIds(@RequestParam("adminId") Integer adminId) ;
}
