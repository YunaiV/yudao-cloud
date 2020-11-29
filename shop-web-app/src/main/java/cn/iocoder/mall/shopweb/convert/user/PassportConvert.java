package cn.iocoder.mall.shopweb.convert.user;

import cn.iocoder.mall.shopweb.controller.user.vo.passport.PassportAccessTokenRespVO;
import cn.iocoder.mall.shopweb.controller.user.vo.passport.PassportLoginBySmsReqVO;
import cn.iocoder.mall.shopweb.controller.user.vo.passport.PassportSendSmsRespVO;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2AccessTokenRespDTO;
import cn.iocoder.mall.userservice.rpc.sms.dto.UserSendSmsCodeReqDTO;
import cn.iocoder.mall.userservice.rpc.sms.dto.UserVerifySmsCodeReqDTO;
import cn.iocoder.mall.userservice.rpc.user.dto.UserCreateReqDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PassportConvert {

    PassportConvert INSTANCE = Mappers.getMapper(PassportConvert.class);

    UserVerifySmsCodeReqDTO convert(PassportLoginBySmsReqVO bean);
    UserCreateReqDTO convert02(PassportLoginBySmsReqVO bean);

    UserSendSmsCodeReqDTO convert(PassportSendSmsRespVO bean);

    PassportAccessTokenRespVO convert(OAuth2AccessTokenRespDTO bean);

}
