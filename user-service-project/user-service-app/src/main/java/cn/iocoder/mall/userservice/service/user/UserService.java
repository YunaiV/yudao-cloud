package cn.iocoder.mall.userservice.service.user;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.util.DigestUtils;
import cn.iocoder.common.framework.util.StringUtils;
import cn.iocoder.mall.userservice.convert.user.UserConvert;
import cn.iocoder.mall.userservice.dal.mysql.dataobject.user.UserDO;
import cn.iocoder.mall.userservice.dal.mysql.mapper.user.UserMapper;
import cn.iocoder.mall.userservice.service.user.bo.UserBO;
import cn.iocoder.mall.userservice.service.user.bo.UserCreateBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public UserBO getUser(Integer id) {
        UserDO userDO = userMapper.selectById(id);
        return UserConvert.INSTANCE.convert(userDO);
    }

    public UserBO getUser(String mobile) {
        UserDO userDO = userMapper.selectByMobile(mobile);
        return UserConvert.INSTANCE.convert(userDO);
    }

    public UserBO createUser(UserCreateBO createBO) {
        UserDO userDO = UserConvert.INSTANCE.convert(createBO)
                .setStatus(CommonStatusEnum.ENABLE.getValue());
        // 加密密码
        String passwordSalt = genPasswordSalt();
        String password = createBO.getPassword();
        if (!StringUtils.hasText(password)) {
            password = genPassword();
        }
        password = encodePassword(password, passwordSalt);
        userDO.setPassword(password).setPasswordSalt(passwordSalt);
        // 保存用户
        userMapper.insert(userDO);
        return UserConvert.INSTANCE.convert(userDO);
    }

    private String genPasswordSalt() {
        return DigestUtils.genBcryptSalt();
    }

    private String genPassword() {
        return StringUtils.uuid(true);
    }

    private String encodePassword(String password, String salt) {
        return DigestUtils.bcrypt(password, salt);
    }

}
