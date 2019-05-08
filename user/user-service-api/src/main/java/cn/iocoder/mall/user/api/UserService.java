package cn.iocoder.mall.user.api;

import cn.iocoder.common.framework.constant.CommonStatusEnum;
import cn.iocoder.common.framework.validator.InEnum;
import cn.iocoder.mall.user.api.bo.UserBO;
import cn.iocoder.mall.user.api.bo.UserPageBO;
import cn.iocoder.mall.user.api.dto.UserPageDTO;
import cn.iocoder.mall.user.api.dto.UserUpdateDTO;

public interface UserService {

    UserPageBO getUserPage(UserPageDTO userPageDTO);

    UserBO getUser(Integer userId);

    /**
     * 更新用户基本信息
     *
     * @param userUpdateDTO 更新 DTO
     * @return 更新结果
     */
    Boolean updateUser(UserUpdateDTO userUpdateDTO);

    /**
     * 更新用户状态
     *
     * @param userId 用户编号
     * @param status 状态
     * @return 更新结果
     */
    Boolean updateUserStatus(Integer userId,
                             @InEnum(value = CommonStatusEnum.class, message = "修改状态必须是 {value}") Integer status);

    /**
     * 更新用户手机号
     *
     * @param userId 用户编号
     * @param mobile 手机号
     * @return 更新结果
     */
    Boolean updateUserMobile(Integer userId, String mobile);

}
