package cn.iocoder.mall.systemservice.rpc.oauth;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2AccessTokenRespDTO;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2CreateAccessTokenReqDTO;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2RefreshAccessTokenReqDTO;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2RemoveTokenByUserReqDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
* 部门 Rpc 接口
*/
@FeignClient("system-service")
public interface OAuthFeign {

    @PostMapping("ccreateAccessToken")
    public CommonResult<OAuth2AccessTokenRespDTO> createAccessToken(@RequestBody OAuth2CreateAccessTokenReqDTO createAccessTokenDTO);

    @PostMapping("/system/oauthcheckAccessToken")
    public CommonResult<OAuth2AccessTokenRespDTO> checkAccessToken(@RequestParam("accessToken") String accessToken) ;

    @PostMapping("/system/oauthrefreshAccessToken")
    public CommonResult<OAuth2AccessTokenRespDTO> refreshAccessToken(@RequestBody OAuth2RefreshAccessTokenReqDTO refreshAccessTokenDTO);
    @PostMapping("/system/oauthremoveToken")
    public CommonResult<Boolean> removeToken(@RequestBody OAuth2RemoveTokenByUserReqDTO removeTokenDTO);
}
