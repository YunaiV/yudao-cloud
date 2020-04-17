package cn.iocoder.mall.system.api;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.system.api.bo.admin.AdminAuthenticationBO;
import cn.iocoder.mall.system.api.bo.admin.AdminAuthorizationBO;
import cn.iocoder.mall.system.api.bo.admin.AdminBO;
import cn.iocoder.mall.system.api.bo.role.RoleBO;
import cn.iocoder.mall.admin.api.dto.admin.*;
import cn.iocoder.mall.system.api.dto.admin.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 管理员 Service 接口
 */
public interface AdminService {

    /**
     * 管理员认证。认证成功后，返回认证信息
     *
     * 实际上，就是用户名 + 密码登陆
     *
     * @param adminAuthenticationDTO 用户认证信息
     * @return 认证信息
     */
    AdminAuthenticationBO authentication(AdminAuthenticationDTO adminAuthenticationDTO);

    PageResult<AdminBO> getAdminPage(AdminPageDTO adminPageDTO);

    AdminBO addAdmin(Integer adminId, AdminAddDTO adminAddDTO);

    Boolean updateAdmin(Integer adminId, AdminUpdateDTO adminUpdateDTO);

    Boolean updateAdminStatus(Integer adminId, AdminUpdateStatusDTO adminUpdateStatusDTO);

    Boolean deleteAdmin(Integer adminId, Integer updateAdminId);

    /**
     * 批量查询每个管理员拥有的角色
     *
     * @param adminIds 管理员编号数组
     * @return 每个管理员拥有的角色
     */
    Map<Integer, Collection<RoleBO>> getAdminRolesMap(Collection<Integer> adminIds);

    /**
     * 获得指定管理员拥有的角色数组
     *
     * @param adminId 指定管理员
     * @return 角色编号数组
     */
    List<RoleBO> getRoleList(Integer adminId);

    /**
     * 分配管理员角色
     *
     * @param adminId 操作管理员编号
     * @param adminAssignRoleDTO 分配信息
     * @return 是否成功。目前，默认返回 true
     */
    Boolean assignAdminRole(Integer adminId, AdminAssignRoleDTO adminAssignRoleDTO);

    /**
     * 判断管理员是否有指定权限
     *
     * @param adminId 管理员
     * @param permissions 权限数组
     * @return 管理员授权信息
     */
    AdminAuthorizationBO checkPermissions(Integer adminId, List<String> permissions);

}
