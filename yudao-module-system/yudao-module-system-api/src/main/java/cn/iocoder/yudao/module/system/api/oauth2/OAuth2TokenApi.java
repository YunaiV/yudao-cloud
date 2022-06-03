package cn.iocoder.yudao.module.system.api.oauth2;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.system.api.oauth2.dto.OAuth2AccessTokenCheckRespDTO;
import cn.iocoder.yudao.module.system.api.oauth2.dto.OAuth2AccessTokenCreateReqDTO;
import cn.iocoder.yudao.module.system.api.oauth2.dto.OAuth2AccessTokenRespDTO;
import cn.iocoder.yudao.module.system.enums.ApiConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient(name = "system-server") // TODO 芋艿：fallbackFactory =
@Api(tags = "RPC 服务 - OAuth2.0 令牌")
public interface OAuth2TokenApi {

    String API_PREFIX = ApiConstants.API_PREFIX + "/oauth2/token";

    @PostMapping(API_PREFIX + "/create")
    @ApiOperation("创建访问令牌")
    CommonResult<OAuth2AccessTokenRespDTO> createAccessToken(@Valid @RequestBody OAuth2AccessTokenCreateReqDTO reqDTO);

    @GetMapping(API_PREFIX + "/check")
    @ApiOperation("校验访问令牌")
    @ApiImplicitParam(name = "accessToken", value = "访问令牌", required = true, dataTypeClass = String.class, example = "tudou")
    CommonResult<OAuth2AccessTokenCheckRespDTO> checkAccessToken(@RequestParam("accessToken") String accessToken);

    @DeleteMapping(API_PREFIX + "/remove")
    @ApiOperation("移除访问令牌")
    @ApiImplicitParam(name = "accessToken", value = "访问令牌", required = true, dataTypeClass = String.class, example = "tudou")
    CommonResult<OAuth2AccessTokenRespDTO> removeAccessToken(@RequestParam("accessToken") String accessToken);

    @PutMapping(API_PREFIX + "/refresh")
    @ApiOperation("刷新访问令牌")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "refreshToken", value = "刷新令牌", required = true, dataTypeClass = String.class, example = "haha"),
        @ApiImplicitParam(name = "clientId", value = "客户端编号", required = true, dataTypeClass = String.class, example = "yudaoyuanma")
    })
    CommonResult<OAuth2AccessTokenRespDTO> refreshAccessToken(@RequestParam("refreshToken") String refreshToken,
                                                              @RequestParam("clientId") String clientId);

}
