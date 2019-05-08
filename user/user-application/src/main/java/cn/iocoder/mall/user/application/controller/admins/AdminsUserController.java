package cn.iocoder.mall.user.application.controller.admins;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.user.api.UserService;
import cn.iocoder.mall.user.api.bo.UserPageBO;
import cn.iocoder.mall.user.api.dto.UserPageDTO;
import cn.iocoder.mall.user.api.dto.UserUpdateDTO;
import cn.iocoder.mall.user.application.convert.UserConvert;
import cn.iocoder.mall.user.application.vo.admins.AdminsUserPageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@RestController
@RequestMapping("/admins/user")
@Api("用户模块")
public class AdminsUserController {

    @Reference(validation = "true", version = "${dubbo.provider.UserService.version}")
    private UserService userService;

    // 分页
    @GetMapping("/page")
    @ApiOperation(value = "用户分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "nickname", value = "昵称，模糊匹配", example = "小王"),
            @ApiImplicitParam(name = "pageNo", value = "页码，从 1 开始", example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, example = "10"),
    })
    public CommonResult<AdminsUserPageVO> page(@RequestParam(value = "nickname", required = false) String nickname,
                                               @RequestParam(value = "status", required = false) Integer status,
                                               @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                               @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        UserPageDTO userPageDTO = new UserPageDTO().setNickname(nickname).setStatus(status)
                .setPageNo(pageNo).setPageSize(pageSize);
        // 查询分页
        UserPageBO result = userService.getUserPage(userPageDTO);
        // 转换结果
        return success(UserConvert.INSTANCE.convert(result));
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新用户基本信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户编号", required = true, example = "1"),
            @ApiImplicitParam(name = "nickname", value = "昵称", required = true, example = "小王"),
            @ApiImplicitParam(name = "avatar", value = "头像", required = true, example = "http://www.iocoder.cn/xxx.jpg"),
    })
    public CommonResult<Boolean> update(@RequestParam("id") Integer id,
                                        @RequestParam("nickname") String nickname,
                                        @RequestParam("avatar") String avatar) {
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO().setId(id).setNickname(nickname).setNickname(nickname).setAvatar(avatar);
        // 更新
        return success(userService.updateUser(userUpdateDTO));
    }

    @PostMapping("/update_status")
    @ApiOperation(value = "更新用户状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户编号", required = true, example = "1"),
            @ApiImplicitParam(name = "status", value = "状态。1 - 开启；2 - 禁用", required = true, example = "1"),
    })
    public CommonResult<Boolean> updateStatus(@RequestParam("id") Integer id,
                                              @RequestParam("status") Integer status) {
        return success(userService.updateUserStatus(id, status));
    }

}
