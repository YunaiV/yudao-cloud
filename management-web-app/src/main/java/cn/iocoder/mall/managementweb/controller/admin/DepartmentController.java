package cn.iocoder.mall.managementweb.controller.admin;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.managementweb.controller.admin.dto.DepartmentCreateDTO;
import cn.iocoder.mall.managementweb.controller.admin.dto.DepartmentUpdateDTO;
import cn.iocoder.mall.managementweb.controller.admin.vo.DepartmentTreeNodeVO;
import cn.iocoder.mall.managementweb.controller.admin.vo.DepartmentVO;
import cn.iocoder.mall.managementweb.manager.admin.DepartmentManager;
import cn.iocoder.security.annotations.RequiresPermissions;
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
* 部门 Controller
*/
@RestController
@RequestMapping("/department")
@Api(tags = "部门")
@Validated
public class DepartmentController {

    @Autowired
    private DepartmentManager departmentManager;

    @PostMapping("/create")
    @ApiOperation("创建部门")
    @RequiresPermissions("system:department:create")
    public CommonResult<Integer> createDepartment(@Valid DepartmentCreateDTO createDTO) {
        return success(departmentManager.createDepartment(createDTO));
    }

    @PostMapping("/update")
    @ApiOperation("更新部门")
    @RequiresPermissions("system:department:update")
    public CommonResult<Boolean> updateDepartment(@Valid DepartmentUpdateDTO updateDTO) {
        departmentManager.updateDepartment(updateDTO);
        return success(true);
    }

    @PostMapping("/delete")
    @ApiOperation("删除部门")
    @ApiImplicitParam(name = "departmentId", value = "部门编号", required = true)
    @RequiresPermissions("system:department:delete")
    public CommonResult<Boolean> deleteDepartment(@RequestParam("departmentId") Integer departmentId) {
        departmentManager.deleteDepartment(departmentId);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得部门")
    @ApiImplicitParam(name = "departmentId", value = "部门编号", required = true)
    @RequiresPermissions("system:department:tree")
    public CommonResult<DepartmentVO> getDepartment(@RequestParam("departmentId") Integer departmentId) {
        return success(departmentManager.getDepartment(departmentId));
    }

    @GetMapping("/list")
    @ApiOperation("获得部门列表")
    @ApiImplicitParam(name = "departmentIds", value = "部门编号列表", required = true)
    @RequiresPermissions("system:department:tree")
    public CommonResult<List<DepartmentVO>> listDepartments(@RequestParam("departmentIds") List<Integer> departmentIds) {
        return success(departmentManager.listDepartments(departmentIds));
    }

    @GetMapping("/tree")
    @ApiOperation("获得部门树")
    @RequiresPermissions("system:department:tree")
    public CommonResult<List<DepartmentTreeNodeVO>> treeDepartment() {
        return success(departmentManager.treeDepartment());
    }

}
