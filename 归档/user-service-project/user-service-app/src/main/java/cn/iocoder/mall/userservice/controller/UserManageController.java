package cn.iocoder.mall.userservice.controller;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.userservice.manager.user.UserManager;
import cn.iocoder.mall.userservice.rpc.user.dto.UserCreateReqDTO;
import cn.iocoder.mall.userservice.rpc.user.dto.UserPageReqDTO;
import cn.iocoder.mall.userservice.rpc.user.dto.UserRespDTO;
import cn.iocoder.mall.userservice.rpc.user.dto.UserUpdateReqDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@RestController
@RequestMapping("/user/manage")
public class UserManageController {

    @Autowired
    private UserManager userManager;

    @GetMapping("getUser")
    public CommonResult<UserRespDTO> getUser(@RequestParam("id") Integer id) {
        return success(userManager.getUser(id));
    }

    @GetMapping("listUsers")
    public CommonResult<List<UserRespDTO>> listUsers(@RequestParam("userIds") List<Integer> userIds) {
        return success(userManager.listUsers(userIds));
    }

    @PostMapping("createUserIfAbsent")
    public CommonResult<UserRespDTO> createUserIfAbsent(@RequestBody UserCreateReqDTO createDTO) {
        return success(userManager.createUserIfAbsent(createDTO));
    }

    @PostMapping("updateUser")
    public CommonResult<Boolean> updateUser(@RequestBody UserUpdateReqDTO updateDTO) {
        userManager.updateUser(updateDTO);
        return success(true);
    }

    @PostMapping("pageUser")
    public CommonResult<PageResult<UserRespDTO>> pageUser(@RequestBody UserPageReqDTO pageDTO) {
        return success(userManager.pageUser(pageDTO));
    }

}