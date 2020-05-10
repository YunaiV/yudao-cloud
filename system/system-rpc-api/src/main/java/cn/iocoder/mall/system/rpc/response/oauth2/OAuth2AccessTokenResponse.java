package cn.iocoder.mall.system.rpc.response.oauth2;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * OAuth2 认证 Response
 */
@Data
@Accessors(chain = true)
public class OAuth2AccessTokenResponse implements Serializable {

    /**
     * 访问令牌
     */
    private String id;
    /**
     * 刷新令牌
     */
    private String refreshToken;
    /**
     * 账号编号
     */
    private Integer accountId;
    /**
     * 过期时间
     */
    private Date expiresTime;

}
