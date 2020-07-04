package cn.iocoder.mall.managementweb.convert.passport;

import cn.iocoder.mall.managementweb.controller.passport.dto.AdminPassportLoginDTO;
import cn.iocoder.mall.managementweb.controller.passport.vo.AdminPassportVO;
import cn.iocoder.mall.systemservice.rpc.admin.dto.AdminVerifyPasswordDTO;
import cn.iocoder.mall.systemservice.rpc.admin.vo.AdminVO;
import cn.iocoder.mall.systemservice.rpc.oauth.vo.OAuth2AccessTokenVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdminPassportConvert {

    AdminPassportConvert INSTANCE = Mappers.getMapper(AdminPassportConvert.class);

    AdminVerifyPasswordDTO convert(AdminPassportLoginDTO loginDTO);

    default AdminPassportVO convert(AdminVO adminVO, OAuth2AccessTokenVO accessTokenVO) {
        return new AdminPassportVO().setAdmin(convert(adminVO)).setAuthorization(convert(accessTokenVO));
    }
    AdminPassportVO.Admin convert(AdminVO adminVO);
    AdminPassportVO.Authentication convert(OAuth2AccessTokenVO accessTokenVO);

}
