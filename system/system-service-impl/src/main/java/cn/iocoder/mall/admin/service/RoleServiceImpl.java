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
import cn.iocoder.mall.system.api.dto.role.RoleUpdateDTO;
import cn.iocoder.mall.admin.convert.RoleConvert;
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
    private ResourceServiceImpl resourceService;

    public List<RoleResourceDO> getRoleByResourceId(Integer resourceId) {
        return roleResourceMapper.selectListByResourceId(resourceId);
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

}
