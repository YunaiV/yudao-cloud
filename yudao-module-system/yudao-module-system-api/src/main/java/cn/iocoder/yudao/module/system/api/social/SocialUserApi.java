package cn.iocoder.yudao.module.system.api.social;

import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.system.api.social.dto.SocialUserBindReqDTO;
import cn.iocoder.yudao.module.system.api.social.dto.SocialUserRespDTO;
import cn.iocoder.yudao.module.system.api.social.dto.SocialUserUnbindReqDTO;
import cn.iocoder.yudao.module.system.enums.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient(name = ApiConstants.NAME) // TODO 芋艿：fallbackFactory =
@Tag(name = "RPC 服务 - 社交用户")
public interface SocialUserApi {

    String PREFIX = ApiConstants.PREFIX + "/social-user";

    @PostMapping(PREFIX + "/bind")
    @Operation(summary = "绑定社交用户")
    CommonResult<String> bindSocialUser(@Valid @RequestBody SocialUserBindReqDTO reqDTO);

    @DeleteMapping(PREFIX + "/unbind")
    @Operation(summary = "取消绑定社交用户")
    CommonResult<Boolean> unbindSocialUser(@Valid @RequestBody SocialUserUnbindReqDTO reqDTO);

    @GetMapping(PREFIX + "/get-by-user-id")
    @Operation(summary = "获得社交用户，基于 userId")
    @Parameters({
            @Parameter(name = "userType", description = "用户类型", example = "2", required = true),
            @Parameter(name = "userId", description = "用户编号", example = "1024", required = true),
            @Parameter(name = "socialType", description = "社交平台的类型", example = "1", required = true),
    })
    CommonResult<SocialUserRespDTO> getSocialUserByUserId(@RequestParam("userType") Integer userType,
                                                          @RequestParam("userId") Long userId,
                                                          @RequestParam("socialType") Integer socialType);

    @GetMapping(PREFIX + "/get-by-code")
    @Operation(summary = "获得社交用") // 在认证信息不正确的情况下，也会抛出 {@link ServiceException} 业务异常
    @Parameters({
            @Parameter(name = "userType", description = "用户类型", example = "2", required = true),
            @Parameter(name = "socialType", description = "社交平台的类型", example = "1", required = true),
            @Parameter(name = "code", description = "授权码", example = "88888", required = true),
            @Parameter(name = "state", description = "state", example = "666", required = true),
    })
    CommonResult<SocialUserRespDTO> getSocialUserByCode(@RequestParam("userType") Integer userType,
                                                        @RequestParam("socialType") Integer socialType,
                                                        @RequestParam("code") String code,
                                                        @RequestParam("state") String state);

}
