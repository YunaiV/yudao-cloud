package cn.iocoder.mall.userweb.convert.passport;

import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2AccessTokenRespDTO;
import cn.iocoder.mall.userservice.rpc.sms.dto.UserSendSmsCodeReqDTO;
import cn.iocoder.mall.userservice.rpc.sms.dto.UserVerifySmsCodeReqDTO;
import cn.iocoder.mall.userservice.rpc.user.dto.UserCreateReqDTO;
import cn.iocoder.mall.userservice.rpc.user.dto.UserRespDTO;
import cn.iocoder.mall.userweb.controller.passport.dto.UserPassportLoginBySmsDTO;
import cn.iocoder.mall.userweb.controller.passport.dto.UserPassportSendSmsCodeDTO;
import cn.iocoder.mall.userweb.controller.passport.vo.UserPassportVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserPassportConvert {

    UserPassportConvert INSTANCE = Mappers.getMapper(UserPassportConvert.class);

    UserVerifySmsCodeReqDTO convert(UserPassportLoginBySmsDTO bean);
    UserCreateReqDTO convert02(UserPassportLoginBySmsDTO bean);

    default UserPassportVO convert(UserRespDTO userVO, OAuth2AccessTokenRespDTO accessTokenVO) {
        return new UserPassportVO().setUser(convert(userVO)).setAuthorization(convert(accessTokenVO));
    }
    UserPassportVO.User convert(UserRespDTO userVO);
    UserPassportVO.Authentication convert(OAuth2AccessTokenRespDTO accessTokenVO);

    UserSendSmsCodeReqDTO convert(UserPassportSendSmsCodeDTO bean);

}
