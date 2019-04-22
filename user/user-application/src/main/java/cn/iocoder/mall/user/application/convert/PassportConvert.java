package cn.iocoder.mall.user.application.convert;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.user.api.bo.OAuth2AccessTokenBO;
import cn.iocoder.mall.user.application.vo.users.UsersAccessTokenVO;
import cn.iocoder.mall.user.application.vo.users.UsersMobileRegisterVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PassportConvert {

    PassportConvert INSTANCE = Mappers.getMapper(PassportConvert.class);

    @Mappings({})
    UsersMobileRegisterVO convert(OAuth2AccessTokenBO oauth2AccessTokenBO);

    @Mappings({})
    CommonResult<UsersMobileRegisterVO> convert(CommonResult<OAuth2AccessTokenBO> oauth2AccessTokenBO);

    @Mappings({})
    CommonResult<UsersAccessTokenVO> convert2(CommonResult<OAuth2AccessTokenBO> result);

}
