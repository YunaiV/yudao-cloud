package cn.iocoder.mall.systemservice.rpc.admin;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.systemservice.manager.admin.AdminManager;
import cn.iocoder.mall.systemservice.rpc.admin.dto.AdminCreateDTO;
import cn.iocoder.mall.systemservice.rpc.admin.dto.AdminPageDTO;
import cn.iocoder.mall.systemservice.rpc.admin.dto.AdminUpdateDTO;
import cn.iocoder.mall.systemservice.rpc.admin.dto.AdminVerifyPasswordDTO;
import cn.iocoder.mall.systemservice.rpc.admin.vo.AdminVO;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@Service(version = "${dubbo.provider.AdminRpc.version}")
public class AdminRpcImpl implements AdminRpc {

    @Autowired
    private AdminManager adminManager;

    @Override
    public CommonResult<AdminVO> verifyPassword(AdminVerifyPasswordDTO verifyPasswordDTO) {
        return success(adminManager.verifyPassword(verifyPasswordDTO));
    }

    @Override
    public CommonResult<Integer> createAdmin(AdminCreateDTO createDTO) {
        AdminVO adminVO = adminManager.createAdmin(createDTO);
        return success(adminVO.getId());
    }

    @Override
    public CommonResult<Boolean> updateAdmin(AdminUpdateDTO updateDTO) {
        adminManager.updateAdmin(updateDTO);
        return success(true);
    }

    @Override
    public CommonResult<PageResult<AdminVO>> pageAdmin(AdminPageDTO pageDTO) {
        return success(adminManager.pageAdmin(pageDTO));
    }

    @Override
    public CommonResult<AdminVO> getAdmin(Integer adminId) {
        return success(adminManager.getAdmin(adminId));
    }

}
