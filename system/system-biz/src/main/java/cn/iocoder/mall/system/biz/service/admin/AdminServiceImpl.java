package cn.iocoder.mall.system.biz.service.admin;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.system.biz.bo.admin.AdminBO;
import cn.iocoder.mall.system.biz.convert.admin.AdminConvert;
import cn.iocoder.mall.system.biz.dao.admin.AdminMapper;
import cn.iocoder.mall.system.biz.dto.admin.AdminPageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public AdminBO getAdmin(Integer id) {
        return AdminConvert.INSTANCE.convert(adminMapper.selectById(id));
    }

    @Override
    public AdminBO getAdminByAccountId(Integer accountId) {
        return AdminConvert.INSTANCE.convert(adminMapper.selectByAccountId(accountId));
    }

    @Override
    public PageResult<AdminBO> getAdminPage(AdminPageDTO pageDTO) {
        return AdminConvert.INSTANCE.convertPage(adminMapper.selectPage(pageDTO));
    }

}
