package cn.iocoder.mall.admin.service;

import cn.iocoder.common.framework.constant.DeletedStatusEnum;
import cn.iocoder.common.framework.constant.SysErrorCodeEnum;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.mall.admin.api.ResourceService;
import cn.iocoder.mall.admin.api.bo.resource.ResourceBO;
import cn.iocoder.mall.admin.api.constant.AdminErrorCodeEnum;
import cn.iocoder.mall.admin.api.constant.ResourceConstants;
import cn.iocoder.mall.admin.api.dto.resource.ResourceAddDTO;
import cn.iocoder.mall.admin.api.dto.resource.ResourceUpdateDTO;
import cn.iocoder.mall.admin.convert.ResourceConvert;
import cn.iocoder.mall.admin.dao.ResourceMapper;
import cn.iocoder.mall.admin.dao.RoleResourceMapper;
import cn.iocoder.mall.admin.dataobject.ResourceDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.ResourceService.version}")
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private RoleResourceMapper roleResourceMapper;

    public ResourceDO getResourceByTypeAndHandler(Integer type, String handler) {
        return resourceMapper.selectByTypeAndHandler(type, handler);
    }

    @Override
    public List<ResourceBO> getResourcesByTypeAndRoleIds(Integer type, Set<Integer> roleIds) {
        if (roleIds == null || roleIds.isEmpty()) {
            return Collections.emptyList();
        }
        return ResourceConvert.INSTANCE.convert(resourceMapper.selectListByTypeAndRoleIds(type, roleIds));
    }

    @Override
    public List<ResourceBO> getResourcesByType(Integer type) {
        return ResourceConvert.INSTANCE.convert(resourceMapper.selectListByType (type));
    }

    @Override
    @SuppressWarnings("Duplicates")
    public ResourceBO addResource(Integer adminId, ResourceAddDTO resourceAddDTO) {
        // 补充未在 Validation 中校验的参数校验
        if (!isValidResourceType(resourceAddDTO.getType())) {
            throw ServiceExceptionUtil.exception(SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getCode(), "资源类型必须是菜单或 Url"); // TODO 有点搓
        }
        // 校验父资源存在
        checkParentResource(resourceAddDTO.getPid(), null);
        // 存储到数据库
        ResourceDO resource = ResourceConvert.INSTANCE.convert(resourceAddDTO);
        initResourceProperty(resource);
        resource.setCreateTime(new Date());
        resource.setDeleted(DeletedStatusEnum.DELETED_NO.getValue());
        resourceMapper.insert(resource);
        // TODO 操作日志
        // 返回成功
        return ResourceConvert.INSTANCE.convert(resource);
    }

    @Override
    @SuppressWarnings("Duplicates")
    public Boolean updateResource(Integer adminId, ResourceUpdateDTO resourceUpdateDTO) {
        // 校验更新的资源是否存在
        if (resourceMapper.selectById(resourceUpdateDTO.getId()) == null) {
            throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.RESOURCE_NOT_EXISTS.getCode());
        }
        // 校验父资源存在
        checkParentResource(resourceUpdateDTO.getPid(), resourceUpdateDTO.getId());
        // 更新到数据库
        ResourceDO resource = ResourceConvert.INSTANCE.convert(resourceUpdateDTO);
        initResourceProperty(resource);
        resourceMapper.update(resource);
        // TODO 操作日志
        // 返回成功
        return true;
    }

    @Override
    @Transactional
    public Boolean deleteResource(Integer adminId, Integer resourceId) {
        // 校验更新的资源是否存在
        if (resourceMapper.selectById(resourceId) == null) {
            throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.RESOURCE_NOT_EXISTS.getCode());
        }
        // 校验是否还有子资源
        if (resourceMapper.selectCountByPid(resourceId) > 0) {
            throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.RESOURCE_EXISTS_CHILDREN.getCode());
        }
        // 更新到数据库
        ResourceDO resource = new ResourceDO().setId(resourceId);
        resource.setDeleted(DeletedStatusEnum.DELETED_YES.getValue());
        resourceMapper.update(resource);
        // 删除资源关联表
        roleResourceMapper.updateToDeletedByResourceId(resourceId);
        // 返回成功
        return true;
    }

    public List<ResourceDO> getResources(Set<Integer> resourceIds) {
        if (resourceIds == null || resourceIds.isEmpty()) {
            return Collections.emptyList();
        }
        return resourceMapper.selectListByIds(resourceIds);
    }

    private boolean isValidResourceType(Integer type) {
        return ResourceConstants.TYPE_MENU.equals(type)
                || ResourceConstants.TYPE_BUTTON.equals(type);
    }

    private boolean checkParentExists(Integer pid) {
        if (!ResourceConstants.PID_ROOT.equals(pid)) {
            return resourceMapper.selectById(pid) == null;
        }
        return false;
    }

    private void checkParentResource(Integer pid, Integer childId) {
        if (pid == null || ResourceConstants.PID_ROOT.equals(pid)) {
            return;
        }
        if (pid.equals(childId)) { // 不能设置自己为父资源
            throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.RESOURCE_PARENT_ERROR.getCode());
        }
        ResourceDO resource = resourceMapper.selectById(pid);
        if (resource == null) { // 父资源不存在
            throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.RESOURCE_PARENT_NOT_EXISTS.getCode());
        }
        if (!ResourceConstants.TYPE_MENU.equals(resource.getType())) { // 父资源必须是菜单类型
            throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.RESOURCE_PARENT_NOT_MENU.getCode());
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
        if (resource.getPid() == null) {
            resource.setPid(ResourceConstants.PID_ROOT);
        }
        if (ResourceConstants.TYPE_BUTTON.equals(resource.getType())) {
            resource.setHandler(null);
            resource.setIcon(null);
        }
    }

}
