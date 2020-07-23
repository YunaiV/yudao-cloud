package cn.iocoder.mall.userservice.rpc.sms;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.userservice.manager.sms.UserSmsCodeManager;
import cn.iocoder.mall.userservice.rpc.sms.dto.UserSendSmsCodeReqDTO;
import cn.iocoder.mall.userservice.rpc.sms.dto.UserVerifySmsCodeReqDTO;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "${dubbo.provider.UserSmsCodeRpc.version}")
public class UserSmsCodeRpcImpl implements UserSmsCodeRpc {

    @Autowired
    private UserSmsCodeManager userSmsCodeManager;

    @Override
    public CommonResult<Boolean> sendSmsCode(UserSendSmsCodeReqDTO sendSmsCodeDTO) {
        userSmsCodeManager.sendSmsCode(sendSmsCodeDTO);
        return CommonResult.success(true);
    }

    @Override
    public CommonResult<Boolean> verifySmsCode(UserVerifySmsCodeReqDTO verifySmsCodeDTO) {
        userSmsCodeManager.verifySmsCode(verifySmsCodeDTO);
        return CommonResult.success(true);
    }

}
