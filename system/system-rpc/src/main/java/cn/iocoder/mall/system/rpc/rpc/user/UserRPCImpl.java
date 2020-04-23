package cn.iocoder.mall.system.rpc.rpc.user;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.system.biz.bo.user.UserBO;
import cn.iocoder.mall.system.biz.service.user.UserService;
import cn.iocoder.mall.system.rpc.api.user.UserRPC;
import cn.iocoder.mall.system.rpc.convert.user.UserConvert;
import cn.iocoder.mall.system.rpc.response.user.UserResponse;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "${dubbo.provider.UserRPC.version}", validation = "true")
public class UserRPCImpl implements UserRPC {

    @Autowired
    private UserService userService;

    @Override
    public CommonResult<UserResponse> getUserByAccountId(Integer accountId) {
        UserBO userBO = userService.getUserByAccountId(accountId);
        return CommonResult.success(UserConvert.INSTANCE.convert(userBO));
    }

}
