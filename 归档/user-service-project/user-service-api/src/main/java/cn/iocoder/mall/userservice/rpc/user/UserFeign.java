package cn.iocoder.mall.userservice.rpc.user;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.userservice.rpc.user.dto.UserCreateReqDTO;
import cn.iocoder.mall.userservice.rpc.user.dto.UserPageReqDTO;
import cn.iocoder.mall.userservice.rpc.user.dto.UserRespDTO;
import cn.iocoder.mall.userservice.rpc.user.dto.UserUpdateReqDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 用户短信验证码 Rpc 接口
 */
@FeignClient("user-service")
public interface UserFeign {
    @GetMapping("/user/manage/getUser")
    public CommonResult<UserRespDTO> getUser(@RequestParam("id") Integer id) ;

    @GetMapping("/user/manage/listUsers")
    public CommonResult<List<UserRespDTO>> listUsers(@RequestParam("userIds") List<Integer> userIds) ;

    @PostMapping("/user/manage/createUserIfAbsent")
    public CommonResult<UserRespDTO> createUserIfAbsent(@RequestBody UserCreateReqDTO createDTO);
    @PostMapping("/user/manage/updateUser")
    public CommonResult<Boolean> updateUser(@RequestBody UserUpdateReqDTO updateDTO);

    @PostMapping("/user/manage/pageUser")
    public CommonResult<PageResult<UserRespDTO>> pageUser(@RequestBody UserPageReqDTO pageDTO);
}
