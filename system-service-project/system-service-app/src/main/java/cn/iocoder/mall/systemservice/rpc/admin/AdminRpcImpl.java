package cn.iocoder.mall.systemservice.rpc.admin;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.systemservice.manager.admin.AdminManager;
import cn.iocoder.mall.systemservice.rpc.admin.dto.AdminVerifyPasswordDTO;
import cn.iocoder.mall.systemservice.rpc.admin.vo.AdminVO;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@Service(version = "${dubbo.provider.AdminRpc.version}", validation = "false")
public class AdminRpcImpl implements AdminRpc {

    @Autowired
    private AdminManager adminManager;

    @Override
    public CommonResult<AdminVO> verifyPassword(AdminVerifyPasswordDTO verifyPasswordDTO) {
        return success(adminManager.verifyPassword(verifyPasswordDTO));
    }

}
