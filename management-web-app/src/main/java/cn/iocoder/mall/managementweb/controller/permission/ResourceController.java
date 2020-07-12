package cn.iocoder.mall.managementweb.controller.permission;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.managementweb.controller.permission.dto.ResourceCreateDTO;
import cn.iocoder.mall.managementweb.controller.permission.dto.ResourceUpdateDTO;
import cn.iocoder.mall.managementweb.controller.permission.vo.ResourceTreeNodeVO;
import cn.iocoder.mall.managementweb.controller.permission.vo.ResourceVO;
import cn.iocoder.mall.managementweb.manager.permission.ResourceManager;
import cn.iocoder.mall.security.admin.core.context.AdminSecurityContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
* 资源 Controller
*/
@RestController
@RequestMapping("/resource")
@Api(tags = "资源")
@Validated
public class ResourceController {

    @Autowired
    private ResourceManager resourceManager;

    @PostMapping("/create")
    @ApiOperation("创建资源")
    public CommonResult<Integer> createResource(@Valid ResourceCreateDTO createDTO) {
        return success(resourceManager.createResource(createDTO, AdminSecurityContextHolder.getAdminId()));
    }

    @PostMapping("/update")
    @ApiOperation("更新资源")
    public CommonResult<Boolean> updateResource(@Valid ResourceUpdateDTO updateDTO) {
        resourceManager.updateResource(updateDTO);
        return success(true);
    }

    @PostMapping("/delete")
    @ApiOperation("删除资源")
    @ApiImplicitParam(name = "resourceId", value = "资源编号", required = true)
    public CommonResult<Boolean> deleteResource(@RequestParam("resourceId") Integer resourceId) {
        resourceManager.deleteResource(resourceId);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得资源")
    public CommonResult<ResourceVO> getResource(@RequestParam("resourceId") Integer resourceId) {
        return success(resourceManager.getResource(resourceId));
    }

    @GetMapping("/list")
    @ApiOperation("获得资源列表")
    @ApiImplicitParam(name = "resourceId", value = "资源编号列表", required = true)
    public CommonResult<List<ResourceVO>> listResource(@RequestParam("resourceIds") List<Integer> resourceIds) {
        return success(resourceManager.listResource(resourceIds));
    }

    @GetMapping("/tree")
    @ApiOperation("获得资源树")
    public CommonResult<List<ResourceTreeNodeVO>> treeResource() {
        return success(resourceManager.treeResource());
    }

}
