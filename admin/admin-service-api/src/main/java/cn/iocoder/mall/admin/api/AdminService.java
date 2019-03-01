package cn.iocoder.mall.admin.api;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.api.bo.AdminPageBO;
import cn.iocoder.mall.admin.api.dto.AdminPageDTO;

public interface AdminService {

    CommonResult<AdminPageBO> getAdminPage(AdminPageDTO adminPageDTO);

}