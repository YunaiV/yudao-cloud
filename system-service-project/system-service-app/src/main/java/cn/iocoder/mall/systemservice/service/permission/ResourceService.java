package cn.iocoder.mall.systemservice.service.permission;

import cn.iocoder.common.framework.util.CollectionUtils;
import cn.iocoder.common.framework.exception.util.ServiceExceptionUtil;
import cn.iocoder.mall.systemservice.convert.permission.ResourceConvert;
import cn.iocoder.mall.systemservice.dal.mysql.dataobject.permission.ResourceDO;
import cn.iocoder.mall.systemservice.dal.mysql.dataobject.permission.RoleResourceDO;
import cn.iocoder.mall.systemservice.dal.mysql.mapper.permission.ResourceMapper;
import cn.iocoder.mall.systemservice.dal.mysql.mapper.permission.RoleResourceMapper;
import cn.iocoder.mall.systemservice.enums.SystemErrorCodeConstants;
import cn.iocoder.mall.systemservice.enums.permission.ResourceIdEnum;
import cn.iocoder.mall.systemservice.enums.permission.ResourceTypeEnum;
import cn.iocoder.mall.systemservice.service.permission.bo.ResourceBO;
import cn.iocoder.mall.systemservice.service.permission.bo.ResourceCreateBO;
import cn.iocoder.mall.systemservice.service.permission.bo.ResourceUpdateBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static cn.iocoder.mall.systemservice.enums.SystemErrorCodeConstants.*;

