package cn.iocoder.mall.managementweb.manager.admin;

import cn.iocoder.mall.managementweb.controller.admin.dto.AdminCreateDTO;
import cn.iocoder.mall.managementweb.controller.admin.dto.AdminUpdateInfoDTO;
import cn.iocoder.mall.managementweb.controller.admin.dto.AdminUpdateStatusDTO;
import org.springframework.stereotype.Service;

@Service
public class AdminManager {

    //TODO 目前需要增加搜索所有子部门的用户


    public Integer createAdmin(AdminCreateDTO createDTO, Integer createAdminId, String createIp) {
        return null;
    }

    public void updateAdmin(AdminUpdateInfoDTO updateInfoDTO) {
    }

    public void updateAdminStatus(AdminUpdateStatusDTO updateStatusDTO) {

    }

}
