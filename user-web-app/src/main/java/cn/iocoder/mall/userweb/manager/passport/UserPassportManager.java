package cn.iocoder.mall.userweb.manager.passport;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.userservice.enums.sms.UserSmsSceneEnum;
import cn.iocoder.mall.userservice.rpc.sms.UserSmsCodeRpc;
import cn.iocoder.mall.userservice.rpc.user.UserRpc;
import cn.iocoder.mall.userservice.rpc.user.vo.UserVO;
import cn.iocoder.mall.userweb.controller.passport.dto.UserPassportLoginBySmsDTO;
import cn.iocoder.mall.userweb.controller.passport.dto.UserPassportSendSmsCodeDTO;
import cn.iocoder.mall.userweb.controller.passport.vo.UserPassportVO;
import cn.iocoder.mall.userweb.convert.passport.UserPassportConvert;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

@Service
public class UserPassportManager {

    @Reference(version = "${dubbo.consumer.UserSmsCodeRpc.version}", validation = "false")
    private UserSmsCodeRpc userSmsCodeRpc;
    @Reference(version = "${dubbo.consumer.UserRpc.version}", validation = "false")
    private UserRpc userRpc;

    public UserPassportVO loginBySms(UserPassportLoginBySmsDTO loginBySmsDTO, String ip) {
        // 校验验证码
        CommonResult<Boolean> verifySmsCodeResult = userSmsCodeRpc.verifySmsCode(UserPassportConvert.INSTANCE.convert(loginBySmsDTO)
                .setScene(UserSmsSceneEnum.LOGIN_BY_SMS.getValue()).setIp(ip));
        verifySmsCodeResult.checkError();
        // 获得用户
        CommonResult<UserVO> createUserResult = userRpc.createUserIfAbsent(UserPassportConvert.INSTANCE.convert02(loginBySmsDTO).setIp(ip));
        createUserResult.checkError();
        // 创建访问令牌
        return UserPassportConvert.INSTANCE.convert(createUserResult.getData());
    }

    public void sendSmsCode(UserPassportSendSmsCodeDTO sendSmsCodeDTO, String ip) {
        CommonResult<Boolean> sendSmsCodeResult = userSmsCodeRpc.sendSmsCode(UserPassportConvert.INSTANCE.convert(sendSmsCodeDTO).setIp(ip));
        sendSmsCodeResult.checkError();
    }

}
