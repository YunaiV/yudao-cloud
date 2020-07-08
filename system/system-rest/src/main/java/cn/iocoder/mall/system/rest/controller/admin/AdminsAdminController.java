package cn.iocoder.mall.system.rest.controller.admin;

import cn.iocoder.common.framework.enums.MallConstants;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.security.core.annotation.RequiresPermissions;
import cn.iocoder.mall.system.biz.bo.admin.AdminBO;
import cn.iocoder.mall.system.biz.dto.admin.AdminPageDTO;
import cn.iocoder.mall.system.biz.service.admin.AdminService;
import cn.iocoder.mall.system.rest.convert.admin.AdminsAdminConvert;
import cn.iocoder.mall.system.rest.request.admin.AdminsAdminPageRequest;
import cn.iocoder.mall.system.rest.response.admin.AdminsAdminPageResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(MallConstants.ROOT_PATH_ADMIN + "/admin")
@Api(tags = "管理员 - 管理员 API")
public class AdminsAdminController {

//    @GetMapping("/role_list")
//    @ApiOperation(value = "指定管理员拥有的角色列表")
//    @ApiImplicitParam(name = "id", value = "管理员编号", required = true, example = "1")
//    public CommonResult<List<AdminRoleVO>> roleList(@RequestParam("id") Integer id) {
//        // 获得所有角色列表
//        List<RoleBO> allRoleList = roleService.getRoleList();
//        // 获得管理员的角色数组
//        Set<Integer> adminRoleIdSet = CollectionUtil.convertSet(adminService.getRoleList(id), RoleBO::getId);
//        // 转换出返回结果
//        List<AdminRoleVO> result = AdminConvert.INSTANCE.convert(allRoleList);
//        // 设置每个角色是否赋予给改管理员
//        result.forEach(adminRoleVO -> adminRoleVO.setAssigned(adminRoleIdSet.contains(adminRoleVO.getId())));
//        return success(result);
//    }

//    @PostMapping("/assign_role")
//    @ApiOperation(value = "分配给管理员角色")
//    public CommonResult<Boolean> assignRole(AdminAssignRoleDTO adminAssignRoleDTO) {
//        return success(adminService.assignAdminRole(AdminSecurityContextHolder.getContext().getAdminId(), adminAssignRoleDTO));
//    }

}
