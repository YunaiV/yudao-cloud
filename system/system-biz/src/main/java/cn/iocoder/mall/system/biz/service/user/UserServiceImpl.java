package cn.iocoder.mall.system.biz.service.user;

import cn.iocoder.mall.mybatis.enums.DeletedStatusEnum;
import cn.iocoder.mall.system.biz.bo.ouath2.OAuth2AccessTokenBO;
import cn.iocoder.mall.system.biz.bo.user.UserAuthenticateBO;
import cn.iocoder.mall.system.biz.bo.user.UserBO;
import cn.iocoder.mall.system.biz.convert.user.UserConvert;
import cn.iocoder.mall.system.biz.dao.user.UserMapper;
import cn.iocoder.mall.system.biz.dataobject.user.UserDO;
import cn.iocoder.mall.system.biz.dto.oatuh2.OAuth2MobileCodeAuthenticateDTO;
import cn.iocoder.mall.system.biz.service.oauth2.OAuth2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OAuth2Service oAuth2Service;

    @Override
    @Transactional
    public UserAuthenticateBO authenticate(OAuth2MobileCodeAuthenticateDTO authenticateDTO) {
        // 执行认证
        OAuth2AccessTokenBO accessTokenBO = oAuth2Service.authenticate(authenticateDTO);
        // 获得用户
        UserDO userDO = userMapper.selectById(accessTokenBO.getAccountId());
        if (userDO == null) {
            userDO = this.creatUser(accessTokenBO.getAccountId());
        }
        UserBO userBO = UserConvert.INSTANCE.convert(userDO);
        // 拼装返回
        return UserConvert.INSTANCE.convert(userBO, accessTokenBO);
    }

    @Override
    public UserBO getUserByAccountId(Integer accountId) {
        UserDO userDO = userMapper.selectById(accountId);
        return UserConvert.INSTANCE.convert(userDO);
    }

    private UserDO creatUser(Integer accountId) {
        UserDO user = new UserDO();
        user.setAccountId(accountId);
        user.setDeleted(DeletedStatusEnum.DELETED_NO.getValue());
        userMapper.insert(user);
        return user;
    }

}
