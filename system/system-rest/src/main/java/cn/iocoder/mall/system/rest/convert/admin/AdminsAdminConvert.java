package cn.iocoder.mall.system.rest.convert.admin;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.system.biz.bo.account.AccountUsernameAuthorizeBO;
import cn.iocoder.mall.system.biz.bo.admin.AdminBO;
import cn.iocoder.mall.system.biz.dto.admin.AdminPageDTO;
import cn.iocoder.mall.system.rest.request.admin.AdminsAdminPageRequest;
import cn.iocoder.mall.system.rest.request.oauth2.AdminsOAuth2UsernameAuthenticateRequest;
import cn.iocoder.mall.system.rest.response.admin.AdminsAdminPageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdminsAdminConvert {

    AdminsAdminConvert INSTANCE = Mappers.getMapper(AdminsAdminConvert.class);

    AccountUsernameAuthorizeBO convert(AdminsOAuth2UsernameAuthenticateRequest bean);

    AdminPageDTO convert(AdminsAdminPageRequest bean);

    PageResult<AdminsAdminPageResponse> convertPage(PageResult<AdminBO> bean);

}
