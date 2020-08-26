package cn.iocoder.mall.userweb.controller.passport;

import cn.iocoder.common.framework.util.HttpUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.userweb.controller.passport.vo.PassportLoginBySmsReqVO;
import cn.iocoder.mall.userweb.controller.passport.vo.UserPassportSendSmsRespVO;
import cn.iocoder.mall.userweb.controller.passport.vo.PassportAccessTokenRespVO;
import cn.iocoder.mall.userweb.manager.passport.PassportManager;
import cn.iocoder.security.annotations.RequiresNone;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@Api(tags = "用户 Passport API")
@RestController
@RequestMapping("/passport")
public class PassportController {

    @Autowired
    private PassportManager passportManager;

    @PostMapping("/login-by-sms")
    @ApiOperation("手机验证码登陆")
    @RequiresNone
    public CommonResult<PassportAccessTokenRespVO> loginBySms(PassportLoginBySmsReqVO loginBySmsDTO,
                                                              HttpServletRequest request) {
        return success(passportManager.loginBySms(loginBySmsDTO, HttpUtil.getIp(request)));
    }

    @PostMapping("/send-sms-code")
    @ApiOperation("发送手机验证码")
    @RequiresNone
    public CommonResult<Boolean> sendSmsCode(UserPassportSendSmsRespVO sendSmsCodeDTO,
                                             HttpServletRequest request) {
        passportManager.sendSmsCode(sendSmsCodeDTO, HttpUtil.getIp(request));
        // 返回成功
        return success(true);
    }

    @PostMapping("/refresh-token")
    @ApiOperation("刷新令牌")
    @RequiresNone
    public CommonResult<PassportAccessTokenRespVO> refreshToken(@RequestParam("refreshToken") String refreshToken,
                                                            HttpServletRequest request) {
        return success(passportManager.refreshToken(refreshToken, HttpUtil.getIp(request)));
    }

}
