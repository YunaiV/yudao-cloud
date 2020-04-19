package cn.iocoder.mall.user.application.controller.users;

import cn.iocoder.common.framework.constant.UserTypeEnum;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.system.api.OAuth2Service;
import cn.iocoder.mall.system.api.bo.oauth2.OAuth2AccessTokenBO;
import cn.iocoder.mall.system.api.dto.oauth2.OAuth2RefreshTokenDTO;
import cn.iocoder.mall.user.api.UserService;
import io.swagger.annotations.Api;
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

    @Reference(validation = "true", version = "${dubbo.consumer.OAuth2Service.version}")
    private OAuth2Service oauth2Service;
    @Reference(validation = "true", version = "${dubbo.provider.UserService.version}")
    private UserService userService;

    // TODO 功能：手机密码登陆
//    @PostMapping("/mobile/pwd/login")
//    public OAuth2AccessToken mobileLogin(@RequestParam("mobile") String mobile,
//                                         @RequestParam("password") String password) {
//        return oauth2Service.getAccessToken(clientId, clientSecret, mobile, password);
//    }

    // TODO 芋艿，改绑手机号

    // TODO 功能：qq 登陆
    @PostMapping("/qq/login")
    public String qqLogin() {
        return null;
    }

    // TODO 功能：qq 绑定
    @PostMapping("/qq/bind")
    public String qqBind() {
        return null;
    }

    @PostMapping("/refresh_token") // TODO 功能：刷新 token
    public CommonResult<OAuth2AccessTokenBO> refreshToken(@RequestParam("refreshToken") String refreshToken) {
        return success(oauth2Service.refreshToken(new OAuth2RefreshTokenDTO().setRefreshToken(refreshToken)
                .setUserType(UserTypeEnum.USER.getValue())));
    }

    // TODO 功能：退出，销毁 token
}
