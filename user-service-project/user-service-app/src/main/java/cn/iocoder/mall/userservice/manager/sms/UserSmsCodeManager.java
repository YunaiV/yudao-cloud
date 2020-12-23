package cn.iocoder.mall.userservice.manager.sms;

import cn.iocoder.mall.userservice.rpc.sms.dto.UserSendSmsCodeReqDTO;
import cn.iocoder.mall.userservice.rpc.sms.dto.UserVerifySmsCodeReqDTO;
import cn.iocoder.mall.userservice.service.sms.UserSmsCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSmsCodeManager {

    @Autowired
    private UserSmsCodeService userSmsCodeService;

    public void sendSmsCode(UserSendSmsCodeReqDTO sendSmsCodeDTO) {
        // 生成短信验证码
        String smsCode = userSmsCodeService.createSmsCode(sendSmsCodeDTO.getMobile(),
                sendSmsCodeDTO.getScene(), sendSmsCodeDTO.getIp());
        // TODO 调用发送验证码
    }

    public void verifySmsCode(UserVerifySmsCodeReqDTO verifySmsCodeDTO) {
        userSmsCodeService.verifySmsCode(verifySmsCodeDTO.getMobile(), verifySmsCodeDTO.getCode(),
                verifySmsCodeDTO.getScene(), verifySmsCodeDTO.getIp());
    }

}
