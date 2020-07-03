package cn.iocoder.mall.userweb.convert.passport;

import cn.iocoder.mall.userservice.rpc.sms.vo.UserSendSmsCodeDTO;
import cn.iocoder.mall.userservice.rpc.sms.vo.UserVerifySmsCodeDTO;
import cn.iocoder.mall.userservice.rpc.user.dto.UserCreateDTO;
import cn.iocoder.mall.userservice.rpc.user.vo.UserVO;
import cn.iocoder.mall.userweb.controller.passport.dto.UserPassportLoginBySmsDTO;
import cn.iocoder.mall.userweb.controller.passport.dto.UserPassportSendSmsCodeDTO;
import cn.iocoder.mall.userweb.controller.passport.vo.UserPassportVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserPassportConvert {

    UserPassportConvert INSTANCE = Mappers.getMapper(UserPassportConvert.class);

    UserVerifySmsCodeDTO convert(UserPassportLoginBySmsDTO bean);

    UserCreateDTO convert02(UserPassportLoginBySmsDTO bean);

    UserPassportVO convert(UserVO userVO);

    UserSendSmsCodeDTO convert(UserPassportSendSmsCodeDTO bean);

}
