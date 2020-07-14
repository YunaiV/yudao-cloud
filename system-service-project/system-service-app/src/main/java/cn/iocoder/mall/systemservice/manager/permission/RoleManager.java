package cn.iocoder.mall.systemservice.manager.permission;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.systemservice.convert.permission.RoleConvert;
import cn.iocoder.mall.systemservice.rpc.permission.dto.RolePageDTO;
import cn.iocoder.mall.systemservice.rpc.permission.dto.RoleUpdateDTO;
import cn.iocoder.mall.systemservice.rpc.permission.vo.RoleCreateDTO;
import cn.iocoder.mall.systemservice.rpc.permission.vo.RoleVO;
import cn.iocoder.mall.systemservice.service.permission.RoleService;
import cn.iocoder.mall.systemservice.service.permission.bo.RoleBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
* 角色 Manager
*/
@Service
public class RoleManager {

    @Autowired
    private RoleService roleService;

    /**
    * 创建角色
    *
    * @param createDTO 创建角色 DTO
    * @return 角色
    */
    public Integer createRole(RoleCreateDTO createDTO) {
        RoleBO roleBO = roleService.createRole(RoleConvert.INSTANCE.convert(createDTO));
        return roleBO.getId();
    }

    /**
    * 更新角色
    *
    * @param updateDTO 更新角色 DTO
    */
    public void updateRole(RoleUpdateDTO updateDTO) {
        roleService.updateRole(RoleConvert.INSTANCE.convert(updateDTO));
    }

    /**
    * 删除角色
    *
    * @param roleId 角色编号
    */
    public void deleteRole(Integer roleId) {
        roleService.deleteRole(roleId);
    }

    /**
    * 获得角色
    *
    * @param roleId 角色编号
    * @return 角色
    */
    public RoleVO getRole(Integer roleId) {
        RoleBO roleBO = roleService.getRole(roleId);
        return RoleConvert.INSTANCE.convert(roleBO);
    }

    /**
     * 获得所有角色
     *
     * @return 角色列表
     */
    public List<RoleVO> listAllRoles() {
        List<RoleBO> roleBOs = roleService.listAllRole();
        return RoleConvert.INSTANCE.convertList02(roleBOs);
    }

    /**
    * 获得角色列表
    *
    * @param roleIds 角色编号列表
    * @return 角色列表
    */
    public List<RoleVO> listRoles(Collection<Integer> roleIds) {
        List<RoleBO> roleBOs = roleService.listRole(roleIds);
        return RoleConvert.INSTANCE.convertList02(roleBOs);
    }

    /**
     * 获得角色分页
     *
     * @param pageDTO 角色分页查询
     * @return 角色分页结果
     */
    public PageResult<RoleVO> pageRole(RolePageDTO pageDTO) {
        PageResult<RoleBO> pageResultBO = roleService.pageRole(RoleConvert.INSTANCE.convert(pageDTO));
        return RoleConvert.INSTANCE.convertPage(pageResultBO);
    }

    /**
     * 获得管理员拥有的角色编号列表
     *
     * @param adminId 管理员编号
     * @return 角色编号列表
     */
    public Set<Integer> listAdminRoleIds(Integer adminId) {
        return roleService.listAdminRoleIds(adminId);
    }

}
