package cn.iocoder.mall.admin.application.controller.admins;

import cn.iocoder.common.framework.constant.MallConstants;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.api.AdminService;
import cn.iocoder.mall.admin.api.ResourceService;
import cn.iocoder.mall.admin.api.RoleService;
import cn.iocoder.mall.admin.api.bo.ResourceBO;
import cn.iocoder.mall.admin.api.bo.RoleBO;
import cn.iocoder.mall.admin.api.bo.admin.AdminBO;
import cn.iocoder.mall.admin.api.bo.admin.AdminPageBO;
import cn.iocoder.mall.admin.api.constant.ResourceConstants;
import cn.iocoder.mall.admin.api.dto.admin.AdminAddDTO;
import cn.iocoder.mall.admin.api.dto.admin.AdminPageDTO;
import cn.iocoder.mall.admin.api.dto.admin.AdminUpdateDTO;
import cn.iocoder.mall.admin.application.convert.AdminConvert;
import cn.iocoder.mall.admin.application.convert.ResourceConvert;
import cn.iocoder.mall.admin.application.vo.AdminMenuTreeNodeVO;
import cn.iocoder.mall.admin.application.vo.AdminPageVO;
import cn.iocoder.mall.admin.application.vo.AdminRoleVO;
import cn.iocoder.mall.admin.sdk.context.AdminSecurityContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@RestController
@RequestMapping(MallConstants.ROOT_PATH_ADMIN + "/admin")
@Api("管理员模块")
public class AdminController {

    @Reference(validation = "true", version = "${dubbo.provider.ResourceService.version}")
    private ResourceService resourceService;
    @Reference(validation = "true", version = "${dubbo.provider.AdminService.version}")
    private AdminService adminService;
    @Reference(validation = "true", version = "${dubbo.provider.RoleService.version}")
    private RoleService roleService;

    // =========== 当前管理员相关的资源 API ===========

    // TODO 功能：当前管理员

    @SuppressWarnings("Duplicates")
    @GetMapping("/menu_resource_tree")
    @ApiOperation(value = "获得当前登陆的管理员拥有的菜单权限", notes = "以树结构返回")
    public CommonResult<List<AdminMenuTreeNodeVO>> menuResourceTree() {
        List<ResourceBO> resources = resourceService.getResourcesByTypeAndRoleIds(ResourceConstants.TYPE_MENU, AdminSecurityContextHolder.getContext().getRoleIds());
        // 创建 AdminMenuTreeNodeVO Map
        Map<Integer, AdminMenuTreeNodeVO> treeNodeMap = new LinkedHashMap<>(); // 使用 LinkedHashMap 的原因，是为了排序 。实际也可以用 Stream API ，就是太丑了。
        resources.stream().sorted(Comparator.comparing(ResourceBO::getSort)).forEach(resourceBO -> treeNodeMap.put(resourceBO.getId(), ResourceConvert.INSTANCE.convert(resourceBO)));
        // 处理父子关系
        treeNodeMap.values().stream()
                .filter(node -> !node.getPid().equals(ResourceConstants.PID_ROOT))
                .forEach((childNode) -> {
                    // 获得父节点
                    AdminMenuTreeNodeVO parentNode = treeNodeMap.get(childNode.getPid());
                    if (parentNode.getChildren() == null) { // 初始化 children 数组
                        parentNode.setChildren(new ArrayList<>());
                    }
                    // 将自己添加到父节点中
                    parentNode.getChildren().add(childNode);
                });
        // 获得到所有的根节点
        List<AdminMenuTreeNodeVO> rootNodes = treeNodeMap.values().stream()
                .filter(node -> node.getPid().equals(ResourceConstants.PID_ROOT))
//                .sorted(Comparator.comparing(AdminMenuTreeNodeVO::getSort))
                .collect(Collectors.toList());
        return success(rootNodes);
    }

    @GetMapping("/url_resource_list")
    @ApiOperation(value = "获得当前登陆的管理员拥有的 URL 权限列表")
//    @ApiModelProperty(value = "data", example = "['/admin/role/add', '/admin/role/update']") 没效果
    public CommonResult<Set<String>> urlResourceList() {
        List<ResourceBO> resources = resourceService.getResourcesByTypeAndRoleIds(ResourceConstants.TYPE_URL, AdminSecurityContextHolder.getContext().getRoleIds());
        return success(resources.stream().map(ResourceBO::getHandler).collect(Collectors.toSet()));
    }

