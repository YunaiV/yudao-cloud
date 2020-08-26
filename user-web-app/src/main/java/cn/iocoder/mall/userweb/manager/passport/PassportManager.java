package cn.iocoder.mall.userweb.manager.passport;

import cn.iocoder.common.framework.enums.UserTypeEnum;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.systemservice.rpc.oauth.OAuth2Rpc;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2AccessTokenRespDTO;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2CreateAccessTokenReqDTO;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2RefreshAccessTokenReqDTO;
import cn.iocoder.mall.userservice.enums.sms.UserSmsSceneEnum;
import cn.iocoder.mall.userservice.rpc.sms.UserSmsCodeRpc;
import cn.iocoder.mall.userservice.rpc.user.UserRpc;
import cn.iocoder.mall.userservice.rpc.user.dto.UserRespDTO;
import cn.iocoder.mall.userweb.controller.passport.vo.PassportAccessTokenRespVO;
import cn.iocoder.mall.userweb.controller.passport.vo.PassportLoginBySmsReqVO;
import cn.iocoder.mall.userweb.controller.passport.vo.UserPassportSendSmsRespVO;
import cn.iocoder.mall.userweb.convert.passport.PassportConvert;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class PassportManager {

    @DubboReference(version = "${dubbo.consumer.UserSmsCodeRpc.version}", validation = "false")
    private UserSmsCodeRpc userSmsCodeRpc;
    @DubboReference(version = "${dubbo.consumer.UserRpc.version}", validation = "false")
    private UserRpc userRpc;
    @DubboReference(version = "${dubbo.consumer.OAuth2Rpc.version}", validation = "false")
    private OAuth2Rpc oauth2Rpc;

    public PassportAccessTokenRespVO loginBySms(PassportLoginBySmsReqVO loginBySmsDTO, String ip) {
        // 校验验证码
        CommonResult<Boolean> verifySmsCodeResult = userSmsCodeRpc.verifySmsCode(
                PassportConvert.INSTANCE.convert(loginBySmsDTO).setScene(UserSmsSceneEnum.LOGIN_BY_SMS.getValue()).setIp(ip));
        verifySmsCodeResult.checkError();
        // 获得用户
        CommonResult<UserRespDTO> createUserResult = userRpc.createUserIfAbsent(
                PassportConvert.INSTANCE.convert02(loginBySmsDTO).setIp(ip));
        createUserResult.checkError();
        // 创建访问令牌
        CommonResult<OAuth2AccessTokenRespDTO> createAccessTokenResult = oauth2Rpc.createAccessToken(
                new OAuth2CreateAccessTokenReqDTO().setUserId(createUserResult.getData().getId())
                        .setUserType(UserTypeEnum.USER.getValue()).setCreateIp(ip));
        createAccessTokenResult.checkError();
        // 返回
        return PassportConvert.INSTANCE.convert(createAccessTokenResult.getData());
    }

    public void sendSmsCode(UserPassportSendSmsRespVO sendSmsCodeDTO, String ip) {
        CommonResult<Boolean> sendSmsCodeResult = userSmsCodeRpc.sendSmsCode(
                PassportConvert.INSTANCE.convert(sendSmsCodeDTO).setIp(ip));
        sendSmsCodeResult.checkError();
    }

    public PassportAccessTokenRespVO refreshToken(String refreshToken, String ip) {
        CommonResult<OAuth2AccessTokenRespDTO> refreshAccessTokenResult = oauth2Rpc.refreshAccessToken(
                new OAuth2RefreshAccessTokenReqDTO().setRefreshToken(refreshToken).setCreateIp(ip));
        refreshAccessTokenResult.checkError();
        return PassportConvert.INSTANCE.convert(refreshAccessTokenResult.getData());
    }

}
