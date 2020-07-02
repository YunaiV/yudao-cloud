package cn.iocoder.mall.userservice.service.user;

import cn.iocoder.mall.userservice.convert.user.UserConvert;
import cn.iocoder.mall.userservice.dal.mysql.dataobject.user.UserDO;
import cn.iocoder.mall.userservice.dal.mysql.mapper.user.UserMapper;
import cn.iocoder.mall.userservice.service.user.bo.UserBO;
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
