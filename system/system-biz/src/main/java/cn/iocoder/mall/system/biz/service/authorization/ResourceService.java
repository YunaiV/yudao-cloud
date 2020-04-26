package cn.iocoder.mall.system.biz.service.authorization;

import cn.iocoder.mall.system.biz.bo.authorization.ResourceBO;
import cn.iocoder.mall.system.biz.dto.authorization.ResourceGetListDTO;

import java.util.Collection;
import java.util.List;

public interface ResourceService {

    List<ResourceBO> getResourcesByPermissions(Collection<String> permissions);

    List<ResourceBO> getResources(ResourceGetListDTO getListDTO);

}
