package cn.iocoder.mall.systemservice.service.admin;

import cn.iocoder.common.framework.util.DigestUtils;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.mall.systemservice.convert.admin.AdminConvert;
import cn.iocoder.mall.systemservice.dal.mysql.dataobject.admin.AdminDO;
import cn.iocoder.mall.systemservice.dal.mysql.mapper.admin.AdminMapper;
import cn.iocoder.mall.systemservice.enums.SystemErrorCodeEnum;
import cn.iocoder.mall.systemservice.enums.admin.AdminStatusEnum;
import cn.iocoder.mall.systemservice.service.admin.bo.AdminBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;

    public AdminBO verifyPassword(String username, String password, String ip) {
        AdminDO adminDO = adminMapper.selectByUsername(username);
        if (adminDO == null) {
            throw ServiceExceptionUtil.exception(SystemErrorCodeEnum.ADMIN_NOT_FOUND);
        }
        // 校验密码是否正确
        String encodedPassword = DigestUtils.bcrypt(password, adminDO.getPasswordSalt());
        if (!encodedPassword.equals(adminDO.getPassword())) {
            // TODO 需要补充密码错误上限
            throw ServiceExceptionUtil.exception(SystemErrorCodeEnum.ADMIN_PASSWORD_ERROR);
        }
        // 账号被禁用
        if (!AdminStatusEnum.ACTIVE.getStatus().equals(adminDO.getStatus())) {
            throw ServiceExceptionUtil.exception(SystemErrorCodeEnum.ADMIN_IS_DISABLE);
        }
        // 返回
        return AdminConvert.INSTANCE.convert(adminDO);
    }

//    public PageResult<AdminBO> getAdminPage(AdminPageDTO pageDTO) {
//        return AdminConvert.INSTANCE.convertPage(adminMapper.selectPage(pageDTO));
//    }

}
