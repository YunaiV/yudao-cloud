package cn.iocoder.mall.admin.application.controller.admins;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.api.DeptmentService;
import cn.iocoder.mall.admin.api.bo.deptment.DeptmentBO;
import cn.iocoder.mall.admin.api.dto.depetment.DeptmentAddDTO;
import cn.iocoder.mall.admin.sdk.context.AdminSecurityContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-06-14
 * @time: 19:07
 */
@RestController
@RequestMapping("admins/dept")
@Api("部门模块")
public class DeptmentController {

    @Autowired
    private DeptmentService deptmentService;

    @PostMapping("add")
    @ApiOperation(value = "新增部门", notes = "选择部门名称，父级部门")
    public CommonResult<DeptmentBO> add(@RequestBody DeptmentAddDTO deptmentAddDTO){
        return success(deptmentService.addDeptment(
                AdminSecurityContextHolder.getContext().getAdminId(), deptmentAddDTO));

    }
}
