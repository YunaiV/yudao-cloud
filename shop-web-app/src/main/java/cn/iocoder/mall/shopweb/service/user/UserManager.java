package cn.iocoder.mall.shopweb.service.user;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.shopweb.controller.user.vo.user.UserRespVO;
import cn.iocoder.mall.shopweb.convert.user.UserConvert;
import cn.iocoder.mall.userservice.rpc.user.UserFeign;
import cn.iocoder.mall.userservice.rpc.user.dto.UserRespDTO;
import cn.iocoder.mall.userservice.rpc.user.dto.UserUpdateReqDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManager {

    @Autowired
    private UserFeign userFeign;
    public UserRespVO getUser(Integer id) {
        CommonResult<UserRespDTO> userResult = userFeign.getUser(id);
        userResult.checkError();
        return UserConvert.INSTANCE.convert(userResult.getData());
    }

    public void updateUserAvatar(Integer userId, String avatar) {
        CommonResult<Boolean> updateUserResult = userFeign.updateUser(new UserUpdateReqDTO().setId(userId).setAvatar(avatar));
        updateUserResult.checkError();
    }

    public void updateUserNickname(Integer userId, String nickname) {
        CommonResult<Boolean> updateUserResult = userFeign.updateUser(new UserUpdateReqDTO().setId(userId).setNickname(nickname));
        updateUserResult.checkError();
    }

}
