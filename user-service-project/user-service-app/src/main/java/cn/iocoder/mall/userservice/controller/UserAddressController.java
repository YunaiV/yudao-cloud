package cn.iocoder.mall.userservice.controller;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.userservice.manager.address.UserAddressManager;
import cn.iocoder.mall.userservice.rpc.address.dto.UserAddressCreateReqDTO;
import cn.iocoder.mall.userservice.rpc.address.dto.UserAddressRespDTO;
import cn.iocoder.mall.userservice.rpc.address.dto.UserAddressUpdateReqDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@RestController
@RequestMapping("/user/address")
public class UserAddressController {

    @Autowired
    private UserAddressManager userAddressManager;

    @PostMapping("createUserAddress")
    public CommonResult<Integer> createUserAddress(@RequestBody UserAddressCreateReqDTO createDTO) {
        return success(userAddressManager.createUserAddress(createDTO));
    }

    @PostMapping("updateUserAddress")
    public CommonResult<Boolean> updateUserAddress(@RequestBody UserAddressUpdateReqDTO updateDTO) {
        userAddressManager.updateUserAddress(updateDTO);
        return success(true);
    }

    @GetMapping("deleteUserAddress")
    public CommonResult<Boolean> deleteUserAddress(@RequestParam("userAddressId") Integer userAddressId) {
        userAddressManager.deleteUserAddress(userAddressId);
        return success(true);
    }

    @GetMapping("getUserAddress")
    public CommonResult<UserAddressRespDTO> getUserAddress(@RequestParam("userAddressId")Integer userAddressId) {
        return success(userAddressManager.getUserAddress(userAddressId));
    }

    @GetMapping("listUserAddressesByIds")
    public CommonResult<List<UserAddressRespDTO>> listUserAddresses(@RequestParam("userAddressIds")List<Integer> userAddressIds) {
        return success(userAddressManager.listUserAddresses(userAddressIds));
    }

    @GetMapping("listUserAddresses")
    public CommonResult<List<UserAddressRespDTO>> listUserAddresses(@RequestParam("userId")Integer userId, @RequestParam("type")Integer type) {
        return success(userAddressManager.listUserAddresses(userId, type));
    }

}