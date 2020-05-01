package cn.iocoder.mall.system.biz.service.admin;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.system.biz.bo.admin.AdminBO;
import cn.iocoder.mall.system.biz.dto.admin.AdminPageDTO;

/**
 * 管理员模块 - Service 接口
 */
public interface AdminService {

    /**
     * 根据编号获得管理员信息
     *
     * @param id 编号
     * @return 管理员
     */
    AdminBO getAdmin(Integer id);

    /**
     * 获得账号编号获得管理员信息
     *
     * @param accountId 账号编号
     * @return 管理员
     */
    AdminBO getAdminByAccountId(Integer accountId);

    PageResult<AdminBO> getAdminPage(AdminPageDTO pageDTO);

}
