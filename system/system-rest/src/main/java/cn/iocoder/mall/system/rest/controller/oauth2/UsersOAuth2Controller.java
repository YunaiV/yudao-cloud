package cn.iocoder.mall.system.rest.controller.oauth2;

import cn.iocoder.common.framework.constant.MallConstants;
import cn.iocoder.mall.system.biz.service.oauth2.OAuth2MobileCodeService;
import cn.iocoder.mall.system.biz.service.oauth2.OAuth2Service;
import cn.iocoder.mall.system.biz.service.user.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(MallConstants.ROOT_PATH_USER + "/oauth2")
@Api(tags = "用户 - OAuth2 API")
public class UsersOAuth2Controller {

    @Autowired
    private OAuth2Service oauth2Service;
    @Autowired
    private UserService userService;
    @Autowired
    private OAuth2MobileCodeService oauth2MobileCodeService;



}
