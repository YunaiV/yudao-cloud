package cn.iocoder.mall.system.api;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.system.api.bo.role.RoleBO;
import cn.iocoder.mall.system.api.dto.role.RoleAddDTO;
import cn.iocoder.mall.system.api.dto.role.RoleAssignResourceDTO;
import cn.iocoder.mall.system.api.dto.role.RolePageDTO;
import cn.iocoder.mall.system.api.dto.role.RoleUpdateDTO;

import java.util.Collection;
import java.util.List;

public interface RoleService {

    PageResult<RoleBO> getRolePage(RolePageDTO rolePageDTO);

    /**
     * @return 返回角色列表
     */
    List<RoleBO> getRoleList();

    Boolean assignRoleResource(Integer adminId, RoleAssignResourceDTO roleAssignResourceDTO);

}
