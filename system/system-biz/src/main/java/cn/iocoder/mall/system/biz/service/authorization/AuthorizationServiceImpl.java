package cn.iocoder.mall.system.biz.service.authorization;

import cn.iocoder.common.framework.util.CollectionUtil;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.mall.system.biz.bo.authorization.ResourceBO;
import cn.iocoder.mall.system.biz.bo.authorization.ResourceTreeNodeBO;
import cn.iocoder.mall.system.biz.dao.authorization.AccountRoleMapper;
import cn.iocoder.mall.system.biz.dao.authorization.RoleResourceMapper;
import cn.iocoder.mall.system.biz.dataobject.authorization.AccountRoleDO;
import cn.iocoder.mall.system.biz.dataobject.authorization.RoleResourceDO;
import cn.iocoder.mall.system.biz.dto.authorization.*;
import cn.iocoder.mall.system.biz.event.authorization.ResourceDeleteEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.*;

import static cn.iocoder.mall.system.biz.enums.SystemErrorCodeEnum.AUTHORIZATION_PERMISSION_DENY;

/**
 * 授权模块 - Service 实现类
 */
@Service
@Slf4j
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    private AccountRoleMapper accountRoleMapper;
    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Autowired
    private RoleService roleService;
    @Autowired
    private ResourceService resourceService;

    @Override
    public void checkPermissions(AuthorizationCheckPermissionsDTO checkPermissionsDTO) {
        // 查询管理员拥有的角色关联数据
        List<AccountRoleDO> accountRoleDOs = accountRoleMapper.selectByAccountId(checkPermissionsDTO.getAccountId());
        if (CollectionUtil.isEmpty(accountRoleDOs)) { // 如果没有角色，默认无法访问
            throw ServiceExceptionUtil.exception(AUTHORIZATION_PERMISSION_DENY);
        }
        Set<Integer> roleIds = CollectionUtil.convertSet(accountRoleDOs, AccountRoleDO::getRoleId);
        // 判断是否为超管。若是超管，默认有所有权限
        if (roleService.hasSuperAdmin(roleIds)) {
            return;
        }
        // 查询权限对应资源
        List<ResourceBO> resourceBOs = resourceService.getResourcesByPermissions(checkPermissionsDTO.getPermissions());
        if (CollectionUtil.isEmpty(resourceBOs)) { // 无对应资源，则认为无需权限验证
            log.warn("[checkPermissions][permission({}) 未配置对应资源]", checkPermissionsDTO.getPermissions());
            return;
        }
        Set<Integer> permissionIds = CollectionUtil.convertSet(resourceBOs, ResourceBO::getId);
        // 权限验证
        List<RoleResourceDO> roleResourceDOs = roleResourceMapper.selectListByResourceIds(permissionIds);
        if (CollectionUtil.isEmpty(roleResourceDOs)) { // 资源未授予任何角色，必然权限验证不通过
            throw ServiceExceptionUtil.exception(AUTHORIZATION_PERMISSION_DENY);
        }
        Map<Integer, List<Integer>> resourceRoleMap = CollectionUtil.convertMultiMap(roleResourceDOs,
                RoleResourceDO::getResourceId, RoleResourceDO::getRoleId);
        for (Map.Entry<Integer, List<Integer>> entry : resourceRoleMap.entrySet()) {
            if (!CollectionUtil.containsAny(roleIds, entry.getValue())) { // 所以有任一不满足，就验证失败，抛出异常
                throw ServiceExceptionUtil.exception(AUTHORIZATION_PERMISSION_DENY);
            }
        }
    }

    @Override
    public List<ResourceBO> getResourcesByAccountId(AuthorizationGetResourcesByAccountIdDTO getResourcesByAccountIdDTO) {
        // 查询管理员拥有的角色关联数据
        List<AccountRoleDO> accountRoleDOs = accountRoleMapper.selectByAccountId(getResourcesByAccountIdDTO.getAccountId());
        if (CollectionUtil.isEmpty(accountRoleDOs)) {
            return Collections.emptyList();
        }
        Set<Integer> roleIds = CollectionUtil.convertSet(accountRoleDOs, AccountRoleDO::getRoleId);
        // 判断是否为超管。若是超管，默认有所有权限
        if (roleService.hasSuperAdmin(roleIds)) {
            return resourceService.getResources(new ResourceGetListDTO().setType(getResourcesByAccountIdDTO.getType()));
        }
        // 查询角色拥有的资源关联数据
        List<RoleResourceDO> roleResourceDOs = roleResourceMapper.selectListByRoleIds(roleIds);
        if (CollectionUtil.isEmpty(roleResourceDOs)) {
            return Collections.emptyList();
        }
        Set<Integer> resourceIds = CollectionUtil.convertSet(roleResourceDOs, RoleResourceDO::getResourceId);
        // 查询对应资源列表
        return resourceService.getResources(new ResourceGetListDTO().setIds(resourceIds).setType(getResourcesByAccountIdDTO.getType()));
    }

    @Override
    public List<ResourceTreeNodeBO> getResourceTreeByAccountId(AuthorizationGetResourcesByAccountIdDTO getResourcesByAccountIdDTO) {
        // 查询管理员拥有的角色关联数据
        List<AccountRoleDO> accountRoleDOs = accountRoleMapper.selectByAccountId(getResourcesByAccountIdDTO.getAccountId());
        if (CollectionUtil.isEmpty(accountRoleDOs)) {
            return Collections.emptyList();
        }
        Set<Integer> roleIds = CollectionUtil.convertSet(accountRoleDOs, AccountRoleDO::getRoleId);
        // 判断是否为超管。若是超管，默认有所有权限
        if (roleService.hasSuperAdmin(roleIds)) {
            return resourceService.getResourceTree(new ResourceGetTreeDTO().setType(getResourcesByAccountIdDTO.getType()));
        }
        // 查询角色拥有的资源关联数据
        List<RoleResourceDO> roleResourceDOs = roleResourceMapper.selectListByRoleIds(roleIds);
        if (CollectionUtil.isEmpty(roleResourceDOs)) {
            return Collections.emptyList();
        }
        Set<Integer> resourceIds = CollectionUtil.convertSet(roleResourceDOs, RoleResourceDO::getResourceId);
        // 查询对应资源树
        return resourceService.getResourceTree(new ResourceGetTreeDTO().setIds(resourceIds).setType(getResourcesByAccountIdDTO.getType()));
    }

    @Override
    public Set<Integer> getRoleResources(AuthorizationGetRoleResourcesDTO getRoleResourcesDTO) {
        Set<Integer> roleIds = Collections.singleton(getRoleResourcesDTO.getRoleId());
        // 判断是否为超管。若是超管，默认有所有权限
        if (roleService.hasSuperAdmin(roleIds)) {
            return CollectionUtil.convertSet(resourceService.getResources(new ResourceGetListDTO()), ResourceBO::getId);
        }
        // 查询角色拥有的资源关联数据
        List<RoleResourceDO> roleResourceDOs = roleResourceMapper.selectListByRoleIds(roleIds);
        if (CollectionUtil.isEmpty(roleResourceDOs)) {
            return Collections.emptySet();
        }
        return CollectionUtil.convertSet(roleResourceDOs, RoleResourceDO::getResourceId);
    }

    @EventListener
    public void handleResourceDeleteEvent(ResourceDeleteEvent event) {
        roleResourceMapper.deleteByResourceId(event.getId());
    }

    @EventListener
    public void handleRoleDeleteEvent(ResourceDeleteEvent event) {
        // 标记删除 RoleResource
        roleResourceMapper.deleteByRoleId(event.getId());
        // 标记删除 AdminRole
        accountRoleMapper.deleteByRoleId(event.getId());
    }

}
