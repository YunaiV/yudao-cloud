package cn.iocoder.mall.user.application.controller.users;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.user.api.UserAddressService;
import cn.iocoder.mall.user.api.bo.UserAddressBO;
import cn.iocoder.mall.user.api.dto.UserAddressAddDTO;
import cn.iocoder.mall.user.api.dto.UserAddressUpdateDTO;
import cn.iocoder.mall.user.application.convert.UserAddressConvert;
import cn.iocoder.mall.user.application.po.UserAddressAddPO;
import cn.iocoder.mall.user.application.po.UserAddressUpdatePO;
import cn.iocoder.mall.user.sdk.context.UserSecurityContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户地址
 *
 * @author Sin
 * @time 2019-04-06 14:11
 */
@RestController
@RequestMapping("users/address")
@Api(value = "用户地址API")
public class UserAddressController {

    @Reference(validation = "true")
    @Autowired // TODO dubbo 2.7.2 删除，用于解决 bug
    private UserAddressService userAddressService;

    @PostMapping("add")
    @ApiOperation(value = "用户地址-添加")
    public CommonResult addAddress(@Validated UserAddressAddPO userAddressAddPO) {
        Integer userId = UserSecurityContextHolder.getContext().getUserId();
        UserAddressAddDTO userAddressAddDTO = UserAddressConvert.INSTANCE.convert(userAddressAddPO);
        userAddressAddDTO.setUserId(userId);
        return userAddressService.addAddress(userAddressAddDTO);
    }

    @PutMapping("update")
    @ApiOperation(value = "用户地址-更新")
    public CommonResult updateAddress(@Validated UserAddressUpdatePO userAddressUpdatePO) {
        Integer userId = UserSecurityContextHolder.getContext().getUserId();
        UserAddressUpdateDTO userAddressUpdateDTO = UserAddressConvert.INSTANCE.convert(userAddressUpdatePO);
        userAddressUpdateDTO.setUserId(userId);
        return userAddressService.updateAddress(userAddressUpdateDTO);
    }

    @DeleteMapping("remove")
    @ApiOperation(value = "用户地址-删除")
    public CommonResult removeAddress(@RequestParam("id") Integer id) {
        Integer userId = UserSecurityContextHolder.getContext().getUserId();
        return userAddressService.removeAddress(userId, id);
    }

    @GetMapping("list")
    @ApiOperation(value = "用户地址列表")
    public CommonResult<List<UserAddressBO>> addressList() {
        Integer userId = UserSecurityContextHolder.getContext().getUserId();
        return userAddressService.addressList(userId);
    }

    @GetMapping("address")
    @ApiOperation(value = "获取地址")
    public CommonResult<UserAddressBO> getAddress(@RequestParam("id") Integer id) {
        Integer userId = UserSecurityContextHolder.getContext().getUserId();
        return userAddressService.getAddress(userId, id);
    }

    @GetMapping("default_address")
    @ApiOperation(value = "获取默认地址")
    public CommonResult<UserAddressBO> getDefaultAddress() {
        Integer userId = UserSecurityContextHolder.getContext().getUserId();
        return userAddressService.getDefaultAddress(userId);
    }
}
