package cn.iocoder.mall.userservice.manager.user;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.enums.UserTypeEnum;
import cn.iocoder.common.framework.util.StringUtils;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.systemservice.rpc.oauth.OAuth2Rpc;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2RemoveTokenByUserReqDTO;
import cn.iocoder.mall.userservice.convert.user.UserConvert;
import cn.iocoder.mall.userservice.rpc.user.dto.UserCreateReqDTO;
import cn.iocoder.mall.userservice.rpc.user.dto.UserPageReqDTO;
import cn.iocoder.mall.userservice.rpc.user.dto.UserRespDTO;
import cn.iocoder.mall.userservice.rpc.user.dto.UserUpdateReqDTO;
import cn.iocoder.mall.userservice.service.user.UserService;
import cn.iocoder.mall.userservice.service.user.bo.UserBO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManager {

    @Autowired
    private UserService userService;

    @DubboReference(version = "${dubbo.consumer.OAuth2Rpc.version}")
    private OAuth2Rpc oauth2Rpc;

    public UserRespDTO createUserIfAbsent(UserCreateReqDTO createDTO) {
        // 用户已经存在
        UserBO userBO = userService.getUser(createDTO.getMobile());
        if (userBO != null) {
            return UserConvert.INSTANCE.convert(userBO);
        }
        // 用户不存在，则进行创建
        userBO = userService.createUser(UserConvert.INSTANCE.convert(createDTO));
        return UserConvert.INSTANCE.convert(userBO);
    }

    /**
     * 更新用户
     *
     * @param updateDTO 更新用户 DTO
     */
    public void updateUser(UserUpdateReqDTO updateDTO) {
        // 更新管理员信息
        userService.updateUser(UserConvert.INSTANCE.convert(updateDTO));
        // 如果修改密码，或者禁用管理员
        if (StringUtils.hasText(updateDTO.getPassword())
                || CommonStatusEnum.DISABLE.getValue().equals(updateDTO.getStatus())) {
            oauth2Rpc.removeToken(new OAuth2RemoveTokenByUserReqDTO().setUserId(updateDTO.getId())
                .setUserType(UserTypeEnum.ADMIN.getValue()));
        }
    }

    /**
     * 获得用户
     *
     * @param userId 用户编号
     * @return 用户
     */
    public UserRespDTO getUser(Integer userId) {
        UserBO userBO = userService.getUser(userId);
        return UserConvert.INSTANCE.convert(userBO);
    }

    /**
     * 获得用户列表
     *
     * @param userIds 用户编号列表
     * @return 用户列表
     */
    public List<UserRespDTO> listUsers(List<Integer> userIds) {
        List<UserBO> userBOs = userService.listUsers(userIds);
        return UserConvert.INSTANCE.convertList02(userBOs);
    }

    /**
     * 获得用户分页
     *
     * @param pageDTO 用户分页查询
     * @return 用户分页结果
     */
    public PageResult<UserRespDTO> pageUser(UserPageReqDTO pageDTO) {
        PageResult<UserBO> pageResultBO = userService.pageUser(UserConvert.INSTANCE.convert(pageDTO));
        return UserConvert.INSTANCE.convertPage(pageResultBO);
    }

}
