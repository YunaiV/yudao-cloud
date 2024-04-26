package cn.iocoder.yudao.framework.security.core.rpc;

import cn.iocoder.yudao.framework.common.util.json.JsonUtils;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * LoginUser 的 RequestInterceptor 实现类：Feign 请求时，将 {@link LoginUser} 设置到 header 中，继续透传给被调用的服务
 *
 * @author 芋道源码
 */
@Slf4j
public class LoginUserRequestInterceptor implements RequestInterceptor {

    @Override
    @SneakyThrows
    public void apply(RequestTemplate requestTemplate) {
        LoginUser user = SecurityFrameworkUtils.getLoginUser();
        if (user == null) {
            return;
        }
        try {
            String userStr = JsonUtils.toJsonString(user);
            userStr = URLEncoder.encode(userStr, StandardCharsets.UTF_8.name()); // 编码，避免中文乱码
            requestTemplate.header(SecurityFrameworkUtils.LOGIN_USER_HEADER, userStr);
        } catch (Exception ex) {
            log.error("[apply][序列化 LoginUser({}) 发生异常]", user, ex);
            throw ex;
        }
    }

}
