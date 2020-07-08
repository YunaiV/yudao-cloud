package cn.iocoder.mall.system.rest.controller.authorization;

import cn.iocoder.common.framework.enums.MallConstants;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.security.core.annotation.RequiresPermissions;
import cn.iocoder.mall.security.core.context.AdminSecurityContextHolder;
import cn.iocoder.mall.system.biz.bo.authorization.ResourceTreeNodeBO;
import cn.iocoder.mall.system.biz.dto.authorization.ResourceAddDTO;
import cn.iocoder.mall.system.biz.dto.authorization.ResourceDeleteDTO;
import cn.iocoder.mall.system.biz.dto.authorization.ResourceGetTreeDTO;
import cn.iocoder.mall.system.biz.dto.authorization.ResourceUpdateDTO;
import cn.iocoder.mall.system.biz.service.authorization.ResourceService;
import cn.iocoder.mall.system.rest.convert.authorization.AdminsResourceConvert;
import cn.iocoder.mall.system.rest.request.authorization.AdminsResourceAddRequest;
import cn.iocoder.mall.system.rest.request.authorization.AdminsResourceUpdateRequest;
import cn.iocoder.mall.system.rest.response.authorization.AdminsResourceTreeResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(MallConstants.ROOT_PATH_ADMIN + "/resource")
@Api(tags = "管理员 - 资源 API")
public class AdminsResourceController {

    @Autowired
    private ResourceService resourceService;

    @GetMapping("/tree")
    @ApiOperation(value = "获得所有资源，按照树形结构返回")
    @RequiresPermissions("system:resource:tree")
    public CommonResult<List<AdminsResourceTreeResponse>> tree() {
        List<ResourceTreeNodeBO> resourceTreeNodeBOs = resourceService.getResourceTree(new ResourceGetTreeDTO());
        return CommonResult.success(AdminsResourceConvert.INSTANCE.convertList(resourceTreeNodeBOs));
    }

}
