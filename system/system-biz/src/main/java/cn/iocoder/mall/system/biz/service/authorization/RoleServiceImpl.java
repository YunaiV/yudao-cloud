package cn.iocoder.mall.system.biz.service.authorization;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.util.StringUtil;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.mybatis.enums.DeletedStatusEnum;
import cn.iocoder.mall.system.biz.bo.authorization.RoleBO;
import cn.iocoder.mall.system.biz.convert.authorization.RoleConvert;
import cn.iocoder.mall.system.biz.dao.authorization.RoleMapper;
import cn.iocoder.mall.system.biz.dataobject.authorization.RoleDO;
import cn.iocoder.mall.system.biz.dto.authorization.RoleAddDTO;
import cn.iocoder.mall.system.biz.dto.authorization.RoleDeleteDTO;
import cn.iocoder.mall.system.biz.dto.authorization.RolePageDTO;
import cn.iocoder.mall.system.biz.dto.authorization.RoleUpdateDTO;
import cn.iocoder.mall.system.biz.enums.SystemErrorCodeEnum;
import cn.iocoder.mall.system.biz.enums.authorization.RoleCodeEnum;
import cn.iocoder.mall.system.biz.event.authorization.ResourceDeleteEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public RoleBO getRole(Integer id) {
        return RoleConvert.INSTANCE.convert(roleMapper.selectById(id));
    }

    @Override
    public List<RoleBO> getRoleList(Collection<Integer> ids) {
        return RoleConvert.INSTANCE.convertList(roleMapper.selectBatchIds(ids));
    }

    @Override
    public PageResult<RoleBO> getRolePage(RolePageDTO pageDTO) {
        return RoleConvert.INSTANCE.convertPage(roleMapper.selectPage(pageDTO));
    }

    @Override
    public boolean hasSuperAdmin(Collection<Integer> ids) {
        List<RoleDO> roleDOs = roleMapper.selectBatchIds(ids);
        for (RoleDO roleDO : roleDOs) {
            if (RoleCodeEnum.SUPER_ADMIN.getCode().equals(roleDO.getCode())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer addRole(RoleAddDTO roleAddDTO) {
        // 校验角色
        checkRole(roleAddDTO.getName(), roleAddDTO.getCode(), null);
        // 保存到数据库
        RoleDO role = RoleConvert.INSTANCE.convert(roleAddDTO);
        role.setCreateTime(new Date());
        role.setDeleted(DeletedStatusEnum.DELETED_NO.getValue());
        roleMapper.insert(role);
        // TODO 插入操作日志
        // 返回成功
        return role.getId();
    }

    @Override
    public void updateRole(RoleUpdateDTO roleUpdateDTO) {
        // 校验角色是否存在
        if (roleMapper.selectById(roleUpdateDTO.getId()) == null) {
            throw ServiceExceptionUtil.exception(SystemErrorCodeEnum.ROLE_NOT_EXISTS);
        }
        // 校验角色
        checkRole(roleUpdateDTO.getName(), roleUpdateDTO.getCode(), roleUpdateDTO.getId());
        // 更新到数据库
        RoleDO roleDO = RoleConvert.INSTANCE.convert(roleUpdateDTO);
        roleMapper.updateById(roleDO);
        // TODO 插入操作日志
    }

    @Override
    @Transactional
    public void deleteRole(RoleDeleteDTO roleDeleteDTO) {
        // 校验角色是否存在
        if (roleMapper.selectById(roleDeleteDTO.getId()) == null) {
            throw ServiceExceptionUtil.exception(SystemErrorCodeEnum.ROLE_NOT_EXISTS);
        }
        // 更新到数据库，标记删除
        roleMapper.deleteById(roleDeleteDTO.getId());
        // 发布角色删除事件，方便清理关联表
        eventPublisher.publishEvent(new ResourceDeleteEvent(this, roleDeleteDTO.getId()));
        // TODO 插入操作日志
    }

    /**
     * 校验角色是否合法
     *
     * 1. 是否存在相同名字的角色
     * 2. 是否存在相同编码的角色
     *
     * @param name 角色名字
     * @param code 角色额编码
     * @param id 角色编号
     */
    private void checkRole(String name, String code, Integer id) {
        // 1. 是否存在相同名字的角色
        RoleDO role = roleMapper.selectByName(name);
        if (role != null) {
            // 如果 id 为空，说明不用比较是否为相同 id 的资源
            if (id == null) {
                throw ServiceExceptionUtil.exception(SystemErrorCodeEnum.ROLE_NAME_DUPLICATE, name);
            }
            if (!role.getId().equals(id)) {
                throw ServiceExceptionUtil.exception(SystemErrorCodeEnum.ROLE_NAME_DUPLICATE, name);
            }
        }
        // 2. 是否存在相同编码的角色
        if (!StringUtil.hasText(code)) {
            return;
        }
        role = roleMapper.selectByCode(code);
        if (role != null) {
            // 如果 id 为空，说明不用比较是否为相同 id 的资源
            if (id == null) {
                throw ServiceExceptionUtil.exception(SystemErrorCodeEnum.ROLE_CODE_DUPLICATE, name);
            }
            if (!role.getId().equals(id)) {
                throw ServiceExceptionUtil.exception(SystemErrorCodeEnum.ROLE_CODE_DUPLICATE, name);
            }
        }
    }

}
