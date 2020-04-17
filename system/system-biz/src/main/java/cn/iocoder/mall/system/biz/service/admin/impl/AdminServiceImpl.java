package cn.iocoder.mall.system.biz.service.admin.impl;

import cn.iocoder.mall.system.biz.bo.admin.AdminBO;
import cn.iocoder.mall.system.biz.convert.AdminConvert;
import cn.iocoder.mall.system.biz.dao.admin.AdminMapper;
import cn.iocoder.mall.system.biz.dataobject.admin.AdminDO;
import cn.iocoder.mall.system.biz.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public AdminBO get(Integer id) {
        AdminDO adminDO = adminMapper.selectById(id);
        return AdminConvert.INSTANCE.convert(adminDO);
    }

}
