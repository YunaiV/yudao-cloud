package cn.iocoder.mall.system.biz.service.authorization;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.system.biz.bo.authorization.RoleBO;
import cn.iocoder.mall.system.biz.dto.authorization.RoleAddDTO;
import cn.iocoder.mall.system.biz.dto.authorization.RoleDeleteDTO;
import cn.iocoder.mall.system.biz.dto.authorization.RolePageDTO;
import cn.iocoder.mall.system.biz.dto.authorization.RoleUpdateDTO;
import cn.iocoder.mall.system.biz.dto.authorization.RoleGetListDTO;

import java.util.Collection;
import java.util.List;

/**
 * 角色模块 - Service 接口
 */
public interface RoleService {

    RoleBO getRole(Integer id);

    List<RoleBO> getRoleList(RoleGetListDTO getListDTO);

    PageResult<RoleBO> getRolePage(RolePageDTO pageDTO);

    /**
     * 判断指定角色是否包含超级管理员角色
     *
     * @param ids 角色编号数组
     * @return 是否有超级管理员角色
     */
    boolean hasSuperAdmin(Collection<Integer> ids);

    Integer addRole(RoleAddDTO roleAddDTO);

    void updateRole(RoleUpdateDTO roleUpdateDTO);

    void deleteRole(RoleDeleteDTO roleDeleteDTO);

}
