package cn.iocoder.mall.system.rest.convert.admin;

import cn.iocoder.mall.system.biz.bo.account.AccountUsernameAuthorizeBO;
import cn.iocoder.mall.system.rest.request.oauth2.AdminsOAuth2UsernameAuthenticateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdminsAdminConvert {

    AdminsAdminConvert INSTANCE = Mappers.getMapper(AdminsAdminConvert.class);

    AccountUsernameAuthorizeBO convert(AdminsOAuth2UsernameAuthenticateRequest request);

}
