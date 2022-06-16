package cn.iocoder.yudao.module.system.api.permission;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.system.api.permission.dto.DeptDataPermissionRespDTO;
import cn.iocoder.yudao.module.system.enums.ApiConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Set;

@FeignClient(name = ApiConstants.NAME) // TODO 芋艿：fallbackFactory =
@Api(tags = "RPC 服务 - 权限")
public interface PermissionApi {

    String PREFIX = ApiConstants.PREFIX + "/permission";

    @GetMapping(PREFIX + "/user-role-id-list-by-role-id")
    @ApiOperation("获得拥有多个角色的用户编号集合")
    @ApiImplicitParam(name = "roleIds", value = "角色编号集合", example = "1,2", required = true, allowMultiple = true)
    CommonResult<Set<Long>> getUserRoleIdListByRoleIds(@RequestParam("roleIds") Collection<Long> roleIds);

    @GetMapping(PREFIX + "/has-any-permissions")
    @ApiOperation("判断是否有权限，任一一个即可")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户编号", example = "1", required = true, dataTypeClass = Long.class),
            @ApiImplicitParam(name = "permissions", value = "权限", example = "read,write", required = true, allowMultiple = true)
    })
    CommonResult<Boolean> hasAnyPermissions(@RequestParam("userId") Long userId,
                                            @RequestParam("permissions") String... permissions);

    @GetMapping(PREFIX + "/has-any-roles")
    @ApiOperation("判断是否有角色，任一一个即可")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户编号", example = "1", required = true, dataTypeClass = Long.class),
            @ApiImplicitParam(name = "roles", value = "角色数组", example = "2", required = true, allowMultiple = true)
    })
    CommonResult<Boolean> hasAnyRoles(@RequestParam("userId") Long userId,
                                      @RequestParam("roles") String... roles);

    @GetMapping(PREFIX + "/get-dept-data-permission")
    @ApiOperation("获得登陆用户的部门数据权限")
    @ApiImplicitParam(name = "userId", value = "用户编号", example = "2", required = true, dataTypeClass = Long.class)
    CommonResult<DeptDataPermissionRespDTO> getDeptDataPermission(@RequestParam("userId") Long userId);

}
