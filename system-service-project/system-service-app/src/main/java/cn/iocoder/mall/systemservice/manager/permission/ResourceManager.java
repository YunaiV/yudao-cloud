package cn.iocoder.mall.systemservice.manager.permission;

import cn.iocoder.mall.systemservice.convert.permission.ResourceConvert;
import cn.iocoder.mall.systemservice.rpc.permission.dto.ResourceCreateDTO;
import cn.iocoder.mall.systemservice.rpc.permission.dto.ResourceUpdateDTO;
import cn.iocoder.mall.systemservice.rpc.permission.vo.ResourceVO;
import cn.iocoder.mall.systemservice.service.permission.ResourceService;
import cn.iocoder.mall.systemservice.service.permission.RoleService;
import cn.iocoder.mall.systemservice.service.permission.bo.ResourceBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
* 资源 Manager
*/
@Service
public class ResourceManager {

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private RoleService roleService;

    /**
    * 创建资源
    *
    * @param createDTO 创建资源 DTO
    * @return 资源
    */
    public Integer createResource(ResourceCreateDTO createDTO) {
        ResourceBO resourceBO = resourceService.createResource(ResourceConvert.INSTANCE.convert(createDTO));
        return resourceBO.getId();
    }

    /**
     * 更新资源
     *
     * @param updateDTO 更新资源 DTO
     */
    public void updateResource(ResourceUpdateDTO updateDTO) {
        resourceService.updateResource(ResourceConvert.INSTANCE.convert(updateDTO));
    }

    /**
     * 删除资源
     *
     * @param resourceId 资源编号
     */
    public void deleteResource(Integer resourceId) {
        resourceService.deleteResource(resourceId);
    }

    /**
     * 获得资源
     *
     * @param resourceId 资源编号
     * @return 资源
     */
    public ResourceVO getResource(Integer resourceId) {
        ResourceBO resourceBO = resourceService.getResource(resourceId);
        return ResourceConvert.INSTANCE.convert(resourceBO);
    }

    /**
     * 获得资源全列表
     *
     * @return 资源列表
     */
    public List<ResourceVO> listResources() {
        List<ResourceBO> resourceBOs = resourceService.listResources();
        return ResourceConvert.INSTANCE.convertList02(resourceBOs);
    }

    /**
     * 获得资源列表
     *
     * @param resourceIds 资源编号列表
     * @return 资源列表
     */
    public List<ResourceVO> listResources(List<Integer> resourceIds) {
        List<ResourceBO> resourceBOs = resourceService.listResources(resourceIds);
        return ResourceConvert.INSTANCE.convertList02(resourceBOs);
    }

    /**
     * 获得指定角色的资源列表
     *
     * @param roleIds 角色编号列表
     * @param type 资源类型，允许空
     * @return 资源列表
     */
    public List<ResourceVO> listRoleResources(Collection<Integer> roleIds, Integer type) {
        List<ResourceBO> resourceBOs;
        // 判断是否为超管。若是超管，默认有所有权限
        if (roleService.hasSuperAdmin(roleIds)) {
            resourceBOs = resourceService.listResourcesByType(type);
        } else {
            resourceBOs = resourceService.listRoleResourcesByType(roleIds, type);
        }
        return ResourceConvert.INSTANCE.convertList02(resourceBOs);
    }

}
