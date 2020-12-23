package cn.iocoder.mall.systemservice.rpc.oauth.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * OAuth2.0 访问令牌创建 Request DTO
 */
@Data
@Accessors(chain = true)
public class OAuth2RefreshAccessTokenReqDTO implements Serializable {

    /**
     * 刷新令牌
     */
    private String refreshToken;
    /**
     * 创建 IP
     */
    private String createIp;

}
