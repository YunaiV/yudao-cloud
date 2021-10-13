package cn.iocoder.mall.systemservice.rpc.permission;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.systemservice.rpc.permission.dto.PermissionAssignAdminRoleDTO;
import cn.iocoder.mall.systemservice.rpc.permission.dto.PermissionAssignRoleResourceDTO;
import cn.iocoder.mall.systemservice.rpc.permission.dto.PermissionCheckDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
*/
@FeignClient("system-service")
public interface PermissionFeign {

    @GetMapping("/system/permission/listRoleResourceIds")
    public CommonResult<Set<Integer>> listRoleResourceIds(@RequestParam("roleId")Integer roleId) ;

    @PostMapping("/system/permission/assignRoleResource")
    public CommonResult<Boolean> assignRoleResource(@RequestBody PermissionAssignRoleResourceDTO assignRoleResourceDTO);

    @GetMapping("/system/permission/listAdminRoleIds")
    public CommonResult<Set<Integer>> listAdminRoleIds(@RequestParam("adminId")Integer adminId);

    @GetMapping("/system/permission/mapAdminRoleIds")
    public CommonResult<Map<Integer, Set<Integer>>> mapAdminRoleIds(@RequestParam("adminIds") Collection<Integer> adminIds);

    @PostMapping("/system/permission/assignAdminRole")
    public CommonResult<Boolean> assignAdminRole(@RequestBody PermissionAssignAdminRoleDTO assignAdminRoleDTO);

    @PostMapping("/system/permission/scheckPermission")
    public CommonResult<Boolean> checkPermission(@RequestBody PermissionCheckDTO checkDTO) ;
}
