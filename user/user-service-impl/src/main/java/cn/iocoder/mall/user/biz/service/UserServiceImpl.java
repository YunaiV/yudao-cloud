package cn.iocoder.mall.user.biz.service;

import cn.iocoder.common.framework.constant.CommonStatusEnum;
import cn.iocoder.common.framework.constant.DeletedStatusEnum;
import cn.iocoder.common.framework.constant.SysErrorCodeEnum;
import cn.iocoder.common.framework.constant.UserTypeEnum;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.util.ValidationUtil;
import cn.iocoder.mall.system.api.OAuth2Service;
import cn.iocoder.mall.system.api.bo.oauth2.OAuth2AccessTokenBO;
import cn.iocoder.mall.system.api.dto.oauth2.OAuth2CreateTokenDTO;
import cn.iocoder.mall.system.api.dto.oauth2.OAuth2RemoveTokenByUserDTO;
import cn.iocoder.mall.user.api.UserService;
import cn.iocoder.mall.user.api.bo.user.UserAuthenticationBO;
import cn.iocoder.mall.user.api.bo.UserBO;
import cn.iocoder.mall.user.api.bo.UserPageBO;
import cn.iocoder.mall.user.api.constant.UserConstants;
import cn.iocoder.mall.user.api.constant.UserErrorCodeEnum;
import cn.iocoder.mall.user.api.dto.UserPageDTO;
import cn.iocoder.mall.user.api.dto.UserUpdateDTO;
import cn.iocoder.mall.user.api.dto.user.UserAuthenticationByMobileCodeDTO;
import cn.iocoder.mall.user.biz.convert.UserConvert;
import cn.iocoder.mall.user.biz.dao.UserMapper;
import cn.iocoder.mall.user.biz.dao.UserRegisterMapper;
import cn.iocoder.mall.user.biz.dataobject.MobileCodeDO;
import cn.iocoder.mall.user.biz.dataobject.UserDO;
import cn.iocoder.mall.user.biz.dataobject.UserRegisterDO;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * UserService ，实现和用户信息相关的逻辑
 */
@Service
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.UserService.version}")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRegisterMapper userRegisterMapper;

    @Reference(validation = "true", version = "${dubbo.consumer.OAuth2Service.version}")
    private OAuth2Service oAuth2Service;

    public UserDO getUser(String mobile) {
        return userMapper.selectByMobile(mobile);
    }

    @Transactional
    public UserDO createUser(String mobile) {
        if (!ValidationUtil.isMobile(mobile)) {
            throw ServiceExceptionUtil.exception(SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getCode(), "手机格式不正确"); // TODO 有点搓
        }
        // 校验用户是否已经存在
        if (getUser(mobile) != null) {
            throw ServiceExceptionUtil.exception(UserErrorCodeEnum.USER_MOBILE_ALREADY_REGISTERED.getCode());
        }
        // 创建用户
        UserDO userDO = new UserDO().setMobile(mobile).setStatus(UserConstants.STATUS_ENABLE);
        userDO.setCreateTime(new Date());
        userDO.setDeleted(DeletedStatusEnum.DELETED_NO.getValue());
        userMapper.insert(userDO);
        // 插入注册信息
        createUserRegister(userDO);
        // 转换返回
        return userDO;
    }

    private void createUserRegister(UserDO userDO) {
        UserRegisterDO userRegisterDO = new UserRegisterDO().setId(userDO.getId())
                .setCreateTime(new Date());
        userRegisterMapper.insert(userRegisterDO);
    }

    @Override
    public UserPageBO getUserPage(UserPageDTO userPageDTO) {
        UserPageBO userPageBO = new UserPageBO();
        // 查询分页数据
        int offset = (userPageDTO.getPageNo() - 1) * userPageDTO.getPageSize();
        userPageBO.setList(UserConvert.INSTANCE.convert(userMapper.selectListByNicknameLike(
                userPageDTO.getNickname(), userPageDTO.getStatus(),
                offset, userPageDTO.getPageSize())));
        // 查询分页总数
        userPageBO.setTotal(userMapper.selectCountByNicknameLike(userPageDTO.getNickname(), userPageDTO.getStatus()));
        return userPageBO;
    }

    @Override
    public UserBO getUser(Integer userId) {
        return UserConvert.INSTANCE.convert(userMapper.selectById(userId));
    }

    @Override
    public Boolean updateUser(UserUpdateDTO userUpdateDTO) {
        // 校验用户存在
        if (userMapper.selectById(userUpdateDTO.getId()) == null) {
            throw ServiceExceptionUtil.exception(UserErrorCodeEnum.USER_NOT_EXISTS.getCode());
        }
        // 更新用户
        UserDO updateUser = UserConvert.INSTANCE.convert(userUpdateDTO);
        userMapper.update(updateUser);
        // 返回成功
        return true;
    }

    @Override
    public Boolean updateUserStatus(Integer userId, Integer status) {
        // 校验用户存在
        UserDO user = userMapper.selectById(userId);
        if (user == null) {
            throw ServiceExceptionUtil.exception(UserErrorCodeEnum.USER_NOT_EXISTS.getCode());
        }
        // 如果状态相同，则返回错误
        if (status.equals(user.getStatus())) {
            throw ServiceExceptionUtil.exception((UserErrorCodeEnum.USER_STATUS_EQUALS.getCode()));
        }
        // 更新管理员状态
        UserDO updateUser = new UserDO().setId(userId).setStatus(status);
        userMapper.update(updateUser);
        // 如果是关闭管理员，则标记 token 失效。否则，管理员还可以继续蹦跶
        if (CommonStatusEnum.DISABLE.getValue().equals(status)) {
            oAuth2Service.removeToken(new OAuth2RemoveTokenByUserDTO().setUserId(userId).setUserType(UserTypeEnum.USER.getValue()));
        }
        // 返回成功
        return true;
    }

    @Override
    public Boolean updateUserMobile(Integer userId, String mobile) {
        if (!ValidationUtil.isMobile(mobile)) {
            throw ServiceExceptionUtil.exception(SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getCode(), "手机格式不正确"); // TODO 有点搓
        }
        // 校验用户存在
        UserDO user = userMapper.selectById(userId);
        if (user == null) {
            throw ServiceExceptionUtil.exception(UserErrorCodeEnum.USER_NOT_EXISTS.getCode());
        }
        // 如果状态相同，则返回错误
        if (mobile.equals(user.getMobile())) {
            throw ServiceExceptionUtil.exception(UserErrorCodeEnum.USER_MOBILE_EQUALS.getCode());
        }
        // 更新管理员状态
        UserDO updateUser = new UserDO().setId(userId).setMobile(mobile);
        userMapper.update(updateUser);
        // 返回成功
        return true;
    }

}
