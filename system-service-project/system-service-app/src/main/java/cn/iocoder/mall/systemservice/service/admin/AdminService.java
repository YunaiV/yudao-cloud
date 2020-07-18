package cn.iocoder.mall.systemservice.service.admin;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.util.DigestUtils;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.util.StringUtils;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.systemservice.convert.admin.AdminConvert;
import cn.iocoder.mall.systemservice.dal.mysql.dataobject.admin.AdminDO;
import cn.iocoder.mall.systemservice.dal.mysql.mapper.admin.AdminMapper;
import cn.iocoder.mall.systemservice.enums.admin.AdminStatusEnum;
import cn.iocoder.mall.systemservice.enums.admin.AdminUsernameEnum;
import cn.iocoder.mall.systemservice.service.admin.bo.AdminBO;
import cn.iocoder.mall.systemservice.service.admin.bo.AdminCreateBO;
import cn.iocoder.mall.systemservice.service.admin.bo.AdminPageBO;
import cn.iocoder.mall.systemservice.service.admin.bo.AdminUpdateBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static cn.iocoder.mall.systemservice.enums.SystemErrorCodeConstants.*;

@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;

    /**
     * 校验登陆的账号密码是否正确
     *
     * @param username 账号
     * @param password 密码
     * @param ip 登陆 IP
     * @return 管理员信息
     */
    public AdminBO verifyPassword(String username, String password, String ip) {
        AdminDO adminDO = adminMapper.selectByUsername(username);
        if (adminDO == null) {
            throw ServiceExceptionUtil.exception(ADMIN_USERNAME_NOT_EXISTS);
        }
        // 校验密码是否正确
        String encodedPassword = DigestUtils.bcrypt(password, adminDO.getPasswordSalt());
        if (!encodedPassword.equals(adminDO.getPassword())) {
            // TODO 需要补充密码错误上限
            throw ServiceExceptionUtil.exception(ADMIN_PASSWORD_ERROR);
        }
        // 账号被禁用
        if (!AdminStatusEnum.ACTIVE.getStatus().equals(adminDO.getStatus())) {
            throw ServiceExceptionUtil.exception(ADMIN_IS_DISABLE);
        }
        // 返回
        return AdminConvert.INSTANCE.convert(adminDO);
    }

    public PageResult<AdminBO> pageAdmin(AdminPageBO adminPageBO) {
        return AdminConvert.INSTANCE.convertPage(adminMapper.selectPage(adminPageBO));
    }

    public AdminBO createAdmin(AdminCreateBO createBO) {
        // 校验账号唯一
        if (adminMapper.selectByUsername(createBO.getUsername()) != null) {
            throw ServiceExceptionUtil.exception(ADMIN_USERNAME_EXISTS);
        }
        // 加密密码
        String passwordSalt = genPasswordSalt();
        String password = encodePassword(createBO.getPassword(), passwordSalt);
        // 保存到数据库
        AdminDO admin = AdminConvert.INSTANCE.convert(createBO)
                .setPassword(password).setPasswordSalt(passwordSalt)
                .setStatus(CommonStatusEnum.ENABLE.getValue());
        adminMapper.insert(admin);
        // 返回成功
        return AdminConvert.INSTANCE.convert(admin);
    }

    private String genPasswordSalt() {
        return DigestUtils.genBcryptSalt();
    }

    private String encodePassword(String password, String salt) {
        return DigestUtils.bcrypt(password, salt);
    }

    public void updateAdmin(AdminUpdateBO updateDTO) {
        // 校验账号存在
        AdminDO admin = adminMapper.selectById(updateDTO.getId());
        if (admin == null) {
            throw ServiceExceptionUtil.exception(ADMIN_NOT_FOUND);
        }
        // 校验是否为特殊账号，不允许编辑
        if (AdminUsernameEnum.ADMIN.getUsername().equals(admin.getUsername())
                || AdminUsernameEnum.DEMO.getUsername().equals(admin.getUsername())) {
            throw ServiceExceptionUtil.exception(ADMIN_ADMIN_CAN_NOT_UPDATE);
        }
        // 校验账号唯一
        if (StringUtils.hasText(updateDTO.getUsername())) {
            AdminDO usernameAdmin = adminMapper.selectByUsername(updateDTO.getUsername());
            if (usernameAdmin != null && !usernameAdmin.getId().equals(updateDTO.getId())) {
                throw ServiceExceptionUtil.exception(ADMIN_USERNAME_EXISTS);
            }
        }
        // 如果有更新状态，则校验是否已经是该状态
        if (updateDTO.getStatus() != null && updateDTO.getStatus().equals(admin.getStatus())) {
            throw ServiceExceptionUtil.exception(ADMIN_STATUS_EQUALS);
        }
        // 更新到数据库
        AdminDO updateAdmin = AdminConvert.INSTANCE.convert(updateDTO);
        // 如果更新密码，需要特殊加密
        if (StringUtils.hasText(updateDTO.getPassword())) {
            String passwordSalt = genPasswordSalt();
            String password = encodePassword(updateDTO.getPassword(), passwordSalt);
            updateAdmin.setPassword(password).setPasswordSalt(passwordSalt);
        }
        adminMapper.updateById(updateAdmin);
    }

    public AdminBO getAdmin(Integer adminId) {
        AdminDO adminDO = adminMapper.selectById(adminId);
        return AdminConvert.INSTANCE.convert(adminDO);
    }

