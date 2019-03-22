package cn.iocoder.mall.admin.service;

import cn.iocoder.common.framework.constant.DeleteStatusEnum;
import cn.iocoder.common.framework.constant.SysErrorCodeEnum;
import cn.iocoder.common.framework.dataobject.DeletableDO;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.api.ResourceService;
import cn.iocoder.mall.admin.api.bo.ResourceBO;
import cn.iocoder.mall.admin.api.constant.AdminErrorCodeEnum;
import cn.iocoder.mall.admin.api.constant.ResourceConstants;
import cn.iocoder.mall.admin.api.dto.ResourceAddDTO;
import cn.iocoder.mall.admin.api.dto.ResourceUpdateDTO;
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
@com.alibaba.dubbo.config.annotation.Service(validation = "true")
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
    public CommonResult<ResourceBO> addResource(Integer adminId, ResourceAddDTO resourceAddDTO) {
        // 补充未在 Validation 中校验的参数校验
        if (!isValidResourceType(resourceAddDTO.getType())) {
            return CommonResult.error(SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getCode(), "资源类型必须是菜单或 Url"); // TODO 有点搓
        }
        // 校验资源唯一性
        if (resourceMapper.selectByName(resourceAddDTO.getName()) != null) {
            return ServiceExceptionUtil.error(AdminErrorCodeEnum.RESOURCE_NAME_DUPLICATE.getCode());
        }
        // 校验父资源存在
        if (resourceAddDTO.getPid() == null) {
            resourceAddDTO.setPid(ResourceConstants.PID_ROOT);
        }
        if (checkParentExists(resourceAddDTO.getPid())) {
            return ServiceExceptionUtil.error(AdminErrorCodeEnum.RESOURCE_PARENT_NOT_EXISTS.getCode());
        }
        // 存储到数据库
        ResourceDO resource = ResourceConvert.INSTANCE.convert(resourceAddDTO);
        if (ResourceConstants.PID_ROOT.equals(resourceAddDTO.getPid())) { // 根节点，必须没有操作
            resource.setHandler(null);
        } else if (!resource.getHandler().startsWith("/")) {
            resource.setHandler("/" + resource.getHandler());
        }
        resource.setCreateTime(new Date());
        resource.setDeleted(DeleteStatusEnum.DELETE_NO.getValue());
        resourceMapper.insert(resource);
        // TODO 操作日志
        // 返回成功
        return CommonResult.success(ResourceConvert.INSTANCE.convert(resource));
    }

    @Override
    @SuppressWarnings("Duplicates")
    public CommonResult<Boolean> updateResource(Integer adminId, ResourceUpdateDTO resourceUpdateDTO) {
        // 校验更新的资源是否存在
        if (resourceMapper.selectById(resourceUpdateDTO.getId()) == null) {
            return ServiceExceptionUtil.error(AdminErrorCodeEnum.RESOURCE_NOT_EXISTS.getCode());
        }
        // 校验资源唯一性
        ResourceDO existNameResource = resourceMapper.selectByName(resourceUpdateDTO.getName());
        if (existNameResource != null && !existNameResource.getId().equals(resourceUpdateDTO.getId())) {
            return ServiceExceptionUtil.error(AdminErrorCodeEnum.RESOURCE_NAME_DUPLICATE.getCode());
        }
        // 不能设置自己为父资源
        if (resourceUpdateDTO.getId().equals(resourceUpdateDTO.getPid())) {
            return ServiceExceptionUtil.error(AdminErrorCodeEnum.RESOURCE_PARENT_ERROR.getCode());
        }
        // 校验父资源存在
        if (resourceUpdateDTO.getPid() == null) {
            resourceUpdateDTO.setPid(ResourceConstants.PID_ROOT);
        }
        if (checkParentExists(resourceUpdateDTO.getPid())) {
            return ServiceExceptionUtil.error(AdminErrorCodeEnum.RESOURCE_PARENT_NOT_EXISTS.getCode());
        }
        // 更新到数据库
        ResourceDO resource = ResourceConvert.INSTANCE.convert(resourceUpdateDTO);
        if (ResourceConstants.PID_ROOT.equals(resourceUpdateDTO.getPid())) { // 根节点，必须没有操作
            resource.setHandler(null);
        }
        resourceMapper.update(resource);
        // TODO 操作日志
        // 返回成功
        return CommonResult.success(true);
    }

    @Override
    @Transactional
    public CommonResult<Boolean> deleteResource(Integer adminId, Integer resourceId) {
        // 校验更新的资源是否存在
        if (resourceMapper.selectById(resourceId) == null) {
            return ServiceExceptionUtil.error(AdminErrorCodeEnum.RESOURCE_NOT_EXISTS.getCode());
        }
        // 校验是否还有子资源
        if (resourceMapper.selectCountByPid(resourceId) > 0) {
            return ServiceExceptionUtil.error(AdminErrorCodeEnum.RESOURCE_EXISTS_CHILDREN.getCode());
        }
        // 更新到数据库
        ResourceDO resource = new ResourceDO().setId(resourceId);
        resource.setDeleted(DeleteStatusEnum.DELETE_YES.getValue());
        resourceMapper.update(resource);
        // 删除资源关联表
        roleResourceMapper.updateToDeletedByResourceId(resourceId);
        // 返回成功
        return CommonResult.success(true);
    }

    public List<ResourceDO> getResources(Set<Integer> resourceIds) {
        if (resourceIds == null || resourceIds.isEmpty()) {
            return Collections.emptyList();
        }
        return resourceMapper.selectListByIds(resourceIds);
    }

    private boolean isValidResourceType(Integer type) {
        return ResourceConstants.TYPE_MENU.equals(type)
                || ResourceConstants.TYPE_URL.equals(type);
    }

    private boolean checkParentExists(Integer pid) {
        if (!ResourceConstants.PID_ROOT.equals(pid)) {
            return resourceMapper.selectById(pid) == null;
        }
        return false;
    }

}