package cn.iocoder.mall.systemservice.rpc.oauth.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * OAuth2.0 访问令牌 Response DTO
 */
@Data
@Accessors(chain = true)
public class OAuth2AccessTokenRespDTO implements Serializable {

    /**
     * 访问令牌
     */
    private String accessToken;
    /**
     * 刷新令牌
     */
    private String refreshToken;
    /**
     * 用户编号
     */
    private Integer userId;
    /**
     * 用户类型
     */
    private Integer userType;
    /**
     * 过期时间
     */
    private Date expiresTime;


}
