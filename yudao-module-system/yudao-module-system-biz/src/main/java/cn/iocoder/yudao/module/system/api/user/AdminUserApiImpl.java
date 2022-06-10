package cn.iocoder.yudao.module.system.api.user;

import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;
import cn.iocoder.yudao.module.system.convert.user.UserConvert;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static cn.iocoder.yudao.module.system.enums.ApiConstants.VERSION;

/**
 * Admin 用户 API 实现类
 *
 * @author 芋道源码
 */
@RestController // 提供 RESTful API 接口，给 Feign 调用
@DubboService(version = VERSION) // 提供 Dubbo RPC 接口，给 Dubbo Consumer 调用
@Validated
public class AdminUserApiImpl implements AdminUserApi {

    @Resource
    private AdminUserService userService;

    @Override
    public AdminUserRespDTO getUser(Long id) {
        AdminUserDO user = userService.getUser(id);
        return UserConvert.INSTANCE.convert4(user);
    }

    @Override
    public List<AdminUserRespDTO> getUsers(Collection<Long> ids) {
        List<AdminUserDO> users = userService.getUsers(ids);
        return UserConvert.INSTANCE.convertList4(users);
    }

    @Override
    public List<AdminUserRespDTO> getUsersByDeptIds(Collection<Long> deptIds) {
        List<AdminUserDO> users = userService.getUsersByDeptIds(deptIds);
        return UserConvert.INSTANCE.convertList4(users);
    }

    @Override
    public List<AdminUserRespDTO> getUsersByPostIds(Collection<Long> postIds) {
        List<AdminUserDO> users = userService.getUsersByPostIds(postIds);
        return UserConvert.INSTANCE.convertList4(users);
    }

    @Override
    public void validUsers(Set<Long> ids) {
        userService.validUsers(ids);
    }

}
