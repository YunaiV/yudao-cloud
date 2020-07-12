package cn.iocoder.mall.system.biz.service.authorization;

import cn.iocoder.mall.system.biz.bo.authorization.ResourceBO;
import cn.iocoder.mall.system.biz.bo.authorization.ResourceTreeNodeBO;
import cn.iocoder.mall.system.biz.dto.authorization.ResourceCountDTO;
import cn.iocoder.mall.system.biz.dto.authorization.ResourceGetListDTO;
import cn.iocoder.mall.system.biz.dto.authorization.ResourceGetTreeDTO;

import java.util.Collection;
import java.util.List;

/**
 * 资源模块 - Service 接口
 */
public interface ResourceService {

    List<ResourceBO> getResourcesByPermissions(Collection<String> permissions);

    List<ResourceBO> getResources(ResourceGetListDTO getListDTO);

    int countResource(ResourceCountDTO countDTO);

}
