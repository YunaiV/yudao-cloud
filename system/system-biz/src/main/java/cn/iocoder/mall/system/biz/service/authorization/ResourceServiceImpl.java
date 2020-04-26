package cn.iocoder.mall.system.biz.service.authorization;

import cn.iocoder.mall.system.biz.bo.authorization.ResourceBO;
import cn.iocoder.mall.system.biz.convert.authorization.ResourceConvert;
import cn.iocoder.mall.system.biz.dao.authorization.ResourceMapper;
import cn.iocoder.mall.system.biz.dataobject.authorization.ResourceDO;
import cn.iocoder.mall.system.biz.dto.authorization.ResourceGetListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public List<ResourceBO> getResourcesByPermissions(Collection<String> permissions) {
        List<ResourceDO> resourceDOs = resourceMapper.selectListByPermissions(permissions);
        return ResourceConvert.INSTANCE.convertList(resourceDOs);
    }

    @Override
    public List<ResourceBO> getResources(ResourceGetListDTO getListDTO) {
        List<ResourceDO> resourceDOs = resourceMapper.selectListByIdsAndType(getListDTO.getIds(), getListDTO.getType());
        return ResourceConvert.INSTANCE.convertList(resourceDOs);
    }

}
