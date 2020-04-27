package cn.iocoder.mall.system.rest.controller.authorization;

import cn.iocoder.common.framework.constant.MallConstants;
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

    @PostMapping("/add")
    @ApiOperation(value = "创建资源", notes = "例如说，菜单资源，按钮资源")
    @RequiresPermissions("system:resource:add")
    public CommonResult<Integer> add(AdminsResourceAddRequest request) {
        ResourceAddDTO addDTO = AdminsResourceConvert.INSTANCE.convert(request)
                .setAdminId(AdminSecurityContextHolder.getAdminId());
        return CommonResult.success(resourceService.addResource(addDTO));
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新资源")
    @RequiresPermissions("system:resource:update")
    public CommonResult<Boolean> update(AdminsResourceUpdateRequest request) {
        ResourceUpdateDTO updateDTO = AdminsResourceConvert.INSTANCE.convert(request)
                .setAdminId(AdminSecurityContextHolder.getAdminId());
        resourceService.updateResource(updateDTO);
        return CommonResult.success(true);
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除资源")
    @ApiImplicitParam(name = "id", value = "资源编号", required = true, example = "1")
    @RequiresPermissions("system:resource:delete")
    public CommonResult<Boolean> delete(@RequestParam("id") Integer id) {
        ResourceDeleteDTO deleteDTO = new ResourceDeleteDTO().setId(id)
                .setAdminId(AdminSecurityContextHolder.getAdminId());
        resourceService.deleteResource(deleteDTO);
        return CommonResult.success(true);
    }

}
