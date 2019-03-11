package cn.iocoder.mall.user.application.convert;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.user.service.api.bo.OAuth2AccessTokenBO;
import cn.iocoder.mall.user.application.vo.users.MobileRegisterVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PassportConvert {

    PassportConvert INSTANCE = Mappers.getMapper(PassportConvert.class);

    @Mappings({})
    MobileRegisterVO convert(OAuth2AccessTokenBO oauth2AccessTokenBO);

    @Mappings({})
    CommonResult<MobileRegisterVO> convert(CommonResult<OAuth2AccessTokenBO> oauth2AccessTokenBO);

}