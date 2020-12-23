package cn.iocoder.mall.userservice.rpc.user;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.userservice.manager.user.UserManager;
import cn.iocoder.mall.userservice.rpc.user.dto.UserCreateReqDTO;
import cn.iocoder.mall.userservice.rpc.user.dto.UserPageReqDTO;
import cn.iocoder.mall.userservice.rpc.user.dto.UserRespDTO;
import cn.iocoder.mall.userservice.rpc.user.dto.UserUpdateReqDTO;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@Service(version = "${dubbo.provider.UserRpc.version}", validation = "false")
public class UserRpcImpl implements UserRpc {

    @Autowired
    private UserManager userManager;

    @Override
    public CommonResult<UserRespDTO> getUser(Integer id) {
        return success(userManager.getUser(id));
    }

    @Override
    public CommonResult<List<UserRespDTO>> listUsers(List<Integer> userIds) {
        return success(userManager.listUsers(userIds));
    }

    @Override
    public CommonResult<UserRespDTO> createUserIfAbsent(UserCreateReqDTO createDTO) {
        return success(userManager.createUserIfAbsent(createDTO));
    }

    @Override
    public CommonResult<Boolean> updateUser(UserUpdateReqDTO updateDTO) {
        userManager.updateUser(updateDTO);
        return success(true);
    }

    @Override
    public CommonResult<PageResult<UserRespDTO>> pageUser(UserPageReqDTO pageDTO) {
        return success(userManager.pageUser(pageDTO));
    }

}
