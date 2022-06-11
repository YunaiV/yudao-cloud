package cn.iocoder.yudao.framework.security.core.rpc;

import cn.iocoder.yudao.framework.rpc.core.util.FeignUtils;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;

public class LoginUserRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        LoginUser user = SecurityFrameworkUtils.getLoginUser();
        if (user != null) {
            FeignUtils.createJsonHeader(requestTemplate, SecurityFrameworkUtils.LOGIN_USER_HEADER, user);
        }
    }

}
