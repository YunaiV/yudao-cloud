package cn.iocoder.mall.system.biz.service.authorization;

import cn.iocoder.common.framework.exception.ServiceException;
import cn.iocoder.mall.system.biz.bo.authorization.ResourceBO;
import cn.iocoder.mall.system.biz.bo.authorization.ResourceTreeNodeBO;
import cn.iocoder.mall.system.biz.dto.authorization.AuthorizationCheckPermissionsDTO;
import cn.iocoder.mall.system.biz.dto.authorization.AuthorizationGetResourcesByAccountIdDTO;
import cn.iocoder.mall.system.biz.dto.authorization.AuthorizationGetRoleResourcesDTO;

import java.util.List;
import java.util.Set;

/**
 * 授权模块 - Service 接口
 */
public interface AuthorizationService {

    /**
     * 校验指定账号是否有指定权限。如果没有，则抛出 {@link ServiceException} 异常
     *
     * @param checkPermissionsDTO 校验权限 DTO
     */
    void checkPermissions(AuthorizationCheckPermissionsDTO checkPermissionsDTO);

    /**
     * 获得指定账号的资源列表
     *
     * 如果该账号为超级管理员，则返回所有资源
     *
     * @param getResourcesByAccountIdDTO 查询条件 DTO
     * @return 资源列表
     */
    List<ResourceBO> getResourcesByAccountId(AuthorizationGetResourcesByAccountIdDTO getResourcesByAccountIdDTO);

    /**
     * 获得指定账号的资源树
     *
     * 如果该账号为超级管理员，则返回所有资源
     *
     * @param getResourceTreeByAccountIdDTO 查询条件 DTO
     * @return 资源树
     */
    List<ResourceTreeNodeBO> getResourceTreeByAccountId(AuthorizationGetResourcesByAccountIdDTO getResourceTreeByAccountIdDTO);

    /**
     * 获得指定角色拥有的资源编号集合
     *
     * @param getRoleResourcesDTO 查询条件 DTO
     * @return 资源编号数集合
     */
    Set<Integer> getRoleResources(AuthorizationGetRoleResourcesDTO getRoleResourcesDTO);

}
