package cn.iocoder.mall.userservice.rpc.address;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.userservice.rpc.address.dto.UserAddressCreateReqDTO;
import cn.iocoder.mall.userservice.rpc.address.dto.UserAddressRespDTO;
import cn.iocoder.mall.userservice.rpc.address.dto.UserAddressUpdateReqDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
* 用户收件地址 Rpc 接口
*/
@FeignClient("user-service")
public interface UserAddressFeign {

    @PostMapping("/user/address/createUserAddress")
    public CommonResult<Integer> createUserAddress(@RequestBody UserAddressCreateReqDTO createDTO);

    @PostMapping("/user/address/updateUserAddress")
    public CommonResult<Boolean> updateUserAddress(@RequestBody UserAddressUpdateReqDTO updateDTO);

    @GetMapping("/user/address/deleteUserAddress")
    public CommonResult<Boolean> deleteUserAddress(@RequestParam("userAddressId") Integer userAddressId);

    @GetMapping("/user/address/getUserAddress")
    public CommonResult<UserAddressRespDTO> getUserAddress(@RequestParam("userAddressId")Integer userAddressId) ;

    @GetMapping("/user/address/listUserAddressesByIds")
    public CommonResult<List<UserAddressRespDTO>> listUserAddresses(@RequestParam("userAddressIds")List<Integer> userAddressIds) ;

    @GetMapping("/user/address/listUserAddresses")
    public CommonResult<List<UserAddressRespDTO>> listUserAddresses(@RequestParam("userId")Integer userId, @RequestParam("type")Integer type);

}
