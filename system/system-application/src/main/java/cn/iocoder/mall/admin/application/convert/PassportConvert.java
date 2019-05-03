package cn.iocoder.mall.admin.application.convert;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.api.bo.OAuth2AccessTokenBO;
import cn.iocoder.mall.admin.application.vo.PassportLoginVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PassportConvert {

    PassportConvert INSTANCE = Mappers.getMapper(PassportConvert.class);

    @Mappings({})
    PassportLoginVO convert(OAuth2AccessTokenBO oauth2AccessTokenBO);

    @Mappings({})
    CommonResult<PassportLoginVO> convert(CommonResult<OAuth2AccessTokenBO> oauth2AccessTokenBO);

}