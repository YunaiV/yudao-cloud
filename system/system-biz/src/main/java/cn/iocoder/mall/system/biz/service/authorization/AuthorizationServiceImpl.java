package cn.iocoder.mall.system.biz.service.authorization;

import cn.iocoder.common.framework.util.CollectionUtil;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.mall.system.biz.bo.authorization.ResourceBO;
import cn.iocoder.mall.system.biz.dao.authorization.AccountRoleMapper;
import cn.iocoder.mall.system.biz.dao.authorization.RoleResourceMapper;
import cn.iocoder.mall.system.biz.dataobject.authorization.AccountRoleDO;
import cn.iocoder.mall.system.biz.dataobject.authorization.RoleResourceDO;
import cn.iocoder.mall.system.biz.dto.authorization.AuthorizationCheckPermissionsDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static cn.iocoder.mall.system.biz.enums.SystemErrorCodeEnum.AUTHORIZATION_PERMISSION_DENY;

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
        List<ResourceBO> resourceBOs = resourceService.getListByPermissions(checkPermissionsDTO.getPermissions());
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

}
