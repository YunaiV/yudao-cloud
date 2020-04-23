package cn.iocoder.mall.system.biz.service.admin;

import cn.iocoder.mall.system.biz.bo.admin.AdminBO;
import cn.iocoder.mall.system.biz.convert.admin.AdminConvert;
import cn.iocoder.mall.system.biz.dao.admin.AdminMapper;
import cn.iocoder.mall.system.biz.dataobject.admin.AdminDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public AdminBO getAdmin(Integer id) {
        AdminDO adminDO = adminMapper.selectById(id);
        return AdminConvert.INSTANCE.convert(adminDO);
    }

    @Override
    public AdminBO getAdminByAccountId(Integer accountId) {
        AdminDO adminDO = adminMapper.selectByAccountId(accountId);
        return AdminConvert.INSTANCE.convert(adminDO);
    }

}
