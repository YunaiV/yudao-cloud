package cn.iocoder.yudao.module.system.api.social;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.system.api.social.dto.SocialUserBindReqDTO;
import cn.iocoder.yudao.module.system.api.social.dto.SocialUserUnbindReqDTO;
import cn.iocoder.yudao.module.system.enums.ApiConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient(name = ApiConstants.NAME) // TODO 芋艿：fallbackFactory =
@Api(tags = "RPC 服务 - 社交用户")
public interface SocialUserApi {

    String PREFIX = ApiConstants.PREFIX + "/social-user";

    @GetMapping(PREFIX + "/get-authorize-url")
    @ApiOperation("获得社交平台的授权 URL")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "社交平台的类型", example = "1", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "redirectUri", value = "重定向 URL", example = "https://www.iocoder.cn",required = true, dataTypeClass = String.class)
    })
    CommonResult<String> getAuthorizeUrl(@RequestParam("type") Integer type,
                                         @RequestParam("redirectUri") String redirectUri);

    @PostMapping(PREFIX + "/bind")
    @ApiOperation("绑定社交用户")
    CommonResult<Boolean> bindSocialUser(@Valid @RequestBody SocialUserBindReqDTO reqDTO);

    @DeleteMapping(PREFIX + "/unbind")
    @ApiOperation("取消绑定社交用户")
    CommonResult<Boolean> unbindSocialUser(@Valid @RequestBody SocialUserUnbindReqDTO reqDTO);

    @GetMapping(PREFIX + "/get-bind-user-id")
    @ApiOperation("获得社交用户的绑定用户编号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userType", value = "用户类型", example = "2", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "type", value = "社交平台的类型", example = "1", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "code", value = "授权码", required = true, example = "tudou", dataTypeClass = String.class),
            @ApiImplicitParam(name = "state", value = "state", required = true, example = "coke", dataTypeClass = String.class)
    })
    CommonResult<Long> getBindUserId(@RequestParam("userType") Integer userType,
                                     @RequestParam("type") Integer type,
                                     @RequestParam("code") String code,
                                     @RequestParam("state") String state);

}
