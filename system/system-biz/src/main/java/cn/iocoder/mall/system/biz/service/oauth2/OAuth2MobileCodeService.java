package cn.iocoder.mall.system.biz.service.oauth2;

import cn.iocoder.mall.system.biz.dto.oatuh2.OAuth2MobileCodeSendDTO;

/**
 * OAuth2 手机验证码 Service 接口
 *
 * 我们将手机验证码登陆的方式，作为一种拓展的 OAuth2 的认证方式。因此，我们放在了 `oauth2` 包下
 */
public interface OAuth2MobileCodeService {

    void send(OAuth2MobileCodeSendDTO sendDTO);

    void use(String mobile, String code);

}
