package cn.iocoder.mall.userweb.controller.user;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.security.user.core.context.UserSecurityContextHolder;
import cn.iocoder.mall.userweb.controller.user.vo.UserRespVO;
import cn.iocoder.mall.userweb.manager.user.UserManager;
import cn.iocoder.security.annotations.RequiresAuthenticate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@Api(tags = "用户信息 API")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserManager userManager;

    @ApiOperation(value = "用户信息")
    @GetMapping("/info")
    @RequiresAuthenticate
    public CommonResult<UserRespVO> info() {
        UserRespVO user = userManager.getUser(UserSecurityContextHolder.getUserId());
        return success(user);
    }

//    @PostMapping("/update_avatar")
//    @RequiresLogin
//    @ApiOperation(value = "更新头像")
//    public CommonResult<Boolean> updateAvatar(@RequestParam("avatar") String avatar) {
//        // 创建
//        UserUpdateDTO userUpdateDTO = new UserUpdateDTO().setId(UserSecurityContextHolder.getContext().getUserId())
//                .setAvatar(avatar);
//        // 更新头像
//        return success(userService.updateUser(userUpdateDTO));
//    }

//    @PostMapping("/update_nickname")
//    @RequiresLogin
//    @ApiOperation(value = "更新昵称")
//    public CommonResult<Boolean> updateNickname(@RequestParam("nickname") String nickname) {
//        // 创建
//        UserUpdateDTO userUpdateDTO = new UserUpdateDTO().setId(UserSecurityContextHolder.getContext().getUserId())
//                .setNickname(nickname);
//        // 更新头像
//        return success(userService.updateUser(userUpdateDTO));
//    }

}
