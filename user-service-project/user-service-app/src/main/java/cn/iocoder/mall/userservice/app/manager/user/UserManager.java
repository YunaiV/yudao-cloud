package cn.iocoder.mall.userservice.app.manager.user;

import cn.iocoder.mall.userservice.app.service.user.bo.UserBO;
import cn.iocoder.mall.userservice.app.service.user.UserService;
import cn.iocoder.mall.userservice.app.convert.user.UserConvert;
import cn.iocoder.mall.userservice.rpc.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManager {

    @Autowired
    private UserService userService;

    public UserVO getUser(Integer id) {
        UserBO userBO = userService.getUser(id);
        return UserConvert.INSTANCE.convert(userBO);
    }

}
