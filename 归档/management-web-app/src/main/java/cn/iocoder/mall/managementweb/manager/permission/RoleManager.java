package cn.iocoder.mall.managementweb.manager.permission;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.permission.dto.RoleCreateDTO;
import cn.iocoder.mall.managementweb.controller.permission.dto.RolePageDTO;
import cn.iocoder.mall.managementweb.controller.permission.dto.RoleUpdateDTO;
import cn.iocoder.mall.managementweb.controller.permission.vo.RoleVO;
import cn.iocoder.mall.managementweb.convert.permission.RoleConvert;
import cn.iocoder.mall.systemservice.rpc.permission.RoleFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 角色 Manager
*/
@Service
public class RoleManager {


    @Autowired
    private RoleFeign roleFeign;
    /**
    * 创建角色
    *
    * @param createDTO 创建角色 DTO
    * @return 角色
    */
    public Integer createRole(RoleCreateDTO createDTO, Integer createAdminId) {
        CommonResult<Integer> createRoleResult = roleFeign.createRole(RoleConvert.INSTANCE.convert(createDTO).setCreateAdminId(createAdminId));
        createRoleResult.checkError();
        return createRoleResult.getData();
    }

    /**
    * 更新角色
    *
    * @param updateDTO 更新角色 DTO
    */
    public void updateRole(RoleUpdateDTO updateDTO) {
        CommonResult<Boolean> updateRoleResult = roleFeign.updateRole(RoleConvert.INSTANCE.convert(updateDTO));
        updateRoleResult.checkError();
    }

    /**
    * 删除角色
    *
    * @param roleId 角色编号
    */
    public void deleteRole(Integer roleId) {
        CommonResult<Boolean> deleteRoleResult = roleFeign.deleteRole(roleId);
        deleteRoleResult.checkError();
    }

    /**
    * 获得角色
    *
    * @param roleId 角色编号
    * @return 角色
    */
    public RoleVO getRole(Integer roleId) {
        CommonResult<cn.iocoder.mall.systemservice.rpc.permission.vo.RoleVO> getRoleResult = roleFeign.getRole(roleId);
        getRoleResult.checkError();
        return RoleConvert.INSTANCE.convert(getRoleResult.getData());
    }

    /**
     * 获得所有角色
     *
     * @return 角色列表
     */
    public List<RoleVO> listAllRoles() {
        CommonResult<List<cn.iocoder.mall.systemservice.rpc.permission.vo.RoleVO>> listRoleResult = roleFeign.listAllRoles();
        listRoleResult.checkError();
        return RoleConvert.INSTANCE.convertList(listRoleResult.getData());
    }

    /**
    * 获得角色列表
    *
    * @param roleIds 角色编号列表
    * @return 角色列表
    */
    public List<RoleVO> listRoles(List<Integer> roleIds) {
        CommonResult<List<cn.iocoder.mall.systemservice.rpc.permission.vo.RoleVO>> listRoleResult = roleFeign.listRoles(roleIds);
        listRoleResult.checkError();
        return RoleConvert.INSTANCE.convertList(listRoleResult.getData());
    }

    /**
    * 获得角色分页
    *
    * @param pageDTO 角色编号列表
    * @return 角色列表
    */
    public PageResult<RoleVO> pageRole(RolePageDTO pageDTO) {
        CommonResult<PageResult<cn.iocoder.mall.systemservice.rpc.permission.vo.RoleVO>> pageRoleResult =
                roleFeign.pageRole(RoleConvert.INSTANCE.convert(pageDTO));
        pageRoleResult.checkError();
        return RoleConvert.INSTANCE.convertPage(pageRoleResult.getData());
    }

}
