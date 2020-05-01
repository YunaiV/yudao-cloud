package cn.iocoder.mall.system.rpc.api.user;

import cn.iocoder.mall.system.rpc.request.user.UserAddressAddRequest;
import cn.iocoder.mall.system.rpc.request.user.UserAddressUpdateRequest;
import cn.iocoder.mall.system.rpc.response.user.UserAddressResponse;

import java.util.List;

/**
 * 用户地址 RPC
 *
 * author: sin
 * time: 2020/5/1 10:26 上午
 */
public interface UserAddressRPC {

    void addAddress(UserAddressAddRequest userAddressAddRequest);

    void updateAddress(UserAddressUpdateRequest userAddressUpdateRequest);

    void removeAddress(Integer userId, Integer addressId);

    List<UserAddressResponse> addressList(Integer userId);

    UserAddressResponse getAddress(Integer userId, Integer id);

    UserAddressResponse getDefaultAddress(Integer userId);
}
