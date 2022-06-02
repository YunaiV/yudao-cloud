package cn.iocoder.yudao.module.system.api.auth;

import cn.iocoder.yudao.module.system.api.auth.dto.OAuth2AccessTokenCheckRespDTO;
import cn.iocoder.yudao.module.system.api.auth.dto.OAuth2AccessTokenCreateReqDTO;
import cn.iocoder.yudao.module.system.api.auth.dto.OAuth2AccessTokenRespDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * OAuth2.0 Token API 接口
 *
 * @author 芋道源码
 */
@FeignClient(name = "system-server") // TODO 芋艿：fallbackFactory =
public interface OAuth2TokenApi {

    /**
     * 创建访问令牌
     *
     * @param reqDTO 访问令牌的创建信息
     * @return 访问令牌的信息
     */
    @GetMapping("/tmp")
    OAuth2AccessTokenRespDTO createAccessToken(@Valid OAuth2AccessTokenCreateReqDTO reqDTO);

    /**
     * 校验访问令牌
     *
     * @param accessToken 访问令牌
     * @return 访问令牌的信息
     */
    @GetMapping("/app-api/check")
    OAuth2AccessTokenCheckRespDTO checkAccessToken(@RequestParam("accessToken") String accessToken);

    /**
     * 移除访问令牌
     *
     * @param accessToken 访问令牌
     * @return 访问令牌的信息
     */
    @GetMapping("/tmp2")
    OAuth2AccessTokenRespDTO removeAccessToken(String accessToken);

    /**
     * 刷新访问令牌
     *
     * @param refreshToken 刷新令牌
     * @param clientId 客户端编号
     * @return 访问令牌的信息
     */
    @GetMapping("/tmp3")
    OAuth2AccessTokenRespDTO refreshAccessToken(@RequestParam("refreshToken") String refreshToken,
                                                @RequestParam("clientId") String clientId);

}
