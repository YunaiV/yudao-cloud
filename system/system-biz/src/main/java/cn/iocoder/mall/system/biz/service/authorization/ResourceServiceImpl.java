package cn.iocoder.mall.system.biz.service.authorization;

import cn.iocoder.mall.system.biz.bo.authorization.ResourceBO;
import cn.iocoder.mall.system.biz.convert.authorization.ResourceConvert;
import cn.iocoder.mall.system.biz.dao.authorization.ResourceMapper;
import cn.iocoder.mall.system.biz.dataobject.authorization.ResourceDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public List<ResourceBO> getListByPermissions(Collection<String> permissions) {
        List<ResourceDO> resourceDOs = resourceMapper.selectListByPermissions(permissions);
        return ResourceConvert.INSTANCE.convertList(resourceDOs);
    }

}
