package cn.iocoder.mall.admin.application.controller;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.api.ResourceService;
import cn.iocoder.mall.admin.api.RoleService;
import cn.iocoder.mall.admin.api.bo.ResourceBO;
import cn.iocoder.mall.admin.api.bo.RolePageBO;
import cn.iocoder.mall.admin.api.constant.ResourceConstants;
import cn.iocoder.mall.admin.api.dto.RoleAddDTO;
import cn.iocoder.mall.admin.api.dto.RolePageDTO;
import cn.iocoder.mall.admin.api.dto.RoleUpdateDTO;
import cn.iocoder.mall.admin.application.convert.ResourceConvert;
import cn.iocoder.mall.admin.application.convert.RoleConvert;
import cn.iocoder.mall.admin.application.vo.RolePageVO;
import cn.iocoder.mall.admin.application.vo.RoleResourceTreeNodeVO;
import cn.iocoder.mall.admin.application.vo.RoleVO;
import cn.iocoder.mall.admin.sdk.context.AdminSecurityContextHolder;
import com.alibaba.dubbo.config.annotation.Reference;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("admin/role")
public class RoleController {

    @Reference(validation = "true")
    private RoleService roleService;
    @Reference(validation = "true")
    private ResourceService resourceService;

    @GetMapping("/page")
    @ApiOperation(value = "角色分页")
    public CommonResult<RolePageVO> page(@RequestParam(value = "name", required = false) String name,
                                         @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
                                         @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        CommonResult<RolePageBO> result = roleService.getRolePage(new RolePageDTO().setName(name).setPageNo(pageNo).setPageSize(pageSize));
        return RoleConvert.INSTANCE.convert2(result);
    }

    @PostMapping("/add")
    @ApiOperation(value = "创建角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "角色", required = true, example = "系统管理员"),
    })
    public CommonResult<RoleVO> add(@RequestParam("name") String name) {
        RoleAddDTO roleAddDTO = new RoleAddDTO().setName(name);
        return RoleConvert.INSTANCE.convert(roleService.addRole(AdminSecurityContextHolder.getContext().getAdminId(), roleAddDTO));
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色编号", required = true, example = "1"),
            @ApiImplicitParam(name = "name", value = "角色名", required = true, example = "系统管理员"),
    })
    public CommonResult<Boolean> update(@RequestParam("id") Integer id,
                                        @RequestParam("name") String name) {
        RoleUpdateDTO roleUpdateDTO = new RoleUpdateDTO().setId(id).setName(name);
        return roleService.updateRole(AdminSecurityContextHolder.getContext().getAdminId(), roleUpdateDTO);
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除角色")
    @ApiImplicitParam(name = "id", value = "角色编号", required = true, example = "1")
    public CommonResult<Boolean> delete(@RequestParam("id") Integer id) {
        return roleService.deleteRole(AdminSecurityContextHolder.getContext().getAdminId(), id);
    }

    @SuppressWarnings("Duplicates")
    @GetMapping("/resource_tree")
    @ApiOperation(value = "获得角色拥有的菜单权限", notes = "以树结构返回")
    @ApiImplicitParam(name = "id", value = "角色编号", required = true, example = "1")
    public CommonResult<List<RoleResourceTreeNodeVO>> resourceTree(@RequestParam("id") Integer id) {
        // 芋艿：此处，严格来说可以在校验下角色是否存在。不过呢，校验了也没啥意义，因为一般不存在这个情况，且不会有业务上的影响。并且，反倒多了一次 rpc 调用。
        Set<Integer> roleIds = new HashSet<>();
        roleIds.add(id);
        List<ResourceBO> resources = resourceService.getResourcesByTypeAndRoleIds(null, roleIds);
        // 创建 AdminMenuTreeNodeVO Map
        Map<Integer, RoleResourceTreeNodeVO> treeNodeMap = resources.stream().collect(Collectors.toMap(ResourceBO::getId, ResourceConvert.INSTANCE::convert4));
        // 处理父子关系
        treeNodeMap.values().stream()
                .filter(node -> !node.getPid().equals(ResourceConstants.PID_ROOT))
                .forEach((childNode) -> {
                    // 获得父节点
                    RoleResourceTreeNodeVO parentNode = treeNodeMap.get(childNode.getPid());
                    if (parentNode.getChildren() == null) { // 初始化 children 数组
                        parentNode.setChildren(new ArrayList<>());
                    }
                    // 将自己添加到父节点中
                    parentNode.getChildren().add(childNode);
                });
        // 获得到所有的根节点
        List<RoleResourceTreeNodeVO> rootNodes = treeNodeMap.values().stream()
                .filter(node -> node.getPid().equals(ResourceConstants.PID_ROOT))
                .sorted(Comparator.comparing(RoleResourceTreeNodeVO::getSort))
                .collect(Collectors.toList());
        return CommonResult.success(rootNodes);
    }

    @PostMapping("/assign_resource")
    @ApiOperation(value = "分配角色资源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色编号", required = true, example = "1"),
            @ApiImplicitParam(name = "resourceIds", value = "资源数组", required = true, example = "1,2,3"),
    })
    public CommonResult<Boolean> assignResource(@RequestParam("id") Integer id,
                                                @RequestParam(value = "resourceIds", required = false) Set<Integer> resourceIds) {
        return roleService.assignResource(AdminSecurityContextHolder.getContext().getAdminId(), id, resourceIds);
    }

}