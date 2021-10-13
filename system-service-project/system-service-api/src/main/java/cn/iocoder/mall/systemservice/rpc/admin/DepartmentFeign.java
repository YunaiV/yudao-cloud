package cn.iocoder.mall.systemservice.rpc.admin;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.systemservice.rpc.admin.dto.DepartmentCreateDTO;
import cn.iocoder.mall.systemservice.rpc.admin.dto.DepartmentUpdateDTO;
import cn.iocoder.mall.systemservice.rpc.admin.vo.DepartmentVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
* 部门 Rpc 接口
*/
@FeignClient("system-service")
public interface DepartmentFeign {
    @PostMapping("/system/department/createDepartment")
    public CommonResult<Integer> createDepartment(@RequestBody DepartmentCreateDTO createDTO) ;

    @PostMapping("/system/department/updateDepartment")
    public CommonResult<Boolean> updateDepartment(@RequestBody  DepartmentUpdateDTO updateDTO);

    @GetMapping("/system/department/deleteDepartment")
    public CommonResult<Boolean> deleteDepartment(@RequestParam("departmentId")Integer departmentId);

    @GetMapping("/system/department/getDepartment")
    public CommonResult<DepartmentVO> getDepartment(@RequestParam("departmentId") Integer departmentId) ;

    @GetMapping("/system/department/listDepartments")
    public CommonResult<List<DepartmentVO>> listDepartments(@RequestParam("departmentIds")Collection<Integer> departmentIds) ;

    @GetMapping("/system/department/listAllDepartments")
    public CommonResult<List<DepartmentVO>> listDepartments();


}
