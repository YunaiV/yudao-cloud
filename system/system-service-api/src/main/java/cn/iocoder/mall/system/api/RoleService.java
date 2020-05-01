package cn.iocoder.mall.system.api;

import cn.iocoder.mall.system.api.bo.role.RoleBO;

import java.util.List;

public interface RoleService {

    /**
     * @return 返回角色列表
     */
    List<RoleBO> getRoleList();

}
