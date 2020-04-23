package cn.iocoder.mall.system.rpc.rpc.admin;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.system.biz.bo.admin.AdminBO;
import cn.iocoder.mall.system.biz.service.admin.AdminService;
import cn.iocoder.mall.system.rpc.api.admin.AdminRPC;
import cn.iocoder.mall.system.rpc.convert.admn.AdminCovert;
import cn.iocoder.mall.system.rpc.response.admin.AdminResponse;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "${dubbo.provider.AdminRPC.version}", validation = "true")
public class AdminRPCImpl implements AdminRPC {

    @Autowired
    private AdminService adminService;

    @Override
    public CommonResult<AdminResponse> getAdmin(Integer id) {
        AdminBO adminBO = adminService.getAdmin(id);
        return CommonResult.success(AdminCovert.INSTANCE.convert(adminBO));
    }

    @Override
    public CommonResult<AdminResponse> getAdminByAccountId(Integer accountId) {
        AdminBO adminBO = adminService.getAdminByAccountId(accountId);
        return CommonResult.success(AdminCovert.INSTANCE.convert(adminBO));
    }

}
