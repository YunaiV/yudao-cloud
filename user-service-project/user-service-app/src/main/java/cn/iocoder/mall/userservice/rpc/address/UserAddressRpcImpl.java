package cn.iocoder.mall.userservice.rpc.address;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.userservice.manager.address.UserAddressManager;
import cn.iocoder.mall.userservice.rpc.address.dto.UserAddressCreateReqDTO;
import cn.iocoder.mall.userservice.rpc.address.dto.UserAddressRespDTO;
import cn.iocoder.mall.userservice.rpc.address.dto.UserAddressUpdateReqDTO;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
* 用户收件地址 Rpc 实现类
*/
@DubboService(version = "${dubbo.provider.UserAddressRpc.version}")
public class UserAddressRpcImpl implements UserAddressRpc {

    @Autowired
    private UserAddressManager userAddressManager;

    @Override
    public CommonResult<Integer> createUserAddress(UserAddressCreateReqDTO createDTO) {
        return success(userAddressManager.createUserAddress(createDTO));
    }

    @Override
    public CommonResult<Boolean> updateUserAddress(UserAddressUpdateReqDTO updateDTO) {
        userAddressManager.updateUserAddress(updateDTO);
        return success(true);
    }

    @Override
    public CommonResult<Boolean> deleteUserAddress(Integer userAddressId) {
        userAddressManager.deleteUserAddress(userAddressId);
        return success(true);
    }

    @Override
    public CommonResult<UserAddressRespDTO> getUserAddress(Integer userAddressId) {
        return success(userAddressManager.getUserAddress(userAddressId));
    }

    @Override
    public CommonResult<List<UserAddressRespDTO>> listUserAddresses(List<Integer> userAddressIds) {
        return success(userAddressManager.listUserAddresses(userAddressIds));
    }

    @Override
    public CommonResult<List<UserAddressRespDTO>> listUserAddresses(Integer userId, Integer type) {
        return success(userAddressManager.listUserAddresses(userId, type));
    }

}
