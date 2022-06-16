package cn.iocoder.mall.systemservice.controller;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.systemservice.manager.admin.DepartmentManager;
import cn.iocoder.mall.systemservice.rpc.admin.dto.*;
import cn.iocoder.mall.systemservice.rpc.admin.vo.DepartmentVO;
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
@RequestMapping("/system/department")
public class DepartmentController {
    @Autowired
    private DepartmentManager departmentManager;

    @PostMapping("createDepartment")
    public CommonResult<Integer> createDepartment(@RequestBody DepartmentCreateDTO createDTO) {
        return success(departmentManager.createDepartment(createDTO));
    }

    @PostMapping("updateDepartment")
    public CommonResult<Boolean> updateDepartment(@RequestBody  DepartmentUpdateDTO updateDTO) {
        departmentManager.updateDepartment(updateDTO);
        return success(true);
    }

    @GetMapping("deleteDepartment")
    public CommonResult<Boolean> deleteDepartment(@RequestParam("departmentId")Integer departmentId) {
        departmentManager.deleteDepartment(departmentId);
        return success(true);
    }

    @GetMapping("getDepartment")
    public CommonResult<DepartmentVO> getDepartment(@RequestParam("departmentId") Integer departmentId) {
        return success(departmentManager.getDepartment(departmentId));
    }

    @GetMapping("listDepartments")
    public CommonResult<List<DepartmentVO>> listDepartments(@RequestParam("departmentIds")Collection<Integer> departmentIds) {
        return success(departmentManager.listDepartments(departmentIds));
    }

    @GetMapping("listAllDepartments")
    public CommonResult<List<DepartmentVO>> listDepartments() {
        return success(departmentManager.listDepartments());
    }


}
