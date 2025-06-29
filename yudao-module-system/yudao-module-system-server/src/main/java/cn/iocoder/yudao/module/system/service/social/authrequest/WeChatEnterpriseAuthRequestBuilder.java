package cn.iocoder.yudao.module.system.service.social.authrequest;

import cn.iocoder.yudao.module.system.dal.dataobject.social.SocialClientDO;
import cn.iocoder.yudao.module.system.enums.social.SocialTypeEnum;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.request.AuthWeChatMpRequest;
import org.springframework.stereotype.Component;

/**
 * 企业微信的授权请求 {@link AuthRequest} 实现
 */
@Component
public class WeChatEnterpriseAuthRequestBuilder implements AuthRequestBuilder {

    @Override
    public boolean support(Integer socialType) {
        return SocialTypeEnum.WECHAT_ENTERPRISE.getType().equals(socialType);
    }

    @Override
    public AuthRequest build(SocialClientDO client) {
        return new AuthWeChatMpRequest(AuthConfig.builder()
                .clientId(client.getClientId())
                .clientSecret(client.getClientSecret())
                .redirectUri(client.getRedirectUri())
                .ignoreCheckRedirectUri(client.getIgnoreCheckRedirectUri())
                .ignoreCheckState(client.getIgnoreCheckState())
                .build());
    }
}
