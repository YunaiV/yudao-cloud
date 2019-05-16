package cn.iocoder.mall.user.application.controller.users;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.user.api.MobileCodeService;
import cn.iocoder.mall.user.api.OAuth2Service;
import cn.iocoder.mall.user.api.UserService;
import cn.iocoder.mall.user.api.bo.OAuth2AccessTokenBO;
import cn.iocoder.mall.user.api.bo.user.UserAuthenticationBO;
import cn.iocoder.mall.user.api.dto.user.UserAuthenticationByMobileCodeDTO;
import cn.iocoder.mall.user.application.convert.PassportConvert;
import cn.iocoder.mall.user.application.vo.users.UsersAccessTokenVO;
import cn.iocoder.mall.user.sdk.annotation.PermitAll;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@RestController
@RequestMapping("users/passport")
@Api("Passport 模块")
public class PassportController {

    @Reference(validation = "true", version = "${dubbo.provider.OAuth2Service.version}")
    private OAuth2Service oauth2Service;
    @Reference(validation = "true", version = "${dubbo.provider.UserService.version}")
    private UserService userService;
    @Reference(validation = "true", version = "${dubbo.provider.MobileCodeService.version}")
    private MobileCodeService mobileCodeService;

    // TODO 功能：手机密码登陆
//    @PostMapping("/mobile/pwd/login")
//    public OAuth2AccessToken mobileLogin(@RequestParam("mobile") String mobile,
//                                         @RequestParam("password") String password) {
//        return oauth2Service.getAccessToken(clientId, clientSecret, mobile, password);
//    }

    @PermitAll
    @PostMapping("/mobile/register")
    @ApiOperation(value = "手机号 + 验证码登陆（注册）", notes = "如果手机对应的账号不存在，则会自动创建")
    public CommonResult<UserAuthenticationBO> mobileRegister(UserAuthenticationByMobileCodeDTO userAuthenticationByMobileCodeDTO) {
        return success(userService.authenticationByMobileCode(userAuthenticationByMobileCodeDTO));
    }

    @PermitAll
    @PostMapping("mobile/send_register_code")
    @ApiOperation(value = "发送手机验证码")
    @ApiImplicitParam(name = "mobile", value = "手机号", required = true, example = "15601691300")
    public CommonResult<Boolean> mobileSend(@RequestParam("mobile") String mobile) {
        mobileCodeService.send(mobile);
        return success(true);
    }

    // TODO 芋艿，改绑手机号

    // TODO 功能：qq 登陆
    @PermitAll
    @PostMapping("/qq/login")
    public String qqLogin() {
        return null;
    }

    // TODO 功能：qq 绑定
    @PermitAll
    @PostMapping("/qq/bind")
    public String qqBind() {
        return null;
    }

    @PermitAll
    @PostMapping("/refresh_token") // TODO 功能：刷新 token
    public CommonResult<UsersAccessTokenVO> refreshToken(@RequestParam("refreshToken") String refreshToken) {
        OAuth2AccessTokenBO result = oauth2Service.refreshToken(refreshToken);
        return success(PassportConvert.INSTANCE.convert2(result));
    }

    // TODO 功能：退出，销毁 token
}
