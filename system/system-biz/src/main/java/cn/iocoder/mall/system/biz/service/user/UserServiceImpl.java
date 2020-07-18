package cn.iocoder.mall.system.biz.service.user;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.system.biz.bo.ouath2.OAuth2AuthenticateBO;
import cn.iocoder.mall.system.biz.bo.user.UserAuthenticateBO;
import cn.iocoder.mall.system.biz.bo.user.UserBO;
import cn.iocoder.mall.system.biz.convert.user.UserConvert;
import cn.iocoder.mall.system.biz.dao.user.UserMapper;
import cn.iocoder.mall.system.biz.dataobject.user.UserDO;
import cn.iocoder.mall.system.biz.dto.oatuh2.OAuth2MobileCodeAuthenticateDTO;
import cn.iocoder.mall.system.biz.dto.user.UserPageDTO;
import cn.iocoder.mall.system.biz.dto.user.UserUpdateDTO;
import cn.iocoder.mall.system.biz.dto.user.UserUpdateStatusDTO;
import cn.iocoder.mall.system.biz.enums.user.UserStatusEnum;
import cn.iocoder.mall.system.biz.service.oauth2.OAuth2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import static cn.iocoder.mall.system.biz.enums.SystemErrorCodeEnum.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据条件分页获取用户列表
     * @param userPageDTO
     * @return
     */
    @Override
    public PageResult<UserBO> getUserPage(UserPageDTO userPageDTO) {
        return  UserConvert.INSTANCE.convertToPage(userMapper.selectUserPage(userPageDTO));
    }

    /**
     * 更新用户信息
     * @param userUpdateDTO
     * @return
     */
    @Override
    public Boolean updateUserInfo(UserUpdateDTO userUpdateDTO) {
        // 查询用户是否存在
        UserDO userDO = userMapper.selectById(userUpdateDTO.getId());
        if (null == userDO) {
            throw ServiceExceptionUtil.exception(USER_NOT_EXISTS);
        }
        // 更新用户信息
        UserDO updateDO = UserConvert.INSTANCE.convertToUserDO(userUpdateDTO);
        userMapper.updateById(updateDO);
        // TODO 伟帆 操作日志
        return true;
    }


    /**
     * 更新用户状态
     * @param userUpdateStatusDTO
     * @return
     */
    @Override
    public Boolean updateUserStatus(UserUpdateStatusDTO userUpdateStatusDTO) {
        // 查询用户是否存在
        UserDO userDO = userMapper.selectById(userUpdateStatusDTO.getId());
        if (null == userDO) {
            throw ServiceExceptionUtil.exception(USER_NOT_EXISTS);
        }
        // 判断更新状态是否存在
        if (null != userUpdateStatusDTO.getStatus() &&
                Arrays.stream(UserStatusEnum.ARRAYS).noneMatch(status -> status == userUpdateStatusDTO.getStatus())) {
            throw ServiceExceptionUtil.exception(USER_STATUS_NOT_EXISTS);
        }
        // 如果状态相同，则返回错误
        if (null != userUpdateStatusDTO.getStatus() && userUpdateStatusDTO.getStatus().equals(userDO.getStatus())) {
            throw ServiceExceptionUtil.exception(USER_STATUS_EQUALS);
        }
        // 更新用户信息
        UserDO updateStatusDO = UserConvert.INSTANCE.convertToUserDO(userUpdateStatusDTO);
        userMapper.updateById(updateStatusDO);
        // TODO 伟帆 操作日志
        return true;
    }

}
