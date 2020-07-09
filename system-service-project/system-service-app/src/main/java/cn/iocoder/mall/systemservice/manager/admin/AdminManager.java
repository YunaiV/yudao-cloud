package cn.iocoder.mall.systemservice.manager.admin;

import cn.iocoder.common.framework.enums.UserTypeEnum;
import cn.iocoder.common.framework.util.StringUtils;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.systemservice.convert.admin.AdminConvert;
import cn.iocoder.mall.systemservice.enums.admin.AdminStatusEnum;
import cn.iocoder.mall.systemservice.rpc.admin.dto.AdminCreateDTO;
import cn.iocoder.mall.systemservice.rpc.admin.dto.AdminPageDTO;
import cn.iocoder.mall.systemservice.rpc.admin.dto.AdminUpdateDTO;
import cn.iocoder.mall.systemservice.rpc.admin.dto.AdminVerifyPasswordDTO;
import cn.iocoder.mall.systemservice.rpc.admin.vo.AdminVO;
import cn.iocoder.mall.systemservice.service.admin.AdminService;
import cn.iocoder.mall.systemservice.service.admin.bo.AdminBO;
import cn.iocoder.mall.systemservice.service.oauth.OAuth2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminManager {

    @Autowired
    private AdminService adminService;
    @Autowired
    private OAuth2Service oauth2Service;

    public AdminVO verifyPassword(AdminVerifyPasswordDTO verifyPasswordDTO) {
        AdminBO adminBO = adminService.verifyPassword(verifyPasswordDTO.getUsername(),
                verifyPasswordDTO.getPassword(), verifyPasswordDTO.getIp());
        return AdminConvert.INSTANCE.convert(adminBO);
    }

    public AdminVO createAdmin(AdminCreateDTO createDTO) {
        AdminBO adminBO = adminService.createAdmin(AdminConvert.INSTANCE.convert(createDTO));
        return AdminConvert.INSTANCE.convert(adminBO);
    }

    @Transactional
    public void updateAdmin(AdminUpdateDTO updateDTO) {
        // 更新管理员信息
        adminService.updateAdmin(AdminConvert.INSTANCE.convert(updateDTO));
        // 如果修改密码，或者禁用管理员
        if (StringUtils.hasText(updateDTO.getPassword())
            || AdminStatusEnum.INACTIVE.getStatus().equals(updateDTO.getStatus())) {
            oauth2Service.removeToken(updateDTO.getId(), UserTypeEnum.ADMIN.getValue());
        }
    }

    public PageResult<AdminVO> pageAdmin(AdminPageDTO pageDTO) {
        PageResult<AdminBO> adminPage = adminService.pageAdmin(AdminConvert.INSTANCE.convert(pageDTO));
        return AdminConvert.INSTANCE.convert(adminPage);
    }

    public AdminVO getAdmin(Integer adminId) {
        AdminBO adminBO = adminService.getAdmin(adminId);
        return AdminConvert.INSTANCE.convert(adminBO);
    }
}
