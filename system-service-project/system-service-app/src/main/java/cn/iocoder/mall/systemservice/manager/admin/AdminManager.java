package cn.iocoder.mall.systemservice.manager.admin;

import cn.iocoder.mall.systemservice.convert.admin.AdminConvert;
import cn.iocoder.mall.systemservice.rpc.admin.dto.AdminVerifyPasswordDTO;
import cn.iocoder.mall.systemservice.rpc.admin.vo.AdminVO;
import cn.iocoder.mall.systemservice.service.admin.AdminService;
import cn.iocoder.mall.systemservice.service.admin.bo.AdminBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminManager {

    @Autowired
    private AdminService adminService;

    public AdminVO verifyPassword(AdminVerifyPasswordDTO verifyPasswordDTO) {
        AdminBO adminBO = adminService.verifyPassword(verifyPasswordDTO.getUsername(),
                verifyPasswordDTO.getPassword(), verifyPasswordDTO.getIp());
        return AdminConvert.INSTANCE.convert(adminBO);
    }

}
