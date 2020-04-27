package cn.iocoder.mall.system.rest.controller.authorization;

import cn.iocoder.common.framework.constant.MallConstants;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.security.core.context.AdminSecurityContextHolder;
import cn.iocoder.mall.system.biz.bo.authorization.ResourceBO;
import cn.iocoder.mall.system.biz.bo.authorization.ResourceTreeNodeBO;
import cn.iocoder.mall.system.biz.dto.authorization.AuthorizationGetResourcesByAccountIdDTO;
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

import java.util.List;
import java.util.Set;
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
        List<ResourceTreeNodeBO> resourceTreeNodeBOs = authorizationService.getResourceTreeByAccountId(new AuthorizationGetResourcesByAccountIdDTO()
                .setAccountId(AdminSecurityContextHolder.getAccountId()).setType(ResourceTypeEnum.MENU.getType()));
        return CommonResult.success(AdminsAuthorizationConvert.INSTANCE.convertList(resourceTreeNodeBOs));
    }

    @GetMapping("/resource-permissions")
    @ApiOperation(value = "获得当前账号的资源权限列表")
    public CommonResult<Set<String>> resourcePermissions() {
        List<ResourceBO> resources = authorizationService.getResourcesByAccountId(new AuthorizationGetResourcesByAccountIdDTO()
                .setAccountId(AdminSecurityContextHolder.getAccountId()));
        return CommonResult.success(resources.stream().map(ResourceBO::getRoute).collect(Collectors.toSet()));
    }

}
