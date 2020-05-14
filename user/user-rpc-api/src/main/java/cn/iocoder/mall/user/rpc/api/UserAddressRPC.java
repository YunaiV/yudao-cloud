package cn.iocoder.mall.user.rpc.api;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.user.rpc.response.user.UserAddressResponse;

/**
 * 用户地址 RPC
 *
 * author: sin
 * time: 2020/5/1 10:26 上午
 */
public interface UserAddressRPC {

    /**
     * 获取 - 根据id获取用户地址
     *
     * @param id
     * @return
     */
    CommonResult<UserAddressResponse> getAddress(Integer id);

    /**
     * 获取 - 获取用户默认地址
     *
     * @param userId
     * @return
     */
    CommonResult<UserAddressResponse> getDefaultAddress(Integer userId);
}
