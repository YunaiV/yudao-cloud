package cn.iocoder.mall.admin.service;

import cn.iocoder.common.framework.constant.DeletedStatusEnum;
import cn.iocoder.common.framework.util.CollectionUtil;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.system.api.RoleService;
import cn.iocoder.mall.system.api.bo.role.RoleBO;
import cn.iocoder.mall.system.api.constant.AdminErrorCodeEnum;
import cn.iocoder.mall.system.api.dto.role.RoleAddDTO;
import cn.iocoder.mall.system.api.dto.role.RoleAssignResourceDTO;
import cn.iocoder.mall.system.api.dto.role.RolePageDTO;
import cn.iocoder.mall.system.api.dto.role.RoleUpdateDTO;
import cn.iocoder.mall.admin.convert.RoleConvert;
import cn.iocoder.mall.admin.dao.AdminRoleMapper;
import cn.iocoder.mall.admin.dao.RoleMapper;
import cn.iocoder.mall.admin.dao.RoleResourceMapper;
import cn.iocoder.mall.admin.dataobject.ResourceDO;
import cn.iocoder.mall.admin.dataobject.RoleDO;
import cn.iocoder.mall.admin.dataobject.RoleResourceDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.RoleService.version}")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleResourceMapper roleResourceMapper;
    @Autowired
    private AdminRoleMapper adminRoleMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private ResourceServiceImpl resourceService;

    public List<RoleResourceDO> getRoleByResourceId(Integer resourceId) {
        return roleResourceMapper.selectListByResourceId(resourceId);
    }

    @Override
    public PageResult<RoleBO> getRolePage(RolePageDTO rolePageDTO) {
        IPage<RoleDO> page = roleMapper.selectPage(rolePageDTO);
        return RoleConvert.INSTANCE.convert(page);
    }

    @Override
    public List<RoleBO> getRoleList() {
        List<RoleDO> roleList = roleMapper.selectList();
        return RoleConvert.INSTANCE.convert(roleList);
    }

    @Override
    public List<RoleBO> getRoleList(Collection<Integer> ids) {
        List<RoleDO> roles = roleMapper.selectBatchIds(ids);
        return RoleConvert.INSTANCE.convert(roles);
    }

    @Override
    public RoleBO addRole(Integer adminId, RoleAddDTO roleAddDTO) {
        // TODO 芋艿，角色名是否要唯一呢？貌似一般系统都是允许的。
        // 保存到数据库
        RoleDO role = RoleConvert.INSTANCE.convert(roleAddDTO);
        role.setCreateTime(new Date());
        role.setDeleted(DeletedStatusEnum.DELETED_NO.getValue());
        roleMapper.insert(role);
        // TODO 插入操作日志
        // 返回成功
        return RoleConvert.INSTANCE.convert(role);
    }

    @Override
    public Boolean updateRole(Integer adminId, RoleUpdateDTO roleUpdateDTO) {
        // TODO 芋艿，角色名是否要唯一呢？貌似一般系统都是允许的。
        // 校验角色是否存在
        if (roleMapper.selectById(roleUpdateDTO.getId()) == null) {
            throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.RESOURCE_NOT_EXISTS.getCode());
        }
        // 更新到数据库
        RoleDO roleDO = RoleConvert.INSTANCE.convert(roleUpdateDTO);
        roleMapper.updateById(roleDO);
        // TODO 插入操作日志
        // 返回成功
        return true;
    }

    @Override
    @Transactional
    public Boolean deleteRole(Integer adminId, Integer roleId) {
        // 校验角色是否存在
        if (roleMapper.selectById(roleId) == null) {
            throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.RESOURCE_NOT_EXISTS.getCode());
        }
        // 更新到数据库，标记删除
        roleMapper.deleteById(roleId);
        // 标记删除 RoleResource
        roleResourceMapper.deleteByRoleId(roleId);
        // 标记删除 AdminRole
        adminRoleMapper.deleteByRoleId(roleId);
        // TODO 插入操作日志
        // 返回成功
        return true;
    }

    @Override
    @Transactional
    public Boolean assignRoleResource(Integer adminId, RoleAssignResourceDTO roleAssignResourceDTO) {
        Integer roleId = roleAssignResourceDTO.getId();
        Set<Integer> resourceIds = roleAssignResourceDTO.getResourceIds();
        // 校验角色是否存在
        if (roleMapper.selectById(roleAssignResourceDTO.getId()) == null) {
            throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.RESOURCE_NOT_EXISTS.getCode());
        }
        // 校验是否有不存在的资源
        if (!CollectionUtil.isEmpty(resourceIds)) {
            List<ResourceDO> resources = resourceService.getResources(resourceIds);
            if (resources.size() != resourceIds.size()) {
                throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.ROLE_ASSIGN_RESOURCE_NOT_EXISTS.getCode());
            }
        }
        // TODO 芋艿，这里先简单实现。即方式是，删除老的分配的资源关系，然后添加新的分配的资源关系
        // 标记角色原资源关系都为删除
        roleResourceMapper.deleteByRoleId(roleId);
        // 创建 RoleResourceDO 数组，并插入到数据库
        if (!CollectionUtil.isEmpty(resourceIds)) {
            List<RoleResourceDO> roleResources = resourceIds.stream().map(resourceId -> {
                RoleResourceDO roleResource = new RoleResourceDO().setRoleId(roleId).setResourceId(resourceId);
                roleResource.setCreateTime(new Date());
                roleResource.setDeleted(DeletedStatusEnum.DELETED_NO.getValue());
                return roleResource;
            }).collect(Collectors.toList());
            roleResourceMapper.insertList(roleResources);
        }
        // TODO 插入操作日志
        // 返回成功
        return true;
    }

    public List<RoleDO> getRoles(Set<Integer> roleIds) {
        if (CollectionUtil.isEmpty(roleIds)) {
            return Collections.emptyList();
        }
        return roleMapper.selectBatchIds(roleIds);
    }

    /**
     * 获得权限与角色的映射关系。
     *
     * TODO 芋艿，等以后有 redis ，优化成从缓存读取。每个 permission ，哪些角色可以访问
     *
     * @param permissions 权限标识数组
     * @return 映射关系。KEY：权限标识；VALUE：角色编号数组
     */
    public Map<String, List<Integer>> getPermissionRoleMap(List<String> permissions) {
        if (CollectionUtil.isEmpty(permissions)) {
            return Collections.emptyMap();
        }
        Map<String, List<Integer>> result = Maps.newHashMapWithExpectedSize(permissions.size());
        for (String permission : permissions) {
            List<ResourceDO> resources = resourceService.getResourceListByPermission(permission);
            if (resources.isEmpty()) { // 无需授权
                result.put(permission, Collections.emptyList());
            } else {
                List<RoleResourceDO> roleResources = roleResourceMapper.selectListByResourceId(
                        CollectionUtil.convertSet(resources, ResourceDO::getId));
                if (roleResources.isEmpty()) {
                    result.put(permission, Collections.emptyList());
                } else {
                    result.put(permission, CollectionUtil.convertList(roleResources, RoleResourceDO::getRoleId));
                }
            }
        }
        return result;
    }

}
