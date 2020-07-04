package cn.iocoder.mall.systemservice.rpc.oauth;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2CreateAccessTokenDTO;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2RefreshAccessTokenDTO;
import cn.iocoder.mall.systemservice.rpc.oauth.vo.OAuth2AccessTokenVO;

public interface OAuth2Rpc {

    CommonResult<OAuth2AccessTokenVO> createAccessToken(OAuth2CreateAccessTokenDTO createAccessTokenDTO);

    CommonResult<OAuth2AccessTokenVO> checkAccessToken(String accessToken);

    CommonResult<OAuth2AccessTokenVO> refreshAccessToken(OAuth2RefreshAccessTokenDTO refreshAccessTokenDTO);

}