//
//    @Override
//    public Map<Integer, Collection<RoleBO>> getAdminRolesMap(Collection<Integer> adminIds) {
//        // 查询管理员拥有的角色关联数据
//        List<AdminRoleDO> adminRoleList = adminRoleMapper.selectListByAdminIds(adminIds);
//        if (adminRoleList.isEmpty()) {
//            return Collections.emptyMap();
//        }
//        // 查询角色数据
//        List<RoleBO> roleList = roleService.getRoleList(CollectionUtil.convertSet(adminRoleList, AdminRoleDO::getRoleId));
//        Map<Integer, RoleBO> roleMap = CollectionUtil.convertMap(roleList, RoleBO::getId);
//        // 拼接数据
//        Multimap<Integer, RoleBO> result = ArrayListMultimap.create();
//        adminRoleList.forEach(adminRole -> result.put(adminRole.getAdminId(), roleMap.get(adminRole.getRoleId())));
//        return result.asMap();
//    }
//
//    @Override
//    public List<RoleBO> getRoleList(Integer adminId) {
//        // 查询管理员拥有的角色关联数据
//        List<AdminRoleDO> adminRoleList = adminRoleMapper.selectByAdminId(adminId);
//        if (adminRoleList.isEmpty()) {
//            return Collections.emptyList();
//        }
//        // 查询角色数据
//        return roleService.getRoleList(CollectionUtil.convertSet(adminRoleList, AdminRoleDO::getRoleId));
//    }
//
//    @Override
//    @Transactional
//    public Boolean assignAdminRole(Integer adminId, AdminAssignRoleDTO adminAssignRoleDTO) {
//        // 校验账号存在
//        AdminDO admin = adminMapper.selectById(adminAssignRoleDTO.getId());
//        if (admin == null) {
//            throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.ADMIN_USERNAME_NOT_REGISTERED.getCode());
//        }
//        // 校验是否有不存在的角色
//        if (!CollectionUtil.isEmpty(adminAssignRoleDTO.getRoleIds())) {
//            List<RoleDO> roles = roleService.getRoles(adminAssignRoleDTO.getRoleIds());
//            if (roles.size() != adminAssignRoleDTO.getRoleIds().size()) {
//                throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.ADMIN_ASSIGN_ROLE_NOT_EXISTS.getCode());
//            }
//        }
//        // TODO 芋艿，这里先简单实现。即方式是，删除老的分配的角色关系，然后添加新的分配的角色关系
//        // 标记管理员角色源关系都为删除
//        adminRoleMapper.deleteByAdminId(adminAssignRoleDTO.getId());
//        // 创建 RoleResourceDO 数组，并插入到数据库
//        if (!CollectionUtil.isEmpty(adminAssignRoleDTO.getRoleIds())) {
//            List<AdminRoleDO> adminRoleDOs = adminAssignRoleDTO.getRoleIds().stream().map(roleId -> {
//                AdminRoleDO roleResource = new AdminRoleDO().setAdminId(adminAssignRoleDTO.getId()).setRoleId(roleId);
//                roleResource.setCreateTime(new Date());
//                roleResource.setDeleted(DeletedStatusEnum.DELETED_NO.getValue());
//                return roleResource;
//            }).collect(Collectors.toList());
//            adminRoleMapper.insertList(adminRoleDOs);
//        }
//        // TODO 插入操作日志
//        // 返回成功
//        return true;
//    }

}
