package cn.iocoder.mall.admin.service;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.api.AdminService;
import cn.iocoder.mall.admin.api.constant.AdminErrorCodeEnum;
import cn.iocoder.mall.admin.dataobject.AdminDO;
import cn.iocoder.mall.admin.dao.AdminMapper;
import cn.iocoder.mall.admin.dao.AdminRoleMapper;
import cn.iocoder.mall.admin.dataobject.AdminRoleDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
@com.alibaba.dubbo.config.annotation.Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private AdminRoleMapper adminRoleMapper;

    public CommonResult<AdminDO> validAdmin(String username, String password) {
        AdminDO admin = adminMapper.selectByUsername(username);
        // 账号不存在
        if (admin == null) {
            return ServiceExceptionUtil.error(AdminErrorCodeEnum.ADMIN_USERNAME_NOT_REGISTERED.getCode());
        }
        // 密码不正确
        if (DigestUtils.md5DigestAsHex(password.getBytes()).equals(admin.getPassword())) {
            return ServiceExceptionUtil.error(AdminErrorCodeEnum.ADMIN_PASSWORD_ERROR.getCode());
        }
        // 账号被禁用
        if (AdminDO.STATUS_DISABLE.equals(admin.getStatus())) {
            return ServiceExceptionUtil.error(AdminErrorCodeEnum.ADMIN_IS_DISABLE.getCode());
        }
        // 校验成功，返回管理员。并且，去掉一些非关键字段，考虑安全性。
        admin.setPassword(null);
        admin.setStatus(null);
        return CommonResult.success(admin);
    }

    public List<AdminRoleDO> getAdminRoles(Integer adminId) {
        return adminRoleMapper.selectByAdminId(adminId);
    }

}
