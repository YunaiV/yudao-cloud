package cn.iocoder.mall.user.rpc.rpc.user;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.user.biz.service.user.UserAddressService;
import cn.iocoder.mall.user.rpc.api.UserAddressRPC;
import cn.iocoder.mall.user.rpc.convert.user.UserAddressRPCConvert;
import cn.iocoder.mall.user.rpc.response.user.UserAddressResponse;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户地址 RPC
 *
 * author: sin
 * time: 2020/5/1 10:26 上午
 */
@Service(version = "${dubbo.provider.UserAddressRPC.version}", validation = "true")
public class UserAddressRPCImpl implements UserAddressRPC {

    @Autowired
    private UserAddressService userAddressService;

    @Override
    public CommonResult<UserAddressResponse> getAddress(Integer id) {
        return CommonResult.success(UserAddressRPCConvert.INSTANCE.convert(userAddressService.getAddress(id)));
    }

    @Override
    public CommonResult<UserAddressResponse> getDefaultAddress(Integer userId) {
        return CommonResult.success(UserAddressRPCConvert.INSTANCE.convert(userAddressService.getDefaultAddress(userId)));
    }
}
