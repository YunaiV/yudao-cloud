package cn.iocoder.mall.userweb.controller.passport;

import cn.iocoder.common.framework.util.HttpUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.userweb.controller.passport.dto.UserPassportLoginBySmsDTO;
import cn.iocoder.mall.userweb.controller.passport.dto.UserPassportSendSmsCodeDTO;
import cn.iocoder.mall.userweb.controller.passport.vo.UserPassportVO;
import cn.iocoder.mall.userweb.manager.passport.UserPassportManager;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@RestController
@RequestMapping("/passport")
public class UserPassportController {

    @Autowired
    private UserPassportManager userPassportManager;

    @PostMapping("/login_by_sms")
    @ApiOperation("手机验证码登陆")
//    @RequiresNone TODO 晚点加上
    public CommonResult<UserPassportVO> loginBySms(UserPassportLoginBySmsDTO loginBySmsDTO,
                                                   HttpServletRequest request) {
        return success(userPassportManager.loginBySms(loginBySmsDTO, HttpUtil.getIp(request)));
    }

        @PostMapping("/send_sms_code")
    @ApiOperation("发送手机验证码")
//    @RequiresNone TODO 晚点加上
    public CommonResult<Boolean> sendSmsCode(UserPassportSendSmsCodeDTO sendSmsCodeDTO,
                                             HttpServletRequest request) {
        userPassportManager.sendSmsCode(sendSmsCodeDTO, HttpUtil.getIp(request));
        // 返回成功
        return success(true);
    }

}
