package cn.iocoder.mall.admin.service;

import cn.iocoder.mall.admin.api.RoleService;
import cn.iocoder.mall.admin.dao.RoleResourceMapper;
import cn.iocoder.mall.admin.dataobject.RoleResourceDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@com.alibaba.dubbo.config.annotation.Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    public List<RoleResourceDO> getRoleByResourceHandler(String resourceHandler) {
        return roleResourceMapper.selectByResourceHandler(resourceHandler);
    }

    public List<RoleResourceDO> getRoleByResourceId(Integer resourceId) {
        return roleResourceMapper.selectRoleByResourceId(resourceId);
    }

}