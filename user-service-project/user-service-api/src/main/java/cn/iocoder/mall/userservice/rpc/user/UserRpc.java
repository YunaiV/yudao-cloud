package cn.iocoder.mall.userservice.rpc.user;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.userservice.rpc.user.dto.UserCreateDTO;
import cn.iocoder.mall.userservice.rpc.user.vo.UserVO;

public interface UserRpc {

    CommonResult<UserVO> getUser(Integer id);

    CommonResult<UserVO> createUserIfAbsent(UserCreateDTO createDTO);

}
