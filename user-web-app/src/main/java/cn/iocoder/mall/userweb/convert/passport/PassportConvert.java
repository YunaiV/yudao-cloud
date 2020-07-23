package cn.iocoder.mall.userweb.convert.passport;

import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2AccessTokenRespDTO;
import cn.iocoder.mall.userservice.rpc.sms.dto.UserSendSmsCodeReqDTO;
import cn.iocoder.mall.userservice.rpc.sms.dto.UserVerifySmsCodeReqDTO;
import cn.iocoder.mall.userservice.rpc.user.dto.UserCreateReqDTO;
import cn.iocoder.mall.userweb.controller.passport.vo.PassportLoginBySmsReqVO;
import cn.iocoder.mall.userweb.controller.passport.vo.UserPassportSendSmsRespVO;
import cn.iocoder.mall.userweb.controller.passport.vo.PassportAccessTokenRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PassportConvert {

    PassportConvert INSTANCE = Mappers.getMapper(PassportConvert.class);

    UserVerifySmsCodeReqDTO convert(PassportLoginBySmsReqVO bean);
    UserCreateReqDTO convert02(PassportLoginBySmsReqVO bean);

    UserSendSmsCodeReqDTO convert(UserPassportSendSmsRespVO bean);

    PassportAccessTokenRespVO convert(OAuth2AccessTokenRespDTO bean);

}
