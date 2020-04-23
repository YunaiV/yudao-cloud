package cn.iocoder.mall.system.rpc.api.user;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.system.rpc.response.user.UserResponse;

/**
 * User RPC 接口
 */
public interface UserRPC {

//    CommonResult<UserResponse> getUser(Integer id);

    CommonResult<UserResponse> getUserByAccountId(Integer accountId);

}
