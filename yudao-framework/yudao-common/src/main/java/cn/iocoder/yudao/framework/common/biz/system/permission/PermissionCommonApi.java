package cn.iocoder.yudao.framework.common.biz.system.permission;

import cn.iocoder.yudao.framework.common.biz.system.permission.dto.DeptDataPermissionRespDTO;
import cn.iocoder.yudao.framework.common.enums.RpcConstants;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = RpcConstants.SYSTEM_NAME, primary = false) // TODO 芋艿：fallbackFactory =
@Tag(name = "RPC 服务 - 权限")
public interface PermissionCommonApi {

    String PREFIX = RpcConstants.SYSTEM_PREFIX + "/permission";

    @GetMapping(PREFIX + "/has-any-permissions")
    @Operation(summary = "判断是否有权限，任一一个即可")
    @Parameters({
            @Parameter(name = "userId", description = "用户编号", example = "1", required = true),
            @Parameter(name = "permissions", description = "权限", example = "read,write", required = true)
    })
    CommonResult<Boolean> hasAnyPermissions(@RequestParam("userId") Long userId,
                                            @RequestParam("permissions") String... permissions);

    @GetMapping(PREFIX + "/has-any-roles")
    @Operation(summary = "判断是否有角色，任一一个即可")
    @Parameters({
            @Parameter(name = "userId", description = "用户编号", example = "1", required = true),
            @Parameter(name = "roles", description = "角色数组", example = "2", required = true)
    })
    CommonResult<Boolean> hasAnyRoles(@RequestParam("userId") Long userId,
                                      @RequestParam("roles") String... roles);

    @GetMapping(PREFIX + "/get-dept-data-permission")
    @Operation(summary = "获得登陆用户的部门数据权限")
    @Parameter(name = "userId", description = "用户编号", example = "2", required = true)
    CommonResult<DeptDataPermissionRespDTO> getDeptDataPermission(@RequestParam("userId") Long userId);

}