package cn.iocoder.mall.system.rest.controller.authorization;

import cn.iocoder.common.framework.constant.MallConstants;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.security.core.annotation.RequiresPermissions;
import cn.iocoder.mall.security.core.context.AdminSecurityContextHolder;
import cn.iocoder.mall.system.biz.bo.authorization.RoleBO;
import cn.iocoder.mall.system.biz.dto.authorization.RoleAddDTO;
import cn.iocoder.mall.system.biz.dto.authorization.RoleDeleteDTO;
import cn.iocoder.mall.system.biz.dto.authorization.RolePageDTO;
import cn.iocoder.mall.system.biz.dto.authorization.RoleUpdateDTO;
import cn.iocoder.mall.system.biz.service.authorization.RoleService;
import cn.iocoder.mall.system.rest.convert.authorization.AdminsRoleConvert;
import cn.iocoder.mall.system.rest.request.authorization.AdminsRoleAddRequest;
import cn.iocoder.mall.system.rest.request.authorization.AdminsRolePageRequest;
import cn.iocoder.mall.system.rest.request.authorization.AdminsRoleUpdateRequest;
import cn.iocoder.mall.system.rest.response.authorization.AdminsRolePageResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(MallConstants.ROOT_PATH_ADMIN + "/role")
@Api(tags = "管理员 - 角色 API")
public class AdminsRoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/page")
    @ApiOperation(value = "角色分页")
    @RequiresPermissions("system:role:page")
    public CommonResult<PageResult<AdminsRolePageResponse>> page(AdminsRolePageRequest request) {
        RolePageDTO pageDTO = AdminsRoleConvert.INSTANCE.convert(request);
        PageResult<RoleBO> pageResult = roleService.getRolePage(pageDTO);
        return CommonResult.success(AdminsRoleConvert.INSTANCE.convertPage(pageResult));
    }

    @PostMapping("/add")
    @ApiOperation(value = "创建角色")
    @RequiresPermissions("system:role:add")
    public CommonResult<Integer> add(AdminsRoleAddRequest request) {
        RoleAddDTO addDTO = AdminsRoleConvert.INSTANCE.convert(request)
                .setAdminId(AdminSecurityContextHolder.getAdminId());
        return CommonResult.success(roleService.addRole(addDTO));
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新角色")
    @RequiresPermissions("system:role:update")
    public CommonResult<Boolean> update(AdminsRoleUpdateRequest request) {
        RoleUpdateDTO updateDTO = AdminsRoleConvert.INSTANCE.convert(request)
                .setAdminId(AdminSecurityContextHolder.getAdminId());
        roleService.updateRole(updateDTO);
        return CommonResult.success(true);
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除角色")
    @RequiresPermissions("system:role:delete")
    @ApiImplicitParam(name = "id", value = "角色编号", required = true, example = "1")
    public CommonResult<Boolean> delete(@RequestParam("id") Integer id) {
        RoleDeleteDTO deleteDTO = new RoleDeleteDTO().setId(id)
                .setAdminId(AdminSecurityContextHolder.getAdminId());
        roleService.deleteRole(deleteDTO);
        return CommonResult.success(true);
    }

//    @GetMapping("/role_tree")
//    @ApiOperation(value = "获得角色拥有的菜单权限", notes = "以树结构返回")
//    @ApiImplicitParam(name = "id", value = "角色编号", required = true, example = "1")
//    public CommonResult<List<RoleRoleTreeNodeVO>> roleTree(@RequestParam("id") Integer id) {
//        // 芋艿：此处，严格来说可以在校验下角色是否存在。不过呢，校验了也没啥意义，因为一般不存在这个情况，且不会有业务上的影响。并且，反倒多了一次 rpc 调用。
//        // 第一步，获得角色拥有的资源数组
//        Set<Integer> roleRoles = roleService.getRolesByTypeAndRoleIds(null, CollectionUtil.asSet(id))
//                .stream().map(RoleBO::getId).collect(Collectors.toSet());
//        // 第二步，获得资源树
//        List<RoleBO> allRoles = roleService.getRolesByType(null);
//        // 创建 AdminMenuTreeNodeVO Map
//        Map<Integer, RoleRoleTreeNodeVO> treeNodeMap = allRoles.stream().collect(Collectors.toMap(RoleBO::getId, RoleConvert.INSTANCE::convert4));
//        // 处理父子关系
//        treeNodeMap.values().stream()
//                .filter(node -> !node.getPid().equals(RoleConstants.PID_ROOT))
//                .forEach((childNode) -> {
//                    // 获得父节点
//                    RoleRoleTreeNodeVO parentNode = treeNodeMap.get(childNode.getPid());
//                    if (parentNode.getChildren() == null) { // 初始化 children 数组
//                        parentNode.setChildren(new ArrayList<>());
//                    }
//                    // 将自己添加到父节点中
//                    parentNode.getChildren().add(childNode);
//                });
//        // 获得到所有的根节点
//        List<RoleRoleTreeNodeVO> rootNodes = treeNodeMap.values().stream()
//                .filter(node -> node.getPid().equals(RoleConstants.PID_ROOT))
//                .sorted(Comparator.comparing(RoleRoleTreeNodeVO::getSort))
//                .collect(Collectors.toList());
//        // 第三步，设置角色是否有该角色
//        treeNodeMap.values().forEach(nodeVO -> nodeVO.setAssigned(roleRoles.contains(nodeVO.getId())));
//        // 返回结果
//        return success(rootNodes);
//    }
//
//    @PostMapping("/assign_role")
//    @ApiOperation(value = "分配角色资源")
//    public CommonResult<Boolean> assignRole(RoleAssignRoleDTO roleAssignRoleDTO) {
//        return success(roleService.assignRoleRole(AdminSecurityContextHolder.getContext().getAdminId(), roleAssignRoleDTO));
//    }

}
