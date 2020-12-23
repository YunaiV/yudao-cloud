package cn.iocoder.mall.systemservice.rpc.oauth;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2CreateAccessTokenReqDTO;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2RefreshAccessTokenReqDTO;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2AccessTokenRespDTO;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2RemoveTokenByUserReqDTO;

public interface OAuth2Rpc {

    CommonResult<OAuth2AccessTokenRespDTO> createAccessToken(OAuth2CreateAccessTokenReqDTO createAccessTokenDTO);

    CommonResult<OAuth2AccessTokenRespDTO> checkAccessToken(String accessToken);

    CommonResult<OAuth2AccessTokenRespDTO> refreshAccessToken(OAuth2RefreshAccessTokenReqDTO refreshAccessTokenDTO);

    CommonResult<Boolean> removeToken(OAuth2RemoveTokenByUserReqDTO removeTokenDTO);

}
