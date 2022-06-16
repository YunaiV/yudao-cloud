package cn.iocoder.mall.userservice.service.user;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.exception.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.util.DigestUtils;
import cn.iocoder.common.framework.util.StringUtils;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.userservice.convert.user.UserConvert;
import cn.iocoder.mall.userservice.dal.mysql.dataobject.user.UserDO;
import cn.iocoder.mall.userservice.dal.mysql.mapper.user.UserMapper;
import cn.iocoder.mall.userservice.service.user.bo.UserBO;
import cn.iocoder.mall.userservice.service.user.bo.UserCreateBO;
import cn.iocoder.mall.userservice.service.user.bo.UserPageBO;
import cn.iocoder.mall.userservice.service.user.bo.UserUpdateBO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static cn.iocoder.mall.userservice.enums.UserErrorCodeConstants.*;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public UserBO getUser(Integer id) {
        UserDO userDO = userMapper.selectById(id);
        return UserConvert.INSTANCE.convert(userDO);
    }

    /**
     * 获得用户列表
     *
     * @param userIds 用户编号列表
     * @return 用户列表
     */
    public List<UserBO> listUsers(List<Integer> userIds) {
        List<UserDO> userDOs = userMapper.selectBatchIds(userIds);
        return UserConvert.INSTANCE.convertList(userDOs);
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

    public void updateUser(UserUpdateBO updateBO) {
        // 校验用户存在
        UserDO userDO = userMapper.selectById(updateBO.getId());
        if (userDO == null) {
            throw ServiceExceptionUtil.exception(USER_NOT_EXISTS);
        }
        // 校验手机唯一
        if (StringUtils.hasText(updateBO.getMobile())) {
            UserDO mobileAdmin = userMapper.selectByMobile(updateBO.getMobile());
            if (mobileAdmin != null && !mobileAdmin.getId().equals(updateBO.getId())) {
                throw ServiceExceptionUtil.exception(USER_MOBILE_EXISTS);
            }
        }
        // 如果有更新状态，则校验是否已经是该状态
        if (updateBO.getStatus() != null && updateBO.getStatus().equals(userDO.getStatus())) {
            throw ServiceExceptionUtil.exception(USER_STATUS_EQUALS);
        }
        // 更新到数据库
        UserDO updateUser = UserConvert.INSTANCE.convert(updateBO);
        // 如果更新密码，需要特殊加密
        if (StringUtils.hasText(updateBO.getPassword())) {
            String passwordSalt = genPasswordSalt();
            String password = encodePassword(updateBO.getPassword(), passwordSalt);
            updateUser.setPassword(password).setPasswordSalt(passwordSalt);
        }
        userMapper.updateById(updateUser);
    }

    /**
     * 获得用户分页
     *
     * @param pageBO 用户分页查询
     * @return 用户分页结果
     */
    public PageResult<UserBO> pageUser(UserPageBO pageBO) {
        IPage<UserDO> userDOPage = userMapper.selectPage(pageBO);
        return UserConvert.INSTANCE.convertPage(userDOPage);
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
