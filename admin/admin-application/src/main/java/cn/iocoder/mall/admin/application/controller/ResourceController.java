package cn.iocoder.mall.admin.application.controller;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.api.ResourceService;
import cn.iocoder.mall.admin.api.bo.ResourceBO;
import cn.iocoder.mall.admin.api.constant.ResourceConstants;
import cn.iocoder.mall.admin.api.dto.ResourceAddDTO;
import cn.iocoder.mall.admin.api.dto.ResourceUpdateDTO;
import cn.iocoder.mall.admin.application.convert.ResourceConvert;
import cn.iocoder.mall.admin.application.vo.ResourceTreeNodeVO;
import cn.iocoder.mall.admin.application.vo.ResourceVO;
import cn.iocoder.mall.admin.sdk.context.AdminSecurityContextHolder;
import com.alibaba.dubbo.config.annotation.Reference;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("admins/resource")
@Api("资源模块")
public class ResourceController {

    @Reference(validation = "true")
    private ResourceService resourceService;

    @SuppressWarnings("Duplicates")
    @GetMapping("/tree")
    @ApiOperation(value = "获得所有资源，按照树形结构返回")
    public CommonResult<List<ResourceTreeNodeVO>> tree() {
        List<ResourceBO> resources = resourceService.getResourcesByType(null);
        // 创建 AdminMenuTreeNodeVO Map
        Map<Integer, ResourceTreeNodeVO> treeNodeMap = resources.stream().collect(Collectors.toMap(ResourceBO::getId, ResourceConvert.INSTANCE::convert2));
        // 处理父子关系
        treeNodeMap.values().stream()
                .filter(node -> !node.getPid().equals(ResourceConstants.PID_ROOT))
                .forEach((childNode) -> {
                    // 获得父节点
                    ResourceTreeNodeVO parentNode = treeNodeMap.get(childNode.getPid());
                    if (parentNode.getChildren() == null) { // 初始化 children 数组
                        parentNode.setChildren(new ArrayList<>());
                    }
                    // 将自己添加到父节点中
                    parentNode.getChildren().add(childNode);
                });
        // 获得到所有的根节点
        List<ResourceTreeNodeVO> rootNodes = treeNodeMap.values().stream()
                .filter(node -> node.getPid().equals(ResourceConstants.PID_ROOT))
                .sorted(Comparator.comparing(ResourceTreeNodeVO::getSort))
                .collect(Collectors.toList());
        return CommonResult.success(rootNodes);
    }

    @PostMapping("/add")
    @ApiOperation(value = "创建资源", notes = "例如说，菜单资源，Url 资源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "资源名字（标识）", required = true, example = "admin/info"),
            @ApiImplicitParam(name = "type", value = "资源类型。1 代表【菜单】；2 代表【Url】", required = true, example = "1"),
            @ApiImplicitParam(name = "sort", value = "排序", required = true, example = "1"),
            @ApiImplicitParam(name = "displayName", value = "菜单展示名", required = true, example = "商品管理"),
            @ApiImplicitParam(name = "pid", value = "父级资源编号", required = true, example = "1"),
            @ApiImplicitParam(name = "handler", value = "操作", required = true, example = "/order/list"),
    })
    public CommonResult<ResourceVO> add(@RequestParam("name") String name,
                                        @RequestParam("type") Integer type,
                                        @RequestParam("sort") Integer sort,
                                        @RequestParam("displayName") String displayName,
                                        @RequestParam("pid") Integer pid,
                                        @RequestParam("handler") String handler) {
        ResourceAddDTO resourceAddDTO = new ResourceAddDTO().setName(name).setType(type).setSort(sort)
                .setDisplayName(displayName).setPid(pid).setHandler(handler);
        return ResourceConvert.INSTANCE.convert3(resourceService.addResource(AdminSecurityContextHolder.getContext().getAdminId(), resourceAddDTO));
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新资源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "资源编号", required = true, example = "1"),
            @ApiImplicitParam(name = "name", value = "资源名字（标识）", required = true, example = "admin/info"),
            @ApiImplicitParam(name = "sort", value = "排序", required = true, example = "1"),
            @ApiImplicitParam(name = "displayName", value = "菜单展示名", required = true, example = "商品管理"),
            @ApiImplicitParam(name = "pid", value = "父级资源编号", required = true, example = "1"),
            @ApiImplicitParam(name = "handler", value = "操作", required = true, example = "/order/list"),
    })
    public CommonResult<Boolean> update(@RequestParam("id") Integer id,
                                        @RequestParam("name") String name,
                                        @RequestParam("sort") Integer sort,
                                        @RequestParam("displayName") String displayName,
                                        @RequestParam("pid") Integer pid,
                                        @RequestParam("handler") String handler) {
        ResourceUpdateDTO resourceUpdateDTO = new ResourceUpdateDTO().setId(id).setName(name).setSort(sort).setDisplayName(displayName).setPid(pid).setHandler(handler);
        return resourceService.updateResource(AdminSecurityContextHolder.getContext().getAdminId(), resourceUpdateDTO);
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除资源")
    @ApiImplicitParam(name = "id", value = "资源编号", required = true, example = "1")
    public CommonResult<Boolean> delete(@RequestParam("id") Integer id) {
        return resourceService.deleteResource(AdminSecurityContextHolder.getContext().getAdminId(), id);
    }

}