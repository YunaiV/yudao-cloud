package cn.iocoder.mall.user.controller;

import cn.iocoder.common.framework.exception.ServiceException;
import cn.iocoder.common.framework.util.ExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.user.sdk.annotation.PermitAll;
import cn.iocoder.mall.user.service.api.MobileCodeService;
import cn.iocoder.mall.user.service.api.OAuth2Service;
import cn.iocoder.mall.user.service.api.UserService;
import cn.iocoder.mall.user.service.api.bo.OAuth2AccessTokenBO;
import cn.iocoder.mall.user.service.api.constant.UserErrorCodeEnum;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/passport")
public class PassportController {

    @Reference
    private OAuth2Service oauth2Service;
    @Reference
    private UserService userService;
    @Reference
    private MobileCodeService mobileCodeService;

    // TODO 功能：手机密码登陆
//    @PostMapping("/mobile/pwd/login")
//    public OAuth2AccessToken mobileLogin(@RequestParam("mobile") String mobile,
//                                         @RequestParam("password") String password) {
//        return oauth2Service.getAccessToken(clientId, clientSecret, mobile, password);
//    }

    /**
     * 手机号 + 验证码登陆
     *
     * @param mobile 手机号
     * @param code   验证码
     * @return 授权信息
     */
    @PermitAll
    @PostMapping("/mobile/login")
    public OAuth2AccessTokenBO mobileRegister(@RequestParam("mobile") String mobile,
                                              @RequestParam("code") String code) {
        // 尝试直接授权
        OAuth2AccessTokenBO accessTokenDTO;
        try {
            accessTokenDTO = oauth2Service.getAccessToken(mobile, code);
            return accessTokenDTO;
        } catch (Exception ex) {
            ServiceException serviceException = ExceptionUtil.getServiceException(ex);
            if (serviceException == null) {
                throw ex;
            }
            if (!serviceException.getCode().equals(UserErrorCodeEnum.USER_MOBILE_NOT_REGISTERED.getCode())) { // 如果是未注册异常，忽略。下面发起自动注册逻辑。
                throw serviceException;
            }
        }
        // 上面尝试授权失败，说明用户未注册，发起自动注册。
        try {
            userService.createUser(mobile, code);
        } catch (Exception ex) {
            ServiceException serviceException = ExceptionUtil.getServiceException(ex);
            if (serviceException == null) {
                throw ex;
            }
            if (!serviceException.getCode().equals(UserErrorCodeEnum.USER_MOBILE_ALREADY_REGISTERED.getCode())) { // 如果是已注册异常，忽略。下面再次发起授权
                throw serviceException;
            }
        }
        // 再次发起授权
        accessTokenDTO = oauth2Service.getAccessToken(mobile, code);
        return accessTokenDTO;
    }

    /**
     * 手机号 + 验证码登陆
     *
     * @param mobile 手机号
     * @param code   验证码
     * @return 授权信息
     */
    @PermitAll
    @PostMapping("/mobile/login2")
    public CommonResult<OAuth2AccessTokenBO> mobileRegister2(@RequestParam("mobile") String mobile,
                                                             @RequestParam("code") String code) {
        return oauth2Service.getAccessToken2(mobile, code);
    }

    /**
     * 发送手机验证码
     *
     * @param mobile 手机号
     */
    @PermitAll
    @PostMapping("mobile/send")
    public void mobileSend(@RequestParam("mobile") String mobile) {
        mobileCodeService.send(mobile);
    }

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

    // TODO 功能：刷新 token

    // TODO 功能：退出，销毁 token
}