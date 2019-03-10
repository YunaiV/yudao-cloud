package cn.iocoder.mall.user.service;

import cn.iocoder.common.framework.constant.SysErrorCodeEnum;
import cn.iocoder.common.framework.dataobject.BaseDO;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.util.ValidationUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.user.convert.UserConvert;
import cn.iocoder.mall.user.dao.UserMapper;
import cn.iocoder.mall.user.dao.UserRegisterMapper;
import cn.iocoder.mall.user.dataobject.UserDO;
import cn.iocoder.mall.user.dataobject.UserRegisterDO;
import cn.iocoder.mall.user.service.api.UserService;
import cn.iocoder.mall.user.service.api.bo.UserBO;
import cn.iocoder.mall.user.service.api.bo.UserPageBO;
import cn.iocoder.mall.user.service.api.constant.UserConstants;
import cn.iocoder.mall.user.service.api.constant.UserErrorCodeEnum;
import cn.iocoder.mall.user.service.api.dto.UserPageDTO;
import cn.iocoder.mall.user.service.api.dto.UserUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * UserService ，实现和用户信息相关的逻辑
 */
@org.springframework.stereotype.Service
@com.alibaba.dubbo.config.annotation.Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRegisterMapper userRegisterMapper;
    @Autowired
    private OAuth2ServiceImpl oAuth2Service;

    public UserDO getUser(String mobile) {
        return userMapper.selectByMobile(mobile);
    }

    @Transactional
    public CommonResult<UserDO> createUser(String mobile) {
        if (!ValidationUtil.isMobile(mobile)) {
            return CommonResult.error(SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getCode(), "手机格式不正确"); // TODO 有点搓
        }
        // 校验用户是否已经存在
        if (getUser(mobile) != null) {
            return ServiceExceptionUtil.error(UserErrorCodeEnum.USER_MOBILE_ALREADY_REGISTERED.getCode());
        }
        // 创建用户
        UserDO userDO = new UserDO().setMobile(mobile).setStatus(UserConstants.STATUS_ENABLE);
        userDO.setCreateTime(new Date()).setDeleted(BaseDO.DELETED_NO);
        userMapper.insert(userDO);
        // 插入注册信息
        createUserRegister(userDO);
        // 转换返回
        return CommonResult.success(userDO);
    }

    private void createUserRegister(UserDO userDO) {
        UserRegisterDO userRegisterDO = new UserRegisterDO().setId(userDO.getId())
                .setCreateTime(new Date());
        userRegisterMapper.insert(userRegisterDO);
    }

    @Override
    public CommonResult<UserPageBO> getUserPage(UserPageDTO userPageDTO) {
        UserPageBO userPageBO = new UserPageBO();
        // 查询分页数据
        int offset = userPageDTO.getPageNo() * userPageDTO.getPageSize();
        userPageBO.setUsers(UserConvert.INSTANCE.convert(userMapper.selectListByNicknameLike(userPageDTO.getNickname(),
                offset, userPageDTO.getPageSize())));
        // 查询分页总数
        userPageBO.setCount(userMapper.selectCountByNicknameLike(userPageDTO.getNickname()));
        return CommonResult.success(userPageBO);
    }

    @Override
    public CommonResult<UserBO> getUser(Integer userId) {
        return CommonResult.success(UserConvert.INSTANCE.convert(userMapper.selectById(userId)));
    }

    @Override
    public CommonResult<Boolean> updateUser(UserUpdateDTO userUpdateDTO) {
        // 校验用户存在
        if (userMapper.selectById(userUpdateDTO.getId()) == null) {
            return ServiceExceptionUtil.error(UserErrorCodeEnum.USER_NOT_EXISTS.getCode());
        }
        // 更新用户
        UserDO updateUser = UserConvert.INSTANCE.convert(userUpdateDTO);
        userMapper.update(updateUser);
        // 返回成功
        return CommonResult.success(true);
    }

    @Override
    public CommonResult<Boolean> updateUserStatus(Integer userId, Integer status) {
        // 校验参数
        if (!isValidStatus(status)) {
            return CommonResult.error(SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getCode(), "变更状态必须是开启（1）或关闭（2）"); // TODO 有点搓
        }
        // 校验用户存在
        UserDO user = userMapper.selectById(userId);
        if (user == null) {
            return ServiceExceptionUtil.error(UserErrorCodeEnum.USER_NOT_EXISTS.getCode());
        }
        // 如果状态相同，则返回错误
        if (status.equals(user.getStatus())) {
            return ServiceExceptionUtil.error(UserErrorCodeEnum.USER_STATUS_EQUALS.getCode());
        }
        // 更新管理员状态
        UserDO updateUser = new UserDO().setId(userId).setStatus(status);
        userMapper.update(updateUser);
        // 如果是关闭管理员，则标记 token 失效。否则，管理员还可以继续蹦跶
        if (UserConstants.STATUS_DISABLE.equals(status)) {
            oAuth2Service.removeToken(userId);
        }
        // 返回成功
        return CommonResult.success(true);
    }

    @Override
    public CommonResult<Boolean> updateUserMobile(Integer userId, String mobile) {
        if (!ValidationUtil.isMobile(mobile)) {
            return CommonResult.error(SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getCode(), "手机格式不正确"); // TODO 有点搓
        }
        // 校验用户存在
        UserDO user = userMapper.selectById(userId);
        if (user == null) {
            return ServiceExceptionUtil.error(UserErrorCodeEnum.USER_NOT_EXISTS.getCode());
        }
        // 如果状态相同，则返回错误
        if (mobile.equals(user.getMobile())) {
            return ServiceExceptionUtil.error(UserErrorCodeEnum.USER_MOBILE_EQUALS.getCode());
        }
        // 更新管理员状态
        UserDO updateUser = new UserDO().setId(userId).setMobile(mobile);
        userMapper.update(updateUser);
        // 返回成功
        return CommonResult.success(true);
    }

    private boolean isValidStatus(Integer status) {
        return UserConstants.STATUS_ENABLE.equals(status)
                || UserConstants.STATUS_DISABLE.equals(status);
    }

}