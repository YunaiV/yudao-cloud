package cn.iocoder.mall.user.api;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.user.api.bo.UserBO;
import cn.iocoder.mall.user.api.bo.UserPageBO;
import cn.iocoder.mall.user.api.dto.UserPageDTO;
import cn.iocoder.mall.user.api.dto.UserUpdateDTO;

public interface UserService {

    CommonResult<UserPageBO> getUserPage(UserPageDTO userPageDTO);

    CommonResult<UserBO> getUser(Integer userId);

    /**
     * 更新用户基本信息
     *
     * @param userUpdateDTO 更新 DTO
     * @return 更新结果
     */
    CommonResult<Boolean> updateUser(UserUpdateDTO userUpdateDTO);

    /**
     * 更新用户状态
     *
     * @param userId 用户编号
     * @param status 状态
     * @return 更新结果
     */
    CommonResult<Boolean> updateUserStatus(Integer userId, Integer status);

    /**
     * 更新用户手机号
     *
     * @param userId 用户编号
     * @param mobile 手机号
     * @return 更新结果
     */
    CommonResult<Boolean> updateUserMobile(Integer userId, String mobile);

}