package cn.iocoder.mall.managementweb.convert.passport;

import cn.iocoder.mall.managementweb.controller.passport.dto.PassportLoginDTO;
import cn.iocoder.mall.managementweb.controller.passport.vo.PassportAccessTokenVO;
import cn.iocoder.mall.managementweb.controller.passport.vo.PassportAdminVO;
import cn.iocoder.mall.systemservice.rpc.admin.dto.AdminVerifyPasswordDTO;
import cn.iocoder.mall.systemservice.rpc.admin.vo.AdminVO;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2AccessTokenRespDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdminPassportConvert {

    AdminPassportConvert INSTANCE = Mappers.getMapper(AdminPassportConvert.class);

    AdminVerifyPasswordDTO convert(PassportLoginDTO bean);

    PassportAccessTokenVO convert(OAuth2AccessTokenRespDTO bean);

    PassportAdminVO convert(AdminVO bean);

}
