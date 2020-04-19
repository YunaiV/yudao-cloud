package cn.iocoder.mall.system.rest.controller.admin;

import cn.iocoder.common.framework.constant.MallConstants;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.system.biz.bo.admin.AdminBO;
import cn.iocoder.mall.system.biz.bo.ouath2.OAuth2AccessTokenBO;
import cn.iocoder.mall.system.biz.dto.oatuh2.OAuth2UsernameAuthenticateDTO;
import cn.iocoder.mall.system.biz.service.admin.AdminService;
import cn.iocoder.mall.system.biz.service.oauth2.OAuth2Service;
import cn.iocoder.mall.system.rest.convert.oauth2.AdminsOAuth2Convert;
import cn.iocoder.mall.system.rest.request.oauth2.AdminsOAuth2UsernameAuthenticateRequest;
import cn.iocoder.mall.system.rest.response.oauth2.AdminsOAuth2AuthenticateResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.iocoder.mall.system.biz.constant.SystemErrorCodeEnum.*;

@RestController
@RequestMapping(MallConstants.ROOT_PATH_ADMIN + "/oauth2")
@Api(tags = "管理员 - OAuth2 API")
public class AdminsOAuth2Controller {

    @Autowired
    private OAuth2Service oauth2Service;
    @Autowired
    private AdminService adminService;

    @PostMapping("/username_authenticate")
    @ApiOperation("用户名认证")
    public CommonResult<AdminsOAuth2AuthenticateResponse> usernameAuthenticate(AdminsOAuth2UsernameAuthenticateRequest request) {
        // 执行认证
        OAuth2UsernameAuthenticateDTO authenticateDTO = AdminsOAuth2Convert.INSTANCE.convert(request);
        OAuth2AccessTokenBO accessTokenBO = oauth2Service.authenticate(authenticateDTO);
        // 获得 Admin 信息
        AdminBO adminBO = adminService.get(accessTokenBO.getAccountId());
        if (adminBO == null) {
            throw ServiceExceptionUtil.exception(ADMIN_NOT_FOUND);
        }
        // 转换返回
        return CommonResult.success(
                AdminsOAuth2Convert.INSTANCE.convert(adminBO, accessTokenBO)
        );
    }

}
