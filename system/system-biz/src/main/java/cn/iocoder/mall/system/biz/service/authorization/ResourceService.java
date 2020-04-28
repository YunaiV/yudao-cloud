package cn.iocoder.mall.system.biz.service.authorization;

import cn.iocoder.common.framework.exception.ServiceException;
import cn.iocoder.mall.system.biz.bo.authorization.ResourceBO;
import cn.iocoder.mall.system.biz.bo.authorization.ResourceTreeNodeBO;
import cn.iocoder.mall.system.biz.dto.authorization.*;

import java.util.Collection;
import java.util.List;

/**
 * 资源模块 - Service 接口
 */
public interface ResourceService {

    List<ResourceBO> getResourcesByPermissions(Collection<String> permissions);

    List<ResourceBO> getResources(ResourceGetListDTO getListDTO);

    /**
     * 获得资源树
     *
     * @param getTreeDTO 查询条件
     * @return 资源树
     */
    List<ResourceTreeNodeBO> getResourceTree(ResourceGetTreeDTO getTreeDTO);

    Integer addResource(ResourceAddDTO addDTO);

    /**
     * 更新资源。如果更新失败，则抛出 {@link ServiceException} 异常
     *
     * @param updateDTO 更新资源
     */
    void updateResource(ResourceUpdateDTO updateDTO);

    /**
     * 删除资源。如果删除失败，则抛出 {@link ServiceException} 异常
     *
     * @param deleteDTO 删除资源
     */
    void deleteResource(ResourceDeleteDTO deleteDTO);

}
