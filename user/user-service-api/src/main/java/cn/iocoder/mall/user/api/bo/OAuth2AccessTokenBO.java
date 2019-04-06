package cn.iocoder.mall.user.api.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class OAuth2AccessTokenBO implements Serializable {

    /**
     * 访问令牌
     */
    private String accessToken;
    /**
     * 刷新令牌
     */
    private String refreshToken;
    /**
     * 过期时间，单位：秒。
     */
    private Integer expiresIn;

}
