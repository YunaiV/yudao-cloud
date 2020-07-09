package cn.iocoder.mall.managementweb.manager.admin;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.admin.dto.AdminCreateDTO;
import cn.iocoder.mall.managementweb.controller.admin.dto.AdminPageDTO;
import cn.iocoder.mall.managementweb.controller.admin.dto.AdminUpdateInfoDTO;
import cn.iocoder.mall.managementweb.controller.admin.dto.AdminUpdateStatusDTO;
import cn.iocoder.mall.managementweb.controller.admin.vo.AdminPageItemVO;
import cn.iocoder.mall.managementweb.controller.admin.vo.AdminVO;
import cn.iocoder.mall.managementweb.convert.admin.AdminConvert;
import cn.iocoder.mall.systemservice.rpc.admin.AdminRpc;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

@Service
public class AdminManager {

    @Reference(version = "${dubbo.consumer.AdminRpc.version}", validation = "false")
    private AdminRpc adminRpc;

    public PageResult<AdminPageItemVO> pageAdmin(AdminPageDTO pageDTO) {
        CommonResult<PageResult<cn.iocoder.mall.systemservice.rpc.admin.vo.AdminVO>> pageResult =
                adminRpc.pageAdmin(AdminConvert.INSTANCE.convert(pageDTO));
        pageResult.checkError();
        // 转换结果
        PageResult<AdminPageItemVO> adminPageVO = AdminConvert.INSTANCE.convert(pageResult.getData());
        // 拼接结果
//        if (!resultPage.getList().isEmpty()) {
//            // 查询角色数组
//            Map<Integer, Collection<RoleBO>> roleMap = adminService.getAdminRolesMap(CollectionUtil.convertList(resultPage.getList(), AdminBO::getId));
//            resultPage.getList().forEach(admin -> admin.setRoles(AdminConvert.INSTANCE.convertAdminVORoleList(roleMap.get(admin.getId()))));
//
//            // 查询对应部门
//            List<DeptmentBO> deptmentBOS =  deptmentService.getAllDeptments();
//            Map<Integer, String> deptNameMap = deptmentBOS.stream().collect(Collectors.toMap(d->d.getId(), d->d.getName()));
//            //管理员所在部门被删后，变成未分配状态
//            deptNameMap.put(0, "未分配");
//            resultPage.getList().forEach(admin->{
//                admin.setDeptment(new AdminVO.Deptment(admin.getDeptmentId(), deptNameMap.get(admin.getDeptmentId())));
//            });
//        }
        return adminPageVO;
    }

    public Integer createAdmin(AdminCreateDTO createDTO, Integer createAdminId, String createIp) {
        CommonResult<Integer> createAdminResult = adminRpc.createAdmin(AdminConvert.INSTANCE.convert(createDTO)
            .setCreateAdminId(createAdminId).setCreateIp(createIp));
        createAdminResult.checkError();
        return createAdminResult.getData();
    }

    public void updateAdmin(AdminUpdateInfoDTO updateInfoDTO) {
        CommonResult<Boolean> updateAdminResult = adminRpc.updateAdmin(AdminConvert.INSTANCE.convert(updateInfoDTO));
        updateAdminResult.checkError();
    }

    public void updateAdminStatus(AdminUpdateStatusDTO updateStatusDTO) {
        CommonResult<Boolean> updateAdminResult = adminRpc.updateAdmin(AdminConvert.INSTANCE.convert(updateStatusDTO));
        updateAdminResult.checkError();
    }

    public AdminVO getAdmin(Integer adminId) {
        CommonResult<cn.iocoder.mall.systemservice.rpc.admin.vo.AdminVO> getAdminResult = adminRpc.getAdmin(adminId);
        getAdminResult.checkError();
        return AdminConvert.INSTANCE.convert(getAdminResult.getData());
    }

}
