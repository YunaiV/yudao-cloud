package cn.iocoder.mall.shopweb.controller.user;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.security.user.core.context.UserSecurityContextHolder;
import cn.iocoder.mall.shopweb.controller.user.vo.address.UserAddressCreateReqVO;
import cn.iocoder.mall.shopweb.controller.user.vo.address.UserAddressRespVO;
import cn.iocoder.mall.shopweb.controller.user.vo.address.UserAddressUpdateReqVO;
import cn.iocoder.mall.shopweb.service.user.UserAddressManager;
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
* 用户收件地址 Controller
*/
@RestController
@RequestMapping("/user-address")
@Api(tags = "用户收件地址")
@Validated
public class UserAddressController {

    @Autowired
    private UserAddressManager userAddressManager;

    @PostMapping("/create")
    @ApiOperation("创建用户收件地址")
    @RequiresPermissions
    public CommonResult<Integer> createUserAddress(@Valid UserAddressCreateReqVO createVO) {
        return success(userAddressManager.createUserAddress(UserSecurityContextHolder.getUserId(), createVO));
    }

    @PostMapping("/update")
    @ApiOperation("更新用户收件地址")
    @RequiresPermissions
    public CommonResult<Boolean> updateUserAddress(@Valid UserAddressUpdateReqVO updateVO) {
        userAddressManager.updateUserAddress(UserSecurityContextHolder.getUserId(), updateVO);
        return success(true);
    }

    @PostMapping("/delete")
    @ApiOperation("删除用户收件地址")
    @ApiImplicitParam(name = "userAddressId", value = "用户收件地址编号", required = true)
    @RequiresPermissions
    public CommonResult<Boolean> deleteUserAddress(@RequestParam("userAddressId") Integer userAddressId) {
        userAddressManager.deleteUserAddress(UserSecurityContextHolder.getUserId(), userAddressId);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得用户收件地址")
    @ApiImplicitParam(name = "userAddressId", value = "用户收件地址编号", required = true)
    @RequiresPermissions
    public CommonResult<UserAddressRespVO> getUserAddress(@RequestParam("userAddressId") Integer userAddressId) {
        return success(userAddressManager.getUserAddress(UserSecurityContextHolder.getUserId(), userAddressId));
    }

    @GetMapping("/get-default")
    @ApiOperation("获得默认的用户收件地址")
    @RequiresPermissions
    public CommonResult<UserAddressRespVO> getDefaultUserAddress() {
        return success(userAddressManager.getDefaultUserAddress(UserSecurityContextHolder.getUserId()));
    }

    @GetMapping("/list")
    @ApiOperation("获得用户收件地址列表")
    @RequiresPermissions
    public CommonResult<List<UserAddressRespVO>> listUserAddresses() {
        return success(userAddressManager.listUserAddresses(UserSecurityContextHolder.getUserId()));
    }

}
