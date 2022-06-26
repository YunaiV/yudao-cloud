package cn.iocoder.mall.shopweb.service.user;

import cn.iocoder.common.framework.enums.UserTypeEnum;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.shopweb.controller.user.vo.passport.PassportAccessTokenRespVO;
import cn.iocoder.mall.shopweb.controller.user.vo.passport.PassportLoginBySmsReqVO;
import cn.iocoder.mall.shopweb.controller.user.vo.passport.PassportSendSmsRespVO;
import cn.iocoder.mall.shopweb.convert.user.PassportConvert;
import cn.iocoder.mall.systemservice.rpc.oauth.OAuthFeign;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2AccessTokenRespDTO;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2CreateAccessTokenReqDTO;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2RefreshAccessTokenReqDTO;
import cn.iocoder.mall.userservice.enums.sms.UserSmsSceneEnum;
import cn.iocoder.mall.userservice.rpc.sms.UserSmsCodeFeign;
import cn.iocoder.mall.userservice.rpc.user.UserFeign;
import cn.iocoder.mall.userservice.rpc.user.dto.UserRespDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassportManager {

    private UserSmsCodeFeign userSmsCodeFeign;
    private UserFeign userFeign;

    @Autowired
    private OAuthFeign oAuthFeign;

    public PassportAccessTokenRespVO loginBySms(PassportLoginBySmsReqVO loginBySmsDTO, String ip) {
        // 校验验证码
        CommonResult<Boolean> verifySmsCodeResult = userSmsCodeFeign.verifySmsCode(
                PassportConvert.INSTANCE.convert(loginBySmsDTO).setScene(UserSmsSceneEnum.LOGIN_BY_SMS.getValue()).setIp(ip));
        verifySmsCodeResult.checkError();
        // 获得用户
        CommonResult<UserRespDTO> createUserResult = userFeign.createUserIfAbsent(
                PassportConvert.INSTANCE.convert02(loginBySmsDTO).setIp(ip));
        createUserResult.checkError();
        // 创建访问令牌
        CommonResult<OAuth2AccessTokenRespDTO> createAccessTokenResult = oAuthFeign.createAccessToken(
                new OAuth2CreateAccessTokenReqDTO().setUserId(createUserResult.getData().getId())
                        .setUserType(UserTypeEnum.USER.getValue()).setCreateIp(ip));
        createAccessTokenResult.checkError();
        // 返回
        return PassportConvert.INSTANCE.convert(createAccessTokenResult.getData());
    }

    public void sendSmsCode(PassportSendSmsRespVO sendSmsCodeDTO, String ip) {
        CommonResult<Boolean> sendSmsCodeResult = userSmsCodeFeign.sendSmsCode(
                PassportConvert.INSTANCE.convert(sendSmsCodeDTO).setIp(ip));
        sendSmsCodeResult.checkError();
    }

    public PassportAccessTokenRespVO refreshToken(String refreshToken, String ip) {
        CommonResult<OAuth2AccessTokenRespDTO> refreshAccessTokenResult = oAuthFeign.refreshAccessToken(
                new OAuth2RefreshAccessTokenReqDTO().setRefreshToken(refreshToken).setCreateIp(ip));
        refreshAccessTokenResult.checkError();
        return PassportConvert.INSTANCE.convert(refreshAccessTokenResult.getData());
    }

}
