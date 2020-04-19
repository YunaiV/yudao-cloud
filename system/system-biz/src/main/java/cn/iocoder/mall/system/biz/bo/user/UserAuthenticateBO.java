package cn.iocoder.mall.system.biz.bo.user;

import cn.iocoder.mall.system.biz.bo.ouath2.OAuth2AccessTokenBO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * TODO 注释
 */
@Data
@Accessors(chain = true)
public class UserAuthenticateBO {

    private UserBO user;

    private OAuth2AccessTokenBO token;

}
