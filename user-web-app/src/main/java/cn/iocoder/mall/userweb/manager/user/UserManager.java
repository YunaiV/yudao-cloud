package cn.iocoder.mall.userweb.manager.user;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.userservice.rpc.user.UserRpc;
import cn.iocoder.mall.userservice.rpc.user.vo.UserVO;
import cn.iocoder.mall.userweb.controller.user.vo.UserInfoVO;
import cn.iocoder.mall.userweb.convert.user.UserConvert;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

@Service
public class UserManager {

    @Reference(version = "${dubbo.consumer.UserRpc.version}", validation = "false")
    private UserRpc userRpc;

    public UserInfoVO getUser(Integer id) {
        CommonResult<UserVO> userResult = userRpc.getUser(id);
        userResult.checkError();
        return UserConvert.INSTANCE.convert(userResult.getData());
    }

}
