package cn.iocoder.mall.userservice.controller;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.userservice.manager.sms.UserSmsCodeManager;
import cn.iocoder.mall.userservice.rpc.sms.dto.UserSendSmsCodeReqDTO;
import cn.iocoder.mall.userservice.rpc.sms.dto.UserVerifySmsCodeReqDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/sms")
public class UserSmsCodeController {

    @Autowired
    private UserSmsCodeManager userSmsCodeManager;

    @PostMapping("sendSmsCode")
    public CommonResult<Boolean> sendSmsCode(@RequestBody UserSendSmsCodeReqDTO sendSmsCodeDTO) {
        userSmsCodeManager.sendSmsCode(sendSmsCodeDTO);
        return CommonResult.success(true);
    }

    @PostMapping("verifySmsCode")
    public CommonResult<Boolean> verifySmsCode(@RequestBody UserVerifySmsCodeReqDTO verifySmsCodeDTO) {
        userSmsCodeManager.verifySmsCode(verifySmsCodeDTO);
        return CommonResult.success(true);
    }

}