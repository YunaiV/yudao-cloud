package cn.iocoder.mall.managementweb.controller.passport;

import cn.iocoder.common.framework.util.HttpUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.managementweb.controller.passport.dto.AdminPassportLoginDTO;
import cn.iocoder.mall.managementweb.controller.passport.vo.AdminPassportVO;
import cn.iocoder.mall.managementweb.manager.admin.AdminPassportManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@Api(tags = "管理员 Passport API")
@RestController
@RequestMapping("/passport")
public class AdminPassportController {

    @Autowired
    private AdminPassportManager adminPassportManager;

    @PostMapping("/login")
    @ApiOperation("账号密码登陆")
//    @RequiresNone TODO 晚点加上
    public CommonResult<AdminPassportVO> login(AdminPassportLoginDTO loginDTO,
                                               HttpServletRequest request) {
        return success(adminPassportManager.login(loginDTO, HttpUtil.getIp(request)));
    }

}
