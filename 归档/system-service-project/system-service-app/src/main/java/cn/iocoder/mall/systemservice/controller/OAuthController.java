package cn.iocoder.mall.systemservice.controller;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2AccessTokenRespDTO;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2CreateAccessTokenReqDTO;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2RefreshAccessTokenReqDTO;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2RemoveTokenByUserReqDTO;
import cn.iocoder.mall.systemservice.service.oauth.OAuth2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
 * Title:
 * Description:
 *
 * @author zhuyang
 * @version 1.0 2021/10/11
 */
@RestController
@RequestMapping("/system/oauth")
public class OAuthController {

    @Autowired
    private OAuth2Service oAuth2Service;

    @PostMapping("createAccessToken")
    public CommonResult<OAuth2AccessTokenRespDTO> createAccessToken(@RequestBody OAuth2CreateAccessTokenReqDTO createAccessTokenDTO) {
        return success(oAuth2Service.createAccessToken(createAccessTokenDTO));
    }

    @GetMapping("checkAccessToken")
    public CommonResult<OAuth2AccessTokenRespDTO> checkAccessToken(@RequestParam("accessToken") String accessToken) {
        return success(oAuth2Service.checkAccessToken(accessToken));
    }

    @PostMapping("refreshAccessToken")
    public CommonResult<OAuth2AccessTokenRespDTO> refreshAccessToken(@RequestBody OAuth2RefreshAccessTokenReqDTO refreshAccessTokenDTO) {
        return success(oAuth2Service.refreshAccessToken(refreshAccessTokenDTO));
    }

    @PostMapping("removeToken")
    public CommonResult<Boolean> removeToken(@RequestBody OAuth2RemoveTokenByUserReqDTO removeTokenDTO) {
        oAuth2Service.removeToken(removeTokenDTO);
        return success(true);
    }
}
