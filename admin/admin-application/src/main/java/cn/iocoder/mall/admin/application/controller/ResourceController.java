package cn.iocoder.mall.admin.application.controller;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.api.ResourceService;
import cn.iocoder.mall.admin.api.bo.ResourceBO;
import cn.iocoder.mall.admin.api.constant.ResourceType;
import cn.iocoder.mall.admin.application.convert.ResourceConvert;
import cn.iocoder.mall.admin.sdk.context.AdminSecurityContextHolder;
import cn.iocoder.mall.admin.application.vo.AdminMenuTreeNodeVO;
import com.alibaba.dubbo.config.annotation.Reference;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("admin/resource")
@Api("资源模块")
public class ResourceController {

    @Reference
    private ResourceService resourceService;

    @GetMapping("/admin_menu_tree")
    @ApiOperation(value = "获得管理员拥有的菜单权限", notes = "以树结构返回")
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
    @ApiOperation(value = "获得管理员拥有的 URL 权限列表")
    public CommonResult adminUrlList() {
        return null;
    }

}