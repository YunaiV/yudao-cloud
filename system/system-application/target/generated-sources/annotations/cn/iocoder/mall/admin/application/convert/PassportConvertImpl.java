package cn.iocoder.mall.admin.application.convert;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.api.bo.oauth2.OAuth2AccessTokenBO;
import cn.iocoder.mall.admin.application.vo.PassportLoginVO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-31T18:11:49+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class PassportConvertImpl implements PassportConvert {

    @Override
    public PassportLoginVO convert(OAuth2AccessTokenBO oauth2AccessTokenBO) {
        if ( oauth2AccessTokenBO == null ) {
            return null;
        }

        PassportLoginVO passportLoginVO = new PassportLoginVO();

        passportLoginVO.setAccessToken( oauth2AccessTokenBO.getAccessToken() );
        passportLoginVO.setRefreshToken( oauth2AccessTokenBO.getRefreshToken() );
        passportLoginVO.setExpiresIn( oauth2AccessTokenBO.getExpiresIn() );

        return passportLoginVO;
    }

    @Override
    public CommonResult<PassportLoginVO> convert(CommonResult<OAuth2AccessTokenBO> oauth2AccessTokenBO) {
        if ( oauth2AccessTokenBO == null ) {
            return null;
        }

        CommonResult<PassportLoginVO> commonResult = new CommonResult<PassportLoginVO>();

        commonResult.setCode( oauth2AccessTokenBO.getCode() );
        commonResult.setMessage( oauth2AccessTokenBO.getMessage() );
        commonResult.setData( convert( oauth2AccessTokenBO.getData() ) );

        return commonResult;
    }
}
