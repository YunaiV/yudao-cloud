package cn.iocoder.yudao.module.kafka.controller.admin.user;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;

import cn.iocoder.yudao.module.kafka.controller.app.user.vo.UserRespVO;

import cn.iocoder.yudao.module.kafka.convert.app.user.UserConvert;
import cn.iocoder.yudao.module.kafka.dal.dataobject.dept.DeptDO;
import cn.iocoder.yudao.module.kafka.service.dept.DeptService;
import cn.iocoder.yudao.module.kafka.service.user.AdminUserService;



import cn.iocoder.yudao.module.kafka.dal.dataobject.user.AdminUserDO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * @author zyh
 */
@Tag(name = "管理后台 - 用户")
@RestController
@RequestMapping("/kafka/user")
@Validated
public class UserController {

    @Resource
    private AdminUserService userService;
    @Resource
    private DeptService deptService;


    @GetMapping("/get")
    @Operation(summary = "获得用户详情")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('system:user:query')")
    public CommonResult<UserRespVO> getUser(@RequestParam("id") Long id) {
        AdminUserDO user = userService.getUser(id);
        if (user == null) {
            // 优化suceess 构造还书
            return success(null,"根据此id查询不到数据");
        }
        // 拼接数据
        DeptDO dept = deptService.getDept(user.getDeptId());
        return success(UserConvert.INSTANCE.convert(user, dept));
    }

}
