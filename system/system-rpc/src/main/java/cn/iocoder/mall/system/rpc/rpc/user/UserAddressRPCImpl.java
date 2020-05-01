package cn.iocoder.mall.system.rpc.rpc.user;

import cn.iocoder.mall.system.biz.service.user.UserAddressService;
import cn.iocoder.mall.system.rpc.api.user.UserAddressRPC;
import cn.iocoder.mall.system.rpc.convert.user.UserAddressRPCConvert;
import cn.iocoder.mall.system.rpc.request.user.UserAddressAddRequest;
import cn.iocoder.mall.system.rpc.request.user.UserAddressUpdateRequest;
import cn.iocoder.mall.system.rpc.response.user.UserAddressResponse;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "${dubbo.provider.UserAddressRPC.version}", validation = "true")
public class UserAddressRPCImpl implements UserAddressRPC {

    @Autowired
    private UserAddressService userAddressService;

    @Override
    public void addAddress(UserAddressAddRequest userAddressAddRequest) {
        userAddressService.addAddress(UserAddressRPCConvert.INSTANCE.convert(userAddressAddRequest));
    }

    @Override
    public void updateAddress(UserAddressUpdateRequest userAddressUpdateRequest) {
        userAddressService.updateAddress(UserAddressRPCConvert.INSTANCE.convert(userAddressUpdateRequest));
    }

    @Override
    public void removeAddress(Integer userId, Integer addressId) {
        userAddressService.removeAddress(userId, addressId);
    }

    @Override
    public List<UserAddressResponse> addressList(Integer userId) {
        return UserAddressRPCConvert.INSTANCE.convert(userAddressService.addressList(userId));
    }

    @Override
    public UserAddressResponse getAddress(Integer userId, Integer id) {
        return UserAddressRPCConvert.INSTANCE.convert(userAddressService.getAddress(userId, id));
    }

    @Override
    public UserAddressResponse getDefaultAddress(Integer userId) {
        return UserAddressRPCConvert.INSTANCE.convert(userAddressService.getDefaultAddress(userId));
    }
}
