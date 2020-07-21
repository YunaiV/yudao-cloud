package cn.iocoder.mall.systemservice.service.permission;

import cn.hutool.core.collection.CollectionUtil;
import cn.iocoder.common.framework.exception.GlobalException;
import cn.iocoder.common.framework.util.CollectionUtils;
import cn.iocoder.common.framework.exception.util.ServiceExceptionUtil;
import cn.iocoder.mall.systemservice.dal.mysql.dataobject.admin.AdminDO;
import cn.iocoder.mall.systemservice.dal.mysql.dataobject.permission.AdminRoleDO;
import cn.iocoder.mall.systemservice.dal.mysql.dataobject.permission.ResourceDO;
import cn.iocoder.mall.systemservice.dal.mysql.dataobject.permission.RoleDO;
import cn.iocoder.mall.systemservice.dal.mysql.dataobject.permission.RoleResourceDO;
import cn.iocoder.mall.systemservice.dal.mysql.mapper.admin.AdminMapper;
import cn.iocoder.mall.systemservice.dal.mysql.mapper.permission.AdminRoleMapper;
import cn.iocoder.mall.systemservice.dal.mysql.mapper.permission.ResourceMapper;
import cn.iocoder.mall.systemservice.dal.mysql.mapper.permission.RoleMapper;
import cn.iocoder.mall.systemservice.dal.mysql.mapper.permission.RoleResourceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static cn.iocoder.common.framework.exception.enums.GlobalErrorCodeConstants.FORBIDDEN;
import static cn.iocoder.mall.systemservice.enums.SystemErrorCodeConstants.*;

/**
 * 权限 Service
 */
@Service
@Slf4j
public class PermissionService {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private AdminRoleMapper adminRoleMapper;
    @Autowired
    private RoleResourceMapper roleResourceMapper;

    /**
     * 获得角色拥有的资源编号
     *
     * @param roleId 角色编号
     * @return 资源编号列表
     */
    public Set<Integer> listRoleResourceIds(Integer roleId) {
        List<RoleResourceDO> roleResourceDOs = roleResourceMapper.selectListByRoleId(roleId);
        return CollectionUtils.convertSet(roleResourceDOs, RoleResourceDO::getResourceId);
    }

    /**
     * 赋予角色资源
     *
     * @param roleId 角色编号
     * @param resourceIds 资源编号列表
     */
    @Transactional
    public void assignRoleResource(Integer roleId, Set<Integer> resourceIds) {
        // 校验角色是否存在
        if (roleMapper.selectById(roleId) == null) {
            throw ServiceExceptionUtil.exception(ROLE_NOT_EXISTS);
        }
        // 校验是否有不存在的资源
        if (!CollectionUtils.isEmpty(resourceIds)) {
            int dbResourceSize = resourceMapper.selectCountByIdsAndType(resourceIds, null);
            if (resourceIds.size() != dbResourceSize) {
                throw ServiceExceptionUtil.exception(PERMISSION_ROLE_ASSIGN_RESOURCE_NOT_EXISTS);
            }
        }
        // TODO 芋艿，这里先简单实现。即方式是，删除老的分配的资源关系，然后添加新的分配的资源关系
        // 标记角色原资源关系都为删除
        roleResourceMapper.deleteByRoleId(roleId);
        // 创建 RoleResourceDO 数组，并插入到数据库
        if (!CollectionUtils.isEmpty(resourceIds)) {
            List<RoleResourceDO> roleResources = resourceIds.stream()
                    .map(resourceId -> new RoleResourceDO().setRoleId(roleId).setResourceId(resourceId)).collect(Collectors.toList());
            roleResourceMapper.insertList(roleResources);
        }
    }

    /**
     * 赋予管理员角色
     *
     * @param adminId 管理员编号
     * @param roleIds 角色编号列表
     */
    @Transactional
    public void assignAdminRole(Integer adminId, Set<Integer> roleIds) {
        // 校验账号存在
        AdminDO admin = adminMapper.selectById(adminId);
        if (admin == null) {
            throw ServiceExceptionUtil.exception(ADMIN_NOT_FOUND);
        }
        // 校验是否有不存在的角色
        if (!CollectionUtils.isEmpty(roleIds)) {
            List<RoleDO> roles = roleMapper.selectBatchIds(roleIds);
            if (roles.size() != roleIds.size()) {
                throw ServiceExceptionUtil.exception(ADMIN_ASSIGN_ROLE_NOT_EXISTS);
            }
        }
        // TODO 芋艿，这里先简单实现。即方式是，删除老的分配的角色关系，然后添加新的分配的角色关系
        // 标记管理员角色源关系都为删除
        adminRoleMapper.deleteByAdminId(adminId);
        // 创建 RoleResourceDO 数组，并插入到数据库
        if (!CollectionUtil.isEmpty(roleIds)) {
            List<AdminRoleDO> adminRoleDOs = roleIds.stream()
                    .map(roleId -> new AdminRoleDO().setAdminId(adminId).setRoleId(roleId)).collect(Collectors.toList());
            adminRoleMapper.insertList(adminRoleDOs);
        }
    }

    /**
     * 获得管理员拥有的角色编号列表
     *
     * @param adminId 管理员编号
     * @return 角色编号列表
     */
    public Set<Integer> listAdminRoleIds(Integer adminId) {
        List<AdminRoleDO> adminRoleDOs = adminRoleMapper.selectListByAdminId(adminId);
        return CollectionUtils.convertSet(adminRoleDOs, AdminRoleDO::getRoleId);
    }

    /**
     * 获得每个管理员拥有的角色编号
     * 返回的结果，key 为管理员编号
     *
     * @param adminIds 管理员编号列表
     * @return 每个管理员拥有的角色编号
     */
    public Map<Integer, Set<Integer>> mapAdminRoleIds(Collection<Integer> adminIds) {
        List<AdminRoleDO> adminRoleDOs = adminRoleMapper.selectListByAdminIds(adminIds);
        if (CollectionUtils.isEmpty(adminRoleDOs)) {
            return Collections.emptyMap();
        }
        return  CollectionUtils.convertMultiMap2(adminRoleDOs, AdminRoleDO::getAdminId, AdminRoleDO::getRoleId);
    }

    public void checkPermission(Collection<Integer> roleIds, Collection<String> permissions) {
        // 查询权限对应资源
        List<ResourceDO> resourceBOs = resourceMapper.selectListByPermissions(permissions);
        if (CollectionUtil.isEmpty(resourceBOs)) { // 无对应资源，则认为无需权限验证
            log.warn("[checkPermission][permission({}) 未配置对应资源]", permissions);
            return;
        }
        Set<Integer> permissionIds = CollectionUtils.convertSet(resourceBOs, ResourceDO::getId);
        // 权限验证
        List<RoleResourceDO> roleResourceDOs = roleResourceMapper.selectListByResourceIds(permissionIds);
        if (CollectionUtil.isEmpty(roleResourceDOs)) { // 资源未授予任何角色，必然权限验证不通过
            throw new GlobalException(FORBIDDEN);
        }
        Map<Integer, List<Integer>> resourceRoleMap = CollectionUtils.convertMultiMap(roleResourceDOs,
                RoleResourceDO::getResourceId, RoleResourceDO::getRoleId);
        for (Map.Entry<Integer, List<Integer>> entry : resourceRoleMap.entrySet()) {
            if (!CollectionUtil.containsAny(roleIds, entry.getValue())) { // 所以有任一不满足，就验证失败，抛出异常
                throw new GlobalException(FORBIDDEN);
            }
        }
    }

}
