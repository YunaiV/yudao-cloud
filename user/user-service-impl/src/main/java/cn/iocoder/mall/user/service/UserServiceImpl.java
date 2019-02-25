package cn.iocoder.mall.user.service;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.mall.user.dao.UserMapper;
import cn.iocoder.mall.user.dao.UserRegisterMapper;
import cn.iocoder.mall.user.dataobject.UserDO;
import cn.iocoder.mall.user.dataobject.UserRegisterDO;
import cn.iocoder.mall.user.service.api.UserService;
import cn.iocoder.mall.user.service.api.constant.UserErrorCodeEnum;
import cn.iocoder.mall.user.service.api.dto.UserDTO;
import com.alibaba.dubbo.config.annotation.Service;
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
    private MobileCodeServiceImpl mobileCodeService;

    public UserDO getUser(String mobile) {
        return userMapper.selectByMobile(mobile);
    }

    @Override
    @Transactional
    public UserDTO createUser(String mobile, String code) {
        // TODO 芋艿，校验手机格式
        // 校验手机号的最后一个手机验证码是否有效
        mobileCodeService.validLastMobileCode(mobile, code);
        // 校验用户是否已经存在
        if (getUser(mobile) != null) {
            throw ServiceExceptionUtil.exception(UserErrorCodeEnum.USER_MOBILE_ALREADY_REGISTERED.getCode());
        }
        // 创建用户
        UserDO userDO = new UserDO().setMobile(mobile).setCreateTime(new Date());
        userMapper.insert(userDO);
        // 插入注册信息
        createUserRegister(userDO);
        // 转换返回
        return new UserDTO().setUid(userDO.getId());
    }

    private void createUserRegister(UserDO userDO) {
        UserRegisterDO userRegisterDO = new UserRegisterDO().setId(userDO.getId())
                .setCreateTime(new Date());
        userRegisterMapper.insert(userRegisterDO);
    }

}