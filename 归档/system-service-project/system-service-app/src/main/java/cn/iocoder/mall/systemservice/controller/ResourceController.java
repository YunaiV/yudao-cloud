package cn.iocoder.mall.systemservice.controller;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.systemservice.manager.permission.ResourceManager;
import cn.iocoder.mall.systemservice.rpc.permission.dto.ResourceCreateDTO;
import cn.iocoder.mall.systemservice.rpc.permission.dto.ResourceUpdateDTO;
import cn.iocoder.mall.systemservice.rpc.permission.vo.ResourceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
 * Title:
 * Description:
 *
 * @author zhuyang
 * @version 1.0 2021/10/11
 */
@RestController
@RequestMapping("/system/resource")
public class ResourceController {

    @Autowired
    private ResourceManager resourceManager;

    @PostMapping("createResource")
    public CommonResult<Integer> createResource(@RequestBody ResourceCreateDTO createDTO) {
        return success(resourceManager.createResource(createDTO));
    }

    @PostMapping("updateResource")
    public CommonResult<Boolean> updateResource(@RequestBody ResourceUpdateDTO updateDTO) {
        resourceManager.updateResource(updateDTO);
        return success(true);
    }

    @GetMapping("deleteResource")
    public CommonResult<Boolean> deleteResource(@RequestParam("resourceId")Integer resourceId) {
        resourceManager.deleteResource(resourceId);
        return success(true);
    }

    @GetMapping("getResource")
    public CommonResult<ResourceVO> getResource(@RequestParam("resourceId")Integer resourceId) {
        return success(resourceManager.getResource(resourceId));
    }

    @GetMapping("listAllResource")
    public CommonResult<List<ResourceVO>> listResource() {
        return success(resourceManager.listResources());
    }

    @GetMapping("listResource")
    public CommonResult<List<ResourceVO>> listResource(@RequestParam("resourceIds")List<Integer> resourceIds) {
        return success(resourceManager.listResources(resourceIds));
    }

    @GetMapping("listRoleResource")
    public CommonResult<List<ResourceVO>> listRoleResource(@RequestParam("roleIds") Collection<Integer> roleIds, @RequestParam("type")Integer type) {
        return success(resourceManager.listRoleResources(roleIds, type));
    }

}
