package cn.iocoder.mall.system.biz.bo.user;

import cn.iocoder.mall.system.biz.bo.ouath2.OAuth2AuthenticateBO;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserAuthenticateBO {

    private UserBO user;

    private OAuth2AuthenticateBO token;

}
