package cn.iocoder.mall.managementweb.controller.user;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.user.vo.UserPageReqVO;
import cn.iocoder.mall.managementweb.controller.user.vo.UserRespVO;
import cn.iocoder.mall.managementweb.controller.user.vo.UserUpdateInfoReqVO;
import cn.iocoder.mall.managementweb.controller.user.vo.UserUpdateStatusReqVO;
import cn.iocoder.mall.managementweb.manager.user.UserManager;
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
* 用户 Controller
*/
@RestController
@RequestMapping("/user")
@Api(tags = "用户")
@Validated
public class UserController {

    @Autowired
    private UserManager userManager;

    @PostMapping("/update-info")
    @ApiOperation("更新用户信息")
    public CommonResult<Boolean> updateUserInfo(@Valid UserUpdateInfoReqVO updateInfoReqVO) {
        userManager.updateUserInfo(updateInfoReqVO);
        return success(true);
    }

    @PostMapping("/update-status")
    @ApiOperation("更新用户信息")
    public CommonResult<Boolean> updateUserStatus(@Valid UserUpdateStatusReqVO updateStatusReqVO) {
        userManager.updateUserStatus(updateStatusReqVO);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得用户")
    @ApiImplicitParam(name = "userId", value = "用户编号", required = true)
    public CommonResult<UserRespVO> getUser(@RequestParam("userId") Integer userId) {
        return success(userManager.getUser(userId));
    }

    @GetMapping("/list")
    @ApiOperation("获得用户列表")
    @ApiImplicitParam(name = "userIds", value = "用户编号列表", required = true)
    public CommonResult<List<UserRespVO>> listUsers(@RequestParam("userIds") List<Integer> userIds) {
        return success(userManager.listUsers(userIds));
    }

    @GetMapping("/page")
    @ApiOperation("获得用户分页")
    public CommonResult<PageResult<UserRespVO>> pageUser(UserPageReqVO pageVO) {
        return success(userManager.pageUser(pageVO));
    }

}
