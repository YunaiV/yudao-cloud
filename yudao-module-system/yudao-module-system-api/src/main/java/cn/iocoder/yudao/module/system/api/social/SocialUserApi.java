package cn.iocoder.yudao.module.system.api.social;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.system.api.social.dto.SocialUserBindReqDTO;
import cn.iocoder.yudao.module.system.api.social.dto.SocialUserUnbindReqDTO;
import cn.iocoder.yudao.module.system.enums.ApiConstants;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient(name = ApiConstants.NAME) // TODO 芋艿：fallbackFactory =
@Tag(name =  "RPC 服务 - 社交用户")
public interface SocialUserApi {

    String PREFIX = ApiConstants.PREFIX + "/social-user";

    @GetMapping("PREFIX + /get-authorize-url")
    @Operation(summary = "获得社交平台的授权 URL")
    @Parameters({
            @Parameter(name = "type", description = "社交平台的类型", example = "1", required = true),
            @Parameter(name = "redirectUri", description = "重定向 URL", example = "https://www.iocoder.cn",required = true)
    })
    CommonResult<String> getAuthorizeUrl(@RequestParam("type") Integer type,
                                         @RequestParam("redirectUri") String redirectUri);

    @PostMapping("PREFIX + /bind")
    @Operation(summary = "绑定社交用户")
    CommonResult<Boolean> bindSocialUser(@Valid @RequestBody SocialUserBindReqDTO reqDTO);

    @DeleteMapping("PREFIX + /unbind")
    @Operation(summary = "取消绑定社交用户")
    CommonResult<Boolean> unbindSocialUser(@Valid @RequestBody SocialUserUnbindReqDTO reqDTO);

    @GetMapping("PREFIX + /get-bind-user-id")
    @Operation(summary = "获得社交用户的绑定用户编号")
    @Parameters({
            @Parameter(name = "userType", description = "用户类型", example = "2", required = true),
            @Parameter(name = "type", description = "社交平台的类型", example = "1", required = true),
            @Parameter(name = "code", description = "授权码", required = true, example = "tudou"),
            @Parameter(name = "state", description = "state", required = true, example = "coke")
    })
    CommonResult<Long> getBindUserId(@RequestParam("userType") Integer userType,
                                     @RequestParam("type") Integer type,
                                     @RequestParam("code") String code,
                                     @RequestParam("state") String state);

}