package cn.iocoder.mall.userservice.app.service.user;

import cn.iocoder.mall.userservice.app.dal.mysql.dataobject.user.UserDO;
import cn.iocoder.mall.userservice.app.dal.mysql.mapper.user.UserMapper;
import cn.iocoder.mall.userservice.app.service.user.bo.UserBO;
import cn.iocoder.mall.userservice.biz.convert.user.UserConvert;
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

}
