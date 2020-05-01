package cn.iocoder.mall.system.api;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.system.api.bo.admin.AdminBO;
import cn.iocoder.mall.system.api.bo.role.RoleBO;
import cn.iocoder.mall.system.api.dto.admin.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 管理员 Service 接口
 */
public interface AdminService {

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

}
