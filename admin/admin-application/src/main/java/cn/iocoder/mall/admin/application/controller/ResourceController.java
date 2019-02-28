package cn.iocoder.mall.admin.application.controller;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.api.ResourceService;
import cn.iocoder.mall.admin.api.bo.ResourceBO;
import cn.iocoder.mall.admin.api.constant.ResourceType;
import cn.iocoder.mall.admin.api.dto.ResourceAddDTO;
import cn.iocoder.mall.admin.application.convert.ResourceConvert;
import cn.iocoder.mall.admin.application.vo.AdminMenuTreeNodeVO;
import cn.iocoder.mall.admin.sdk.context.AdminSecurityContextHolder;
import com.alibaba.dubbo.config.annotation.Reference;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("admin/resource")
@Api("资源模块")
public class ResourceController {

    @Reference(validation = "true")
    private ResourceService resourceService;

    // =========== 当前管理员相关的资源 API ===========

    @GetMapping("/admin_menu_tree")
    @ApiOperation(value = "获得当前登陆的管理员拥有的菜单权限", notes = "以树结构返回")
    public CommonResult<List<AdminMenuTreeNodeVO>> adminMenuTree() {
        List<ResourceBO> resources = resourceService.getResourceByTypeAndRoleIds(ResourceType.MENU, AdminSecurityContextHolder.getContext().getRoleIds());
        // 创建 AdminMenuTreeNodeVO Map
        Map<Integer, AdminMenuTreeNodeVO> treeNodeMap = resources.stream().collect(Collectors.toMap(ResourceBO::getId, ResourceConvert.INSTANCE::convert));
        // 处理父子关系
        treeNodeMap.values().stream().filter(node -> {
            return node.getPid() != 0; // TODO magic number
        }).forEach((childNode) -> {
            // 获得父节点
            AdminMenuTreeNodeVO parentNode = treeNodeMap.get(childNode.getPid());
            if (parentNode.getChildren() == null) { // 初始化 children 数组
                parentNode.setChildren(new ArrayList<>());
            }
            // 将自己添加到父节点中
            parentNode.getChildren().add(childNode);
        });
        // 获得到所有的根节点
        List<AdminMenuTreeNodeVO> rootNodes = treeNodeMap.values().stream().filter(node -> {
            return node.getPid() == 0; // TODO magic number
        }).collect(Collectors.toList());
        return CommonResult.success(rootNodes);
    }

    @GetMapping("/admin_url_list")
    @ApiOperation(value = "获得当前登陆的管理员拥有的 URL 权限列表")
//    @ApiModelProperty(value = "data", example = "['/admin/role/add', '/admin/role/update']") 没效果
    public CommonResult<Set<String>> adminUrlList() {
        List<ResourceBO> resources = resourceService.getResourceByTypeAndRoleIds(ResourceType.URL, AdminSecurityContextHolder.getContext().getRoleIds());
        return CommonResult.success(resources.stream().map(ResourceBO::getHandler).collect(Collectors.toSet()));
    }


    // =========== 资源管理 API ===========

    // TODO 芋艿，注释
    @PostMapping("/add")
    @ApiOperation(value = "创建资源", notes = "例如说，菜单资源，Url 资源")
    public void add(@RequestParam("name") String name,
                    @RequestParam("type") Integer type,
                    @RequestParam("sort") Integer sort,
                    @RequestParam("displayName") String displayName,
                    @RequestParam("pid") Integer pid,
                    @RequestParam("handler") String handler) {
        ResourceAddDTO resourceAddDTO = new ResourceAddDTO().setName(name).setType(type).setSort(sort)
                .setDisplayName(displayName).setPid(pid).setHandler(handler);
        CommonResult<ResourceBO> result = resourceService.addResource(resourceAddDTO);
    }

    public void update() {

    }

    public void delete() {

    }

}