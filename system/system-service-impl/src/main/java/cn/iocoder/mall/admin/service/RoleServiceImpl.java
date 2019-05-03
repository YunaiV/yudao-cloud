package cn.iocoder.mall.admin.service;

import cn.iocoder.common.framework.constant.DeletedStatusEnum;
import cn.iocoder.common.framework.util.CollectionUtil;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.api.RoleService;
import cn.iocoder.mall.admin.api.bo.RoleBO;
import cn.iocoder.mall.admin.api.bo.RolePageBO;
import cn.iocoder.mall.admin.api.constant.AdminErrorCodeEnum;
import cn.iocoder.mall.admin.api.dto.RoleAddDTO;
import cn.iocoder.mall.admin.api.dto.RolePageDTO;
import cn.iocoder.mall.admin.api.dto.RoleUpdateDTO;
import cn.iocoder.mall.admin.convert.RoleConvert;
import cn.iocoder.mall.admin.dao.AdminRoleMapper;
import cn.iocoder.mall.admin.dao.RoleMapper;
import cn.iocoder.mall.admin.dao.RoleResourceMapper;
import cn.iocoder.mall.admin.dataobject.AdminRoleDO;
import cn.iocoder.mall.admin.dataobject.ResourceDO;
import cn.iocoder.mall.admin.dataobject.RoleDO;
import cn.iocoder.mall.admin.dataobject.RoleResourceDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@org.apache.dubbo.config.annotation.Service(validation = "true")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleResourceMapper roleResourceMapper;
    @Autowired
    private AdminRoleMapper adminRoleMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private ResourceServiceImpl resourceService;

    public List<RoleResourceDO> getRoleByResourceHandler(String resourceHandler) {
        return roleResourceMapper.selectByResourceHandler(resourceHandler);
    }

    public List<RoleResourceDO> getRoleByResourceId(Integer resourceId) {
        return roleResourceMapper.selectByResourceId(resourceId);
    }

    @Override
    public CommonResult<RolePageBO> getRolePage(RolePageDTO rolePageDTO) {
        RolePageBO rolePage = new RolePageBO();
        // 查询分页数据
        int offset = rolePageDTO.getPageNo() * rolePageDTO.getPageSize();
        rolePage.setRoles(RoleConvert.INSTANCE.convert(roleMapper.selectListByNameLike(rolePageDTO.getName(),
                offset, rolePageDTO.getPageSize())));
        // 查询分页总数
        rolePage.setCount(roleMapper.selectCountByNameLike(rolePageDTO.getName()));
        return CommonResult.success(rolePage);
    }

    @Override
    public CommonResult<Set<Integer>> getRoleList(Integer adminId) {
        List<AdminRoleDO> adminRoleDOs = adminRoleMapper.selectByAdminId(adminId);
        return CommonResult.success(adminRoleDOs.stream().map(AdminRoleDO::getRoleId).collect(Collectors.toSet()));
    }

    @Override
    public CommonResult<List<RoleBO>> getRoleList() {
        List<RoleDO> roleList = roleMapper.selectList();
        return CommonResult.success(RoleConvert.INSTANCE.convert(roleList));
    }

    @Override
    public CommonResult<RoleBO> addRole(Integer adminId, RoleAddDTO roleAddDTO) {
        // TODO 芋艿，角色名是否要唯一呢？貌似一般系统都是允许的。
        // 保存到数据库
        RoleDO role = RoleConvert.INSTANCE.convert(roleAddDTO);
        role.setCreateTime(new Date());
        role.setDeleted(DeletedStatusEnum.DELETED_NO.getValue());
        roleMapper.insert(role);
        // TODO 插入操作日志
        // 返回成功
        return CommonResult.success(RoleConvert.INSTANCE.convert(role));
    }

    @Override
    public CommonResult<Boolean> updateRole(Integer adminId, RoleUpdateDTO roleUpdateDTO) {
        // TODO 芋艿，角色名是否要唯一呢？貌似一般系统都是允许的。
        // 校验角色是否存在
        if (roleMapper.selectById(roleUpdateDTO.getId()) == null) {
            return ServiceExceptionUtil.error(AdminErrorCodeEnum.RESOURCE_NOT_EXISTS.getCode());
        }
        // 更新到数据库
        RoleDO roleDO = RoleConvert.INSTANCE.convert(roleUpdateDTO);
        roleMapper.update(roleDO);
        // TODO 插入操作日志
        // 返回成功
        return CommonResult.success(true);
    }

    @Override
    @Transactional
    public CommonResult<Boolean> deleteRole(Integer adminId, Integer roleId) {
        // 校验角色是否存在
        if (roleMapper.selectById(roleId) == null) {
            return ServiceExceptionUtil.error(AdminErrorCodeEnum.RESOURCE_NOT_EXISTS.getCode());
        }
        // 更新到数据库，标记删除
        RoleDO roleDO = new RoleDO().setId(roleId);
        roleDO.setDeleted(DeletedStatusEnum.DELETED_YES.getValue());
        roleMapper.update(roleDO);
        // 标记删除 RoleResource
        roleResourceMapper.updateToDeletedByRoleId(roleId);
        // 标记删除 AdminRole
        adminRoleMapper.updateToDeletedByRoleId(roleId);
        // TODO 插入操作日志
        // 返回成功
        return CommonResult.success(true);
    }

    @Override
    @Transactional
    public CommonResult<Boolean> assignResource(Integer adminId, Integer roleId, Set<Integer> resourceIds) {
        // 校验角色是否存在
        if (roleMapper.selectById(roleId) == null) {
            return ServiceExceptionUtil.error(AdminErrorCodeEnum.RESOURCE_NOT_EXISTS.getCode());
        }
        // 校验是否有不存在的资源
        List<ResourceDO> resources = resourceService.getResources(resourceIds);
        if (resources.size() != resourceIds.size()) {
            return ServiceExceptionUtil.error(AdminErrorCodeEnum.ROLE_ASSIGN_RESOURCE_NOT_EXISTS.getCode());
        }
        // TODO 芋艿，这里先简单实现。即方式是，删除老的分配的资源关系，然后添加新的分配的资源关系
        // 标记角色原资源关系都为删除
        roleResourceMapper.updateToDeletedByRoleId(roleId);
        // 创建 RoleResourceDO 数组，并插入到数据库
        if (!resourceIds.isEmpty()) {
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
        return CommonResult.success(true);
    }

    public List<RoleDO> getRoles(Set<Integer> roleIds) {
        if (CollectionUtil.isEmpty(roleIds)) {
            return Collections.emptyList();
        }
        return roleMapper.selectListByIds(roleIds);
    }

}
