package cn.iocoder.mall.admin.service;

import cn.iocoder.mall.admin.api.ResourceService;
import cn.iocoder.mall.admin.api.bo.ResourceBO;
import cn.iocoder.mall.admin.convert.ResourceConvert;
import cn.iocoder.mall.admin.dao.ResourceMapper;
import cn.iocoder.mall.admin.dataobject.ResourceDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@com.alibaba.dubbo.config.annotation.Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    public ResourceDO getResourceByTypeAndHandler(Integer type, String handler) {
        return resourceMapper.selectByTypeAndHandler(type, handler);
    }

    @Override
    public List<ResourceBO> getResourceByTypeAndRoleIds(Integer type, Set<Integer> roleIds) {
        if (roleIds == null || roleIds.isEmpty()) {
            return Collections.emptyList();
        }
        return ResourceConvert.INSTANCE.convert(resourceMapper.selectListByTypeAndRoleIds(type, roleIds));
    }

}