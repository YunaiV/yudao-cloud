package cn.iocoder.mall.userservice.rpc.user;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.userservice.rpc.user.dto.UserCreateReqDTO;
import cn.iocoder.mall.userservice.rpc.user.dto.UserPageReqDTO;
import cn.iocoder.mall.userservice.rpc.user.dto.UserRespDTO;
import cn.iocoder.mall.userservice.rpc.user.dto.UserUpdateReqDTO;

import java.util.List;

public interface UserRpc {

    /**
     * 获得用户
     *
     * @param userId 用户编号
     * @return 用户
     */
    CommonResult<UserRespDTO> getUser(Integer userId);

    /**
     * 基于手机号创建用户。
     * 如果用户已经存在，则直接进行返回
     *
     * @param createDTO 创建用户 DTO
     * @return 用户信息
     */
    CommonResult<UserRespDTO> createUserIfAbsent(UserCreateReqDTO createDTO);

    /**
     * 更新用户
     *
     * @param updateDTO 更新用户 DTO
     */
    CommonResult<Boolean> updateUser(UserUpdateReqDTO updateDTO);

    /**
     * 获得用户列表
     *
     * @param userIds 用户编号列表
     * @return 用户列表
     */
    CommonResult<List<UserRespDTO>> listUsers(List<Integer> userIds);

    /**
     * 获得用户分页
     *
     * @param pageDTO 用户分页查询
     * @return 用户分页结果
     */
    CommonResult<PageResult<UserRespDTO>> pageUser(UserPageReqDTO pageDTO);

}
