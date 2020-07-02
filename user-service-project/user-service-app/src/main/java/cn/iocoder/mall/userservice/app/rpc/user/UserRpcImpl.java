package cn.iocoder.mall.userservice.app.rpc.user;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.userservice.app.manager.user.UserManager;
import cn.iocoder.mall.userservice.rpc.user.UserRpc;
import cn.iocoder.mall.userservice.rpc.user.vo.UserVO;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@Service(version = "${dubbo.provider.UserRpc.version}", validation = "true")
public class UserRpcImpl implements UserRpc {

    @Autowired
    private UserManager userManager;

    @Override
    public CommonResult<UserVO> getUser(Integer id) {
        return success(userManager.getUser(id));
    }

}
