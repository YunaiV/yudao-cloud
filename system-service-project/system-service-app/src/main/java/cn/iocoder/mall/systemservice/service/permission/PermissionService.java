package cn.iocoder.mall.systemservice.service.permission;

import cn.iocoder.common.framework.util.CollectionUtils;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.mall.mybatis.enums.DeletedStatusEnum;
import cn.iocoder.mall.systemservice.dal.mysql.dataobject.permission.RoleResourceDO;
import cn.iocoder.mall.systemservice.dal.mysql.mapper.permission.AdminRoleMapper;
import cn.iocoder.mall.systemservice.dal.mysql.mapper.permission.ResourceMapper;
import cn.iocoder.mall.systemservice.dal.mysql.mapper.permission.RoleMapper;
import cn.iocoder.mall.systemservice.dal.mysql.mapper.permission.RoleResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static cn.iocoder.mall.systemservice.enums.SystemErrorCodeEnum.AUTHORIZATION_ROLE_ASSIGN_RESOURCE_NOT_EXISTS;
import static cn.iocoder.mall.systemservice.enums.SystemErrorCodeEnum.ROLE_NOT_EXISTS;

/**
 * 权限 Service
 */
@Service
public class PermissionService {

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
    public Set<Integer> listRoleResourceId(Integer roleId) {
        List<RoleResourceDO> roleResourceDOs = roleResourceMapper.selectListByRoleId(roleId);
        return CollectionUtils.convertSet(roleResourceDOs, RoleResourceDO::getResourceId);
    }

    /**
     * 赋予角色资源
     *
     * @param roleId 角色编号
     * @param resourceIds 资源编号列表
     */
    public void assignRoleResource(Integer roleId, Set<Integer> resourceIds) {
        // 校验角色是否存在
        if (roleMapper.selectById(roleId) == null) {
            throw ServiceExceptionUtil.exception(ROLE_NOT_EXISTS);
        }
        // 校验是否有不存在的资源
        if (!CollectionUtils.isEmpty(resourceIds)) {
            int dbResourceSize = resourceMapper.selectCountByIdsAndType(resourceIds, null);
            if (resourceIds.size() != dbResourceSize) {
                throw ServiceExceptionUtil.exception(AUTHORIZATION_ROLE_ASSIGN_RESOURCE_NOT_EXISTS);
            }
        }
        // TODO 芋艿，这里先简单实现。即方式是，删除老的分配的资源关系，然后添加新的分配的资源关系
        // 标记角色原资源关系都为删除
        roleResourceMapper.deleteByRoleId(roleId);
        // 创建 RoleResourceDO 数组，并插入到数据库
        if (!CollectionUtils.isEmpty(resourceIds)) {
            List<RoleResourceDO> roleResources = resourceIds.stream().map(resourceId -> {
                RoleResourceDO roleResource = new RoleResourceDO().setRoleId(roleId).setResourceId(resourceId);
                roleResource.setCreateTime(new Date());
                roleResource.setDeleted(DeletedStatusEnum.DELETED_NO.getValue());
                return roleResource;
            }).collect(Collectors.toList());
            roleResourceMapper.insertList(roleResources);
        }
    }

}
