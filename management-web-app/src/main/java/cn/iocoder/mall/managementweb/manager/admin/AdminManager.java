package cn.iocoder.mall.managementweb.manager.admin;

import cn.iocoder.common.framework.util.CollectionUtils;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.admin.dto.AdminCreateDTO;
import cn.iocoder.mall.managementweb.controller.admin.dto.AdminPageDTO;
import cn.iocoder.mall.managementweb.controller.admin.dto.AdminUpdateInfoDTO;
import cn.iocoder.mall.managementweb.controller.admin.dto.AdminUpdateStatusDTO;
import cn.iocoder.mall.managementweb.controller.admin.vo.AdminPageItemVO;
import cn.iocoder.mall.managementweb.controller.admin.vo.AdminVO;
import cn.iocoder.mall.managementweb.convert.admin.AdminConvert;
import cn.iocoder.mall.systemservice.rpc.admin.AdminFeign;
import cn.iocoder.mall.systemservice.rpc.admin.DepartmentFeign;
import cn.iocoder.mall.systemservice.rpc.admin.vo.DepartmentVO;
import cn.iocoder.mall.systemservice.rpc.permission.PermissionFeign;
import cn.iocoder.mall.systemservice.rpc.permission.RoleFeign;
import cn.iocoder.mall.systemservice.rpc.permission.vo.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.*;

@Service
@Validated
public class AdminManager {

    @Autowired
    private AdminFeign adminFeign;
    @Autowired
    private RoleFeign roleFeign;
    @Autowired
    private DepartmentFeign departmentFeign;
    @Autowired
    private PermissionFeign permissionFeign;

    public PageResult<AdminPageItemVO> pageAdmin(AdminPageDTO pageDTO) {
        CommonResult<PageResult<cn.iocoder.mall.systemservice.rpc.admin.vo.AdminVO>> pageResult =
                adminFeign.pageAdmin(AdminConvert.INSTANCE.convert(pageDTO));
        pageResult.checkError();
        // 转换结果
        PageResult<AdminPageItemVO> adminPageVO = new PageResult<>();
        adminPageVO.setTotal(pageResult.getData().getTotal());
        adminPageVO.setList(new ArrayList<>(pageResult.getData().getList().size()));
        // 拼接结果
        if (!pageResult.getData().getList().isEmpty()) {
            // 查询角色数组
            Map<Integer, List<RoleVO>> adminRoleMap = this.listAdminRoles(CollectionUtils.convertList(pageResult.getData().getList(),
                    cn.iocoder.mall.systemservice.rpc.admin.vo.AdminVO::getId));
            // 查询部门
            CommonResult<List<DepartmentVO>> listDepartmentsResult = departmentFeign.listDepartments(
                    CollectionUtils.convertSet(pageResult.getData().getList(),
                    cn.iocoder.mall.systemservice.rpc.admin.vo.AdminVO::getDepartmentId));
            listDepartmentsResult.checkError();
            Map<Integer, DepartmentVO> departmentMap = CollectionUtils.convertMap(listDepartmentsResult.getData(), DepartmentVO::getId);
            // 拼接数据
            for (cn.iocoder.mall.systemservice.rpc.admin.vo.AdminVO adminVO : pageResult.getData().getList()) {
                AdminPageItemVO adminPageItemVO = AdminConvert.INSTANCE.convert02(adminVO);
                adminPageVO.getList().add(adminPageItemVO);
                // 拼接部门
                adminPageItemVO.setDepartment(AdminConvert.INSTANCE.convert(departmentMap.get(adminVO.getDepartmentId())));
                // 拼接角色
                adminPageItemVO.setRoles( AdminConvert.INSTANCE.convertList(adminRoleMap.get(adminVO.getId())));
            }
        } else {
            adminPageVO.setList(Collections.emptyList());
        }
        return adminPageVO;
    }

    private Map<Integer, List<RoleVO>> listAdminRoles(List<Integer> adminIds) {
        // 获得管理员拥有的角色
        CommonResult<Map<Integer, Set<Integer>>> mapAdminRoleIdsResult = permissionFeign.mapAdminRoleIds(adminIds);
        mapAdminRoleIdsResult.checkError();
        // 获得角色列表
        Set<Integer> roleIds = new HashSet<>();
        mapAdminRoleIdsResult.getData().values().forEach(roleIds::addAll);
        CommonResult<List<RoleVO>> listRolesResult = roleFeign.listRoles(roleIds);
        listRolesResult.checkError();
        Map<Integer, RoleVO> roleVOMap = CollectionUtils.convertMap(listRolesResult.getData(), RoleVO::getId);
        // 拼接结果
        Map<Integer, List<RoleVO>> adminRoleVOMap = new HashMap<>();
        mapAdminRoleIdsResult.getData().forEach((adminId, adminRoleIds) -> {
            List<RoleVO> roleVOs = new ArrayList<>(adminRoleIds.size());
            adminRoleIds.forEach(roleId -> {
                RoleVO roleVO = roleVOMap.get(roleId);
                if (roleVO != null) {
                    roleVOs.add(roleVO);
                }
            });
            adminRoleVOMap.put(adminId, roleVOs);
        });
        return adminRoleVOMap;
    }

    public Integer createAdmin(AdminCreateDTO createDTO, Integer createAdminId, String createIp) {
        CommonResult<Integer> createAdminResult = adminFeign.createAdmin(AdminConvert.INSTANCE.convert(createDTO)
            .setCreateAdminId(createAdminId).setCreateIp(createIp));
        createAdminResult.checkError();
        return createAdminResult.getData();
    }

    public void updateAdmin(AdminUpdateInfoDTO updateInfoDTO) {
        CommonResult<Boolean> updateAdminResult = adminFeign.updateAdmin(AdminConvert.INSTANCE.convert(updateInfoDTO));
        updateAdminResult.checkError();
    }

    public void updateAdminStatus(@Valid AdminUpdateStatusDTO updateStatusDTO) {
        CommonResult<Boolean> updateAdminResult = adminFeign.updateAdmin(AdminConvert.INSTANCE.convert(updateStatusDTO));
        updateAdminResult.checkError();
    }

    public AdminVO getAdmin(Integer adminId) {
        CommonResult<cn.iocoder.mall.systemservice.rpc.admin.vo.AdminVO> getAdminResult = adminFeign.getAdmin(adminId);
        getAdminResult.checkError();
        return AdminConvert.INSTANCE.convert(getAdminResult.getData());
    }

}