    // =========== 管理员管理 API ===========

    @GetMapping("/page")
    @ApiOperation(value = "管理员分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "nickname", value = "昵称，模糊匹配", example = "小王"),
            @ApiImplicitParam(name = "pageNo", value = "页码，从 1 开始", example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, example = "10"),
    })
    public CommonResult<AdminPageVO> page(AdminPageDTO adminPageDTO) {
//        CommonResult<AdminPageBO> result = adminService.getAdminPage(new AdminPageDTO().setNickname(nickname).setPageNo(pageNo).setPageSize(pageSize));
        CommonResult<AdminPageBO> result = adminService.getAdminPage(adminPageDTO);
        return AdminConvert.INSTANCE.convert(result);
    }

    @PostMapping("/add")
    @ApiOperation(value = "创建管理员")
    public CommonResult<AdminBO> add(AdminAddDTO adminAddDTO) {
        return success(adminService.addAdmin(AdminSecurityContextHolder.getContext().getAdminId(), adminAddDTO));
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "管理员编号", required = true, example = "1"),
            @ApiImplicitParam(name = "username", value = "账号", required = true, example = "15601691300"),
            @ApiImplicitParam(name = "nickname", value = "昵称", required = true, example = "小王"),
            @ApiImplicitParam(name = "password", value = "密码", example = "buzhidao"),
    })
    public CommonResult<Boolean> update(@RequestParam("id") Integer id,
                                        @RequestParam("username") String username,
                                        @RequestParam("nickname") String nickname,
                                        @RequestParam(value = "password", required = false) String password) {
        AdminUpdateDTO adminUpdateDTO = new AdminUpdateDTO().setId(id).setUsername(username).setNickname(nickname).setPassword(password);
        return adminService.updateAdmin(AdminSecurityContextHolder.getContext().getAdminId(), adminUpdateDTO);
    }

    @PostMapping("/update_status")
    @ApiOperation(value = "更新管理员状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "管理员编号", required = true, example = "1"),
            @ApiImplicitParam(name = "status", value = "状态。1 - 开启；2 - 禁用", required = true, example = "1"),
    })
    public CommonResult<Boolean> updateStatus(@RequestParam("id") Integer id,
                                              @RequestParam("status") Integer status) {
        return adminService.updateAdminStatus(AdminSecurityContextHolder.getContext().getAdminId(), id, status);
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除管理员")
    @ApiImplicitParam(name = "id", value = "管理员编号", required = true, example = "1")
    public CommonResult<Boolean> delete(@RequestParam("id") Integer id) {
        return adminService.deleteAdmin(AdminSecurityContextHolder.getContext().getAdminId(), id);
    }

    @GetMapping("/role_list")
    @ApiOperation(value = "指定管理员拥有的角色列表")
    @ApiImplicitParam(name = "id", value = "管理员编号", required = true, example = "1")
    public CommonResult<List<AdminRoleVO>> roleList(@RequestParam("id") Integer id) {
        // 获得管理员拥有的角色集合
        Set<Integer> adminRoleIdSet = roleService.getRoleList(id).getData();
        // 获得所有角色数组
        List<RoleBO> allRoleList = roleService.getRoleList().getData();
        // 转换出返回结果
        List<AdminRoleVO> result = AdminConvert.INSTANCE.convert(allRoleList);
        // 设置每个角色是否赋予给改管理员
        result.forEach(adminRoleVO -> adminRoleVO.setAssigned(adminRoleIdSet.contains(adminRoleVO.getId())));
        return success(result);
    }

    @PostMapping("/assign_role")
    @ApiOperation(value = "分配给管理员角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "管理员编号", required = true, example = "1"),
            @ApiImplicitParam(name = "roleIds", value = "角色编号集合", required = true, example = "1,2,3"),
    })
    public CommonResult<Boolean> assignRole(@RequestParam("id") Integer id,
                                            @RequestParam("roleIds")Set<Integer> roleIds) {
        return adminService.assignRole(AdminSecurityContextHolder.getContext().getAdminId(), id, roleIds);
    }

}