/**
* 资源 Service
*/
@Service
@Validated
public class ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private RoleResourceMapper roleResourceMapper;

    /**
     * 创建资源
     *
     * @param createBO 创建资源 BO
     * @return 资源
     */
    public ResourceBO createResource(@Valid ResourceCreateBO createBO) {
        // 校验父资源存在
        checkParentResource(createBO.getPid(), null);
        // 校验资源（自己）
        checkResource(createBO.getPid(), createBO.getName(), null);
        // 插入数据库
        ResourceDO resourceDO = ResourceConvert.INSTANCE.convert(createBO);
        initResourceProperty(resourceDO);
        resourceMapper.insert(resourceDO);
        // 返回
        return ResourceConvert.INSTANCE.convert(resourceDO);
    }

    /**
     * 更新资源
     *
     * @param updateBO 更新资源 BO
     */
    public void updateResource(@Valid ResourceUpdateBO updateBO) {
        // 校验更新的资源是否存在
        if (resourceMapper.selectById(updateBO.getId()) == null) {
            throw ServiceExceptionUtil.exception(RESOURCE_NOT_EXISTS);
        }
        // 校验父资源存在
        checkParentResource(updateBO.getPid(), updateBO.getId());
        // 校验资源（自己）
        checkResource(updateBO.getPid(), updateBO.getName(), updateBO.getId());
        // 更新到数据库
        ResourceDO updateObject = ResourceConvert.INSTANCE.convert(updateBO);
        initResourceProperty(updateObject);
        resourceMapper.updateById(updateObject);
    }

    /**
     * 删除资源
     *
     * @param resourceId 资源编号
     */
    public void deleteResource(Integer resourceId) {
        // 校验更新的资源是否存在
        if (resourceMapper.selectById(resourceId) == null) {
            throw ServiceExceptionUtil.exception(SystemErrorCodeConstants.RESOURCE_NOT_EXISTS);
        }
        // 校验是否还有子资源
        if (resourceMapper.selectCountByPid(resourceId) > 0) {
            throw ServiceExceptionUtil.exception(SystemErrorCodeConstants.RESOURCE_EXISTS_CHILDREN);
        }
        // 校验删除的资源是否存在
        if (resourceMapper.selectById(resourceId) == null) {
            throw ServiceExceptionUtil.exception(RESOURCE_NOT_EXISTS);
        }
        // 标记删除
        resourceMapper.deleteById(resourceId);
        // 删除授予给角色的权限
        roleResourceMapper.deleteByResourceId(resourceId);
    }

    /**
     * 获得资源
     *
     * @param resourceId 资源编号
     * @return 资源
     */
    public ResourceBO getResource(Integer resourceId) {
        ResourceDO resourceDO = resourceMapper.selectById(resourceId);
        return ResourceConvert.INSTANCE.convert(resourceDO);
    }

    /**
     * 获得资源列表
     *
     * @param resourceIds 资源编号列表
     * @return 资源列表
     */
    public List<ResourceBO> listResources(List<Integer> resourceIds) {
        List<ResourceDO> resourceDOs = resourceMapper.selectBatchIds(resourceIds);
        return ResourceConvert.INSTANCE.convertList(resourceDOs);
    }

    /**
     * 获得资源全列表
     *
     * @return 资源全列表
     */
    public List<ResourceBO> listResources() {
        List<ResourceDO> resourceDOs = resourceMapper.selectList(null);
        return ResourceConvert.INSTANCE.convertList(resourceDOs);
    }

    /**
     * 获得指定类型的资源列表
     *
     * @param type 资源类型，允许空
     * @return 资源列表
     */
    public List<ResourceBO> listResourcesByType(Integer type) {
        List<ResourceDO> resourceDOs = resourceMapper.selectListByType(type);
        return ResourceConvert.INSTANCE.convertList(resourceDOs);
    }

    /**
     * 获得角色拥有的资源列表
     *
     * @param roleIds 角色编号
     * @param type 资源类型，允许空
     * @return 资源列表
     */
    public List<ResourceBO> listRoleResourcesByType(Collection<Integer> roleIds, Integer type) {
        List<RoleResourceDO> roleResourceDOs = roleResourceMapper.selectListByRoleIds(roleIds);
        if (CollectionUtils.isEmpty(roleResourceDOs)) {
            return Collections.emptyList();
        }
        List<ResourceDO> resourceDOs = resourceMapper.selectListByIdsAndType(
                CollectionUtils.convertSet(roleResourceDOs, RoleResourceDO::getResourceId), type);
        return ResourceConvert.INSTANCE.convertList(resourceDOs);
    }

    /**
     * 校验父资源是否合法
     *
     * 1. 不能设置自己为父资源
     * 2. 父资源不存在
     * 3. 父资源必须是 {@link ResourceTypeEnum#MENU} 菜单类型
     *
     * @param pid 父资源编号
     * @param childId 当前资源编号
     */
    private void checkParentResource(Integer pid, Integer childId) {
        if (pid == null || ResourceIdEnum.ROOT.getId().equals(pid)) {
            return;
        }
        // 不能设置自己为父资源
        if (pid.equals(childId)) {
            throw ServiceExceptionUtil.exception(RESOURCE_PARENT_ERROR);
        }
        ResourceDO resource = resourceMapper.selectById(pid);
        // 父资源不存在
        if (resource == null) {
            throw ServiceExceptionUtil.exception(RESOURCE_PARENT_NOT_EXISTS);
        }
        // 父资源必须是菜单类型
        if (!ResourceTypeEnum.MENU.getType().equals(resource.getType())) {
            throw ServiceExceptionUtil.exception(RESOURCE_PARENT_NOT_MENU);
        }
    }

    /**
     * 校验资源是否合法
     *
     * 1. 校验相同父资源编号下，是否存在相同的资源名
     *
     * @param name 资源名字
     * @param pid 父资源编号
     * @param id 资源编号
     */
    private void checkResource(Integer pid, String name, Integer id) {
        ResourceDO resource = resourceMapper.selectByPidAndName(pid, name);
        if (resource == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的资源
        if (id == null) {
            throw ServiceExceptionUtil.exception(RESOURCE_NAME_DUPLICATE);
        }
        if (!resource.getId().equals(id)) {
            throw ServiceExceptionUtil.exception(RESOURCE_NAME_DUPLICATE);
        }
    }

    /**
     * 初始化资源的通用属性。
     *
     * 例如说，只有菜单类型的资源，才设置 icon
     *
     * @param resource 资源
     */
    private void initResourceProperty(ResourceDO resource) {
        // 资源为按钮类型时，无需 route、icon、view 属性，进行置空
        if (ResourceTypeEnum.BUTTON.getType().equals(resource.getType())) {
            resource.setRoute(null);
            resource.setIcon(null);
            resource.setView(null);
        }
    }

}
