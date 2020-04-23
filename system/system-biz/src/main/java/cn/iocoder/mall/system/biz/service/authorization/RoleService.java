package cn.iocoder.mall.system.biz.service.authorization;

import cn.iocoder.mall.system.biz.bo.authorization.RoleBO;

import java.util.Collection;
import java.util.List;

public interface RoleService {

    List<RoleBO> getRoleList(Collection<Integer> ids);

    /**
     * 判断指定角色是否包含超级管理员角色
     *
     * @param ids 角色编号数组
     * @return 是否有超级管理员角色
     */
    boolean hasSuperAdmin(Collection<Integer> ids);

}
