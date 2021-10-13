package cn.iocoder.mall.systemservice.rpc.permission;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.systemservice.rpc.permission.dto.ResourceCreateDTO;
import cn.iocoder.mall.systemservice.rpc.permission.dto.ResourceUpdateDTO;
import cn.iocoder.mall.systemservice.rpc.permission.vo.ResourceVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

/**
* 部门 Rpc 接口
*/
@FeignClient("system-service")
public interface ResourceFeign {

    @PostMapping("/system/resource/createResource")
    public CommonResult<Integer> createResource(@RequestBody ResourceCreateDTO createDTO);

    @PostMapping("/system/resource/updateResource")
    public CommonResult<Boolean> updateResource(@RequestBody ResourceUpdateDTO updateDTO);

    @GetMapping("/system/resource/deleteResource")
    public CommonResult<Boolean> deleteResource(@RequestParam("resourceId") Integer resourceId) ;
    @GetMapping("/system/resource/getResource")
    public CommonResult<ResourceVO> getResource(@RequestParam("resourceId") Integer resourceId);

    @GetMapping("/system/resource/listAllResource")
    public CommonResult<List<ResourceVO>> listResource() ;

    @GetMapping("/system/resource/listResource")
    public CommonResult<List<ResourceVO>> listResource(@RequestParam("resourceIds") List<Integer> resourceIds);

    @GetMapping("/system/resource/listRoleResource")
    public CommonResult<List<ResourceVO>> listRoleResource(@RequestParam("roleIds") Collection<Integer> roleIds, @RequestParam("type") Integer type) ;

}
