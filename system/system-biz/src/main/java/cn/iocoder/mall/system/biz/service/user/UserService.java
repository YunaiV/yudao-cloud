package cn.iocoder.mall.system.biz.service.user;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.system.biz.bo.user.UserBO;
import cn.iocoder.mall.system.biz.dto.user.UserPageDTO;
import cn.iocoder.mall.system.biz.dto.user.UserUpdateDTO;
import cn.iocoder.mall.system.biz.dto.user.UserUpdateStatusDTO;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * 用户 Service 接口
 */
@Validated
public interface UserService {

    /**
     * 根据条件分页获取用户列表
     * @param userPageDTO
     * @return
     */
    PageResult<UserBO> getUserPage(UserPageDTO userPageDTO);

    /**
     * 更新用户信息
     * @param userUpdateDTO
     * @return
     */
    Boolean updateUserInfo(@Valid UserUpdateDTO userUpdateDTO);

    /**
     * 更新用户状态
     * @param userUpdateStatusDTO
     * @return
     */
    Boolean updateUserStatus(@Valid UserUpdateStatusDTO userUpdateStatusDTO);

}
