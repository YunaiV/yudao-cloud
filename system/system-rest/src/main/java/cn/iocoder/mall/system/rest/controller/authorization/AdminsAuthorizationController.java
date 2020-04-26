package cn.iocoder.mall.system.rest.controller.authorization;

import cn.iocoder.common.framework.constant.MallConstants;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.security.core.context.AdminSecurityContextHolder;
import cn.iocoder.mall.system.biz.bo.authorization.ResourceBO;
import cn.iocoder.mall.system.biz.dto.authorization.AuthorizationGetResourcesByAccountIdDTO;
import cn.iocoder.mall.system.biz.enums.authorization.ResourceIdEnum;
import cn.iocoder.mall.system.biz.enums.authorization.ResourceTypeEnum;
import cn.iocoder.mall.system.biz.service.authorization.AuthorizationService;
import cn.iocoder.mall.system.rest.convert.authorization.AdminsAuthorizationConvert;
import cn.iocoder.mall.system.rest.response.authorization.AdminsAuthorizationMenuTreeResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(MallConstants.ROOT_PATH_ADMIN + "/authorization")
@Api(tags = "管理员 - 授权 API")
@Slf4j
public class AdminsAuthorizationController {

    @Autowired
    private AuthorizationService authorizationService;

    @GetMapping("/menu-resource-tree")
    @ApiOperation(value = "获得当前账号的菜单资源树", notes = "以树结构返回")
    public CommonResult<List<AdminsAuthorizationMenuTreeResponse>> menuResourceTree() {
        List<ResourceBO> resources = authorizationService.getResourcesByAccountId(new AuthorizationGetResourcesByAccountIdDTO()
                .setAccountId(AdminSecurityContextHolder.getAccountId()).setType(ResourceTypeEnum.MENU.getValue()));
        // 创建 AdminMenuTreeNodeVO Map
        // 使用 LinkedHashMap 的原因，是为了排序 。实际也可以用 Stream API ，就是太丑了。
        Map<Integer, AdminsAuthorizationMenuTreeResponse> treeNodeMap = new LinkedHashMap<>();
        resources.stream().sorted(Comparator.comparing(ResourceBO::getSort))
                .forEach(resourceBO -> treeNodeMap.put(resourceBO.getId(), AdminsAuthorizationConvert.INSTANCE.convert(resourceBO)));
        // 处理父子关系
        treeNodeMap.values().stream()
                .filter(node -> !node.getPid().equals(ResourceIdEnum.ROOT.getId()))
                .forEach((childNode) -> {
                    // 获得父节点
                    AdminsAuthorizationMenuTreeResponse parentNode = treeNodeMap.get(childNode.getPid());
                    if (parentNode == null) {
                        log.error("[menuResourceTree][resource({}) 找不到父资源({})]", childNode.getId(), childNode.getPid());
                        return;
                    }
                    if (parentNode.getChildren() == null) { // 初始化 children 数组
                        parentNode.setChildren(new ArrayList<>());
                    }
                    // 将自己添加到父节点中
                    parentNode.getChildren().add(childNode);
                });
        // 获得到所有的根节点
        List<AdminsAuthorizationMenuTreeResponse> rootNodes = treeNodeMap.values().stream()
                .filter(node -> node.getPid().equals(ResourceIdEnum.ROOT.getId()))
                .collect(Collectors.toList());
        return CommonResult.success(rootNodes);
    }

    @GetMapping("/resource-permissions")
    @ApiOperation(value = "获得当前账号的资源权限列表")
    public CommonResult<Set<String>> resourcePermissions() {
        List<ResourceBO> resources = authorizationService.getResourcesByAccountId(new AuthorizationGetResourcesByAccountIdDTO()
                .setAccountId(AdminSecurityContextHolder.getAccountId()));
        return CommonResult.success(resources.stream().map(ResourceBO::getRoute).collect(Collectors.toSet()));
    }

}
