package cn.iocoder.mall.systemservice.service.admin;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.util.DigestUtils;
import cn.iocoder.common.framework.exception.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.util.StringUtils;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.systemservice.convert.admin.AdminConvert;
import cn.iocoder.mall.systemservice.dal.mysql.dataobject.admin.AdminDO;
import cn.iocoder.mall.systemservice.dal.mysql.mapper.admin.AdminMapper;
import cn.iocoder.mall.systemservice.enums.admin.AdminStatusEnum;
import cn.iocoder.mall.systemservice.enums.admin.AdminUsernameEnum;
import cn.iocoder.mall.systemservice.service.admin.bo.AdminBO;
import cn.iocoder.mall.systemservice.service.admin.bo.AdminCreateBO;
import cn.iocoder.mall.systemservice.service.admin.bo.AdminPageBO;
import cn.iocoder.mall.systemservice.service.admin.bo.AdminUpdateBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static cn.iocoder.mall.systemservice.enums.SystemErrorCodeConstants.*;

@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;

    /**
     * 校验登陆的账号密码是否正确
     *
     * @param username 账号
     * @param password 密码
     * @param ip 登陆 IP
     * @return 管理员信息
     */
    public AdminBO verifyPassword(String username, String password, String ip) {
        AdminDO adminDO = adminMapper.selectByUsername(username);
        if (adminDO == null) {
            throw ServiceExceptionUtil.exception(ADMIN_USERNAME_NOT_EXISTS);
        }
        // 校验密码是否正确
        String encodedPassword = DigestUtils.bcrypt(password, adminDO.getPasswordSalt());
        if (!encodedPassword.equals(adminDO.getPassword())) {
            // TODO 需要补充密码错误上限
            throw ServiceExceptionUtil.exception(ADMIN_PASSWORD_ERROR);
        }
        // 账号被禁用
        if (!AdminStatusEnum.ACTIVE.getStatus().equals(adminDO.getStatus())) {
            throw ServiceExceptionUtil.exception(ADMIN_IS_DISABLE);
        }
        // 返回
        return AdminConvert.INSTANCE.convert(adminDO);
    }

    public PageResult<AdminBO> pageAdmin(AdminPageBO adminPageBO) {
        return AdminConvert.INSTANCE.convertPage(adminMapper.selectPage(adminPageBO));
    }

    public AdminBO createAdmin(AdminCreateBO createBO) {
        // 校验账号唯一
        if (adminMapper.selectByUsername(createBO.getUsername()) != null) {
            throw ServiceExceptionUtil.exception(ADMIN_USERNAME_EXISTS);
        }
        // 加密密码
        String passwordSalt = genPasswordSalt();
        String password = encodePassword(createBO.getPassword(), passwordSalt);
        // 保存到数据库
        AdminDO admin = AdminConvert.INSTANCE.convert(createBO)
                .setPassword(password).setPasswordSalt(passwordSalt)
                .setStatus(CommonStatusEnum.ENABLE.getValue());
        adminMapper.insert(admin);
        // 返回成功
        return AdminConvert.INSTANCE.convert(admin);
    }

    private String genPasswordSalt() {
        return DigestUtils.genBcryptSalt();
    }

    private String encodePassword(String password, String salt) {
        return DigestUtils.bcrypt(password, salt);
    }

    public void updateAdmin(AdminUpdateBO updateBO) {
        // 校验账号存在
        AdminDO adminDO = adminMapper.selectById(updateBO.getId());
        if (adminDO == null) {
            throw ServiceExceptionUtil.exception(ADMIN_NOT_FOUND);
        }
        // 校验是否为特殊账号，不允许编辑
        if (AdminUsernameEnum.ADMIN.getUsername().equals(adminDO.getUsername())
                || AdminUsernameEnum.DEMO.getUsername().equals(adminDO.getUsername())) {
            throw ServiceExceptionUtil.exception(ADMIN_ADMIN_CAN_NOT_UPDATE);
        }
        // 校验账号唯一
        if (StringUtils.hasText(updateBO.getUsername())) {
            AdminDO usernameAdmin = adminMapper.selectByUsername(updateBO.getUsername());
            if (usernameAdmin != null && !usernameAdmin.getId().equals(updateBO.getId())) {
                throw ServiceExceptionUtil.exception(ADMIN_USERNAME_EXISTS);
            }
        }
        // 如果有更新状态，则校验是否已经是该状态
        if (updateBO.getStatus() != null && updateBO.getStatus().equals(adminDO.getStatus())) {
            throw ServiceExceptionUtil.exception(ADMIN_STATUS_EQUALS);
        }
        // 更新到数据库
        AdminDO updateAdmin = AdminConvert.INSTANCE.convert(updateBO);
        // 如果更新密码，需要特殊加密
        if (StringUtils.hasText(updateBO.getPassword())) {
            String passwordSalt = genPasswordSalt();
            String password = encodePassword(updateBO.getPassword(), passwordSalt);
            updateAdmin.setPassword(password).setPasswordSalt(passwordSalt);
        }
        adminMapper.updateById(updateAdmin);
    }

    public AdminBO getAdmin(Integer adminId) {
        AdminDO adminDO = adminMapper.selectById(adminId);
        return AdminConvert.INSTANCE.convert(adminDO);
    }

}
